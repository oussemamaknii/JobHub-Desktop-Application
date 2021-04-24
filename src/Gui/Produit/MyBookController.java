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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MyBookController {

    @FXML
    private ImageView productImage;

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;
    int idProduit;
    int qtyStock;
    String ref,desc,img;
    ObservableList<Cart> panier = FXCollections.observableArrayList();

    @FXML
    void addToCart(ActionEvent event) {
        Cart produitPanier = new Cart(idProduit,productName.getText(),Float.parseFloat(productPrice.getText()),1,img,1);
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("/Gui/Produit/AddCart.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];

        AddCartController addPController = loader.getController();
        addPController.setRecords(idProduit,ref,productName.getText(),Float.parseFloat(productPrice.getText()),desc,img,panier);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        parent.setOnMousePressed(e -> {
            xOffset[0] = e.getSceneX();
            yOffset[0] = e.getSceneY();
        });

        parent.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - xOffset[0]);
            stage.setY(e.getScreenY() - yOffset[0]);
        });
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void setData(Produit produit){
        Image image = new Image(getClass().getResourceAsStream(produit.getImage()));
        productImage.setImage(image);
        productName.setText(produit.getName());
        productPrice.setText(String.valueOf(produit.getPrice())+" DT");
        idProduit = produit.getId();
        ref = produit.getRef();
        desc = produit.getDescription();
        img = produit.getImage();
        qtyStock = produit.getQuantity();
    }
}