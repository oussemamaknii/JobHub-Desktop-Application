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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Offre_Emploi;
import Utils.Connexion;
import Interfaces.IService;
import Entities.Category;

/**
 *
 * @author souso
 */
public class Offre_Emploi_Service implements IService<Offre_Emploi>{

    Connection cnx = Connexion.getInstance().getConnection();

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
                offre.setDate_debut(rs.getDate("date_debut"));
                offre.setDate_debut(rs.getDate("date_expiration"));
                offre.setMax_salary(rs.getFloat("max_salary"));
                offre.setMin_salary(rs.getFloat("min_salary"));
                offre.setCategory(rs.getInt("categorie_id"));
                offres.add(offre);
            }
        } catch (SQLException e) {
            Logger
                    .getLogger(Offre_Emploi_Service.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return offres;
    }

    @Override
    public void add(Offre_Emploi entity) {
        try {
            String request
                    = "INSERT INTO OFFRE_EMPLOI(titre,poste, description, location, file, email,category_id"
                    + ",date_debut, date_expiration,max_salary, min_salary) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setString(1, entity.getTitre());
            st.setString(2, entity.getPoste());
            st.setString(3, entity.getDescription());
            st.setString(4, entity.getLocation());
            st.setString(5, entity.getFile());
            st.setString(6, entity.getEmail());
            st.setDate(7, (Date) Calendar.getInstance().getTime());
            st.setDate(8, (Date) entity.getDate_expiration());
            st.setFloat(9, entity.getMax_salary());
            st.setFloat(9, entity.getMin_salary());
            st.setInt(10, entity.getCategory());
        } catch (SQLException e) {
            Logger
                    .getLogger(Offre_Emploi_Service.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }
    
}
