package Interfaces;

import Entities.Category;
import javafx.collections.ObservableList;

public interface ServiceG <T>{

    public void addcateg(T entity);

    void supprimer(Category t);




    void updatecat(String titre, String descriptionc);

    public ObservableList<T> getAll() ;


}