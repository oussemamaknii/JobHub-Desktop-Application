/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.OffreEmploi;

import javafx.event.Event;

import javafx.geometry.Side;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import Services.Offre_Emploi_Service;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showstat();
    }

    private void showstat() {
        pie.setTitle("Job Statistiques By Categorie");
        pie.setData(new Offre_Emploi_Service().getdata());
        for (PieChart.Data data : pie.getData()) {
            data.nameProperty().set(data.getName() + " : " + (int) data.getPieValue() + " Jobs");
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    JOptionPane.showMessageDialog(null, "Categorie = " + data.getName() + " Total jobs = " + (int) data.getPieValue());
                }
            });
        }
    }


}
