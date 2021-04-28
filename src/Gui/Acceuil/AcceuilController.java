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
    private Button home;
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
    private MenuItem stat;
    @FXML
    private MenuItem shopMI;

    @FXML
    private MenuItem cartMI;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        URL root_url1 = null;
        try {
            root_url1 = new File("src/Gui/Acceuil/firstpage.fxml").toURI().toURL();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        Pane view1 = new FXloader().getPane(root_url1);
        mainpane.setCenter(view1);

        home.setOnAction(e->{
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Acceuil/firstpage.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });

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

        stat.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/OffreEmploi/stat.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });

        shopMI.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Produit/Shop2.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });

        cartMI.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Produit/Card.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });

 	addevent.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/evenement/Add_event.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        listevent.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/evenement/List_event.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
        editevent.setOnAction(e -> {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/evenement/edit_event.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        });
    }
}
