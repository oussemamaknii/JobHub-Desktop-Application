package Gui.Demande;

import Entities.Demande_Recrutement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DemandeCell extends ListCell<Demande_Recrutement> {

    @FXML
    private AnchorPane OffreCell;
    @FXML
    private Label offername;
    @FXML
    private ImageView img;
    @FXML
    private Label user;
    @FXML
    private Label stat;
    @FXML
    private ImageView tit;
    @FXML
    private ImageView pos;
    @FXML
    private ImageView em;
    @FXML
    private Label debdate;
    @FXML
    private Label expdate1;
    @FXML
    private ImageView imgexp;
    @FXML
    private ImageView imgdeb;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Demande_Recrutement student, boolean empty) {
        super.updateItem(student, empty);

        if(empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("DemandeListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            img.setImage(new Image("jobapp.png"));
            tit.setImage(new Image("job.png"));
            pos.setImage(new Image("user.png"));
            imgexp.setImage(new Image("enddat.png"));
            imgdeb.setImage(new Image("strtdat.png"));
            offername.setText(student.getOfftit());
            user.setText(student.getUsername());
            if (student.getStatus()){
                stat.setText("Treated");
                em.setImage(new Image("active.png"));
            }else{
                stat.setText("Not Treated ...");
                em.setImage(new Image("unactive.png"));
            }
            expdate1.setText(String.valueOf(student.getDate_expiration()));
            debdate.setText(String.valueOf(student.getDate_debut()));

            setText(null);
            setGraphic(OffreCell);
        }
    }
}
