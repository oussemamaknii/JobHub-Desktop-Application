package Interfaces;

import Entities.Produit;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mintoua
 */
public interface IServiceProduit {
    void add(Produit produit) throws SQLException;
    boolean delete(int idProduit) throws SQLException;
    public void update(Produit entity, AtomicReference<Produit> prod);
    public ObservableList <Produit> readAll() throws SQLException;
    boolean verifierQuantite(int idProduit, int quantite) throws SQLException;
}
