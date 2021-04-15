/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Entities.Offre_Emploi;
import Services.Offre_Emploi_Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jobhub_app.FXMain;

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
    private Button addoffer1;
    @FXML
    private Button displaybtn;
    @FXML
    private Button exit;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choicecateg.setItems(FXCollections.observableArrayList(new Offre_Emploi_Service().getCateg()));

        addoffer1.setOnAction(e -> {
            Offre_Emploi offer = new Offre_Emploi(new Offre_Emploi_Service().getCategId(choicecateg.getValue()),
                    tftitle.getText(), tfpost.getText(), tfdesc.getText(), tfloc.getText(), tffile.getText(),
                    tfemil.getText(), LocalDate.now(), tfexp.getValue(), Integer.parseInt(tfmax.getText()),
                    Integer.parseInt(tfmin.getText())
            );
            new Offre_Emploi_Service().add(offer);
            afficherOffres();
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

        displaybtn.setOnAction(e->{
            Stage stage1 = (Stage) displaybtn.getScene().getWindow();
            stage1.close();
            afficherOffres();
        });
    }

    private void afficherOffres() {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Offre Emploi/DeletOffreEmploi.fxml"));

        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];
        try {
            URL root_url = new File("src/Gui/Offre Emploi/DeletOffreEmploi.fxml").toURI().toURL();
            Parent parent = FXMLLoader.load(root_url);
            Stage primaryStage = new Stage();
            Scene scene = new Scene(parent);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            parent.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset[0] = event.getSceneX();
                    yOffset[0] = event.getSceneY();
                }
            });
            parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset[0]);
                    primaryStage.setY(event.getScreenY() - yOffset[0]);
                }
            });
            primaryStage.setTitle("JobHub Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void home() throws IOException {
        new FXMain().start(new Stage());
    }

}