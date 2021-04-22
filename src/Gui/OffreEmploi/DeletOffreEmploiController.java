/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.OffreEmploi;

import Entities.Offre_Emploi;
import Services.Offre_Emploi_Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class DeletOffreEmploiController implements Initializable {

    @FXML
    private Button deletebtn;
    @FXML
    private ListView<Offre_Emploi> listdisplay;
    @FXML
    private Label total;
    @FXML
    private Label countaaps;
    @FXML
    private Label treatedapps;
    @FXML
    private Label nontreated;
    @FXML
    private TextField search;
    @FXML
    private StackPane effect;
    @FXML
    private Pane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AtomicReference<Offre_Emploi> off = new AtomicReference<>(new Offre_Emploi());
        refresh();
        showOffres();
        search();

        deletebtn.setOnAction(e -> {
            Offre_Emploi offre = listdisplay.getSelectionModel().getSelectedItem();
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
                        refresh();
                        showOffres();
                    }
                });
            }else {
                effect.setDisable(false);
                BoxBlur blur = new BoxBlur(3,3,3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Error"));
                content.setBody(new Text("Select A Job So You Can Delete \n The Job !!"));
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
    }

    public void showOffres() {
        ObservableList<Offre_Emploi> offres = new Offre_Emploi_Service().getAll(1);
        listdisplay.setItems(offres);
        listdisplay.setCellFactory(studentListView -> new OffreCell());
    }

    public void refresh() {
        total.setText(String.valueOf(new Offre_Emploi_Service().countalljobs()));
        countaaps.setText(String.valueOf(new Offre_Emploi_Service().countallapps()));
        treatedapps.setText(String.valueOf(new Offre_Emploi_Service().countallappstreated()));
        nontreated.setText(String.valueOf(new Offre_Emploi_Service().countallapps() - new Offre_Emploi_Service().countallappstreated()));
    }

    public void search(){
        ObservableList<Offre_Emploi> offres = new Offre_Emploi_Service().getAll(1);
        FilteredList<Offre_Emploi> filteredData = new FilteredList(offres, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every client with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches first name
                } else if (client.getLocation().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches last name
                }
                return false; //Does not match
            });
        });

        //Wrap the FilteredList in a SortedList.
        SortedList<Offre_Emploi> sortedData = new SortedList<>(filteredData);

        //put the sorted list into the listview
        listdisplay.setItems(sortedData);
        listdisplay.setCellFactory(studentListView -> new OffreCell());
    }
}
