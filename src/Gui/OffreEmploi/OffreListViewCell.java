package Gui.OffreEmploi;

import Entities.Offre_Emploi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OffreListViewCell extends ListCell<Offre_Emploi> {

    @FXML
    private Pane cell;
    @FXML
    private ImageView img;
    @FXML
    private Label lab;

    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(Offre_Emploi student, boolean empty) {
        super.updateItem(student, empty);

        if (empty || student == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getClassLoader().getResource("ListCell.fxml"));
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(mLLoader.getResources());

            Path imageFile = Paths.get("/logo.png");
            try {
                img.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            setText(null);
            setGraphic(cell);
        }

    }
}
