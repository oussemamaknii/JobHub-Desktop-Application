package Gui.Backoffice;

import Entities.Cart;
import Entities.Produit;
import Gui.Produit.AddProductController;
import Services.ServiceProduit;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class BookController {

    @FXML
    private ImageView productImage;

    @FXML
    private Label lbName;

    @FXML
    private Label lbRef;

    @FXML
    private Label lbQty;

    @FXML
    private Label lbPrice;

    @FXML
    private FontAwesomeIcon deletStrash;

    @FXML
    private FontAwesomeIcon edit;

    @FXML
    private Label lbDesc;

    int idProduit;
    int qtyStock;
    String img;

    @FXML
    void editProd(MouseEvent event) {
        Produit productTab = new Produit(idProduit,lbName.getText(),lbRef.getText(),lbDesc.getText(),Float.parseFloat(lbPrice.getText()),
                qtyStock,img);
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("/Gui/Produit/AddProduct.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        AddProductController addPController = loader.getController();
        addPController.setUpdate(true);
        addPController.setRecords(productTab.getId(),productTab.getRef(),productTab.getName(),
                productTab.getPrice(),productTab.getDescription(),productTab.getImage());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    }

    @FXML
    void suppProd(MouseEvent event) {
        Produit produit = new Produit(idProduit,lbName.getText(),lbRef.getText(),lbDesc.getText(),Float.parseFloat(lbPrice.getText()),
                qtyStock,img);
        if(produit != null){
            try {

                new ServiceProduit().delete(idProduit);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    ObservableList<Cart> panier = FXCollections.observableArrayList();

    public void setData(Produit produit){
        Image image = new Image(getClass().getResourceAsStream(produit.getImage()));
        productImage.setImage(image);
        lbName.setText(produit.getName());
        lbPrice.setText(String.valueOf(produit.getPrice()));
        lbQty.setText(String.valueOf(produit.getQuantity()));
        lbRef.setText(produit.getRef());
        lbDesc.setText(produit.getDescription());

        idProduit = produit.getId();
        img = produit.getImage();
        qtyStock = produit.getQuantity();
    }
}
