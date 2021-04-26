package Gui.Produit;

import Entities.Cart;
import Entities.Commande;
import Entities.Panier;
import Entities.SendEmail;
import Gui.Commande.PaymentController;
import Services.ServiceCommande;
import Services.ServicePanier;
import Utils.Mail;
import animatefx.animation.Bounce;
import animatefx.animation.FadeInDown;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private TableColumn<Cart, String> prix;

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
    @FXML
    private AnchorPane centerContent;
    ObservableList<Cart> panier = FXCollections.observableArrayList();

    float Total;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Bounce(banner).play();
    }

    @FXML
    void cancel(MouseEvent event) {
        Stage stg = (Stage) removebtn.getScene().getWindow();
        stg.close();
    }

    public void displayCart(ObservableList<Cart> panierCart, AnchorPane aP){

        centerContent = aP;
        if (panier.isEmpty()) {
            panier.addAll(panierCart);
        } else {
            panier.clear();
            panier.addAll(panierCart);
        }
        nom.setCellValueFactory(new PropertyValueFactory<Cart, String>("nomProduit"));
        image.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("imageView"));
        quantite.setCellValueFactory(new PropertyValueFactory<Cart, Spinner<Integer>>("spinner"));
        prix.setCellValueFactory(new PropertyValueFactory<Cart, String>("prix"));
        remove.setCellValueFactory(new PropertyValueFactory<Panier, Button>("remove"));
        panierView.setItems(panier);
        this.prixTotal();
    }
    @FXML
    void displaySelected(MouseEvent event) {
        Cart selected = panierView.getSelectionModel().getSelectedItem();
        selected.setQuantite(selected.getSpinner().getValue());
        panierView.refresh();
        prixTotal();
        selected.getRemove().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Iterator<Cart> it = panier.iterator();
                while (it.hasNext()) {
                    Cart p = it.next();
                    if (p.getIdProduit() == selected.getIdProduit()) {
                        it.remove();
                        panierView.refresh();
                        prixTotal();
                    }
                }
            }
        });

    }

    @FXML
    void passerCommande(ActionEvent event) {
        ServiceCommande serviceCommande = new ServiceCommande();
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateCommande = dateFormat.format(date);
/*
        Commande commande = new Commande(Total,false,dateCommande,1);
        Notifications notificationBuilder2 = Notifications.create()
                .title("Confrimation Commande")
                .text("Commande ajoutée avec succès")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder2.showConfirm();
        serviceCommande.create(commande);
        try {
            serviceCommande.historique(serviceCommande.getLastCommande(),panier);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Mail.sendMail("tpkdmta@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServicePanier servicePanier = new ServicePanier();
        for (Cart p : panier){
            try {
               // System.out.println(p.getIdProduit());
                servicePanier.add(new Panier(p.getQuantite(),p.getIdProduit(), serviceCommande.getLastCommande() ));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/

        Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
        dg.setTitle("ConfrimationDialogbox");
        dg.setContentText("Paiement Paypal");
        dg.setHeaderText("Effectuer le paiement");
        dg.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                       /* FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/Commande/Payment.fxml"));
                        Parent fxml;
                        try {
                            fxml = Loader.load();
                            PaymentController e = Loader.getController();
                            e.redirection(centerContent, panier, Total, serviceCommande.getLastCommande());
                            centerContent.getChildren().removeAll();
                            new FadeInDown(fxml).play();
                            centerContent.getChildren().setAll(fxml);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }*/
                        FXMLLoader loader = new FXMLLoader ();
                        loader.setLocation(getClass().getResource("/Gui/Commande/Payment.fxml"));
                        try {
                            loader.load();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        Parent parent = loader.getRoot();
                        PaymentController payController = loader.getController();
                        payController.redirection(centerContent, panier, Total, serviceCommande.getLastCommande());
                        centerContent.getChildren().removeAll();
                        new FadeInDown(parent).play();
                        centerContent.getChildren().setAll(parent);

                    }
                }
        );


    }
    public void prixTotal() {
        int subtotal = 0;
        for (Cart panier1 : panier) {
            subtotal += panier1.getQuantite() * panier1.getPrix();
        }

        Total = subtotal;
        total.setText("$" + Integer.toString(subtotal));
    }

}
