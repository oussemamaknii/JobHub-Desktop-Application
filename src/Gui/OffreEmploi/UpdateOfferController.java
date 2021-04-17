/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.OffreEmploi;

import Entities.Offre_Emploi;
import Services.Offre_Emploi_Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class UpdateOfferController implements Initializable {

    @FXML
    private Pane paneupdatet;
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
    private TableView<Offre_Emploi> table;
    @FXML
    private TableColumn<?, ?> startdated;
    @FXML
    private TableColumn<?, ?> categ;
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
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AtomicReference<Offre_Emploi> off = new AtomicReference<>(new Offre_Emploi());
        showOffres();
        update2.setOnAction(e -> {
                Offre_Emploi offre = table.getSelectionModel().getSelectedItem();
                if (offre != null) {
                    off.set(offre);
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
        update.setOnAction(e->{
            Offre_Emploi offer = new Offre_Emploi(new Offre_Emploi_Service().getCategId(choicecateg.getValue()),
                    tftitle.getText(), tfpost.getText(), tfdesc.getText(), tfloc.getText(), tffile.getText(),
                    tfemil.getText(), LocalDate.now(), tfexp.getValue(), Integer.parseInt(tfmax.getText()),
                    Integer.parseInt(tfmin.getText())
            );
            new Offre_Emploi_Service().updateoffre(offer, off);
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

}
