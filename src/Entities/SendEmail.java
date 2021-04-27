/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author toshiba
 */
public class SendEmail {


    public SendEmail(String receiverEmailID) throws Exception{
        Mail.sendMail(receiverEmailID);
    }

    public static void main(String[] args) {

        try {
            SendEmail s;
            s = new SendEmail("tpkdmta@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
