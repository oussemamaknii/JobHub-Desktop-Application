package Gui.Produit;

import Entities.Cart;
import Entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class BookController {

    @FXML
    private ImageView productImage;

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;
    int idProduit;
    int qtyStock;

    ObservableList<Cart> panier = FXCollections.observableArrayList();

    @FXML
    void addToCart(ActionEvent event) {
        try {
            System.out.println(productImage);
            Cart prodPanier = new Cart(idProduit,productName.getText(),Float.parseFloat(productPrice.getText()),
                    qtyStock,productImage.getImage().getUrl(),0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Produit/Card.fxml"));

            Parent root = (Parent) loader.load();
            CardController panierController = loader.getController();

            if(panier.isEmpty())
                panier.addAll(prodPanier);
            else {
                panier.clear();;
                panier.addAll(prodPanier);
            }
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
        qtyStock = produit.getQuantity();
    }
}
