package Gui.Produit;

import Entities.Produit;
import Services.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddProductController implements Initializable {


    @FXML
    private TextField tfref;

    @FXML
    private TextField tfdesc;

    @FXML
    private TextField tfprice;

    @FXML
    private TextField tfname;

    @FXML
    private Spinner<Integer> sPquantity;

    @FXML
    private Button btnImage;

    @FXML
    private ImageView loadImg;

    @FXML
    private JFXButton addproduct;

    @FXML
    private JFXButton clearproduct;

    @FXML
    private Button displaybtn;

    @FXML
    private JFXButton cancel;

    private boolean update;
    int productId;

    int currentValueQty;
    String productImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
        valueFactory.setValue(1);
        sPquantity.setValueFactory(valueFactory);
        currentValueQty = sPquantity.getValue();
    }
    @FXML
    private void addProduct(){
        String name =tfname.getText();
        String ref = tfref.getText();
        String desc = tfdesc.getText();
        Float prix = Float.parseFloat(tfprice.getText());

        int qty = sPquantity.getValue();
        String img = productImage;
        if(name.isEmpty()||ref.isEmpty()||desc.isEmpty()||prix.isNaN()||(img ==null)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();
        }else {
            Produit produit = new Produit(name,ref,desc,prix,qty,img);
            if(!update){
                try {
                    new ServiceProduit().add(produit);
                    Stage stg = (Stage) addproduct.getScene().getWindow();
                    clean();
                    stg.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else {
                    new ServiceProduit().update2(produit,productId);
                    Stage stg = (Stage) addproduct.getScene().getWindow();
                    clean();
                    stg.close();
            }

        }
    }
    @FXML
    private void clean() {
        tfname.setText(null);
        tfdesc.setText(null);
        tfprice.setText(null);
        tfref.setText(null);
        loadImg.setImage(null);
        btnImage.setText("Upload");
        sPquantity.setValueFactory(null);
    }
    @FXML
    void cancel(MouseEvent event) {
        Stage stg = (Stage) cancel.getScene().getWindow();
        stg.close();
    }

    @FXML
    void loadImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            productImage = selectedFile.getName();
            loadImg.setImage(new Image(getClass().getResourceAsStream("/Gui/Images/"+productImage)));
        }else {
            System.out.println("File is not valid");
        }

    }


    public void setUpdate(boolean b) {
        this.update = b;
    }

    public void setRecords(int id, String ref,String nom, Float prix,String desc,String img){
        productId = id;
        tfref.setText(ref);
        tfname.setText(nom);
        tfprice.setText(String.valueOf(prix)+"  DT");
        tfdesc.setText(desc);
        loadImg.setImage(new Image(getClass().getResourceAsStream("/Gui/Images/"+img)));
        btnImage.setText("Upload");
    }
}
