/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Acceuil;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private BorderPane mainpane;
    @FXML
    private MenuItem addoffre;
    @FXML
    private MenuItem deloff;
    @FXML
    private MenuItem upoff;
    @FXML
    private MenuItem treatapps;
    @FXML
    private MenuItem userapps;
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private MenuItem updateProfile;
    @FXML
    private MenuItem resume;
    @FXML
    private MenuItem company;
    @FXML
    private MenuItem candidatesProfiles;
    @FXML
    private MenuItem companiesProfiles;
    @FXML
    private TextField showloggeduser;
    @FXML
    private MenuItem stat;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addoffre.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/OffreEmploi/AddOffre_Emploi.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        signup.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/User/register.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });

        exit.setOnAction(e -> {
            Stage stage1 = (Stage) exit.getScene().getWindow();
            stage1.close();
        });

        deloff.setOnAction(e->{
            URL root_url = null;
            try {
                root_url = new File("src/Gui/OffreEmploi/DeletOffreEmploi.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });

        treatapps.setOnAction(e->{
            URL root_url = null;
            try {
                root_url = new File("src/Gui/OffreEmploi/SeeApps.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });

        userapps.setOnAction(e->{
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Demande/AddDemande.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });

        upoff.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/OffreEmploi/UpdateOffer.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        resume.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Career/career.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        candidatesProfiles.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/User/ShowUsers.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        companiesProfiles.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Company/companies.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        company.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Company/addCompany.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        login.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/User/login.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        updateProfile.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/User/updateProfile.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
    }
}
