package Services;
import java.sql.*;

import Entities.categorie;
import Interfaces.IService;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public  class Categorie_Service implements IService <categorie> {

    Connection cnx = Connexion.getInstance().getConnection();


@Override
    public void addcateg(categorie p) {

        try {
            java.lang.String req = "INSERT INTO  categorie(id,nom,couleur)"
                    +"VALUES(?,?,?)";
            java.sql.PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1,p.getId());
            pst.setString(2,p.getTitref());
            pst.setString(3,p.getCouleur());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("categorie mis à jour dans la base de données");


}  catch(SQLException ex){
            Logger.getLogger(Categorie_Service.class.getName()).log(Level.SEVERE, null, ex);


}
}






    public  ObservableList<categorie> getAll() {

        ObservableList<categorie> categorie= FXCollections.observableArrayList();
        String req = "SELECT * FROM categorie";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);

            while (rst.next()){
                categorie s= new categorie();
                s.setId(rst.getInt("id"));
                s.setTitref(rst.getString("nom"));
                s.setCouleur(rst.getString("couleur"));

                categorie.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Categorie_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (categorie);
    }

}
