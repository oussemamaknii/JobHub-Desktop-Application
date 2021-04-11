package Interfaces;

import Entities.Produit;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mintoua
 */
public interface IServiceProduit {
    void add(Produit produit) throws SQLException;
    boolean delete(int idProduit) throws SQLException;
    boolean update(Produit produit) throws SQLException;
    List<Produit> readAll() throws SQLException;
    boolean verifierQuantite(int idProduit, int quantite) throws SQLException;
}
