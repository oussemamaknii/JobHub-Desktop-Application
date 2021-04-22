/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Demande;

import Entities.Demande_Recrutement;
import Entities.Offre_Emploi;
import Gui.OffreEmploi.OffreCell;
import Services.Demande_Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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
    private Button apply;
    @FXML
    private Button delete;
    @FXML
    private Button seeapps;
    @FXML
    private ListView<Offre_Emploi> table;
    @FXML
    private Pagination page;
    int alluserapps = new Demande_Service().countalluserapps(5);
    int from = 0, to = 0,itemperpage = 5;
    @FXML
    private ChoiceBox<String> tri;
    @FXML
    private Label lab;
    @FXML
    private Pane pane;
    @FXML
    private StackPane effect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Post","Categprie","Date Expiration");
        tri.setItems(list);
        table.setVisible(false);
        tri.setVisible(false);
        lab.setVisible(false);
        table2.setVisible(false);
        int iduser = 5;
        showapplies(iduser);

        tri.setOnAction(e->{
            showofferstri(tri.getValue());
        });

        apply.setOnAction(e -> {
            table.setVisible(true);
            tri.setVisible(true);
            lab.setVisible(true);
            table2.setVisible(false);
            showoffers();
            Offre_Emploi offre = table.getSelectionModel().getSelectedItem();
            if (offre != null) {
                new Demande_Service().apply(offre.getId(), iduser);
                showapplies(iduser);
                table.setVisible(false);
                page.setVisible(true);
                table2.setVisible(true);
            }
        });

        delete.setOnAction(e -> {
            table.setVisible(false);
            tri.setVisible(false);
            lab.setVisible(false);
            page.setVisible(true);
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
            }else {
                effect.setDisable(false);
                BoxBlur blur = new BoxBlur(3,3,3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Error"));
                content.setBody(new Text("Select An Application So You Can  \n Delete it !!"));
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

        seeapps.setOnAction(e -> {
            table.setVisible(false);
            tri.setVisible(false);
            lab.setVisible(false);
            table2.setVisible(true);
            showapplies(iduser);
        });
    }

    public void showofferstri(String value){
        ObservableList<Offre_Emploi> offres = new Demande_Service().tri(value);
        table.setItems(offres);
        table.setCellFactory(studentListView -> new OffreCell());
    }

    public void showoffers() {
        table.setVisible(true);
        page.setVisible(false);
        table2.setVisible(false);
        ObservableList<Offre_Emploi> offres = new Demande_Service().get_not_applied_jobs();
        table.setItems(offres);
        table.setCellFactory(studentListView -> new OffreCell());
    }

    public void showapplies(int id) {
        table.setVisible(false);
        page.setVisible(true);
        table2.setVisible(true);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        offtit.setCellValueFactory(new PropertyValueFactory<>("offtit"));
        stat.setCellValueFactory(new PropertyValueFactory<>("status"));
        stdate.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        enddate.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        page.setPageCount((alluserapps / itemperpage) + 1);
        page.setPageFactory(this::createtable);
    }

    public Node createtable(int pageindex) {
        from = pageindex * itemperpage;
        to = itemperpage;
        table2.setItems(FXCollections.observableList(new Demande_Service().getAllUser(5,from, to)));
        return table2;
    }

}
