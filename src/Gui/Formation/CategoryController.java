package Gui.Formation;

import Entities.categorie;
import Services.Categorie_Service;
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

public class CategoryController implements Initializable {


    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfcouleur;





    @FXML
    private Button addcateg;


    private boolean update;
    int productId;



    int currentValueQty;
    String productImage;
    boolean setRec = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        addcateg.setOnAction((ActionEvent e) -> {
            categorie p=new categorie();

            p.setTitref(tfnom.getText());
            p.setCouleur(tfcouleur.getText());
            //  p.setEtage(tsEtage.getAnchor());
            // p.setLibre((tsLibre.getAnchor()));







        });
}
}
