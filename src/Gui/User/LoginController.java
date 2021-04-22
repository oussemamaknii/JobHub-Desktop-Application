/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import Entities.user;
import Gui.Acceuil.FXloader;
import Services.LoginService;
import Utils.Connexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class LoginController implements Initializable {
    private BorderPane mainpane;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pw;
    private final String path = "src\\LoginData.ini";
    int x;
    LoginService service = new LoginService();
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginService.readinifile(path, email, pw);
    }

    private void login(ActionEvent event) throws SQLException, IOException {
        // Last solution
        if (service.getUserByuserName(email.getText()).getRoles().equals("a:2:{i:0;s:10:\"ROLE_ADMIN\";i:1;s:9:\"ROLE_USER\";}")) {
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Company/companies.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            mainpane.setCenter(view);
        }
        Connection cnx = Connexion.getInstance().getConnection();
        String req = "Select * from user where (email=?)";
        PreparedStatement prs = cnx.prepareStatement(req);
        prs.setString(1, email.getText());
        prs.setString(2, email.getText());
        ResultSet rs = prs.executeQuery();
        if (!rs.next()){
            msg.setText("Username incorrect");
        }
        else{
        if (BCrypt.checkpw(pw.getText(), rs.getString("password").substring(0, 2) + "a" + rs.getString("password").substring(3))) {
            service.createiniFile(path, email.getText(), pw.getText());
            System.out.println("Success");
            String req1 = "Select id from user where email=? ";
            PreparedStatement prs1 = cnx.prepareStatement(req1);
            prs.setString(1, email.getText());
            ResultSet res = prs.executeQuery();
            while (res.next()) {
                x = res.getInt("id");
            }
            user u = new user();
        } else {
            msg.setText("Invalid Password!");
        }
        }
        }
    }



