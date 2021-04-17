package Gui.Acceuil;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import jobhub_app.FXMain;

import java.net.URL;

public class FXloader {
    public FXloader(){}
    private Pane view;

    public Pane getPane(URL fileName) {

        try{
            view = new FXMLLoader().load(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
