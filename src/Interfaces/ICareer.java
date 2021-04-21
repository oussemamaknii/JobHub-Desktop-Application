package Interfaces;

import Entities.candidateResume;
import Entities.education;
import javafx.collections.ObservableList;

public interface ICareer {
    public default void addCareer(education edu, candidateResume resume) {

    }
    public ObservableList<candidateResume> showResumes();
    public ObservableList<education> showEducations();
}
