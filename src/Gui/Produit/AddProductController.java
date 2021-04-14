package Gui.Produit;

import Entities.Produit;
import Services.ServiceProduit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductController implements Initializable {

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
    private Button addproduct;
    @FXML
    private Button displaybtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addproduct.setOnAction(e->{
            Produit produit = new Produit(
                    tfname.getText(),tfref.getText(),tfdesc.getText(),
                    Float.parseFloat(tfprice.getText()),
                    Integer.parseInt(tfquantity.getText()),tfimage.getText()
            );

            try {
                new ServiceProduit().add(produit);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            afficherProduits();
        });

    }

    @FXML
    private void afficherProduits(){
        try {
        Stage stage1 =  (Stage) displaybtn.getScene().getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteProduct.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("JobHub Application");
        stage.setScene(new Scene(root1));
        stage.show();
        } catch (IOException e) {
            System.out.println("Ici error");
            e.printStackTrace();

        }


    }
}
