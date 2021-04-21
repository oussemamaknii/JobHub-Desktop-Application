/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Career;

import Entities.candidateResume;
import Entities.education;
import Services.CareerService;
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
    @FXML
    private Button addCareer;
    @FXML
    private TextField tfInstitute;
    @FXML
    private DatePicker dateFrom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addCareer.setOnAction(e -> {
            education educatio1 = new education(tfCourse.getText(),tfInstitute.getText(),dateFrom.getValue(),dateTo.getValue());
            candidateResume resume1 = new candidateResume(tfResumeHeadline.getText(),tfSkills.getText(),tfExperience.getText());


            new CareerService().addCareer(educatio1,resume1);
        });



    }

}
