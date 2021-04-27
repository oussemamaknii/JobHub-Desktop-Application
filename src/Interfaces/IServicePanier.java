package Interfaces;


import Entities.Commande;
import Entities.Panier;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mintoua
 * @param <Panier>
 */
public interface IServicePanier<Panier> {

    void add(Entities.Panier panier) throws SQLException;
    boolean delete(int idPanier) throws SQLException;
    boolean update(Entities.Panier panier) throws SQLException;
    List<Panier> readAll() throws SQLException;
    public ObservableList<Panier> getPanier(int idC) ;
}
