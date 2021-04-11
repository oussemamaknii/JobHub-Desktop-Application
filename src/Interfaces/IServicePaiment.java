package Interfaces;

import Entities.Paiement;


import java.nio.channels.Pipe;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mintoua
 */
public interface IServicePaiment {

    void add(Paiement paiement) throws SQLException;
    boolean delete(Paiement paiement) throws SQLException;
    boolean update(Paiement paiement) throws SQLException;
    List<Paiement> readAll() throws SQLException;
}
