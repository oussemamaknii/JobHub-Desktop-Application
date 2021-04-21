/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import Entities.user;
import Services.Register;
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
public class ShowUsersController implements Initializable {


    @FXML
    private TableView<user> table;
    @FXML
    private TableColumn<?, ?> colFirstName;
    @FXML
    private TableColumn<?, ?> colLastName;
    @FXML
    private TableColumn<?, ?> colDateOfBirth;
    @FXML
    private TableColumn<?, ?> colAdress;
    @FXML
    private TableColumn<?, ?> colPhone;
    @FXML
    private TableColumn<?, ?> colProfessionalTitle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsers();
        table.setVisible(true);

    }
    public void showUsers() {
        ObservableList<user> users = new Register().showUsers();

        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colAdress.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colProfessionalTitle.setCellValueFactory(new PropertyValueFactory<>("professionalTitle"));
        table.setItems(users);
    }

}
