/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Company;

import Entities.company;
import Services.CompanyService;
import Services.LoginService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class CompaniesController implements Initializable {

    @FXML
    private TableView<company> table;
    @FXML
    private TableColumn<?, ?> colCompanyName;
    @FXML
    private TableColumn<?, ?> colFoundedDate;
    @FXML
    private TableColumn<?, ?> colWebsite;
    @FXML
    private TableColumn<?, ?> colCountry;
    @FXML
    private TableColumn<?, ?> colRating;
    @FXML
    private TableColumn<?, ?> colFacebook;
    @FXML
    private TableColumn<?, ?> colContactEmail;
    @FXML
    private TableColumn<?, ?> colContactPhone;
    @FXML
    private TableColumn<?, ?> colCategory;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCompanies();
        table.setVisible(true);

    }
    public void showCompanies() {
        ObservableList<company> companies = new CompanyService().showCompanies();

        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colFoundedDate.setCellValueFactory(new PropertyValueFactory<>("foundedDate"));
        colWebsite.setCellValueFactory(new PropertyValueFactory<>("website"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("companyAdress"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colContactEmail.setCellValueFactory(new PropertyValueFactory<>("contactEmail"));
        colContactPhone.setCellValueFactory(new PropertyValueFactory<>("contactPhone"));
        colFacebook.setCellValueFactory(new PropertyValueFactory<>("facebookLink"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("stars"));

        table.setItems(companies);



    }


    
}
