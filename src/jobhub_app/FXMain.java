/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobhub_app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author souso
 */
public class FXMain extends Application {
    public double xOffset,yOffset;

    @Override
    public void start(Stage primaryStage) throws IOException{
       // URL root_url = new File("src/Gui/Offre_Emploi.fxml").toURI().toURL();
        /*URL root_url = new File("src/Gui/Produit/Shop.fxml").toURI().toURL();
        Parent parent = FXMLLoader.load(root_url);
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("JobHub");
        primaryStage.show();*/
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Backoffice/Backoffice.fxml"));

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        primaryStage.setTitle("JOBHUBDASHBOARD");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    /*
        primaryStage.setTitle("TalkTo");
        primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
