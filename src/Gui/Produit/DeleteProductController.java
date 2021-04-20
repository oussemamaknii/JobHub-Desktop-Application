package Gui.Produit;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import Entities.Offre_Emploi;
import Entities.Produit;
import Services.Offre_Emploi_Service;
import Services.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class DeleteProductController implements Initializable {
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
    private TableColumn<Produit, String> colimage;
    @FXML
    private Button deletebtn;
    @FXML
    private TableView<Produit> table;
    @FXML
    private Button updatebtn;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfref;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tfquantity;
    @FXML
    private TextField tfimage;
    @FXML
    private Button update2;
    @FXML
    private Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicReference<Produit> product = new AtomicReference<>(new Produit());
            showProducts();
        pane.setVisible(false);

        deletebtn.setOnAction(e->{
            Produit produit = table.getSelectionModel().getSelectedItem();
            if(produit != null){
                try {
                    new ServiceProduit().delete(produit.getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                    showProducts();
            }
        });

        updatebtn.setOnAction(e->{
            Produit produit = table.getSelectionModel().getSelectedItem();
            if(produit != null){
                product.set(produit);
                pane.setVisible(true);
                tfname.setText(produit.getName());
                tfdesc.setText(produit.getDescription());
                tfref.setText(produit.getRef());
                tfprice.setText(String.valueOf(produit.getPrice()));
                tfimage.setText(produit.getImage());
                tfquantity.setText(String.valueOf(produit.getQuantity()));
            }
        });

        update2.setOnAction(e->{
            Produit prod = new Produit(tfname.getText(),tfref.getText(),tfdesc.getText(),
                    Float.parseFloat(tfprice.getText()),Integer.parseInt(tfquantity.getText()),
                    tfimage.getText()
            );
                new ServiceProduit().update(prod,product);
                showProducts();

        });


    }

    public void showProducts() {
        try {
        ObservableList<Produit> products = new ServiceProduit().readAll();
            colname.setCellValueFactory(new PropertyValueFactory<>("name"));
            colref.setCellValueFactory(new PropertyValueFactory<>("ref"));
            coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
            table.setItems(products);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
