package Services;

import Entities.user;
import Interfaces.IServiceRegister;
import Utils.Connexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Register implements IServiceRegister {

  Connection cnx = Connexion.getInstance().getConnection();


    @Override
    public void Register(user u) {
        String request = "INSERT INTO user (firstName,lastName,email,adresse,dateOfBirth,phone,password,isActive,createdAt) VALUES (?,?,?,?,?,?,?,'1',?) ";
        try {
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1,u.getFirstName());
            pst.setString(2,u.getLastName());
            pst.setString(3,u.getEmail());
            pst.setString(4, u.getAdresse());
            pst.setDate(5, (Date) u.getDateOfBirth());
            pst.setInt(6,u.getPhone());
            pst.setString(7,u.getPassword());
            pst.setInt(8,u.getIsActive());
            pst.setDate(9,(Date)u.getCreatedAt(LocalDate.now()) );



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
