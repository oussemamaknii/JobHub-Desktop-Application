package Interfaces;

import Entities.Commande;
import Entities.Produit;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mintoua
 */
public interface IServiceCommande {

    void create (Commande commande) ;
    boolean delete(int idCommande) throws  SQLException;
    boolean update(int idCommande) throws  SQLException;
    public List<Commande> readAll() throws SQLException;
    public ObservableList<Commande> getAll() throws SQLException;
    int getLastCommande();
    public Commande getCommande(int idCommande) throws SQLException;
    public int[] statistiques() throws SQLException;
}
