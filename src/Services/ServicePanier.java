package Services;

import Entities.Panier;
import Entities.Produit;
import Interfaces.IServicePanier;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mintoua
 */
public class ServicePanier implements IServicePanier {
    private Connection cnx;
    private Statement ste;

    public ServicePanier(){
        cnx = Connexion.getInstance().getConnection();
    }
    @Override
    public void add(Panier p) throws SQLException {
        String req = "insert into product_cart (quantity,idOrder,idProduct) values (?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getQuantity());
            pst.setInt(2, p.getIdCommande());
            pst.setInt(3, p.getIdProduit());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("panier mis à jour dans la base de données");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    @Override
    public boolean delete(int idPanier) throws SQLException {
        String req = "delete from product_cart where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idPanier);
            pst.executeUpdate();
            System.out.println("Produit supprime de la commande");
            return true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Panier p) throws SQLException {
        String req = "update product_cart set quantity=? where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getQuantity());
            pst.setInt(2, p.getId());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("Commande mis à jour avec success");
            return true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }

    @Override
    public List<Panier> readAll() throws SQLException {
        List<Panier> list = new ArrayList();
        String req = "select * from product_cart ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
               Panier p = new Panier(rs.getInt("quantity"),rs.getInt("idOder"),rs.getInt("idProduct"));
                list.add(p);
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return list;
    }

    @Override
    public ObservableList<Panier> getPanier(int idC) {
        ObservableList<Panier> Oblist = FXCollections.observableArrayList();
        String requete = "select * from product_cart where idOrder=?";
        try{

            PreparedStatement preparedStatement = cnx.prepareStatement(requete);
            preparedStatement.setInt(1, idC);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Panier panier = new Panier(resultSet.getInt("id"),resultSet.getInt("quantity"),
                        resultSet.getInt("idOrder"),resultSet.getInt("idProduct"));

                Oblist.add(panier);
            }

        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return Oblist;
    }
}
