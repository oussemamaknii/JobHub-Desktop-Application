package Utils;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import Utils.pdf;




/**
 *
 * @author Mintoua
 */


public class Mail {
    public Mail() {
    }


    public static void sendMail(String recepient ) throws Exception{
        System.out.println("preparing to send emaill!!!!!!!!!!!");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        System.out.println("preparing ");
        String myAccountEmail = "tunisgottalent@gmail.com";
        String password = "t20202020";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });




        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            msg.setSubject("Commande ");

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Commande ");

            //Attachment body part.
            System.out.println("env!!!");
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile("C:\\Users\\Yasmine\\Desktop\\yasmine\\ProjetJava\\CycleProJava\\CyclePro\\velo.pdf");
            System.out.println("env!!!125");
            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

