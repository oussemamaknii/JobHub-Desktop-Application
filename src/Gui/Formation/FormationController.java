package Gui.Formation;


import Entities.formation;

import Services.Formation_Service;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import org.controlsfx.control.Notifications;


import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import java.util.ResourceBundle;

public class FormationController implements Initializable {
    public ObservableList<formation> currentProduct = FXCollections.observableArrayList();
    int idC;
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
    @FXML
    private PieChart pieChart;
    ObservableList<PieChart.Data> piechartdata;

    private void refresh()
    {       pieChart.setData(piechartdata);


    }
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
            int idCat = new Formation_Service().getCategId(choicecateg.getValue());
            String nom = tfnom.getText();
            String formateur = tformateur.getText();
            String add = tfadresse.getText();
            double tel = Double.parseDouble(tftel.getText());
            Date date_deb = Date.valueOf(date_debut.getValue());
            Date date_f = Date.valueOf(date_fin.getValue());
            double prix = Double.parseDouble(tfprix.getText());
            String desc = tfdescription.getText();
            String email = tfmail.getText();
            formation p = new formation(idCat,nom,formateur,desc,add,tel,email,date_deb,date_f,prix);
            new Formation_Service().addformation(p);
            readEvents(e);

        });

        Stat();
        pieChart.setData(piechartdata);
        refresh();
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
                    if (formation.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true; //filter matches name
                    }else if (formation.getAdresse().toLowerCase().indexOf(lowerCaseFilter)!=-1){
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



    private PreparedStatement pst,pst1;
    private ResultSet rs=null;
    private ResultSet rs1=null;
    private void Stat(){

        ArrayList<Integer> np=new ArrayList<Integer>();
        ArrayList<String> cat=new ArrayList<String>();
        Connection cnx = Connexion.getInstance().getConnection();

        piechartdata=FXCollections.observableArrayList();
        try {

            pst=cnx.prepareStatement("select * from category");
            rs=pst.executeQuery();

            while(rs.next() )
            {


                pst1=cnx.prepareStatement("select count(*) as countab FROM formation WHERE category_id="+rs.getInt("id"));
                rs1=pst1.executeQuery();
                while(rs1.next())
                {
                    int i=Integer.valueOf(rs1.getString("countab"));
                    piechartdata.add(new PieChart.Data(rs.getString("titre"),i));

                    np.add(i);
                    cat.add(rs.getString("titre"));
                }

            }
        } catch (SQLException ex) {
            System.out.println("stat reclamation");
        }
        pieChart.setData(piechartdata);
    }

    @FXML
    private void updateAction(ActionEvent event) {


        formation s = tvBooks.getSelectionModel().getSelectedItem();
        idC = s.getId();
        id.setText(String.valueOf(s.getId()));
        formation.setText(String.valueOf(s.getNom()));

        date_debutf.setText(String.valueOf(s.getDate_debut()));
        date_finf.setText(String.valueOf(s.getDate_fin()));
        adresse.setText(String.valueOf(s.getAdresse()));
        tel.setText(String.valueOf(s.getTel()));
        prix.setText(String.valueOf(s.getPrix()));


        tfnom.setText(s.getNom());
        tformateur.setText(s.getFormateur());
        tfdescription.setText(s.getDescription());
        date_debutf.setText(String.valueOf(s.getDate_fin()));
        date_finf.setText(String.valueOf(s.getDate_fin()));
        tfadresse.setText(s.getAdresse());
        tfmail.setText(s.getEmail());
        tftel.setText(String.valueOf(s.getTel()));
        tfprix.setText(String.valueOf(s.getPrix()));


    }


    @FXML
    private void editAction(ActionEvent event) {


        formation cat = new formation(tfnom.getText(),tformateur.getText(),tfdescription.getText(),tfadresse.getText(),Double.parseDouble(tftel.getText()),tfmail.getText(),Date.valueOf(date_debut.getValue()),Date.valueOf(date_fin.getValue()),Double.parseDouble(tfprix.getText()));
        new Formation_Service().updatecat(cat,idC);


        readEvents(event);

    }

}