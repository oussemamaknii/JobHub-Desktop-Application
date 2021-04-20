/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Commande;

import Entities.Cart;
import animatefx.animation.Bounce;
import animatefx.animation.FadeInDown;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author toshiba
 */
public class PanierController implements Initializable {
 @FXML
    private Pane banner;

    @FXML
    private TableColumn<Cart, Spinner<Integer>> quantite;

    @FXML
    private TableColumn<Cart, ImageView> image;

    @FXML
    private TableColumn<Cart, String> nom;

    @FXML
    private TableColumn<Cart, Integer> prix;

    @FXML
    private TableColumn<Cart, Button> remove;

    @FXML
    private TableView<Cart> panierView;
    AnchorPane centerContent;
    ObservableList<Cart> panier = FXCollections.observableArrayList();
    @FXML
    private Label subTotal;

    @FXML
    private Label total;
   // User user;
    int Total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Bounce(banner).play();

    }

    //public void redirection(AnchorPane c, ObservableList<Cart> pa, User u)
    public void redirection(AnchorPane c, ObservableList<Cart> pa) {
        centerContent = c;
        if (panier.isEmpty()) {
            panier.addAll(pa);
        } else {
            panier.clear();
            panier.addAll(pa);
        }
        //user = u;
        nom.setCellValueFactory(new PropertyValueFactory<Cart, String>("nomProduit"));
        image.setCellValueFactory(new PropertyValueFactory<Cart, ImageView>("imageView"));
        quantite.setCellValueFactory(new PropertyValueFactory<Cart, Spinner<Integer>>("spinner"));
        prix.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("prix"));
        remove.setCellValueFactory(new PropertyValueFactory<Cart, Button>("remove"));
        panierView.setItems(panier);
        this.prixTotal();
    }

    @FXML
    void displaySelected(MouseEvent event) {
        Cart selected = panierView.getSelectionModel().getSelectedItem();
        System.out.println(selected);
        selected.setQuantite(selected.getSpinner().getValue());
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
    void displayCatalogue(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/Stock/Shop2.fxml"));
        Parent fxml = Loader.load();
        ShopController2 e = Loader.getController();
        //e.redirectionFromPanier(centerContent, panier, user);
        e.redirectionFromPanier(centerContent, panier);
        centerContent.getChildren().removeAll();
        new FadeInDown(fxml).play();
        centerContent.getChildren().setAll(fxml);
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
