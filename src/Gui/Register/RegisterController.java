/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Register;

import Entities.user;
import Services.Register;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
    private TextField tfAdresse;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        register.setOnAction(e -> {
            if (testfields()) {


            user register1 = new user(tfEmail.getText(), tfPassword.getText(), tfFirstName.getText(), tfLastName.getText(),
                    tfDateOfBirth.getValue(), tfAdresse.getText(), Integer.parseInt(tfPhone.getText()));
            new Register().Register(register1);}

        });

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
