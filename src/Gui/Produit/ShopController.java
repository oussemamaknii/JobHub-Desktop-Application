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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    @FXML
    private TableColumn<Produit, String> colname;
    @FXML
    private TableColumn<Produit, String> colref;
    @FXML
    private TableColumn<Produit, String> coldesc;
    @FXML
    private TableColumn<Produit, String> colprice;
    @FXML
    private TableColumn<Produit, String> colquantity;
    @FXML
    private TableColumn<Produit, LocalDate> colimage;
    @FXML
    private TableView<Produit> table;
    ObservableList<Cart> panier = FXCollections.observableArrayList();
    private AnchorPane centerContent;
    @FXML
    private Pane banner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Bounce(banner).play();
    }

    @FXML
    void addCart(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Commande/Panier.fxml"));
            Parent root = (Parent) loader.load();

            PanierController panierController = loader.getController();
            panierController.redirection(centerContent,panier);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProducts() {
        try {
            ObservableList<Produit> products = new ServiceProduit().readAll();
            colname.setCellValueFactory(new PropertyValueFactory<>("name"));
            colref.setCellValueFactory(new PropertyValueFactory<>("ref"));
            coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
            table.setItems(products);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @FXML
    void produit1(ActionEvent event) throws IOException {
        Cart produitPanier = new Cart(1, "Road Bicycles", 200, 1, "/Gui/Images/2.jpg", 0);
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Gui/Commande/ProductSingle.fxml"));
        Parent fxml = Loader.load();
        ProductSingleController e = Loader.getController();
        centerContent = e.redirection(centerContent, produitPanier,panier);
        System.out.println(centerContent);
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
        e.redirection(centerContent, produitPanier,panier);
        centerContent.getChildren().removeAll();
        new FadeInDown(fxml).play();
        centerContent.getChildren().setAll(fxml);

    }
}
