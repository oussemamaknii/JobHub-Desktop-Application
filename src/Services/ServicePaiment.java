package Services;

import Entities.Paiement;
import Interfaces.IServicePaiment;
import Utils.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Mintoua
 */
public class ServicePaiment implements IServicePaiment {
    private Connection cnx;
    private Statement ste;

    public ServicePaiment(){
        cnx = Connexion.getInstance().getConnection();
    }
    @Override
    public void add(Paiement paiement) throws SQLException {

        String req = "insert into payment (cardHolderName,cardNumber,securityCode,moisExpiration,anneeExpiration,commande_id) values (?,?,?,?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, paiement.getCardHolderName());
            pst.setInt(2, paiement.getCardNumber());
            pst.setInt(3, paiement.getSecurityCode());
            pst.setInt(4, paiement.getMoiExpiration());
            pst.setInt(5, paiement.getAnneeExpiration());
            pst.setInt(6, paiement.getIdCommande());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("Paiement effectuee avec succès");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    @Override
    public boolean delete(Paiement paiement) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Paiement paiement) throws SQLException {
        return false;
    }

    @Override
    public List<Paiement> readAll() throws SQLException {
        return null;
    }
}
