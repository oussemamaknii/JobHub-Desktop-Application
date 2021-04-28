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
import Utils.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class LoginController extends Controller implements Initializable {
    Stage stage = new Stage();
    Scene scene;

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
    @FXML
    private CheckBox remember;
    @FXML
    private Button login;
    @FXML
    private Button forgotPass;
    @FXML
    private Label welcome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginService.readinifile(path, email, pw, remember);


    }
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        Connection cnx = Connexion.getInstance().getConnection();
        String req = "Select * from user where email=?";
        PreparedStatement prs = cnx.prepareStatement(req);
        prs.setString(1, email.getText());
        ResultSet rs = prs.executeQuery();
        if (!rs.next()) {
            msg.setText("Username incorrect");
        } else {
            if (BCrypt.checkpw(pw.getText(), rs.getString("password").substring(0, 2) + "a" + rs.getString("password").substring(3))) {
                if (!remember.isSelected()) {
                    String req1 = "Select id from user where email=? ";
                    PreparedStatement prs1 = cnx.prepareStatement(req1);
                    prs.setString(1, email.getText());
                    ResultSet res = prs.executeQuery();
                    while (res.next()) {
                        x = res.getInt("id");
                    }
                    user user = new user();
                    user.setId(x);
                    Controller.setUserId(x);
                    if (service.getUserByuserName(email.getText()).getRoles().equals("a:2:{i:0;s:10:\"ROLE_ADMIN\";i:1;s:9:\"ROLE_USER\";}")) {
                        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Backoffice/Backoffice.fxml"));
                        Scene scene = new Scene(root);
                        Node node = (Node) event.getSource();
                        stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        stage.setScene(scene);
                        stage.show();
                        stage.setResizable(false);
                        return;
                    } else { welcome.setText("Welcome " + this.getUser().getFirstName()+"! Navigate through the left Menu");

                    }
                    service.createiniFile(path, email.getText(), pw.getText());
                    System.out.println("Success");
                    String req2 = "Select id from user where email=? ";
                    PreparedStatement prs2 = cnx.prepareStatement(req2);
                    prs1.setString(1, email.getText());
                    ResultSet res1 = prs.executeQuery();
                    while (res1.next()) {
                        x = res1.getInt("id");
                    }
                    Controller.setUserId(x);

                } else {
                    msg.setText("Invalid Password!");
                }
            }

        }
    }

}


