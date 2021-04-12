package Services;

import Entities.Demande_Recrutement;
import Entities.Offre_Emploi;
import Interfaces.IService;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demande_Service implements IService {

    Connection cnx = Connexion.getInstance().getConnection();

    @Override
    public void add(Object entity) {

    }

    @Override
    public ObservableList<Demande_Recrutement> getAll(int id) {
        ObservableList<Demande_Recrutement> offres = FXCollections.observableArrayList();
        String request = "select * from demande_recrutement where offre_id = '" + id + "'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                Demande_Recrutement offre = new Demande_Recrutement();
                offre.setId(rs.getInt("id"));
                offre.setCandidat_id(rs.getInt("candidat_id"));
                offre.setOffre_id(rs.getInt("offre_id"));
                offre.setStatus(rs.getBoolean("status"));
                offre.setDate_debut(rs.getDate("date_debut").toLocalDate());
                offre.setDate_expiration(rs.getDate("dateexpiration").toLocalDate());
                offre.setOfftit(getoffretitre(rs.getInt("offre_id")));
                offre.setUsername(getoffreuser(rs.getInt("candidat_id")));
                offres.add(offre);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return offres;
    }

    public ObservableList<Demande_Recrutement> getAllUser(int id) {
        ObservableList<Demande_Recrutement> offres = FXCollections.observableArrayList();
        String request = "select * from demande_recrutement where candidat_id = '" + id + "'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                Demande_Recrutement offre = new Demande_Recrutement();
                offre.setId(rs.getInt("id"));
                offre.setCandidat_id(rs.getInt("candidat_id"));
                offre.setOffre_id(rs.getInt("offre_id"));
                offre.setStatus(rs.getBoolean("status"));
                offre.setDate_debut(rs.getDate("date_debut").toLocalDate());
                offre.setDate_expiration(rs.getDate("dateexpiration").toLocalDate());
                offre.setOfftit(getoffretitre(rs.getInt("offre_id")));
                offre.setUsername(getoffreuser(rs.getInt("candidat_id")));
                offres.add(offre);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return offres;
    }

    public String getoffretitre(int id) {
        String request = "select titre from Offre_emploi where id = '" + id + "'";
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

    public void upstat(String id) {
        try {
            String request
                    = "update Demande_recrutement set status = true where id = '" + id + "'";
            PreparedStatement st = cnx.prepareStatement(request);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String getoffreuser(int id) {
        String request = "select first_name,last_name from user where id = '" + id + "'";
        String a = "";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                a += rs.getString("first_name");
                a += " ";
                a += rs.getString("last_name");
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return a;
    }

    public ObservableList<Offre_Emploi> get_not_applied_jobs() {
        ObservableList<Offre_Emploi> offres = FXCollections.observableArrayList();
        String request = "select * from Offre_Emploi where id not in ( select offre_id from demande_recrutement )";
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

    public void deletapp(int id) {
        String request = "delete from demande_recrutement where id = '" + id + "'";
        try {
            Statement statement = cnx.createStatement();
            statement.executeUpdate(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void apply(int idoffre, int iduser) {
        try {
            String request
                    = "INSERT INTO Demande_recrutement(offre_id,candidat_id,date_debut,dateexpiration,status) " +
                    "VALUES('" + idoffre + "','" + iduser + "',SYSDATE(),SYSDATE() + 20,0)";
            PreparedStatement st = cnx.prepareStatement(request);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
