/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import Entities.user;
import Services.Register;
import Utils.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class updateProfileController extends Controller implements Initializable {
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfFirstName;
    @FXML
    private DatePicker tfDateOfBirth;
    @FXML
    private Button register;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfProfessionalTitle;
    @FXML
    private Label showFirstName;
    @FXML
    private Label showEmail;
    @FXML
    private Label showLastName;
    @FXML
    private Label showAdresse;
    @FXML
    private Label showProfessionalTitle;
    @FXML
    private Label showPhone;
    @FXML
    private Label showDateOfBirth;
    private user connectedUser;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectedUser = this.getUser();

        showFirstName.setText(connectedUser.getFirstName());
        showLastName.setText(connectedUser.getLastName());
        showAdresse.setText(connectedUser.getAdresse());
        showEmail.setText(connectedUser.getEmail());
        showPhone.setText(String.valueOf(connectedUser.getPhone()));
        showDateOfBirth.setText(String.valueOf(connectedUser.getDateOfBirth()));
        showProfessionalTitle.setText(connectedUser.getProfessionalTitle());
        
        user u = new user(tfEmail.getText(), tfPassword.getText(), tfFirstName.getText(), tfLastName.getText(),
                tfDateOfBirth.getValue(), tfAdresse.getText(), Integer.parseInt(tfPhone.getText()));
        new Register().updateprofile(u);}


    public user getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(user connectedUser) {
        this.connectedUser = connectedUser;
    }

}
