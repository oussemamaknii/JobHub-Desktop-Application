/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Career;

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

import Entities.candidateResume;
import Entities.company;
import Services.CareerService;
import Services.CompanyService;
import Services.Offre_Emploi_Service;
import Utils.Connexion;
import Utils.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ryaan
 */
public class UpdateResumeController implements Initializable {

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
    private Button UpdateResume;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private TextField tfInstitute;
    @FXML
    private Button updateEducation;
    @FXML
    private Label showExperience;
    @FXML
    private Label showDateTo;
    @FXML
    private Label showResumeHeadline;
    @FXML
    private Label showSkills;
    @FXML
    private Label showCourse;
    @FXML
    private Label showInstitute;
    @FXML
    private Label showDateFrom;
    List<candidateResume> resumeList = new ArrayList<candidateResume>();
    @FXML
    private Button deleteResume;
    @FXML
    private Label msgg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        resumeList = showResume();
        for (candidateResume resume : resumeList
        ) {
           showResumeHeadline .setText(resume.getResumeHeadline());
            showExperience.setText(resume.getExperience());
            showSkills.setText(resume.getSkills());
        }
        System.out.println(resumeList);
        UpdateResume.setOnAction(e -> {
            //      if (!testfields()) {

            candidateResume resume = new candidateResume(tfResumeHeadline.getText(), tfSkills.getText(), tfExperience.getText());
            new CareerService().updateResume(resume);
            msgg.setText("Your resume has been updated");
        });
        deleteResume.setOnAction(e -> {

            new CompanyService().deleteCompany(Controller.getUserId());
            System.out.println("Resume Deleted");
            msgg.setText("Resume Deleted");
        });

    }

    public List<candidateResume> showResume() {
        Connection cnx = Connexion.getInstance().getConnection();
        List<candidateResume> resumes = new ArrayList<candidateResume>();
        String request = "select * from candidate_resume where user_id='" + Controller.getUserId() + "'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                candidateResume resume = new candidateResume();
                resume.setResumeHeadline(rs.getString("resume_headline"));
                resume.setSkills(rs.getString("skills"));
                resume.setExperience(rs.getString("experience"));
                resumes.add(resume);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return resumes;

    }
}
