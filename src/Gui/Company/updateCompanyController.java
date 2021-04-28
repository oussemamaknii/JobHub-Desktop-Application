/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Company;
import Entities.company;
import Services.CompanyService;
import Utils.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       deleteCompany.setOnAction(e -> {
           new CompanyService().deleteCompany(Controller.getUserId());
       });

        updateCompany.setOnAction(e -> {
            if (!testfields()) {

                company company = new company(tfCompanyName.getText(), tfCompanyEmail.getText(), tfCompanyAdress.getText(), foundedDate.getValue(),
                        tfWebsite.getText(), Integer.parseInt(tfCompanyPhone.getText()), tfCategory.getText(),tfFacebook.getText(),Integer.parseInt(String.valueOf(Controller.getUserId())));
                new CompanyService().updateCompany(company, Controller.getUserId());}
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
}

    

