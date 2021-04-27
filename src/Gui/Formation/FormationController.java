package Gui.Formation;

import Entities.formation;
import Services.Formation_Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import org.controlsfx.control.Notifications;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class FormationController implements Initializable {
    public ObservableList<formation> currentProduct = FXCollections.observableArrayList();
    @FXML
    private Button addformation;
    @FXML
    private ChoiceBox<String> choicecateg;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tformateur;
    @FXML
    private TextField tfdescription;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfprix;
    @FXML
    private TableView<formation> tvBooks;
    @FXML
    private TableColumn<formation, Integer> id;

    @FXML
    private TableColumn<formation, String> formation;

    @FXML
    private TableColumn<formation, DatePicker> date_debutf;

    @FXML
    private TableColumn<formation, DatePicker> date_finf;
    @FXML
    private TableColumn<formation, String> adresse;

    @FXML
    private TableColumn<formation, Double> tel;
    @FXML
    private TableColumn<formation, Double> prix;

    @FXML
    private TextField tfSearch;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchFormation();
        /*ObservableList<formation> list = (ObservableList<formation>) new Formation_Service().getAll();
        id.setCellValueFactory(new PropertyValueFactory<formation,Integer>("id"));

        formation.setCellValueFactory(new PropertyValueFactory<formation, String>("formateur"));

        date_debutf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_debut"));
        date_finf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_fin"));
        adresse.setCellValueFactory(new PropertyValueFactory<formation, String>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<formation, Double>("tel"));
        prix.setCellValueFactory(new PropertyValueFactory<formation, Double>("prix"));
        tvBooks.setItems(list);*/

        choicecateg.setItems(FXCollections.observableArrayList(new Formation_Service().getCateg()));

        addformation.setOnAction(e -> {
            formation p = new formation(new Formation_Service().getCategId(choicecateg.getValue()), tfnom.getText(),
                    tformateur.getText(),
                    tfdescription.getText(),
                    tfadresse.getText(),
                    Double.parseDouble(tftel.getText()),
                    tfmail.getText(),
                    Date.valueOf(date_debut.getValue()),
                    Date.valueOf(date_fin.getValue()),
                    Double.parseDouble(tfprix.getText()));

            new Formation_Service().addformation(p);
            readEvents(e);

        });
    }

    private void readEvents(ActionEvent event) {


        ObservableList<formation> list = (ObservableList<formation>) new Formation_Service().getAll();
        id.setCellValueFactory(new PropertyValueFactory<formation,Integer>("id"));

        formation.setCellValueFactory(new PropertyValueFactory<formation, String>("nom"));


        date_debutf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_debut"));
        date_finf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_fin"));
        adresse.setCellValueFactory(new PropertyValueFactory<formation, String>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<formation, Double>("tel"));
        prix.setCellValueFactory(new PropertyValueFactory<formation, Double>("prix"));
        tvBooks.setItems(list);

    }

    @FXML
    void searchFormation(){

            ObservableList<formation> list = new Formation_Service().getAll();
            id.setCellValueFactory(new PropertyValueFactory<formation,Integer>("id"));

            formation.setCellValueFactory(new PropertyValueFactory<formation, String>("nom"));
            date_debutf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_debut"));
            date_finf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_fin"));
            adresse.setCellValueFactory(new PropertyValueFactory<formation, String>("adresse"));
            tel.setCellValueFactory(new PropertyValueFactory<formation, Double>("tel"));
            prix.setCellValueFactory(new PropertyValueFactory<formation, Double>("prix"));
            tvBooks.setItems(list);

            FilteredList<formation> filteredData = new FilteredList<>(list, b->true);
            tfSearch.textProperty().addListener((Observable,oldValue,newValue)->{
                filteredData.setPredicate(formation->{
                    if (newValue == null || newValue.isEmpty()){
                        return  true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (formation.getTitre().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true; //filter matches name
                    }else if (formation.getFormateur().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true; //filter matches formatteur
                    }
                else if (String.valueOf(formation.getDate_debut()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true; //filter matches date_debut
                    }
                    else
                        return false; // does not match
                });
            });

            SortedList<formation> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tvBooks.comparatorProperty());
            tvBooks.setItems(sortedData);
    }

    /*
    public void tfSearchOnKeyReleased(javafx.scene.input.KeyEvent keyEvent) {
        String t = tfSearch.getText();
        search(t);
    }*/

    public void search(String t){
        System.out.println("CLCKED");
        ArrayList<formation> l;
        Formation_Service cp = new Formation_Service();

        l =cp.getProduitsByNameOrID(t);
        currentProduct.clear();
        currentProduct.addAll(l);
        tvBooks.setItems(currentProduct);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        formation.setCellValueFactory(new PropertyValueFactory<>("nom"));

        date_debutf.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_finf.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    @FXML
    private void deleteAction(MouseDragEvent event) {

    }

    @FXML
    private void removeAction(ActionEvent event) {
        formation s = tvBooks.getSelectionModel().getSelectedItem();
        Formation_Service crs = new Formation_Service();
        crs.supprimer(s);
        Notifications notificationBuild = Notifications.create()
                .title("Traitement formation")
                .text("la formation été supprimé avec succes")
                .graphic(null)
                //.hideAfter(Duration.Hours(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("click here");
                    }

                });

        readEvents(event);


    }
    @FXML
    private void updateAction(ActionEvent event) {


        formation s = tvBooks.getSelectionModel().getSelectedItem();

        id.setText(String.valueOf(s.getId()));
        formation.setText(String.valueOf(s.getTitre()));
        date_debutf.setText(String.valueOf(s.getDate_debut()));
        date_finf.setText(String.valueOf(s.getDate_fin()));
        adresse.setText(String.valueOf(s.getAdresse()));
        tel.setText(String.valueOf(s.getTel()));
        prix.setText(String.valueOf(s.getPrix()));

    }

}