package Interfaces;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author ouma
 */

public interface IService<T> {


    public void addcateg(T entity);


    public   ObservableList<T> getAll() ;



}
