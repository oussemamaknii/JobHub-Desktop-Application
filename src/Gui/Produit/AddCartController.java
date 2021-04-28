package Gui.Produit;

import Entities.Cart;
import animatefx.animation.FadeInDown;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCartController implements Initializable {


    @FXML
    private Label lbName;

    @FXML
    private Label lbRef;

    @FXML
    private Label lbDesc;

    @FXML
    private Label lbPrice;

    @FXML
    private Spinner<Integer> sPquantity;

    @FXML
    private ImageView loadImg;
  //  @FXML
    private AnchorPane centerContent;

    @FXML
    private JFXButton addproduct;

    @FXML
    private JFXButton cancel;

    private boolean update;
    int productId;
    int currentValueQty;
    String productImage;

    ObservableList<Cart> panier = FXCollections.observableArrayList();
    Cart produitPanier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
        valueFactory.setValue(1);
        sPquantity.setValueFactory(valueFactory);
        currentValueQty = sPquantity.getValue();
    }


    @FXML
    void addProduct(MouseEvent event) {

        produitPanier.setQuantite(sPquantity.getValue());
        produitPanier.setIdProduit(productId);
        //System.out.println("Qté"+produitPanier.getQuantite());
        panier.add(produitPanier);

        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("/Gui/Produit/Card.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent parent = loader.getRoot();
        CardController cardController = loader.getController();
        cardController.displayCart(panier,centerContent);
        centerContent.getChildren().removeAll();
        new FadeInDown(parent).play();
        centerContent.getChildren().setAll(parent);
    }

    @FXML
    void cancel(MouseEvent event) {
        Stage stg = (Stage) cancel.getScene().getWindow();
        stg.close();
    }

    public void setRecords(int id, String ref,String nom, Float prix,String desc,String img,ObservableList<Cart> pa, AnchorPane cP){
        produitPanier = new Cart(id,nom,prix,1,img,1);
        productId = id;
        lbRef.setText(ref);
        lbName.setText(nom);
        lbPrice.setText(String.valueOf(prix)+"DT");
        lbDesc.setText(desc);
        loadImg.setImage(new Image(getClass().getResourceAsStream(img)));
        centerContent = cP;
        if(panier.isEmpty())
            panier.addAll(pa);
        else {
            panier.clear();;
            panier.addAll(pa);
        }
        //user=u;
    }
}
