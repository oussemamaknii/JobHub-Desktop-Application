package Services;

import Entities.user;
import Gui.User.updateProfileController;
import Interfaces.IServiceRegister;
import Utils.Connexion;
import Utils.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register extends Controller implements IServiceRegister {


    Connection cnx = Connexion.getInstance().getConnection();


    @Override
    public void Register(user u) {
        String request = "INSERT INTO user (first_name,last_name,email,adresse,date_of_birth,phone,password,is_active,created_at,roles,image_name) VALUES (?,?,?,?,?,?,?,'1',SYSDATE(),'a:1:{i:0;s:9:\"ROLE_USER\";}',?) ";
        try {
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, u.getFirstName());
            pst.setString(2, u.getLastName());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getAdresse());
            pst.setDate(5, Date.valueOf(u.getDateOfBirth()));
            pst.setInt(6, u.getPhone());
            String pwd = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13));
            pst.setString(7, pwd.substring(0, 2) + "y" + pwd.substring(3));
            pst.setString(8, u.getImageName());
            pst.executeUpdate();
            System.out.println("Your account has been created");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateprofile(user u, user u1) {
        String request1 = "UPDATE user SET first_name=?,last_name=?,date_of_birth=?,email=?,password=?,adresse=?,phone=?,professional_title=? where id='" + u1.getId() + "'";
        try {
            PreparedStatement pst = cnx.prepareStatement(request1);
            pst.setString(1, u.getFirstName());
            pst.setString(2, u.getLastName());
            pst.setDate(3, Date.valueOf(u.getDateOfBirth()));
            pst.setString(4, u.getEmail());
            String pwd = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(13));
            pst.setString(5, pwd.substring(0, 2) + "y" + pwd.substring(3));
            pst.setString(6, u.getAdresse());
            pst.setInt(7, u.getPhone());
            pst.setString(8, u.getProfessionalTitle());

            pst.executeUpdate();
            System.out.println("Your account has been updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ObservableList<user> showUsers() {
        ObservableList<user> users = FXCollections.observableArrayList();
        String request = "select * from user where roles not like 'a:2:{i:0;s:10:\"ROLE_ADMIN\";i:1;s:9:\"ROLE_USER\";}'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                user user1 = new user();
                user1.setFirstName(rs.getString("first_name"));
                user1.setLastName(rs.getString("last_name"));
                user1.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                user1.setAdresse(rs.getString("adresse"));
                user1.setPhone(rs.getInt("phone"));
                user1.setProfessionalTitle(rs.getString("professional_title"));
                users.add(user1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return users;
    }

    @Override
    public void modifierUserPhoto(user u) {
        try {
            String req = "update user set image_name=?, updated_at=? where id=? ";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, u.getImageName());
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            pre.setTimestamp(2, date);
            pre.setInt(3, u.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(updateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
