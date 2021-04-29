/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;


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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class ForgotPasswordController implements Initializable {
 //   public static final String ACCOUNT_SID = "ACf06d8ef396999213b853a5dd057af9e2";
   // public static final String AUTH_TOKEN = "a593a1983d6b04f2beab8f16ca81614e";
    @FXML
    private TextField email;
    @FXML
    private Label msg;
    public ResultSet rs;
    public String username,pass,mesg;
    public String y;
    Stage stage = new Stage();
    @FXML
    private Button sendEmail;
    @FXML
    private Label label;
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
    @FXML
    private void sendEmail(javafx.event.ActionEvent event) throws SQLException, MessagingException, IOException {
        if (email.getText().isEmpty()){ msg.setText("Please enter your email");  }
        else {
            Connection conn = Connexion.getInstance().getConnection();
            String req= "Select email,password from user where email=? ";
            PreparedStatement prs= conn.prepareStatement(req);
            prs.setString(1, email.getText());

            rs= prs.executeQuery();
            while (rs.next()){
                username= rs.getString("email");
                pass=rs.getString("password");
            }
            y = getSaltString();
            mesg="Your temporary password is : " + y + " ,Don't forget to update it";
            String req1 = "UPDATE user SET password=? where email='"+email.getText()+"'";
            PreparedStatement prs1= conn.prepareStatement(req1);
            String pwd = BCrypt.hashpw(y,BCrypt.gensalt(13));
            prs1.setString(1, pwd.substring(0,2)+"y"+pwd.substring(3));
            prs1.executeUpdate();
            System.out.println(pwd);
            System.out.println(y);
         //   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            String from ="jobhubwebsiteesprit@gmail.com";
            String pass="jobhub0000";
            String  to = email.getText();
            String host="smtp.gmail.com";
            String sub="Password Recovery";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtps.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            //get Session
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from,pass);
                }
            });
            //compose message
            MimeMessage mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress(username));
            mes.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            mes.setSubject(sub);
            mes.setText(mesg);
            Transport.send(mes);
            System.out.println("sent");
            //send message
            Transport.send(mes);
            System.out.println("Message sent successfully");
            label.setText("Message sent successfully");


        }
    }
}
