package Services;

import Entities.Category;
import Entities.formation;
import Interfaces.ServiceF;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Formation_Service implements ServiceF <formation> {

    Connection cnx = Connexion.getInstance().getConnection();

    public ArrayList<String> getCateg() {
        ArrayList<String> ids = new ArrayList<>();
        String request = "select titre from category";
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
        String request = "select id from category where titre = '" + value+"'";
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
    public String getcatname(int id){
        String request = "select titre from category where titre = '"+id+"'";
        String a = "";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                a += rs.getString("titre");
            }
        } catch (SQLException e) {
            Logger.getLogger(Formation_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return a;
    }
    public void supprimer(formation t) {

        try {
            String requete = "DELETE FROM formation WHERE id=?";
            PreparedStatement pst = Connexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("formation supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void addformation(formation p) {

        try {
           /** String req =
                    "INSERT INTO formation(category_id,nom,formateur,description"
                            + ",date_debut,date_fin,adresse,mail,tel,prix) VALUES(?,?,?,?,?,?,?,?,?,?)";**/
            String req = "INSERT INTO formation (category_id, nom, formateur, description,date_debut,date_fin,adresse,mail,tel,prix) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            System.out.println(p);
            pst.setInt(1,p.getCategory());
            pst.setString(2,p.getNom());
            pst.setString(3,p.getFormateur());
            pst.setString(4,p.getDescription());
            pst.setDate(5, p.getDate_debut());
            pst.setDate(6, p.getDate_fin());
            pst.setString(7,p.getAdresse());
            pst.setString(8,p.getEmail());
            pst.setDouble(9,p.getTel());
            pst.setDouble(10,p.getPrix());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("formation mis à jour dans la base de données");

        }  catch(SQLException ex){
            Logger.getLogger(Formation_Service.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    @Override
    public void updatecat(formation cat, int id){

        try {

            String requete = "UPDATE formation SET nom='"+cat.getNom()+"'  where id='"+id+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Type modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public ObservableList<formation> getAll() {

        ObservableList<formation> formation= FXCollections.observableArrayList();
        String req = "SELECT * FROM formation";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);

            while (rst.next()){
                formation s= new formation();
                s.setId(rst.getInt("id"));

                s.setTitre(rst.getString("nom"));
                s.setDescription(rst.getString("description"));
                s.setFormateur(rst.getString("formateur"));
                s.setDate_debut(rst.getDate("date_debut"));
                s.setDate_fin(rst.getDate("date_fin"));
                s.setAdresse(rst.getString("adresse"));
                s.setEmail(rst.getString("mail"));
                s.setTel(rst.getDouble("tel"));
                s.setPrix(rst.getDouble("prix"));



                formation.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Formation_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (formation);
    }


}