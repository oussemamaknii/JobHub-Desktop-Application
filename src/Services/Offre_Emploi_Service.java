/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Offre_Emploi;
import Interfaces.IService;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ObservableList<PieChart.Data> getdata(){
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        String request = "select count(o.id),c.titre from Offre_Emploi o join category c on o.categorie_id = c.id "
                +"group by c.titre";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                String titre = rs.getString(2);
                Double count = rs.getDouble(1);
                PieChart.Data data = new PieChart.Data(titre,count);
                list.add(data);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return list;
    }

    public String getOffreCateg(String id) {
        String ids = "";
        String request = "select titre from Category where id = '" + id + "'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                ids = rs.getString("titre");
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }
        return ids;
    }

    public int getCategId(String value) {
        int result = 0;
        String request = "select id from Category where titre = '" + value + "'";
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

    public void deleteoffre(String id) {
        String request = "delete from offre_emploi where id = '" + id + "'";
        String request1 = "delete from demande_recrutement where offre_id = '" + id + "'";
        try {
            Statement statement = cnx.createStatement();
            statement.executeUpdate(request1);
            statement.executeUpdate(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Offre_Emploi> getAll(int id) {
        ObservableList<Offre_Emploi> offres = FXCollections.observableArrayList();
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
                offre.setDate_expiration(rs.getDate("date_expiration").toLocalDate());
                offre.setMax_salary(rs.getInt("max_salary"));
                offre.setMin_salary(rs.getInt("min_salary"));
                offre.setCategory_id(rs.getInt("categorie_id"));
                offre.setCatname(getcatname(rs.getInt("categorie_id")));
                offres.add(offre);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return offres;
    }

    public ObservableList<Offre_Emploi> getAll(int from,int to) {
        ObservableList<Offre_Emploi> offres = FXCollections.observableArrayList();
        String request = "select * from Offre_Emploi limit "+from+","+to;
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
                offre.setDate_expiration(rs.getDate("date_expiration").toLocalDate());
                offre.setMax_salary(rs.getInt("max_salary"));
                offre.setMin_salary(rs.getInt("min_salary"));
                offre.setCategory_id(rs.getInt("categorie_id"));
                offre.setCatname(getcatname(rs.getInt("categorie_id")));
                offres.add(offre);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return offres;
    }

    public String getcatname(int id) {
        String request = "select titre from category where id = '" + id + "'";
        String a = "";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                a += rs.getString("titre");
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return a;
    }

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

    public void updateoffre(Offre_Emploi entity, AtomicReference<Offre_Emploi> off) {
        try {
            String request
                    = "update offre_emploi set titre = '" + entity.getTitre() + "' ,poste = '" + entity.getPoste() +
                    "', description = '" + entity.getDescription() + "', location = '" + entity.getLocation() +
                    "', file = '" + entity.getFile() + "', email = '" + entity.getEmail() + "',categorie_id = " + entity.getCategory()
                    + ", date_expiration = '" + entity.getDate_expiration() +
                    "',max_salary = '" + entity.getMax_salary() + "', min_salary = '" + entity.getMin_salary() + "' where id = '" + off.get().getId() + "'";
            PreparedStatement st = cnx.prepareStatement(request);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public int countalljobs() {
        String request = "select count(*) from offre_emploi";
        int a = 0;
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                a = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return a;
    }

    public int countallapps() {
        String request = "select count(*) from demande_recrutement";
        int a = 0;
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                a = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return a;
    }

    public int countallappstreated() {
        String request = "select count(*) from demande_recrutement where status = 1";
        int a = 0;
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                a = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return a;
    }
}