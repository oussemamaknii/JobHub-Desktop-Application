package Services;

import Entities.categorie;
import Entities.formation;
import Interfaces.ServiceF;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Formation_Service implements ServiceF <formation> {
    Connection cnx = Connexion.getInstance().getConnection();
    @Override
    public ArrayList<String> getCateg(String value) {
        ArrayList<String> ids = new ArrayList<>();
        String request = "select titre from categorie";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                ids.add(rs.getString("nom"));
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }
        return ids;
    }
   public int getCategId(String value){
        int result = 0;
        String request = "select id from categorie where titre = '" + value+"'";
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
    public void addformation(formation p) {

        try {
            String req =
            "INSERT INTO formation(id,category_id,nom,formateur,description"
                    + "date_debut,date_fin,adresse,mail,tel,prix) VALUES(?,null,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1,p.getId());
            pst.setInt(2,p.getCategory_id());
            pst.setString(3,p.getTitre());
            pst.setString(4,p.getFormateur());
            pst.setString(5,p.getDescription());
            pst.setDate(6, p.getDate_debut());
            pst.setDate(7, p.getDate_fin());
            pst.setString(8,p.getAdresse());
            pst.setString(9,p.getEmail());
            pst.setDouble(10,p.getTel());
            pst.setDouble(11,p.getPrix());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("formation mis à jour dans la base de données");


        }  catch(SQLException ex){
            Logger.getLogger(Formation_Service.class.getName()).log(Level.SEVERE, null, ex);
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
                s.setCategory_id(rst.getInt("category_id"));
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
