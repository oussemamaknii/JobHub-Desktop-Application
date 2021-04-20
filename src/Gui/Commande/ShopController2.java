/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Commande;


//import Entitie.User.User;
import Entities.Cart;
//import GUI.Evenement.EvenementController;
import Gui.Produit.ProductSingleController;
import animatefx.animation.Bounce;
import animatefx.animation.FadeInDown;
import java.io.IOException;
import java.net.URL;
        import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author toshiba
 */
public class ShopController2 implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane banner;
    ObservableList<Cart> panier = FXCollections.observableArrayList();
    AnchorPane centerContent;
    int idProduit;
    //User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new Bounce(banner).play();
    }

    @FXML
    void produit1(ActionEvent event) throws IOException {
        Cart produitPanier = new Cart(1, "Road Bicycles", 200, 1, "2.jpg", 0);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/Produit/ProductSingle.fxml"));
        Parent fxml = Loader.load();
        ProductSingleController e = Loader.getController();
        System.out.println(centerContent);
        //e.redirection(centerContent, produitPanier,panier,user);
        e.redirection(centerContent, produitPanier,panier);
        centerContent.getChildren().removeAll();
        new FadeInDown(fxml).play();
        centerContent.getChildren().setAll(fxml);

    }

    @FXML
    void produit2(ActionEvent event) throws IOException {
        Cart produitPanier = new Cart(2, "Mountain Bicycles", 400, 1, "3.jpg", 0);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/Produit/ProductSingle.fxml"));
        Parent fxml = Loader.load();
        ProductSingleController e = Loader.getController();
        //e.redirection(centerContent, produitPanier,panier,user);
        e.redirection(centerContent, produitPanier,panier);
        centerContent.getChildren().removeAll();
        new FadeInDown(fxml).play();
        centerContent.getChildren().setAll(fxml);

    }
  @FXML
    void produit3(ActionEvent event) throws IOException {
      Cart produitPanier = new Cart(3, "Course Bicycles", 600, 1, "3.jpg", 0);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/Produit/ProductSingle.fxml"));
        Parent fxml = Loader.load();
        ProductSingleController e = Loader.getController();
        //e.redirection(centerContent, produitPanier,panier,user);
        e.redirection(centerContent, produitPanier,panier);
        centerContent.getChildren().removeAll();
        new FadeInDown(fxml).play();
        centerContent.getChildren().setAll(fxml);

    }

    // public void redirection(AnchorPane c,User u)
    public void redirection(AnchorPane c) {
        centerContent = c;
       // user=u;
    }

    //public void redirectionFromPanier(AnchorPane c, ObservableList<Cart> p,User u)
    public void redirectionFromPanier(AnchorPane c, ObservableList<Cart> p) {
        centerContent = c;
        if(panier.isEmpty())
        panier.addAll(p);
        else {
            panier.clear();
            panier.addAll(p);
        }
        //user=u;
    }
    //public void redirectionFromPayment(AnchorPane c,User u)
    public void redirectionFromPayment(AnchorPane c) {
        centerContent = c;
        panier.clear();
        //user=u;
    }

}
