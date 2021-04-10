/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import Entities.Offre_Emploi;
import Services.Offre_Emploi_Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class DeletOffreEmploiController implements Initializable {

    @FXML
    private TreeTableColumn<Offre_Emploi, String> coltitre;
    @FXML
    private TreeTableColumn<Offre_Emploi, String> colposte;
    @FXML
    private TreeTableColumn<Offre_Emploi, String> coldesc;
    @FXML
    private TreeTableColumn<Offre_Emploi, String> colloc;
    @FXML
    private TreeTableColumn<Offre_Emploi, String> colemail;
    @FXML
    private TreeTableColumn<Offre_Emploi, LocalDate> colexp;
    @FXML
    private TreeTableColumn<Offre_Emploi, Integer> colid;
    @FXML
    private Button deletebtn;
    @FXML
    private TreeTableView<Offre_Emploi> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Offre_Emploi_Service()
                .getAll()
                .stream()
                .forEach(p -> {
                    TreeItem<Offre_Emploi> item = new TreeItem<Offre_Emploi>(p);
                    table.setRoot(item);
                    coltitre.setCellValueFactory(new TreeItemPropertyValueFactory<>("titre"));
                    colposte.setCellValueFactory(new TreeItemPropertyValueFactory<>("poste"));
                    coldesc.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
                    colloc.setCellValueFactory(new TreeItemPropertyValueFactory<>("location"));
                    colemail.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
                    colexp.setCellValueFactory(new TreeItemPropertyValueFactory<>("date_expiration"));
                    colid.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
                });
        colid.setVisible(false);
    }
}
