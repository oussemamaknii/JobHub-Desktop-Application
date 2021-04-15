/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Acceuil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button offbtn;
    @FXML
    private Button dembtn;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        offbtn.setOnAction(e -> {
            Stage stage1 = (Stage) offbtn.getScene().getWindow();
            stage1.close();
            final double[] xOffset = new double[1];
            final double[] yOffset = new double[1];
            try {
                URL root_url = new File("src/Gui/Offre Emploi/AddOffre_Emploi.fxml").toURI().toURL();
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
        });

        exit.setOnAction(e -> {
            Stage stage1 = (Stage) exit.getScene().getWindow();
            stage1.close();
        });

        dembtn.setOnAction(e -> {
            final double[] xOffset = new double[1];
            final double[] yOffset = new double[1];
            Stage stage1 = (Stage) offbtn.getScene().getWindow();
            stage1.close();
            try {
                URL root_url = new File("src/Gui/Demande/AddDemande.fxml").toURI().toURL();
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
        });
    }

}
