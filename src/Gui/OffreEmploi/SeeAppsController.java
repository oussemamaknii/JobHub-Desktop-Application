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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class SeeAppsController implements Initializable {

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
    private Button seeapp;
    @FXML
    private Button treatapp;
    @FXML
    private Button displayoff;
    @FXML
    private ListView<Offre_Emploi> table;
    @FXML
    private StackPane effect;
    @FXML
    private Pane pane;

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
            }else {
                effect.setDisable(false);
                BoxBlur blur = new BoxBlur(3,3,3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Error"));
                content.setBody(new Text("Select A Job So You Can See \n Your Job Applications"));
                JFXDialog fialog = new JFXDialog(effect,content,JFXDialog.DialogTransition.CENTER);
                JFXButton btn = new JFXButton("Done !");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        fialog.close();
                        pane.setEffect(null);
                        effect.setDisable(true);
                    }
                });
                content.setActions(btn);
                pane.setEffect(blur);
                fialog.show();
            }
        });

        treatapp.setOnAction(e->{
            Demande_Recrutement demande = table2.getSelectionModel().getSelectedItem();
            if (demande != null) {
                table.setVisible(false);
                table2.setVisible(true);
                new Demande_Service().upstat(String.valueOf(demande.getId()));
            }else {
                effect.setDisable(false);
                BoxBlur blur = new BoxBlur(3,3,3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Error"));
                content.setBody(new Text("Select A Job Application So You Can Treat \n The Job Applications"));
                JFXDialog fialog = new JFXDialog(effect,content,JFXDialog.DialogTransition.CENTER);
                JFXButton btn = new JFXButton("Done !");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        fialog.close();
                        pane.setEffect(null);
                        effect.setDisable(true);
                    }
                });
                content.setActions(btn);
                pane.setEffect(blur);
                fialog.show();
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
        table.setItems(offres);
        table.setCellFactory(studentListView -> new OffreCell());
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
