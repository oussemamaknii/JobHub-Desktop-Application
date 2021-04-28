package Interfaces;

import Entities.candidateResume;
import Entities.education;
import javafx.collections.ObservableList;

public interface ICareer {
    public default void addResume(candidateResume resume) {

    }
    public boolean deleteResume(int userId);
    public default void updateResume(candidateResume resume) {

    }

    public default void addEducation(education educ) {

    }

    public ObservableList<candidateResume> showResumes();
    public ObservableList<education> showEducations();
}
