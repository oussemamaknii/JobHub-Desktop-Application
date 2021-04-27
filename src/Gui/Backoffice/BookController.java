package Gui.Backoffice;

import Entities.Cart;
import Entities.Commande;
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
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class BookController extends ListCell<Produit> {

    @FXML
    private ImageView productImage;

    @FXML
    private AnchorPane ProduitCell;

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

    private FXMLLoader mLLoader;
    int idProduit;
    int qtyStock;
    String img;

    @Override
    protected void updateItem(Produit student, boolean empty) {
        super.updateItem(student, empty);

        if(empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("Book.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Image image = new Image(getClass().getResourceAsStream("/Gui/Images/"+student.getImage()));
            productImage.setImage(image);
            lbName.setText(student.getName());
            lbDesc.setText(student.getDescription());
            lbRef.setText(student.getRef());
            lbPrice.setText(String.valueOf(student.getPrice()));
            lbQty.setText(String.valueOf(student.getQuantity()));

            setText(null);
            setGraphic(ProduitCell);
        }
    }
/*


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

 */

}
