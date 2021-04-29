package Services;

import Entities.company;
import Interfaces.IServiceCompany;
import Utils.Connexion;
import Utils.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyService implements IServiceCompany {
    Connection cnx = Connexion.getInstance().getConnection();
    @Override
    public void AddCompany(company comp) {
        String request = "INSERT INTO company (company_name,contact_email,company_adress,founded_date,website,contact_phone,category,facebook_link,user_id,status) VALUES (?,?,?,?,?,?,?,?,'"+Controller.getUserId()+"','1') ";
        try {
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, comp.getCompanyName());
            pst.setString(2, comp.getContactEmail());
            pst.setString(3, comp.getCompanyAdress());
            pst.setDate(4, Date.valueOf(comp.getFoundedDate()));
            pst.setString(5, comp.getWebsite());
            pst.setInt(6, comp.getContactPhone());
            pst.setString(7, comp.getCategory());
            pst.setString(8, comp.getFacebookLink());
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Your Company Profile has been created");
    }
    @Override
    public void updateCompany(company comp) {
        String request = "UPDATE company set company_name=?,contact_email=?,company_adress=?,founded_date=?,website=?,contact_phone=?,category=?,facebook_link=? where user_id='"+Controller.getUserId()+"' ";
        try {
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, comp.getCompanyName());
            pst.setString(2, comp.getContactEmail());
            pst.setString(3, comp.getCompanyAdress());
            pst.setDate(4, Date.valueOf(comp.getFoundedDate()));
            pst.setString(5, comp.getWebsite());
            pst.setInt(6, comp.getContactPhone());
            pst.setString(7, comp.getCategory());
            pst.setString(8, comp.getFacebookLink());
            pst.executeUpdate();
            System.out.println("Your Company Profile has been updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ObservableList<company> showCompanies() {
        ObservableList<company> companies = FXCollections.observableArrayList();
        String request = "select * from company";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                company company1 = new company();
                company1.setCompanyName(rs.getString("company_name"));
                company1.setFoundedDate(rs.getDate("founded_date").toLocalDate());
                company1.setWebsite(rs.getString("website"));
                company1.setCompanyAdress(rs.getString("company_adress"));
                company1.setCategory(rs.getString("category"));
                company1.setContactEmail(rs.getString("contact_email"));
                company1.setContactPhone(rs.getInt("contact_phone"));
                company1.setFacebookLink(rs.getString("facebook_link"));
                company1.setStars(rs.getDouble("stars"));
                companies.add(company1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return companies;
    }
    @Override
    public boolean deleteCompany(int userId) {
        String req = "delete from company where user_id=? ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, userId);
            pst.executeUpdate();
            System.out.println("Company Deleted");
            return true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }
}
