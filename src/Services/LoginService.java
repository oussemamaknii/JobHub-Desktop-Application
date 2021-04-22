package Services;

import Entities.user;
import Utils.Connexion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginService {
    PreparedStatement pst;
    ResultSet rs;
    Connection cnx= Connexion.getInstance().getConnection();
    public Statement ste;

    public LoginService() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String LoginAction(user u) throws SQLException{
        String req= "Select * from user where email=? and password=?";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, u.getEmail());
        prs.setString(2, u.getPassword());
        rs = prs.executeQuery();
        return "good job you made it here!!";
    }
    public void createiniFile(String path,String user,String pass){
        try {
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.put("Login data", "Username",user);
            wini.put("Login data", "Password",pass);
            wini.store();

        } catch (IOException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void readinifile(String path, TextField email, PasswordField pw, CheckBox remember){
        File file = new File(path);
        if(file.exists()){
            try {
                Wini wini = new Wini(new File(path));
                String username = wini.get("Login data","Username");
                String password = wini.get("Login data","Password");
                if ((username!=null && !username.equals(""))&&(password!=null && !password.equals("")) ){
                    email.setText(username);
                    pw.setText(password);
                    //               remember.setSelected(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public ResultSet info(String email) throws SQLException {

        String req= "Select email,password from user where email=? ";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, email);
        rs= prs.executeQuery();
        return rs;
    }




    public ResultSet userbyid(Integer id) throws SQLException {

        String req= "Select * from user where id=? ";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setInt(1, id);
        rs= prs.executeQuery();
        return rs;
    }

    public ResultSet user(String username , String password) throws SQLException {

        String req= "Select * from user where email=? and password=?";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, username);
        prs.setString(2, password);
        rs= prs.executeQuery();
        return rs;
    }



    public user getUserByuserName(String email) {
        try {
            String req = "select * from user where email=?";
            user u = null;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                u = new user(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getString("roles"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
}
