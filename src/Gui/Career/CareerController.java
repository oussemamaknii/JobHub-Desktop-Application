/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Career;

import Entities.candidateResume;
import Entities.company;
import Entities.education;
import Services.CareerService;
import Services.CompanyService;
import Services.Offre_Emploi_Service;
import Utils.Connexion;
import Utils.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class CareerController implements Initializable {

    @FXML
    private TextField tfSkills;
    @FXML
    private TextField tfCourse;
    @FXML
    private TextField tfExperience;
    @FXML
    private TextField tfResumeHeadline;
    @FXML
    private DatePicker dateTo;
    private Button addCareer;
    @FXML
    private TextField tfInstitute;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private Button addResume;
    @FXML
    private Label msgResume;
    @FXML
    private Label msgEducation;
    @FXML
    private Button addEducation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addResume.setOnAction(e -> {
            candidateResume resume = new candidateResume(tfResumeHeadline.getText(),tfSkills.getText(),tfExperience.getText());
            new CareerService().addResume(resume);
            msgResume.setText("Your Resume has been added");
        });

    }


}
