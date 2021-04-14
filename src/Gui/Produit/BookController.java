package Gui.Produit;

import Entities.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BookController {

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    public void setData(Produit produit){
        System.out.println(getClass().getResourceAsStream(produit.getName()));
        Image image = new Image(getClass().getResourceAsStream(produit.getName()));
        productImage.setImage(image);
        productName.setText(produit.getName());
        productPrice.setText(String.valueOf(produit.getPrice()));
    }
}
