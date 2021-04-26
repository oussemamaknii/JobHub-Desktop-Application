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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Button update;
    @FXML
    private AnchorPane pane;
    @FXML
    private StackPane effect;
    @FXML
    private Pane pane1;
    @FXML
    private ListView<Offre_Emploi> listdisplay;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfexp.setEditable(false);
        effect.setDisable(true);
        tfmax.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfmax.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tffile.setEditable(false);
        tfmin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfmin.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        AtomicReference<Offre_Emploi> off = new AtomicReference<>(new Offre_Emploi());
        showOffres();
        search();
        update2.setOnAction(e -> {
                    Offre_Emploi offre = listdisplay.getSelectionModel().getSelectedItem();
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
                    } else {
                        effect.setDisable(false);
                        BoxBlur blur = new BoxBlur(3, 3, 3);
                        JFXDialogLayout content = new JFXDialogLayout();
                        content.setHeading(new Text("Error"));
                        content.setBody(new Text("Select A Job So You Can Update \n The Job !"));
                        JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
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
                }
        );
        update.setOnAction(e -> {
            if (testfields()) {
                Offre_Emploi offer = new Offre_Emploi(new Offre_Emploi_Service().getCategId(choicecateg.getValue()),
                        tftitle.getText(), tfpost.getText(), tfdesc.getText(), tfloc.getText(), tffile.getText(),
                        tfemil.getText(), LocalDate.now(), tfexp.getValue(), Integer.parseInt(tfmax.getText()),
                        Integer.parseInt(tfmin.getText())
                );
                new Offre_Emploi_Service().updateoffre(offer, off);
                effect.setDisable(false);
                BoxBlur blur = new BoxBlur(3, 3, 3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Adding A Job Offer"));
                content.setBody(new Text("Your Job\n Title : " + offer.getTitre() + " Post needed : " + offer.getPoste() + "\nHas Been modified successfully !"));
                JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
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
                showOffres();
            }
        });

    }

    public void showOffres() {
        ObservableList<Offre_Emploi> offres = new Offre_Emploi_Service().getAll(1);
        listdisplay.setItems(offres);
        listdisplay.setCellFactory(studentListView -> new OffreCell());
    }

    @FXML
    private void filechooser(ActionEvent event) {
        FileChooser c = new FileChooser();
        c.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files", "*.jpg"));
        File f = c.showOpenDialog(null);
        if (f != null)
            tffile.setText(f.getAbsolutePath());
    }

    public boolean validateEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email);
        if (m.find() && m.group().equals(email)) {
            return true;
        } else {
            return false;
        }
    }

    public void search() {
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

    public boolean testfields() {

        if (tftitle.getText().length() == 0) {
            tftitle.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tftitle).play();
            return false;
        } else
            tftitle.setStyle(null);
        if (tfpost.getText().length() == 0) {
            tfpost.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfpost).play();
            return false;
        } else
            tfpost.setStyle(null);
        if (tfdesc.getText().length() == 0) {
            tfdesc.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfdesc).play();
            return false;
        } else
            tfdesc.setStyle(null);
        if (tfloc.getText().length() == 0) {
            tfloc.setStyle("-fx-border-color : red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfloc).play();
            return false;
        } else
            tfloc.setStyle(null);
        if (tfemil.getText().length() == 0 || !validateEmail(tfemil.getText())) {
            tfemil.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfemil).play();
            return false;
        } else
            tfemil.setStyle(null);
        if (tfmax.getText().length() == 0) {
            tfmax.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfmax).play();
            return false;
        } else
            tfmax.setStyle(null);
        if (tfmin.getText().length() == 0) {
            tfmin.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfmin).play();
            return false;
        } else
            tfmin.setStyle(null);
        if (tffile.getText().length() == 0) {
            tffile.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tffile).play();
            return false;
        } else
            tffile.setStyle(null);
        if (tfexp.getValue() == null || tfexp.getValue().isBefore(LocalDate.now())) {
            tfexp.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfexp).play();
            return false;
        } else
            tfexp.setStyle(null);
        if (choicecateg.getValue() == null) {
            choicecateg.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(choicecateg).play();
            return false;
        } else
            choicecateg.setStyle(null);
        return true;
    }

}
