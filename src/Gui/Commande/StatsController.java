package Gui.Commande;

import Services.Offre_Emploi_Service;
import Services.ServiceCommande;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class StatsController implements Initializable {

    @FXML
    private PieChart pie;

    @FXML
    private ImageView close;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showstat();
    }

    private void showstat() {
        pie.setTitle("Orders Stats");
        pie.setData(new ServiceCommande().getdata());
        for (PieChart.Data data : pie.getData()) {
            data.nameProperty().set(data.getName() + " : " + (int) data.getPieValue() + "Non Payé");
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    JOptionPane.showMessageDialog(null, "Date = " + data.getName() + " Total Orders = " + (int) data.getPieValue());
                }
            });
        }
    }

    @FXML
    void handleClose(MouseEvent event) {
        if(event.getSource() == close){
            Stage stg = (Stage) close.getScene().getWindow();
            stg.close();
        }
    }
}
