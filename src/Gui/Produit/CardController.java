package Gui.Produit;

import Entities.Cart;
import Entities.Commande;
import Entities.Panier;
import Services.ServiceCommande;
import Services.ServicePanier;
import animatefx.animation.Bounce;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class CardController implements Initializable {

    @FXML
    private Pane banner;

    @FXML
    private TableView<Cart> panierView;

    @FXML
    private TableColumn<Cart, ImageView> image;

    @FXML
    private TableColumn<Cart, String> nom;

    @FXML
    private TableColumn<Cart, Integer> prix;

    @FXML
    private TableColumn<Cart, Spinner<Integer>> quantite;
    @FXML
    private TableColumn<Panier, Button> remove;

    @FXML
    private Label subTotal;

    @FXML
    private Label total;

    @FXML
    private Button removebtn;
    ObservableList<Cart> panier = FXCollections.observableArrayList();

    float Total;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Bounce(banner).play();
    }

    public void displayCart(ObservableList<Cart> panierCart){

        if (panier.isEmpty()) {
            panier.addAll(panierCart);
        } else {
            panier.clear();
            panier.addAll(panierCart);
        }
        nom.setCellValueFactory(new PropertyValueFactory<Cart, String>("nomProduit"));
        image.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("imageView"));
        quantite.setCellValueFactory(new PropertyValueFactory<Cart, Spinner<Integer>>("spinner"));
        prix.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("prix"));
        remove.setCellValueFactory(new PropertyValueFactory<Panier, Button>("remove"));
        panierView.setItems(panier);
        this.prixTotal();
    }
    @FXML
    void displaySelected(MouseEvent event) {

    }

    @FXML
    void passerCommande(ActionEvent event) {
        ServiceCommande serviceCommande = new ServiceCommande();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateCommande = dateFormat.format(date);

        Commande commande = new Commande(Total,false,dateCommande,1);
        serviceCommande.create(commande);

        ServicePanier servicePanier = new ServicePanier();
        for (Cart p : panier){
            try {
                servicePanier.add(new Panier(p.getQuantite(),p.getIdProduit(), serviceCommande.getLastCommande() ));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void prixTotal() {
        int subtotal = 0, total2 = 850;
        for (Cart panier1 : panier) {
            subtotal += panier1.getQuantite() * panier1.getPrix();
        }
        total2 = total2 + subtotal;
        Total = total2;
        total.setText("$" + Integer.toString(total2));
        subTotal.setText("$" + Integer.toString(subtotal));
    }
}
