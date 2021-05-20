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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class AddOffre_EmploiController implements Initializable {

    @FXML
    private TextField tfpost;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tffile;
    @FXML
    private TextField tfemil;
    @FXML
    private TextField tfmax;
    @FXML
    private TextField tftitle;
    @FXML
    private BorderPane loc;
    @FXML
    private DatePicker tfexp;
    @FXML
    private TextField tfmin;
    @FXML
    private ChoiceBox<String> choicecateg;
    @FXML
    private Button addoffer1;
    @FXML
    private Pane pane;
    @FXML
    private BorderPane map;
    @FXML
    private StackPane effect;
    AutoCompleteAddressField tfloc = new AutoCompleteAddressField();

    double lat;
    double lon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfexp.setEditable(false);
        effect.setDisable(true);
        autocomplete();
        tfmax.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfmax.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        showmap();

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

        choicecateg.setItems(FXCollections.observableArrayList(new Offre_Emploi_Service().getCateg()));

        addoffer1.setOnAction(e -> {
            if (testfields()) {
                Offre_Emploi offer = new Offre_Emploi(new Offre_Emploi_Service().getCategId(choicecateg.getValue()),
                        tftitle.getText(), tfpost.getText(), tfdesc.getText(), tfloc.getText(), "desc.png",
                        tfemil.getText(), LocalDate.now(), tfexp.getValue(), Integer.parseInt(tfmax.getText()),
                        Integer.parseInt(tfmin.getText())
                );
                new Offre_Emploi_Service().add(offer);
                effect.setDisable(false);
                BoxBlur blur = new BoxBlur(3, 3, 3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Adding A Job Offer"));
                content.setBody(new Text("Your Job\n Title : " + offer.getTitre() + " Post needed : " + offer.getPoste() + "\nHas Been added successfully !"));
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
        });
    }

    private void autocomplete() {

        tfloc.getEntryMenu().setOnAction((ActionEvent e) ->
        {
            ((MenuItem) e.getTarget()).addEventHandler(Event.ANY, (Event event) ->
            {
                if (tfloc.getLastSelectedObject() != null) {
                    tfloc.setText(tfloc.getLastSelectedObject().toString());
                }
            });
        });
        loc.setCenter(tfloc);
    }

    private void showmap() {

            WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        final URL urlGoogleMaps = getClass().getResource("map.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> e) {
                System.out.println(e.toString());
            }
        });/*
        update.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                lat = Double.parseDouble(lati.getText());
                lon = Double.parseDouble(longi.getText());

                System.out.printf("%.2f %.2f%n", lat, lon);

                webEngine.executeScript("" +
                        "window.lat = " + lat + ";" +
                        "window.lon = " + lon + ";" +
                        "document.goToLocation(window.lat, window.lon);"
                );
            }
        });*/
        map.setCenter(webView);

    }

    @FXML
    private void filechooser(ActionEvent event) {
        FileChooser c = new FileChooser();
        c.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files", "*.png"));
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