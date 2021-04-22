/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Commande;

import Entities.Panier;
//import Entitie.User.User;
import Entities.Cart;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeInDown;
import animatefx.animation.FadeInLeft;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideOutRight;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author toshiba
 */
public class ProductSingleController implements Initializable {

    @FXML
    private ImageView imagePanier;

    @FXML
    private ImageView imagePanier1;

    @FXML
    private ImageView imagePanier2;

    @FXML
    private ImageView imagePanier3;

    @FXML
    private Spinner<Integer> quantite;
    final int initialValue = 1;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    SpinnerValueFactory<Integer> svf;
    ObservableList<Cart> panier = FXCollections.observableArrayList();
    AnchorPane centerContent;
    Cart produitPanier;
     //User user;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void imagePanier1OnClick(MouseEvent event) {
        if (imagePanier1.getParent().getStyleClass().contains("focus") == false) {
            imagePanier1.getParent().getStyleClass().add("focus");
        }
        if (imagePanier2.getParent().getStyleClass().contains("focus")) {
            imagePanier2.getParent().getStyleClass().remove("focus");
        }
        if (imagePanier3.getParent().getStyleClass().contains("focus")) {
            imagePanier3.getParent().getStyleClass().remove("focus");
        }

        imagePanier.setImage(new Image("/GUI/Images/4.jpg"));
        new FadeIn(imagePanier) {
        }.play();

    }

    @FXML
    void imagePanier2OnClick(MouseEvent event) {
        if (imagePanier2.getParent().getStyleClass().contains("focus") == false) {
            imagePanier2.getParent().getStyleClass().add("focus");
        }
        if (imagePanier3.getParent().getStyleClass().contains("focus")) {
            imagePanier3.getParent().getStyleClass().remove("focus");
        }
        if (imagePanier1.getParent().getStyleClass().contains("focus")) {
            imagePanier1.getParent().getStyleClass().remove("focus");
        }
        imagePanier.setImage(new Image("/GUI/Images/5.jpg"));
        new FadeIn(imagePanier).play();
    }

    @FXML
    void imagePanier3OnClick(MouseEvent event) {
        if (imagePanier3.getParent().getStyleClass().contains("focus") == false) {
            imagePanier3.getParent().getStyleClass().add("focus");
        }
        if (imagePanier2.getParent().getStyleClass().contains("focus")) {
            imagePanier2.getParent().getStyleClass().remove("focus");
        }
        if (imagePanier1.getParent().getStyleClass().contains("focus")) {
            imagePanier1.getParent().getStyleClass().remove("focus");
        }
        imagePanier.setImage(new Image("/GUI/Images/6.jpg"));
        new FadeIn(imagePanier).play();
    }

    //public void redirection(AnchorPane c, Cart p,ObservableList<Cart> pa,User u)
    public void redirection(AnchorPane c, Cart p,ObservableList<Cart> pa) {
        centerContent = c;
        produitPanier = p;
        nom.setText(p.getNomProduit());
        prix.setText(Float.toString(p.getPrix()));
        svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, p.getQuantite());
        quantite.setValueFactory(svf);
        imagePanier.setImage(new Image("/GUI/Images/"+p.getImage()));
        if(panier.isEmpty())
        panier.addAll(pa);
        else {
            panier.clear();;
            panier.addAll(pa);
        }
      //  user=u;
    }
      @FXML
    void ajouterPanierAction(ActionEvent event) throws IOException {
     produitPanier.setQuantite(quantite.getValue());
          System.out.println("quantite"+ produitPanier.getQuantite());
     panier.add(produitPanier);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/Commande/Panier.fxml"));
        Parent fxml = Loader.load();
        PanierController e = Loader.getController();
        //e.redirection(centerContent,panier,user);
        e.redirection(centerContent,panier);
        centerContent.getChildren().removeAll();
        new FadeInDown(fxml).play();
        centerContent.getChildren().setAll(fxml);
    }

}
