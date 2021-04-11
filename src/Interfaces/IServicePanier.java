package Interfaces;


import Entities.Panier;

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
}
