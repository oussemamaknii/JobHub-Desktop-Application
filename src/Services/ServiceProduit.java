package Services;

import Entities.Produit;
import Interfaces.IServiceProduit;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mintoua
 */
public class ServiceProduit implements IServiceProduit {

    private Connection cnx;
    private Statement statement;

    public ServiceProduit(){
        cnx = Connexion.getInstance().getConnection();
    }
    @Override
    public void add(Produit produit) throws SQLException {
        try {
            String request
                    = "INSERT INTO products(name,ref,description,price,quantity,image) VALUES(?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setString(1, produit.getName());
            st.setString(2,produit.getRef());
            st.setString(3,produit.getDescription());
            st.setFloat(4,produit.getPrice());
            st.setInt(5,produit.getQuantity());
            st.setString(6,produit.getImage());
            st.executeUpdate(); // uniquement avec l'ajout, la suppression et la modification dans le BD
            System.out.println("Produit ajouté");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

    }

    @Override
    public boolean delete(int idProduit) throws SQLException {
        String req = "delete from products where id=?";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idProduit);
            pst.executeUpdate();
            System.out.println("Produit supprimer de la BD");
            return true;
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }

        return false;
    }

    @Override
    public void update(Produit entity, AtomicReference<Produit> prod) {
        try {
        String request=
                " update products set name='"+entity.getName()+"', ref='"+entity.getRef()+
                        "', description='"+entity.getDescription()+"', price='"+entity.getPrice()+
                        "', quantity='"+entity.getQuantity()+"', image='"+entity.getImage()+"' where id ='"+prod.get().getId()+"' ";

        PreparedStatement pst = null;

            pst = cnx.prepareStatement(request);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public ArrayList<Produit> getAll() {
        ArrayList<Produit> Oblist = new ArrayList<>();
        String requete = "select * from products";
        try{
            PreparedStatement preparedStatement = cnx.prepareStatement(requete);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Produit produit = new Produit(resultSet.getInt("id"),resultSet.getString("name"),
                        resultSet.getString("ref"),resultSet.getString("description"),resultSet.getFloat("price"),
                        resultSet.getInt("quantity"),resultSet.getString("image"));

                Oblist.add(produit);
            }

        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return Oblist;
    }

    @Override
    public ObservableList<Produit> readAll() throws SQLException {
        ObservableList<Produit> Oblist = FXCollections.observableArrayList();
        String requete = "select * from products";
        try{
            PreparedStatement preparedStatement = cnx.prepareStatement(requete);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Produit produit = new Produit(resultSet.getInt("id"),resultSet.getString("name"),
                        resultSet.getString("ref"),resultSet.getString("description"),resultSet.getFloat("price"),
                        resultSet.getInt("quantity"),resultSet.getString("image"));

                Oblist.add(produit);
            }

        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return Oblist;
    }

    @Override
    public boolean verifierQuantite(int idProduit, int quantite) throws SQLException {
        return false;
    }
}
