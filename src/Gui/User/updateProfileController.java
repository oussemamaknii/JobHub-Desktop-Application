/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import Entities.user;
import Services.LoginService;
import Services.Register;
import Utils.Connexion;
import Utils.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Connection;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.event.Event;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

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
    FileChooser saveFileChooser = new FileChooser();
    File saveFile;
    File srcFile, destFile;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label mess;
    Connection con = Connexion.getInstance().getConnection();
    @FXML





    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        profileImage.setImage(new Image(getClass().getResource("/uploads/" + this.getUser().getImageName()).toExternalForm()));
        profileImage.setFitWidth(150);
        profileImage.setFitHeight(150);
        profileImage.setOnMouseClicked(this::updatePhotoProfilAction);
        mess.setText("Welcome " + this.getUser().getFirstName() + ", Please Update your profile");
        connectedUser = this.getUser();
        LoginService ser = new LoginService();
        showFirstName.setText(connectedUser.getFirstName());
        showLastName.setText(connectedUser.getLastName());
        showAdresse.setText(connectedUser.getAdresse());
        showEmail.setText(connectedUser.getEmail());
        showPhone.setText(String.valueOf(connectedUser.getPhone()));
        showDateOfBirth.setText(String.valueOf(connectedUser.getDateOfBirth()));
        showProfessionalTitle.setText(connectedUser.getProfessionalTitle());
        register.setOnAction(e -> {
            if (!testfields()) {
                user update1 = new user(tfEmail.getText(), tfPassword.getText(), tfFirstName.getText(), tfLastName.getText(),
                        tfDateOfBirth.getValue(), tfAdresse.getText(), Integer.parseInt(tfPhone.getText()), tfProfessionalTitle.getText());
                new Register().updateprofile(update1, this.getUser());
            }
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
    public void updatePhotoProfilAction(Event event)
    {
        File file = saveFileChooser.showOpenDialog(null);
        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            String imageName= uuidAsString + file.getName();
            String p = System.getProperty("user.dir") + "/src/uploads/" + imageName;
            copyFile(file, new File(p));
            user up_user = new user();
            up_user= connectedUser;
            up_user.setImageName(imageName);
            connectedUser.setImageName(imageName);
            this.getUser();
            new Register().modifierUserPhoto(up_user);
        } catch (Exception ex) {
            Logger.getLogger(updateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        try (
                FileChannel in = new FileInputStream(sourceFile).getChannel();
                FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }
    public user getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(user connectedUser) {
        this.connectedUser = connectedUser;
    }



}
