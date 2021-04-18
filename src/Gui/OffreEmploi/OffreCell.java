package Gui.OffreEmploi;

import Entities.Offre_Emploi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OffreCell extends ListCell<Offre_Emploi> {


    @FXML
    private AnchorPane OffreCell;
    @FXML
    private Label titre;
    @FXML
    private Label poste;
    @FXML
    private Label location;
    @FXML
    private Label email;
    @FXML
    private Label categ;
    @FXML
    private Label maxsal;
    @FXML
    private Label minsal;
    @FXML
    private ImageView img;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Offre_Emploi student, boolean empty) {
        super.updateItem(student, empty);

        if(empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            img.setImage(new Image("job.png"));
            titre.setText(student.getTitre());
            poste.setText(student.getPoste());
            location.setText(student.getLocation());
            email.setText(student.getEmail());
            categ.setText(student.getCatname());
            maxsal.setText(String.valueOf(student.getMax_salary()));
            minsal.setText(String.valueOf(student.getMin_salary()));

            setText(null);
            setGraphic(OffreCell);
        }
}}


