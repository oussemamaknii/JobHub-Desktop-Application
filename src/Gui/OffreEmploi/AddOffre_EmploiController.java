/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.OffreEmploi;

import Entities.Offre_Emploi;
import Services.Offre_Emploi_Service;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class AddOffre_EmploiController implements Initializable {

    @FXML
    private TextField tfpost;
    @FXML
    private TextField tfloc;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tffile;
    @FXML
    private TextField tfemil;
    @FXML
    private TextField tfmax;
    @FXML
    private TextField tftitle;
    @FXML
    private DatePicker tfexp;
    @FXML
    private TextField tfmin;
    @FXML
    private ChoiceBox<String> choicecateg;
    @FXML
    private Button addoffer1;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfmax.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfmax.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        tffile.setEditable(false);
        tfmin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfmin.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        choicecateg.setItems(FXCollections.observableArrayList(new Offre_Emploi_Service().getCateg()));

        addoffer1.setOnAction(e -> {
            if (testfields()) {
                Offre_Emploi offer = new Offre_Emploi(new Offre_Emploi_Service().getCategId(choicecateg.getValue()),
                        tftitle.getText(), tfpost.getText(), tfdesc.getText(), tfloc.getText(), tffile.getText(),
                        tfemil.getText(), LocalDate.now(), tfexp.getValue(), Integer.parseInt(tfmax.getText()),
                        Integer.parseInt(tfmin.getText())
                );
                new Offre_Emploi_Service().add(offer);
            }
        });
    }

    @FXML
    private void filechooser(ActionEvent event) {
        FileChooser c = new FileChooser();
        c.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files", "*.jpg"));
        File f = c.showOpenDialog(null);
        if (f != null)
            tffile.setText(f.getAbsolutePath());
    }

    public boolean validateEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email);
        if (m.find() && m.group().equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean testfields() {

        if (tftitle.getText().length() == 0) {
            tftitle.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tftitle).play();
            return false;
        } else
            tftitle.setStyle(null);
        if (tfpost.getText().length() == 0) {
            tfpost.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfpost).play();
            return false;
        } else
            tfpost.setStyle(null);
        if (tfdesc.getText().length() == 0) {
            tfdesc.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfdesc).play();
            return false;
        } else
            tfdesc.setStyle(null);
        if (tfloc.getText().length() == 0) {
            tfloc.setStyle("-fx-border-color : red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfloc).play();
            return false;
        } else
            tfloc.setStyle(null);
        if (tfemil.getText().length() == 0 || !validateEmail(tfemil.getText())) {
            tfemil.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfemil).play();
            return false;
        } else
            tfemil.setStyle(null);
        if (tfmax.getText().length() == 0) {
            tfmax.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfmax).play();
            return false;
        } else
            tfmax.setStyle(null);
        if (tfmin.getText().length() == 0) {
            tfmin.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfmin).play();
            return false;
        } else
            tfmin.setStyle(null);
        if (tffile.getText().length() == 0) {
            tffile.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tffile).play();
            return false;
        } else
            tffile.setStyle(null);
        if (tfexp.getValue() == null || tfexp.getValue().isBefore(LocalDate.now())) {
            tfexp.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfexp).play();
            return false;
        } else
            tfexp.setStyle(null);
        if (choicecateg.getValue() == null) {
            choicecateg.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(choicecateg).play();
            return false;
        } else
            choicecateg.setStyle(null);
        return true;
    }
}