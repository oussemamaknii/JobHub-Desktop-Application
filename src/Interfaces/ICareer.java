package Interfaces;

import Entities.candidateResume;
import Entities.education;

public interface ICareer {
    public default void addCareer(education edu, candidateResume resume) {

    }
}
