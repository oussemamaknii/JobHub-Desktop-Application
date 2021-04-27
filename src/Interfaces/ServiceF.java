package Interfaces;

import Entities.Category;
import Entities.formation;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ServiceF <T>{


   /**void supprimer(formation t);**/

   public void addformation(T entity);


   public ArrayList<formation> getProduitsByNameOrID(String name);
   public   ObservableList<T> getAll() ;
}
