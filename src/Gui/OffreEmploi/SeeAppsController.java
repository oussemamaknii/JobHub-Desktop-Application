/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.OffreEmploi;

import Entities.Demande_Recrutement;
import Entities.Offre_Emploi;
import Services.Demande_Service;
import Services.Offre_Emploi_Service;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class SeeAppsController implements Initializable {

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
    private TableColumn<?, ?> startdated;
    @FXML
    private TableColumn<?, ?> categ;
    @FXML
    private Button seeapp;
    @FXML
    private Button treatapp;
    @FXML
    private Button displayoff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setVisible(true);
        table2.setVisible(false);
        showOffres();

        seeapp.setOnAction(e->{
            Offre_Emploi offre = table.getSelectionModel().getSelectedItem();
            if (offre != null) {
                showapps(offre.getId());
                table.setVisible(false);
                table2.setVisible(true);
            }
        });

        treatapp.setOnAction(e->{
            table.setVisible(false);
            table2.setVisible(true);
            Demande_Recrutement demande = table2.getSelectionModel().getSelectedItem();
            if (demande != null) {
                new Demande_Service().upstat(String.valueOf(demande.getId()));
            }
        });

        treatapp.setOnAction(e->{
            table.setVisible(false);
            table2.setVisible(true);
            Demande_Recrutement demande = table2.getSelectionModel().getSelectedItem();
            if (demande != null) {
                new Demande_Service().upstat(String.valueOf(demande.getId()));
            }
        });

        displayoff.setOnAction(e->{
            table.setVisible(true);
            table2.setVisible(false);
            showOffres();
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

    public void showapps(int id) {
        ObservableList<Demande_Recrutement> offres = new Demande_Service().getAll(id);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        offtit.setCellValueFactory(new PropertyValueFactory<>("offtit"));
        stat.setCellValueFactory(new PropertyValueFactory<>("status"));
        stdate.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        enddate.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        table2.setItems(offres);
    }

}
