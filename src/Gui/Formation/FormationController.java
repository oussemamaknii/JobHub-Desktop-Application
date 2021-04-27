package Gui.Formation;

import Entities.formation;
import Services.Formation_Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
        ObservableList<formation> list = (ObservableList<formation>) new Formation_Service().getAll();
        id.setCellValueFactory(new PropertyValueFactory<formation,Integer>("id"));

        formation.setCellValueFactory(new PropertyValueFactory<formation, String>("formateur"));

        date_debutf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_debut"));
        date_finf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_fin"));
        adresse.setCellValueFactory(new PropertyValueFactory<formation, String>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<formation, Double>("tel"));
        prix.setCellValueFactory(new PropertyValueFactory<formation, Double>("prix"));
        tvBooks.setItems(list);

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

        formation.setCellValueFactory(new PropertyValueFactory<formation, String>("formateur"));

        date_debutf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_debut"));
        date_finf.setCellValueFactory(new PropertyValueFactory<formation, DatePicker>("date_fin"));
        adresse.setCellValueFactory(new PropertyValueFactory<formation, String>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<formation, Double>("tel"));
        prix.setCellValueFactory(new PropertyValueFactory<formation, Double>("prix"));
        tvBooks.setItems(list);

    }



    public void tfSearchOnKeyReleased(javafx.scene.input.KeyEvent keyEvent) {
        String t = tfSearch.getText();
        search(t);
    }

    public void search(String t){
        System.out.println("CLCKED");
        ArrayList<formation> l;
        Formation_Service cp = new Formation_Service();

        l =cp.getProduitsByNameOrID(t);
        currentProduct.clear();
        currentProduct.addAll(l);
        tvBooks.setItems(currentProduct);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        formation.setCellValueFactory(new PropertyValueFactory<>("formateur"));

        date_debutf.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_finf.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

}
