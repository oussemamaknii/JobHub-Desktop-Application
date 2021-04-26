/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import Utils.Connexion;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class ForgotPasswordController implements Initializable {
    public static final String ACCOUNT_SID = "ACf06d8ef396999213b853a5dd057af9e2";
    public static final String AUTH_TOKEN = "a593a1983d6b04f2beab8f16ca81614e";
    @FXML
    private TextField email;
    @FXML
    private Button resetPassword;
    @FXML
    private Label msg;
    public ResultSet rs;
    public String username,pass,mesg;
    public String y,z;
    public int x;
    Stage stage = new Stage();
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


    private void SendMail(ActionEvent event) throws AddressException, MessagingException, SQLException, IOException {
        if (email.getText().isEmpty()){ msg.setText("remarque : email vide");  }
        else if (!email.getText().matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{2}") ){ msg.setText("remarque : email non valide");  }
        else {
            Connection conn = Connexion.getInstance().getConnection();
            String req= "Select username,password from user where email=? ";
            PreparedStatement prs= conn.prepareStatement(req);
            prs.setString(1, email.getText());

            rs= prs.executeQuery();
            while (rs.next()){
                username= rs.getString("username");
                pass=rs.getString("password");
            }
            y = getSaltString();
            z = email.getText();
            mesg="Your code is : " + y;


            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21629903274"),
                    new PhoneNumber("+14156505681"), y).create();

            String from ="merghed.rayen@esprit.tn";
            String pass="flawnnabusegmail";
            String [] to={email.getText()};
            String host="mail.javatpoint.com";
            String sub="Password Recovery";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            //get Session
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from,pass);
                }
            });
            //compose message
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getText()));
            message.setSubject(sub);
            message.setText(mesg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");

            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/Acceuil/Acceuil.fxml"));
            Scene scene = new Scene(parent);
            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
            stage.close();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            scene.setFill(Color.TRANSPARENT);


        }
    }

    @FXML
    private void login(javafx.event.ActionEvent event) {
    }
}
