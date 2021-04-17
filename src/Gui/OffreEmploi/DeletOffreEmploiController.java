/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.OffreEmploi;

import Entities.Offre_Emploi;
import Services.Offre_Emploi_Service;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class DeletOffreEmploiController implements Initializable {

    @FXML
    private TableColumn<Offre_Emploi, String> coltitre;
    @FXML
    private TableColumn<Offre_Emploi, String> colposte;
    @FXML
    private TableColumn<Offre_Emploi, String> coldesc;
    @FXML
    private TableColumn<Offre_Emploi, String> colloc;
    @FXML
    private TableColumn<Offre_Emploi, String> colemail;
    @FXML
    private TableColumn<Offre_Emploi, LocalDate> colexp;
    @FXML
    private TableView<Offre_Emploi> table;
    @FXML
    private Button deletebtn;
    @FXML
    private TableColumn<?, ?> startdated;
    @FXML
    private TableColumn<?, ?> categ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AtomicReference<Offre_Emploi> off = new AtomicReference<>(new Offre_Emploi());
        showOffres();

        deletebtn.setOnAction(e -> {
            Offre_Emploi offre = table.getSelectionModel().getSelectedItem();
            if (offre != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deleting a job application");
                alert.setContentText("Do you really wanna delete this application ?");
                ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        new Offre_Emploi_Service().deleteoffre(String.valueOf(offre.getId()));
                        showOffres();
                    }
                });
            }
        });
    }

    public void showOffres() {
        ObservableList<Offre_Emploi> offres = new Offre_Emploi_Service().getAll(1);
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colposte.setCellValueFactory(new PropertyValueFactory<>("poste"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colloc.setCellValueFactory(new PropertyValueFactory<>("location"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colexp.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        startdated.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        categ.setCellValueFactory(new PropertyValueFactory<>("catname"));
        table.setItems(offres);
    }
}
