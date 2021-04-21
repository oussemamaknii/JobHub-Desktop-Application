package Services;

import Entities.user;
import Interfaces.IServiceRegister;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register implements IServiceRegister {

    Connection cnx = Connexion.getInstance().getConnection();


    @Override
    public void Register(user u) {
        String request = "INSERT INTO user (first_name,last_name,email,adresse,date_of_birth,phone,password,is_active,created_at,roles) VALUES (?,?,?,?,?,?,?,'1',SYSDATE(),'a:1:{i:0;s:9:\"ROLE_USER\";}') ";
        try {
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, u.getFirstName());
            pst.setString(2, u.getLastName());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getAdresse());
            pst.setDate(5, Date.valueOf(u.getDateOfBirth()));
            pst.setInt(6, u.getPhone());
            String pwd = BCrypt.hashpw(u.getPassword(),BCrypt.gensalt(13));
            pst.setString(7, pwd.substring(0,2)+"y"+pwd.substring(3));





            pst.executeUpdate();
            System.out.println("Your account has been created");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void updateprofile(user User) {

    }

    @Override
    public ObservableList<user> showUsers() {
        ObservableList<user> users = FXCollections.observableArrayList();
        String request = "select * from user";
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
}
