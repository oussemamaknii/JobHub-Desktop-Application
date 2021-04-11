/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Entities.Offre_Emploi;
import Services.Offre_Emploi_Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class Offre_EmploiController implements Initializable {

    @FXML
    private TextField tfpost;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfloc;
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
    private Label lbRes;
    @FXML
    private Button addoffer1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choicecateg.setItems(FXCollections.observableArrayList(new Offre_Emploi_Service().getCateg())
        );

        addoffer1.setOnAction(e -> {
            Offre_Emploi offer = new Offre_Emploi(new Offre_Emploi_Service().getCategId(choicecateg.getValue()),
                    tftitle.getText(), tfpost.getText(), tfdesc.getText(), tfloc.getText(), tffile.getText(),
                    tfemil.getText(), LocalDate.now(), tfexp.getValue(), Integer.parseInt(tfmax.getText()),
                    Integer.parseInt(tfmin.getText())
            );
            new Offre_Emploi_Service().add(offer);
            afficherOffres();
        });
    }

    @FXML
    private void afficherOffres() {
        new Offre_Emploi_Service()
                .getAll()
                .stream()
                .forEach(p -> {
                    lbRes.setText(lbRes.getText() + '\n' + p.toString());
                });
    }

}