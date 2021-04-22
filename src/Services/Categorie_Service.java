/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.categorie;
import Interfaces.IService;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ouma
 */
public abstract class Categorie_Service implements IService<categorie> {

    Connection cnx = Connexion.getInstance().getConnection();



    public void addcat(categorie t) {
        try{
            java.lang.String req="INSERT INTO  categorie(id,nom,couleur)"
                    +"VALUES(?,?,?)";
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setString(1,t.getId());
            pst.setString(2,t.getTitref());
            pst.setInt(3,t.getCouleur());

            pst.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(Categorie_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}