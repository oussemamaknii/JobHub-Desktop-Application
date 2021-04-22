/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Company;

import Entities.company;
import Services.CompanyService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class AddCompanyController implements Initializable {

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
    private Button addCompany;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addCompany.setOnAction(e -> {
            if (testfields()) {


                company company1 = new company(tfCompanyName.getText(), tfCompanyEmail.getText(), tfCompanyAdress.getText(), foundedDate.getValue(),
                        tfWebsite.getText(), Integer.parseInt(tfCompanyPhone.getText()), tfCategory.getText(),tfFacebook.getText());
                new CompanyService().AddCompany(company1);}

        });

    }
    public boolean testfields(){
        if (tfCompanyName.getText().isEmpty()) {
            tfCompanyName.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfCompanyName).play();
            return false;
        } else
            tfCompanyName.setStyle(null);
        if (tfCompanyEmail.getText().isEmpty()) {
            tfCompanyEmail.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfCompanyEmail).play();
            return false;
        } else
            tfCompanyEmail.setStyle(null);
        if (tfCompanyPhone.getText().length() <6 || tfCompanyPhone.getText().contains(" /(?!-)(?!.*-)[A-Za-z]+(?<!-)/")) {
            tfCompanyPhone.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfCompanyPhone).play();
            return false;
        } else
            tfCompanyPhone.setStyle(null);
        if (tfCategory.getText().isEmpty()) {
            tfCategory.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfCategory).play();
            return false;
        } else
            tfCategory.setStyle(null);

        return true;

    }

    
}
