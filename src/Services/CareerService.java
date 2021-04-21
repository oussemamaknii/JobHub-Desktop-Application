package Services;

import Entities.candidateResume;
import Entities.education;
import Interfaces.ICareer;
import Utils.Connexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
