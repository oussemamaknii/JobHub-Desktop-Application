package Interfaces;

import Entities.formation;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ServiceF <T>{




   public void addformation(T entity);


   public ArrayList<formation> getProduitsByNameOrID(String name);
   public   ObservableList<T> getAll() ;
}
