/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import Entities.user;
import Services.Register;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.PlaceDetails;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.walkercrou.places.Place;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfFirstName;
    @FXML
    private DatePicker tfDateOfBirth;
    @FXML
    private Button register;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfPhone;

    private final AutoCompleteAddressField Place1 = new AutoCompleteAddressField();

    @FXML
    private VBox root;
    @FXML
    private Label msgg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Place1.setPromptText("Place");

        Place1.getEntryMenu().setOnAction((ActionEvent e)
                -> {
            ((MenuItem) e.getTarget()).addEventHandler(Event.ANY, (Event event)
                    -> {
                if (Place1.getLastSelectedObject() != null) {
                    Place1.setText(Place1.getLastSelectedObject().toString());
                    PlaceDetails place = AutoCompleteAddressField.getPlace((AutoCompleteAddressField.AddressPrediction) Place1.getLastSelectedObject());
                    if (place != null) {
                        Place1.setText(Place1.getLastSelectedObject().toString());
                    } else {
                        Place1.clear();
                    }
                }
            });
        });
        System.out.println("here");

        register.setOnAction(e -> {

         //   if (testfields()) {
            user register1 = new user(tfEmail.getText(), tfPassword.getText(), tfFirstName.getText(), tfLastName.getText(),
                    tfDateOfBirth.getValue(), Place1.getText(), Integer.parseInt(tfPhone.getText()));
            System.out.println(register1);
            new Register().Register(register1);
            msgg.setText("Your Account has been Created! Welcome to Jobhub");
        });
        root.getChildren().addAll(Place1);

    }
    public boolean testfields(){
        if (tfFirstName.getText().isEmpty()) {
            tfFirstName.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfFirstName).play();
            return false;
        } else
            tfFirstName.setStyle(null);
        if (tfLastName.getText().isEmpty()) {
            tfLastName.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfLastName).play();
            return false;
        } else
            tfLastName.setStyle(null);
        if (tfPassword.getText().length() < 6) {
            tfPassword.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfPassword).play();
            return false;
        } else
            tfPassword.setStyle(null);
        if (tfPhone.getText().contains(" /(?!-)(?!.*-)[A-Za-z]+(?<!-)/")) {
            tfPhone.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfPhone).play();
            return false;
        } else
            tfPhone.setStyle(null);
        if (tfEmail.getText().contains(" /[A-Z]/")) {
            tfEmail.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfEmail).play();
            return false;
        } else
            tfPhone.setStyle(null);

        return true;

    }

}
