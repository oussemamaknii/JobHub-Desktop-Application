package Interfaces;

import Entities.Cart;
import Entities.Commande;
import Entities.Produit;
import com.itextpdf.text.DocumentException;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.io.FileNotFoundException;
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
    public void historique(int idCommande, ObservableList<Cart> panier) throws SQLException, DocumentException, FileNotFoundException;
    public int[] statistiques() throws SQLException;
    public ObservableList<PieChart.Data> getdata();
    int orders();
}
