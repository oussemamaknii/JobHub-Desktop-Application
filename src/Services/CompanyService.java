package Services;

import Entities.company;
import Interfaces.IServiceCompany;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyService implements IServiceCompany {
    Connection cnx = Connexion.getInstance().getConnection();
    @Override
    public void AddCompany(company comp) {


    }

    @Override
    public void updateCompany(company comp) {


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
}
