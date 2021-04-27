package Services;
import java.sql.*;

import Entities.Category;

import Interfaces.ServiceG;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public  class Categorie_Service implements ServiceG<Category> {

    Connection cnx = Connexion.getInstance().getConnection();


    public void addcateg(Category p) {

        try {
            java.lang.String req = "INSERT INTO  Category(id,titre,descriptionc,couleur)"
                    +"VALUES(?,?,?,?)";
            java.sql.PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1,p.getId());
            pst.setString(2,p.getTitre());
            pst.setString(3,p.getDescriptionc());
            pst.setString(4,p.getCouleur());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("categorie mis à jour dans la base de données");


}  catch(SQLException ex){
            Logger.getLogger(Categorie_Service.class.getName()).log(Level.SEVERE, null, ex);


}
}


    public void supprimer(Category t) {

        try {
            String requete = "DELETE FROM Category WHERE id=?";
            PreparedStatement pst = Connexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("category supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

@Override
    public void updatecat(Category cat, int id){

        try {

            String requete = "UPDATE category SET titre='"+cat.getTitre()+"'  where id='"+id+"'  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Type modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }






    public  ObservableList<Category> getAll() {

        ObservableList<Category> Category= FXCollections.observableArrayList();
        String req = "SELECT * FROM Category";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);

            while (rst.next()){
                Category s= new Category();
                s.setId(rst.getInt("id"));
                s.setTitre(rst.getString("titre"));
                s.setDescriptionc(rst.getString("descriptionc"));
                s.setCouleur(rst.getString("couleur"));

                Category.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Categorie_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Category);
    }

}
