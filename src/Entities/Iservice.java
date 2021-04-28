/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
    * @author ASUS
 */
public interface Iservice <T>{
    void insert(T e);
    boolean update(T e,int id);
    boolean delete(int id);
    List<T> displayAll();
    
}
