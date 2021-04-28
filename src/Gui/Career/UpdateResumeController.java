/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Career;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @FXML
    private Button deleteResume;
    @FXML
    private Label msgg;
    Connection cnx = Connexion.getInstance().getConnection();
    @FXML
    private Label resumeId;
    List<candidateResume> resumeList = new ArrayList<candidateResume>();
    List<education> educationList = new ArrayList<education>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        educationList = showEducation();
        for (education education : educationList
        ) {
            showCourse .setText(education.getCourse());
            showInstitute.setText(education.getInstitute());
            showDateFrom.setText(String.valueOf(education.getDateFrom()));
            showDateTo.setText(String.valueOf(education.getDateTo()));
        }
        System.out.println(educationList);
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
        updateEducation.setOnAction(e -> {
            //      if (!testfields()) {
            education edu = new education(tfCourse.getText(), tfInstitute.getText(), dateFrom.getValue(),dateTo.getValue());
            updateEducation(edu);
            msgg.setText("Your Education has been updated");
        });
        deleteResume.setOnAction(e -> {
            new CompanyService().deleteCompany(Controller.getUserId());
            System.out.println("Resume Deleted");
            msgg.setText("Resume Deleted");
            System.out.println(resumeId);
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
                resume.setId(rs.getInt("id"));
                resumes.add(resume);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return resumes;

    }
    public void addEducation(education edu) {

        String request2 = "INSERT into education (resume_id,course,institute,date_from,date_to) VALUES ('"+resumeId+"',?,?,?,?)";
        try{
            PreparedStatement pst2 = cnx.prepareStatement(request2);
            pst2.setString(1, edu.getCourse());
            pst2.setString(2,edu.getInstitute());
            pst2.setDate(3, Date.valueOf(edu.getDateFrom()));
            pst2.setDate(4,Date.valueOf(edu.getDateTo()));
            pst2.executeUpdate();
            System.out.println("Your Education has been added");

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void updateEducation(education education) {
        String request1 = "UPDATE education course=?,institute=?,date_from=?,date_to=? where resume_id='"+resumeId+"' ";
        try {
            PreparedStatement pst1 = cnx.prepareStatement(request1);
            pst1.setString(1, education.getCourse());
            pst1.setString(2, education.getInstitute());
            pst1.setString(3, String.valueOf(education.getDateFrom()));
            pst1.setString(4, String.valueOf(education.getDateTo()));
            pst1.executeUpdate();
            System.out.println("Your Education has been added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<education> showEducation() {
        Connection cnx = Connexion.getInstance().getConnection();
        List<education> educations = new ArrayList<education>();
        String request = "select * from education where resume_id='" + resumeId+ "'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                education education = new education();
                education.setCourse(rs.getString("course"));
                education.setInstitute(rs.getString("institute"));
                education.setDateFrom(LocalDate.parse(rs.getString("date_from")));
                education.setDateTo(LocalDate.parse(rs.getString("date_to")));
                educations.add(education);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return educations;
    }
}
