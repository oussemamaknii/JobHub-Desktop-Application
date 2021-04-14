/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Demande;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Demande_Recrutement;
import Entities.Offre_Emploi;
import Services.Demande_Service;
import Services.Offre_Emploi_Service;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jobhub_app.FXMain;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class AddDemandeController implements Initializable {

    @FXML
    private TableView<Demande_Recrutement> table2;
    @FXML
    private TableColumn<?, ?> offtit;
    @FXML
    private TableColumn<?, ?> username;
    @FXML
    private TableColumn<?, ?> stat;
    @FXML
    private TableColumn<?, ?> stdate;
    @FXML
    private TableColumn<?, ?> enddate;
    @FXML
    private TableView<Offre_Emploi> table;
    @FXML
    private TableColumn<?, ?> coltitre;
    @FXML
    private TableColumn<?, ?> colposte;
    @FXML
    private TableColumn<?, ?> coldesc;
    @FXML
    private TableColumn<?, ?> colloc;
    @FXML
    private TableColumn<?, ?> colemail;
    @FXML
    private TableColumn<?, ?> startdated;
    @FXML
    private TableColumn<?, ?> colexp;
    @FXML
    private TableColumn<?, ?> categ;
    @FXML
    private Button apply;
    @FXML
    private Button delete;
    @FXML
    private Button exit;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int iduser = 5;
        table.setVisible(false);
        table2.setVisible(true);
        showapplies(iduser);

        exit.setOnAction(e->{
            Stage stage1 = (Stage) exit.getScene().getWindow();
            stage1.close();
        });

        apply.setOnAction(e -> {
            table.setVisible(true);
            table2.setVisible(false);
            showoffers();
            Offre_Emploi offre = table.getSelectionModel().getSelectedItem();
            if (offre != null) {
                new Demande_Service().apply(offre.getId(), iduser);
                showapplies(iduser);
                table.setVisible(false);
                table2.setVisible(true);
            }
        });
        
        home.setOnAction(e->{
            try {
                Stage stage1 = (Stage) home.getScene().getWindow();
                stage1.close();
                home();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        delete.setOnAction(e -> {
            table.setVisible(false);
            table2.setVisible(true);
            showapplies(iduser);
            Demande_Recrutement demande = table2.getSelectionModel().getSelectedItem();
            if (demande != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deleting a job application");
                alert.setContentText("Do you really wanna delete this application ?");
                ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        new Demande_Service().deletapp(demande.getId());
                        showapplies(iduser);
                    }
                });
            }
        });
    }

    public void home() throws IOException {
        new FXMain().start(new Stage());
    }

    public void showoffers() {
        ObservableList<Offre_Emploi> offres = new Demande_Service().get_not_applied_jobs();
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

    public void showapplies(int id) {
        ObservableList<Demande_Recrutement> offres = new Demande_Service().getAllUser(id);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        offtit.setCellValueFactory(new PropertyValueFactory<>("offtit"));
        stat.setCellValueFactory(new PropertyValueFactory<>("status"));
        stdate.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        enddate.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        table2.setItems(offres);
    }

}
