package Services;

import Entities.candidateResume;
import Entities.education;
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
    Connection cnx = Connexion.getInstance().getConnection();
    public Statement ste;

    public LoginService() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String LoginAction(user u) throws SQLException {
        String req = "Select * from user where email=? and password=?";
        PreparedStatement prs = cnx.prepareStatement(req);
        prs.setString(1, u.getEmail());
        prs.setString(2, u.getPassword());
        rs = prs.executeQuery();
        return "good job you made it here!!";
    }

    public void createiniFile(String path, String user, String pass) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.put("Login data", "Username", user);
            wini.put("Login data", "Password", pass);
            wini.store();

        } catch (IOException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void readinifile(String path, TextField email, PasswordField pw, CheckBox remember) {
        File file = new File(path);
        if (file.exists()) {
            try {
                Wini wini = new Wini(new File(path));
                String username = wini.get("Login data", "Username");
                String password = wini.get("Login data", "Password");
                if ((username != null && !username.equals("")) && (password != null && !password.equals(""))) {
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

        String req = "Select email,password from user where email=? ";
        PreparedStatement prs = cnx.prepareStatement(req);
        prs.setString(1, email);
        rs = prs.executeQuery();
        return rs;
    }

    public ResultSet user(String username, String password) throws SQLException {
        String req = "Select * from user where email=? and password=?";
        PreparedStatement prs = cnx.prepareStatement(req);
        prs.setString(1, username);
        prs.setString(2, password);
        rs = prs.executeQuery();
        return rs;
    }

    public user getUserById(int id) {
        try {
            String req = "select * from user where id=?";
            user u = null;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new user(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString("roles")
                        , rs.getString("first_name"), rs.getString("last_name"), rs.getInt("phone")
                        , rs.getString("professional_title"), rs.getString("adresse"), rs.getString("image_name"),
                        rs.getDate("date_of_birth").toLocalDate());
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public candidateResume candidateResume(int id) {

        candidateResume g = new candidateResume();
        try {
            String req = "Select * from candidate_resume where user_id=? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {
                g.setId(rest.getInt("id"));
                g.setUser_id(rest.getInt("user_id"));
                g.setResumeHeadline(rest.getString("resume_headline"));
                g.setSkills(rest.getString("skills"));
                g.setExperience(rest.getString("experience"));


            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }
    public education education(int id) {

        education g = new education();
        try {
            String req = "Select * from education where resume_id=? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {
                g.setId(rest.getInt("id"));
                g.setResumeId(rest.getInt("resume_id"));
                g.setCourse(rest.getString("course"));
                g.setInstitute(rest.getString("institute"));
                g.setDateFrom(rest.getDate("date_from").toLocalDate());
                g.setDateTo(rest.getDate("date_to").toLocalDate());
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }

        public user getUserByuserName(String email) {
        try {
            String req = "select * from user where email=?";
            user u = null;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new user(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString("roles")
                        , rs.getString("first_name"), rs.getString("last_name"), rs.getInt("phone")
                        , rs.getString("professional_title"), rs.getString("adresse"), rs.getString("image_name")
                        , rs.getDate("date_of_birth").toLocalDate());
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
