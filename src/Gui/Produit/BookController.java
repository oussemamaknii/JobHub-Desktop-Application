package Gui.Produit;

import Entities.Cart;
import Entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.Iterator;

public class BookController {

    @FXML
    private ImageView productImage;

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;
    int idProduit;
    int qtyStock;
    String img;

    ObservableList<Cart> panier = FXCollections.observableArrayList();

    @FXML
    void addToCart(ActionEvent event) {
        try {
            Cart prodPanier = new Cart(idProduit,productName.getText(),Float.parseFloat(productPrice.getText()),2,img,1);
            panier.add(prodPanier);
            /*Notifications notificationBuilder2 = Notifications.create()
                    .title("Confrimation Commande")
                    .text("Commande ajoutée avec succès")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder2.showConfirm();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Produit/Card.fxml"));
            Parent root = loader.load();
            CardController panierController = loader.getController();
            panierController.displayCart(panier);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setData(Produit produit){
        Image image = new Image(getClass().getResourceAsStream(produit.getImage()));
        productImage.setImage(image);
        productName.setText(produit.getName());
        productPrice.setText(String.valueOf(produit.getPrice()));
        idProduit = produit.getId();
        img = produit.getImage();
        qtyStock = produit.getQuantity();
    }
}
