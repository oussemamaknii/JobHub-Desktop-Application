package Services;

import Entities.candidateResume;
import Entities.education;
import Interfaces.ICareer;
import Utils.Connexion;
import Utils.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CareerService extends Controller implements ICareer {
    Connection cnx = Connexion.getInstance().getConnection();

    @Override
    public void addResume(candidateResume resume) {
        String request1 = "INSERT into candidate_resume (resume_headline,skills,experience,user_id) VALUES (?,?,?,'"+Controller.getUserId()+"')";
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
    public void updateResume(candidateResume resume) {
        String request1 = "UPDATE candidate_resume resume_headline=?,skills=?,experience=? where user_id='"+Controller.getUserId()+"' ";
        try {
            PreparedStatement pst1 = cnx.prepareStatement(request1);
            pst1.setString(1, resume.getResumeHeadline());
            pst1.setString(2, resume.getSkills());
            pst1.setString(3, resume.getExperience());
            pst1.executeUpdate();
            System.out.println("Your resume has been updated");

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
    @Override
    public boolean deleteResume(int userId) {
        String req = "delete from candidate_resume where user_id=? ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, userId);
            pst.executeUpdate();
            System.out.println("Company Deleted");
            return true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteEducation(int resume_id) {
        String req = "delete from education where resume_id=? ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, resume_id);
            pst.executeUpdate();
            System.out.println("Education Deleted");
            return true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }




}
