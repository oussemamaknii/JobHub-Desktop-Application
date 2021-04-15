/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import Entities.Demande_Recrutement;
import Entities.Offre_Emploi;
import Services.Demande_Service;
import Services.Offre_Emploi_Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jobhub_app.FXMain;

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
    private Button deletebtn;
    @FXML
    private TableView<Offre_Emploi> table;
    @FXML
    private Button updatebtn;
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
    private Button update2;
    @FXML
    private Button treatapp;
    @FXML
    private Button seeapp;
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
    private TableView<Demande_Recrutement> table2;
    @FXML
    private TableColumn<?, ?> startdated;
    @FXML
    private TableColumn<?, ?> categ;
    @FXML
    private Pane paneoffers;
    @FXML
    private Pane panedemande;
    @FXML
    private Pane paneupdatet;
    @FXML
    private Button exit;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AtomicReference<Offre_Emploi> off = new AtomicReference<>(new Offre_Emploi());
        showOffres();
        paneoffers.setVisible(true);
        panedemande.setVisible(false);
        paneupdatet.setVisible(false);

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
        home.setOnAction(e->{
            try {
                Stage stage1 = (Stage) home.getScene().getWindow();
                stage1.close();
                home();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        exit.setOnAction(e->{
            Stage stage1 = (Stage) exit.getScene().getWindow();
            stage1.close();
        });

        treatapp.setOnAction(e->{
            Demande_Recrutement dem = table2.getSelectionModel().getSelectedItem();
            if (dem != null) {
                new Demande_Service().upstat(String.valueOf(dem.getId()));
            }
        });

        updatebtn.setOnAction(e -> {
            Offre_Emploi offre = table.getSelectionModel().getSelectedItem();
            if (offre != null) {
                off.set(offre);
                paneoffers.setVisible(false);
                panedemande.setVisible(false);
                paneupdatet.setVisible(true);
                choicecateg.setItems(FXCollections.observableArrayList(new Offre_Emploi_Service().getCateg()));
                tfdesc.setText(offre.getDescription());
                tfmax.setText(String.valueOf(offre.getMax_salary()));
                tfmin.setText(String.valueOf(offre.getMin_salary()));
                tfexp.setValue(offre.getDate_expiration());
                tfpost.setText(offre.getPoste());
                tftitle.setText(offre.getTitre());
                tfloc.setText(offre.getLocation());
                tffile.setText(offre.getFile());
                tfemil.setText(offre.getEmail());
                choicecateg.setValue(new Offre_Emploi_Service().getOffreCateg(String.valueOf(offre.getCategory())));
            }
        });

        update2.setOnAction(e -> {
            Offre_Emploi offer = new Offre_Emploi(new Offre_Emploi_Service().getCategId(choicecateg.getValue()),
                    tftitle.getText(), tfpost.getText(), tfdesc.getText(), tfloc.getText(), tffile.getText(),
                    tfemil.getText(), LocalDate.now(), tfexp.getValue(), Integer.parseInt(tfmax.getText()),
                    Integer.parseInt(tfmin.getText())
            );
            new Offre_Emploi_Service().updateoffre(offer,off);
            paneoffers.setVisible(true);
            panedemande.setVisible(false);
            paneupdatet.setVisible(false);
            showOffres();
        });

        seeapp.setOnAction(e->{
            Offre_Emploi offre = table.getSelectionModel().getSelectedItem();
            if (offre != null) {
                showapps(offre.getId());
                paneoffers.setVisible(false);
                panedemande.setVisible(true);
                paneupdatet.setVisible(false);
            }
        });
    }
    public void home() throws IOException {
        new FXMain().start(new Stage());
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

    public void showapps(int id){
        ObservableList<Demande_Recrutement> offres = new Demande_Service().getAll(id);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        offtit.setCellValueFactory(new PropertyValueFactory<>("offtit"));
        stat.setCellValueFactory(new PropertyValueFactory<>("status"));
        stdate.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        enddate.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        table2.setItems(offres);
    }
}
