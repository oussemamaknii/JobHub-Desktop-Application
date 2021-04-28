package Interfaces;

import Entities.Category;
import Entities.formation;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ServiceF <T>{


   /**void supprimer(formation t);**/

   public void addformation(T entity);
    public void updatecat(formation cat, int id);

   public   ObservableList<T> getAll() ;
}
