/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Produit;


import Entities.Panier;
//import Entitie.User.User;
import Entities.Cart;
import Entities.Produit;
import Gui.Commande.ProductSingleController;
//import GUI.Evenement.EvenementController;
import Services.ServiceProduit;
import animatefx.animation.Bounce;
import animatefx.animation.FadeInDown;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author toshiba
 */
public class ShopController2 implements Initializable {

    @FXML
    private HBox shopContainer;

    List<Produit> shopProducts;
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
        shopProducts = new ArrayList<>(getShopProducts());
        try {
            for (Produit prod : shopProducts){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Book.fxml"));
                VBox vBox = fxmlLoader.load();
                BookController bookController = fxmlLoader.getController();
                bookController.setData(prod);
                shopContainer.getChildren().add(vBox);
                }
        }catch (IOException e) {
                e.printStackTrace();
        }
        //new Bounce(banner).play();
    }

    private List<Produit> getShopProducts(){
        List<Produit> lp = new ArrayList<>();
        ArrayList<Produit> products = new ServiceProduit().getAll();

        for (int i = 0; i < products.size(); i++){
            Produit product = new Produit();
            product.setName(products.get(i).getName());
            product.setPrice(products.get(i).getPrice());
            product.setImage("/Gui/Images/"+products.get(i).getImage());
            lp.add(product);
        }

        return lp;
    }

    @FXML
    void produit1(ActionEvent event) throws IOException {
        Cart produitPanier = new Cart(1, "Road Bicycles", 200, 1, "2.jpg", 0);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/Commande/ProductSingle.fxml"));
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
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/Commande/ProductSingle.fxml"));
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
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/Commande/ProductSingle.fxml"));
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
