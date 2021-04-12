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

/**
 *
 * @author souso
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        URL root_url = new File("src/Gui/Acceuil/Acceuil.fxml").toURI().toURL();
        Parent parent = FXMLLoader.load(root_url);
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("JobHub Application");
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
