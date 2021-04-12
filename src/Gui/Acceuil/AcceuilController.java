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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        offbtn.setOnAction(e -> {
            try {
                Stage stage1 = (Stage) offbtn.getScene().getWindow();
                stage1.close();
                Stage primaryStage = new Stage();
                URL root_url = new File("src/Gui/Offre Emploi/AddOffre_Emploi.fxml").toURI().toURL();
                Parent parent = null;
                parent = FXMLLoader.load(root_url);

                primaryStage.setScene(new Scene(parent));
                primaryStage.setTitle("JobHub Application");
                primaryStage.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        dembtn.setOnAction(e -> {
            try {
                Stage stage1 = (Stage) dembtn.getScene().getWindow();
                stage1.close();
                Stage primaryStage = new Stage();
                URL root_url = new File("src/Gui/Demande/AddDemande.fxml").toURI().toURL();
                Parent parent = null;
                parent = FXMLLoader.load(root_url);

                primaryStage.setScene(new Scene(parent));
                primaryStage.setTitle("JobHub Application");
                primaryStage.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

}
