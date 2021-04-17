package Gui.Acceuil;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FXloader {
    public FXloader() {
    }

    private Pane view;

    public Pane getPane(URL filename) {
        try {
            view = new FXMLLoader().load(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
