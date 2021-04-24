package Gui.Produit;

import Entities.Cart;
import Entities.Produit;
import Gui.Commande.PanierController;
import Gui.Commande.ProductSingleController;
import Services.ServiceProduit;
import animatefx.animation.Bounce;
import animatefx.animation.FadeInDown;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @FXML
    private GridPane productGrid;

    @FXML
    private Pane banner;

    private List<Produit> productsList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Bounce(banner).play();
        loadProductPane();
    }
    void loadProductPane(){
        productsList = new ArrayList<>(getShopProducts());
        int column = 0;
        int row =1;

        try {
            for (Produit prod : productsList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("myBook.fxml"));

                HBox hBoxItem = fxmlLoader.load();
                MyBookController bookController = fxmlLoader.getController();
                bookController.setData(prod);
                if (column == 3){
                    column=0;
                    ++row;
                }

                productGrid.add(hBoxItem,column++,row);
                GridPane.setMargin(hBoxItem, new Insets(10));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
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

}
