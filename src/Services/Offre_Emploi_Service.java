/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Offre_Emploi;
import Utils.Connexion;
import Interfaces.IService;
import Entities.Category;

/**
 * @author souso
 */
public class Offre_Emploi_Service implements IService<Offre_Emploi> {

    Connection cnx = Connexion.getInstance().getConnection();

    public ArrayList<String> getCateg() {
        ArrayList<String> ids = new ArrayList<>();
        String request = "select titre from Category";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                ids.add(rs.getString("titre"));
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }
        return ids;
    }
    public int getCategId(String value){
        int result = 0;
        String request = "select id from Category where titre = '" + value+"'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Offre_Emploi> getAll() {
        ArrayList<Offre_Emploi> offres = new ArrayList<>();
        String request = "select * from Offre_Emploi";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                Offre_Emploi offre = new Offre_Emploi();
                offre.setId(rs.getInt("id"));
                offre.setTitre(rs.getString("titre"));
                offre.setPoste(rs.getString("poste"));
                offre.setDescription(rs.getString("description"));
                offre.setLocation(rs.getString("location"));
                offre.setFile(rs.getString("file"));
                offre.setEmail(rs.getString("email"));
                offre.setDate_debut(rs.getDate("date_debut").toLocalDate());
                offre.setDate_debut(rs.getDate("date_expiration").toLocalDate());
                offre.setMax_salary(rs.getInt("max_salary"));
                offre.setMin_salary(rs.getInt("min_salary"));
                offre.setCategory(rs.getInt("categorie_id"));
                offres.add(offre);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return offres;
    }

    @Override
    public void add(Offre_Emploi entity) {
        try {
            String request
                    = "INSERT INTO offre_emploi(titre,poste, description, location, file, email,categorie_id"
                    + ",date_debut, date_expiration,max_salary, min_salary,id_recruteur_id,id_candidat_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,null,null)";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setString(1, entity.getTitre());
            st.setString(2, entity.getPoste());
            st.setString(3, entity.getDescription());
            st.setString(4, entity.getLocation());
            st.setString(5, entity.getFile());
            st.setString(6, entity.getEmail());
            st.setInt(7, entity.getCategory());
            st.setDate(8, Date.valueOf(entity.getDate_debut()));
            st.setDate(9, Date.valueOf(entity.getDate_expiration()));
            st.setInt(10, entity.getMax_salary());
            st.setInt(11, entity.getMin_salary());
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
