package Services;

import Entities.Cart;
import Entities.Commande;
import Interfaces.IServiceCommande;
import Utils.Connexion;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String req = "select * from `order` order by id desc limit 1";
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
                    = "INSERT INTO `order` (total_payment,state,date,id_user) VALUES(?,?,?,?)";
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
        String req = "delete from `order` where id=?";

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
        String req = "update `order` set state=1 where id=? ";

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
        String req = "select * from `order` ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Commande(rs.getInt("id"),rs.getFloat("total_payment"),rs.getBoolean("state"),rs.getString("date"),rs.getInt("id_user")));
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
        String req = "select * from `order` ";

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
        String req = "select * from `order` where id=?";
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
    public int orders(){
        String req = "select count(*) from";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while ( rs.next()){
                return  rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public ObservableList<PieChart.Data> getdata(){
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        String request = "select count(state),date from `order` where state = 0";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                String date = rs.getString(2);
                Double count = rs.getDouble(1);
                PieChart.Data data = new PieChart.Data(date,count);
                list.add(data);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return list;
    }


    @Override
    public int[] statistiques() throws SQLException {
        int nbreVentes[]={0,0,0,0,0,0,0,0,0,0,0,0};
        String req = "select date from  `order` ";
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


    @Override
    public void historique(int idCommande, ObservableList<Cart> paniers) throws DocumentException, FileNotFoundException, BadElementException, SQLException {
        Document document = new Document();
        String file_name = "C:\\Users\\souso\\Desktop\\JobHub-Desktop-Application\\src\\Gui\\Commande\\Facture.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        System.out.println("Montant " + this.getCommande(idCommande).getTotalPayment());
        Paragraph p7 = new Paragraph(" ");
        Paragraph p4 = new Paragraph(" ");
        Paragraph p = new Paragraph("Ci-joint votre facture");
        //Paragraph p1 = new Paragraph("Utilisateur: " + user.getUsername());
        Paragraph p1 = new Paragraph("Utilisateur: name ");
        //Paragraph p3 = new Paragraph("Email: " + user.getEmail());
        Paragraph p3 = new Paragraph("Email: useremail" );
        Paragraph p2 = new Paragraph("Montant: " + this.getCommande(idCommande).getTotalPayment() + "$ ");
        try {
            document.add(Image.getInstance("C:\\Users\\souso\\Desktop\\JobHub-Desktop-Application\\images\\logo.png"));
        } catch (IOException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.add(p4);
        document.add(p7);
        document.add(p);
        document.add(p1);
        document.add(p3);
        document.add(p2);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(6);
        table.addCell("Produit");
        table.addCell("Prix");
        table.addCell("Etat");
        table.addCell("Date");
        table.addCell("Quantité");
        table.addCell("Adresse");
        table.setHeaderRows(1);

        for (Cart panier : paniers) {
            try {
                table.addCell(Image.getInstance("E:\\Etudes\\ESPRIT\\Esprit_3A28\\Semestre 2\\PiDev\\Desktop\\FinalWork\\JobHub-Desktop-Application\\src\\" + panier.getImage()));
            } catch (IOException ex) {
                Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
            }
            table.addCell(Float.toString(panier.getPrix()));
            table.addCell(String.valueOf(this.getCommande(idCommande).isState()));
            table.addCell(this.getCommande(idCommande).getDate());
            table.addCell(Integer.toString(panier.getQuantite()));

        }
        System.out.println("Facture générée");
        document.add(table);
        document.close();

    }
}
