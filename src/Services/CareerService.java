package Services;

import Entities.candidateResume;
import Entities.education;
import Interfaces.ICareer;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CareerService implements ICareer {
    Connection cnx = Connexion.getInstance().getConnection();

    @Override
    public void addCareer(education edu, candidateResume resume) {

        String request2 = "INSERT into education (resume_id,course,institute,date_from,date_to) VALUES (NULL,?,?,?,?)";
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
        String request1 = "INSERT into candidate_resume (resume_headline,skills,experience) VALUES (?,?,?)";
        try {
            PreparedStatement pst1 = cnx.prepareStatement(request1);
            pst1.setString(1, resume.getResumeHeadline());
            pst1.setString(2, resume.getSkills());
            pst1.setString(3, resume.getExperience());
            pst1.executeUpdate();
            System.out.println("Your resume has been created");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public ObservableList<candidateResume> showResumes() {
        ObservableList<candidateResume> resumes = FXCollections.observableArrayList();
        String request = "select * from candidate_resume";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                candidateResume candidateresumes1 = new candidateResume();
                candidateresumes1.setResumeHeadline(rs.getString("resume_headline"));
                candidateresumes1.setSkills(rs.getString("skills"));
                candidateresumes1.setExperience(rs.getString("experience"));
                resumes.add(candidateresumes1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return resumes;
    }

    @Override
    public ObservableList<education> showEducations() {
        ObservableList<education> educations = FXCollections.observableArrayList();
        String request = "select * from education";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(request);
            while (rs.next()) {
                education education1 = new education();
                education1.setCourse(rs.getString("course"));
                education1.setInstitute(rs.getString("institute"));
                education1.setDateFrom(rs.getDate("date_from").toLocalDate());
                education1.setDateTo(rs.getDate("date_to").toLocalDate());
                educations.add(education1);
            }
        } catch (SQLException e) {
            Logger.getLogger(Offre_Emploi_Service.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return educations;

    }
}
