/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import Entities.candidateResume;
import Entities.education;
import Entities.user;
import Services.LoginService;
import Services.Register;
import Utils.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
        LoginService ser = new LoginService();
        candidateResume resume = ser.candidateResume(connectedUser.getId());
        showFirstName.setText(connectedUser.getFirstName());
        showLastName.setText(connectedUser.getLastName());
        showAdresse.setText(connectedUser.getAdresse());
        showEmail.setText(connectedUser.getEmail());
        showPhone.setText(String.valueOf(connectedUser.getPhone()));
        showDateOfBirth.setText(String.valueOf(connectedUser.getDateOfBirth()));
        showProfessionalTitle.setText(connectedUser.getProfessionalTitle());
        System.out.println(resume.getResumeHeadline());
        education edu = ser.education(resume.getId());
        System.out.println(edu.getCourse());

        register.setOnAction(e -> {
            if (testfields()) {
                user update1 = new user(tfEmail.getText(), tfPassword.getText(), tfFirstName.getText(), tfLastName.getText(),
                        tfDateOfBirth.getValue(), tfAdresse.getText(), Integer.parseInt(tfPhone.getText()),tfProfessionalTitle.getText());
                new Register().updateprofile(update1);}
        });
    }
    public boolean testfields() {
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
        return false;
    }

    public user getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(user connectedUser) {
        this.connectedUser = connectedUser;
    }

}