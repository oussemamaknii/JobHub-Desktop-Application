/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Acceuil;

import Services.LoginService;
import Utils.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class AcceuilController extends Controller implements Initializable {

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
    private MenuItem stat;
    LoginService service = new LoginService();
    @FXML
    private Button forgotPass;
    @FXML
    private Button createCompany;
    @FXML
    private MenuItem updateResume;




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
        createCompany.setOnAction(e->{
            if (this.getUser().getRoles().equals("a:2:{i:0;s:12:\"ROLE_COMPANY\";i:1;s:9:\"ROLE_USER\";}")){
                URL root_url = null;
                try {
                    root_url = new File("src/Gui/Company/updateCompany.fxml").toURI().toURL();
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                Pane view = new FXloader().getPane(root_url);
                mainpane.setCenter(view);
            }else{
                URL root_url = null;
                try {
                    root_url = new File("src/Gui/Company/addCompany.fxml").toURI().toURL();
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                Pane view = new FXloader().getPane(root_url);
                mainpane.setCenter(view);
            }
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
            if(this.getUser() == null) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Please Login to update Add or Update your Profile!");
                a.setTitle("Login Error");
                a.show();
            }
                else{
                    URL root_url = null;
                    try {
                        root_url = new File("src/Gui/Career/career.fxml").toURI().toURL();
                    } catch (MalformedURLException malformedURLException) {
                        malformedURLException.printStackTrace();
                    }
                    Pane view = new FXloader().getPane(root_url);
                    mainpane.setCenter(view);
                }
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
            if(this.getUser() == null){
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Please Login!");
                a.setTitle("Login Error");
                a.show();
            }
            if(!this.getUser().getRoles().equals("a:2:{i:0;s:12:\"ROLE_COMPANY\";i:1;s:9:\"ROLE_USER\";}"))
            {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Please Create company Profile!");
                a.setTitle("Redirection Error");
                a.show();
            }else{
                URL root_url = null;
                try {
                    root_url = new File("src/Gui/Company/updateCompany.fxml").toURI().toURL();
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                Pane view = new FXloader().getPane(root_url);
                mainpane.setCenter(view);
            }

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
            if(this.getUser() == null){
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Please Login to update your profile!");
                a.setTitle("Login Error");
                a.show();
            }else{
                URL root_url = null;
                try {
                    root_url = new File("src/Gui/User/updateProfile.fxml").toURI().toURL();
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                Pane view = new FXloader().getPane(root_url);
                mainpane.setCenter(view);
            }
        });
        forgotPass.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/User/forgotPassword.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        updateResume.setOnAction(e -> {
            if(this.getUser() == null){
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Please Login to update your Resume!");
                a.setTitle("Login Error");
                a.show();
            }else{
                URL root_url = null;
                try {
                    root_url = new File("src/Gui/Career/updateResume.fxml").toURI().toURL();
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                Pane view = new FXloader().getPane(root_url);
                mainpane.setCenter(view);
            }
        });
    }
}
