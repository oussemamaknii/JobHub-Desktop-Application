package Services;

import Entities.user;
import Interfaces.IServiceRegister;
import Utils.Connexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Register implements IServiceRegister {

  Connection cnx = Connexion.getInstance().getConnection();


    @Override
    public void Register(user u) {
        String request = "INSERT INTO user (first_name,last_name,email,adresse,date_of_birth,phone,password,is_active,created_at,roles) VALUES (?,?,?,?,?,?,?,'1',SYSDATE(),'a:1:{i:0;s:9:\"ROLE_USER\";}') ";
        try {
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1,u.getFirstName());
            pst.setString(2,u.getLastName());
            pst.setString(3,u.getEmail());
            pst.setString(4, u.getAdresse());
            pst.setDate(5, Date.valueOf(u.getDateOfBirth()));
            pst.setInt(6,u.getPhone());
            pst.setString(7,u.getPassword());

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
    public List<user> showUsers() {
        return null;
    }
}
