package Services;

import Entities.Commande;
import Interfaces.IServiceCommande;
import Utils.Connexion;
import com.sun.jdi.StringReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mintoua
 */
public class ServiceCommande implements IServiceCommande {
    private Connection cnx;
    private Statement ste;

    public ServiceCommande(){
        cnx = Connexion.getInstance().getConnection();
    }

    @Override
    public int getLastCommande() {
        String req = "select * from commande order by id desc limit 1";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                return rs.getInt("id");
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return -1;
    }

    @Override
    public void create(Commande commande){
        try {
            String request
                    = "INSERT INTO commande (total_payment,state,date,id_user) VALUES(?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setFloat(1, commande.getTotalPayment());
            st.setBoolean(2,commande.isState());
            st.setString(3,commande.getDate());
            st.setInt(4,commande.getIdUser());
            st.executeUpdate(); // uniquement avec l'ajout, la suppression et la modification dans le BD
            System.out.println("Votre Commande a été enregistrée");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    @Override
    public boolean delete(int idCommande) throws SQLException {
        String req = "delete from commande where id=?";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idCommande);
            String req2 = "select id from product_cart where idOrder = ?";
            try {
                PreparedStatement pst2  = cnx.prepareStatement(req2);
                pst2.setInt(1,idCommande);
                ResultSet rs = pst2.executeQuery();
                while (rs.next()){
                    ServicePanier s1= new ServicePanier();
                    s1.delete(rs.getInt(1));
                }
            }catch (SQLException err){
                System.out.println(err.getMessage());
            }

            pst.executeUpdate();
            System.out.println("Commande annulé");
            return true;

        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(int idCommande) throws SQLException {
        String req = "update commande set state=1 where id=? ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idCommande);
            pst.executeUpdate();
            System.out.println("Commande mis à jour avec success");
            return true;
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return false;
    }

    @Override
    public ObservableList<Commande> getAll() throws SQLException {
        ObservableList <Commande> list = FXCollections.observableArrayList();
        // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from commande";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Commande(rs.getFloat("total_payment"),rs.getBoolean("state"),rs.getString("date"),rs.getInt("id_user")));
                return list;
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return list;
    }

    @Override
    public List<Commande> readAll() throws SQLException {
        List <Commande> list = new ArrayList();
       // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from commande";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Commande(rs.getFloat("total_payment"),rs.getBoolean("state"),rs.getString("date"),rs.getInt("id_user")));
                return list;
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return list;
    }

    @Override
    public Commande getCommande(int idCommande) throws SQLException {
        String req = "select * from commande where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idCommande);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return new Commande(rs.getInt("id"), rs.getFloat("total_payment"), rs.getBoolean("state"),rs.getString("date"), rs.getInt("id_user"));
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return new Commande();
    }

    @Override
    public int[] statistiques() throws SQLException {
        int nbreVentes[]={0,0,0,0,0,0,0,0,0,0,0,0};
        String req = "select date from  order";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).contains("-01-")) {
                    nbreVentes[0]++;
                }
                else if (rs.getString(1).contains("-02-")) {
                    nbreVentes[1]++;
                }
                else if (rs.getString(1).contains("-03-")) {
                    nbreVentes[2]++;
                }
                else if (rs.getString(1).contains("-04-")) {
                    nbreVentes[3]++;
                }
                else if (rs.getString(1).contains("-05-")) {
                    nbreVentes[4]++;
                }
                else if (rs.getString(1).contains("-06-")) {
                    nbreVentes[5]++;
                }
                else if (rs.getString(1).contains("-07-")) {
                    nbreVentes[6]++;
                }
                else if (rs.getString(1).contains("-08-")) {
                    nbreVentes[7]++;
                }
                else if (rs.getString(1).contains("-09-")) {
                    nbreVentes[8]++;
                }
                else if (rs.getString(1).contains("-10-")) {
                    nbreVentes[9]++;
                }
                else if (rs.getString(1).contains("-11-")) {
                    nbreVentes[10]++;
                }
                else if (rs.getString(1).contains("-12-")) {
                    nbreVentes[11]++;
                }

            }
            return nbreVentes;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return nbreVentes;
    }
}
