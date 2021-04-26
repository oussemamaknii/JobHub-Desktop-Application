package Interfaces;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ServiceF <T>{
   ArrayList<String> getCateg(String value);

   public void addformation(T entity);
   public ObservableList<T> getAll() ;
}
