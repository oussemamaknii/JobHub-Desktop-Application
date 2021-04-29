/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Company;
import Entities.company;
import Entities.user;
import Gui.User.updateProfileController;
import Services.CompanyService;
import Services.Offre_Emploi_Service;
import Services.Register;
import Utils.Connexion;
import Utils.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class updateCompanyController extends Controller implements Initializable {

    @FXML
    private TextField tfCompanyEmail;
    @FXML
    private TextField tfWebsite;
    @FXML
    private TextField tfCompanyAdress;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfCompanyName;
    @FXML
    private DatePicker foundedDate;
    @FXML
    private TextField tfCompanyPhone;
    @FXML
    private TextField tfFacebook;
    @FXML
    private Button updateCompany;
    @FXML
    private Label showCompanyName;
    @FXML
    private Label showWebsite;
    @FXML
    private Label showContactEmail;
    @FXML
    private Label showCompanyAdress;
    @FXML
    private Label showFoundedDate;
    @FXML
    private Label showCompanyPhone;
    @FXML
    private Label showCategory;
    @FXML
    private Label showFacebook;
    @FXML
    private Button deleteCompany;
    List<company> companyList= new ArrayList<company>();
    @FXML
    private ImageView companyImage;
    @FXML
    private Label msgg;
    FileChooser saveFileChooser = new FileChooser();
    File saveFile;
    File srcFile, destFile;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        companyList = showCompanies();
        for (company company :companyList
             ) {
            showCompanyName.setText(company.getCompanyName());
            showCategory.setText(company.getCategory());
            showCompanyAdress.setText(company.getCompanyAdress());
            showCompanyPhone.setText(String.valueOf(company.getContactPhone()));
            showFoundedDate.setText(String.valueOf(company.getFoundedDate()));
            showWebsite.setText(company.getWebsite());
            showFacebook.setText(company.getFacebookLink());
            showContactEmail.setText(company.getContactEmail());
            companyImage.setImage(new Image(getClass().getResource("/uploads/" + company.getCompanyImageName()).toExternalForm()));        }

       deleteCompany.setOnAction(e -> {

           new CompanyService().deleteCompany(Controller.getUserId());
           System.out.println("Company Deleted");
           msgg.setText("Company Deleted");
       });
        updateCompany.setOnAction(e -> {
      //      if (!testfields()) {

                company company = new company(tfCompanyName.getText(), tfCompanyEmail.getText(), tfCompanyAdress.getText(), foundedDate.getValue(),
                        tfWebsite.getText(), Integer.parseInt(tfCompanyPhone.getText()), tfCategory.getText(),tfFacebook.getText());
                new CompanyService().updateCompany(company);
        });

    }
    public boolean testfields(){

        if (tfCompanyPhone.getText().length() <6 || tfCompanyPhone.getText().contains(" /(?!-)(?!.*-)[A-Za-z]+(?<!-)/")) {
            tfCompanyPhone.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfCompanyPhone).play();
            return false;
        } else
            tfCompanyPhone.setStyle(null);

        return true;
    }

    public List<company> showCompanies() {
        Connection cnx = Connexion.getInstance().getConnection();
        List<company> companies = new ArrayList<company>();
        String request = "select * from company where user_id='"+Controller.getUserId()+"'";
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
                companies.add(company1);
                System.out.println(rs.getString("company_name"));
                company1.setCompanyImageName(rs.getString("company_image_name"));
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return companies;

    }
    public void updatePhotoProfilAction(Event event)
    {
        File file = saveFileChooser.showOpenDialog(null);
        try {
            //-------
            srcFile = file;
            String p = System.getProperty("user.dir") + "/src/uploads/" + srcFile.getName();
            copyFile(srcFile, new File(p));
        } catch (Exception ex) {
            Logger.getLogger(updateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
       // user up_user = new user();
       // up_user= connectedUser;
        //up_user.setImageName(srcFile.getName());
        //new Register().modifierUserPhoto(up_user);

    }

    @FXML
    private void uploadImage(ActionEvent event) {
        File file = saveFileChooser.showOpenDialog(null);
        if (file != null) {
            srcFile = file;
            if (srcFile != null) {
                try {
                    String p = System.getProperty("user.dir") + "/src/uploads/" + srcFile.getName();
                    System.out.println(p);
                    copyFile(srcFile, new File(p));
                } catch (IOException ex) {
                    Logger.getLogger(updateProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        try (
                FileChannel in = new FileInputStream(sourceFile).getChannel();
                FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }

}

    

