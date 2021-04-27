package Gui.Commande;

import Entities.Commande;
import Entities.Offre_Emploi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class    ListCellCommande extends ListCell<Commande> {
    @FXML
    private AnchorPane CommadeCell;

    @FXML
    private Label ref;

    @FXML
    private Label total;

    @FXML
    private Label state;

    @FXML
    private Label date;

    @FXML
    private Label username;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Commande student, boolean empty) {
        super.updateItem(student, empty);

        if(empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ListCellCommande.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            ref.setText("B4"+ String.valueOf(student.getId())+"J4");
            total.setText(String.valueOf(student.getTotalPayment())+" DT");
            state.setText(String.valueOf(student.isState()));
            date.setText(student.getDate());
            username.setText("User" + String.valueOf(student.getIdUser()));


            setText(null);
            setGraphic(CommadeCell);
        }
    }

}
