package Gui.evenement;

import Entities.event;
import Gui.Acceuil.AcceuilController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Services.evenementService;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.ResourceBundle;

import static Gui.evenement.List_eventController.idEvent;

public class edit_EventController implements Initializable {


    @FXML
    private TextField eventName;

    @FXML
    private TextField eventaddress;

    @FXML
    private TextArea eventdescription;

    @FXML
    private TextField eventprice;


    @FXML
    private TextField eventNbrPlace;

    @FXML
    private DatePicker eventdate;

    @FXML
    private ImageView eventImg;

    @FXML
    private Button fileBtn;

    @FXML
    private Button saveBtn;

    private File selectedFile2;
    private FileChooser file;
    List<String> list = new ArrayList<>();
    private int idEvent;
    private event oldEvent;
    private File getImageFile()
    {
        return this.selectedFile2=selectedFile2;
    }

    private void setImageFile(File file2)
    {
        this.selectedFile2=file2;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        evenementService es=new evenementService();
        try {
            event myEvent=es.fetchMyEvent(Gui.evenement.List_eventController.idEvent);
            oldEvent=myEvent;
            Image image =  new Image("file:///C:\\Users\\souso\\Desktop\\JobHub-Desktop-Application\\images\\"+myEvent.getImage());
            eventImg.setImage(image);
            eventImg.setStyle("-fx-background-color:transparent;");
            eventdate.setValue(myEvent.getDate());
            eventName.setText(myEvent.getNom());
            eventprice.setText(myEvent.getPrix().toString());
            eventaddress.setText(myEvent.getAdresse());
            eventNbrPlace.setText(myEvent.getNbre_place().toString());
            eventdescription.setText(myEvent.getDescription());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void updateEvent(ActionEvent event) throws SQLException, IOException {

        event updatedEvent=new event();
        updatedEvent.setNom(eventName.getText());
        updatedEvent.setAdresse(eventaddress.getText());
        updatedEvent.setDescription(eventdescription.getText());
        if(list.size()>0){
            updatedEvent.setImage(list.get(0));
            File f=new File(getImageFile().getAbsolutePath());
            f.renameTo(new File("C:\\Users\\souso\\Desktop\\JobHub-Desktop-Application\\images\\"+list.get(0)));

        }else{
            updatedEvent.setImage(oldEvent.getImage());
        }

        updatedEvent.setNbre_place(Integer.valueOf(eventNbrPlace.getText()));
        updatedEvent.setPrix(Integer.valueOf(eventprice.getText()));
        updatedEvent.setDate(eventdate.getValue());
        updatedEvent.setId(Gui.evenement.List_eventController.idEvent);


        evenementService es =new evenementService();
        es.UpdateEvent(updatedEvent);

        URL root_url = new File("src/Gui/Acceuil/Acceuil.fxml").toURI().toURL();
        Parent parent = FXMLLoader.load(root_url);
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        primaryStage.setTitle("JobHub Application");
        primaryStage.setScene(scene);
        primaryStage.show();


        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void uploadFIle(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\souso\\Desktop\\JobHub-Desktop-Application\\images"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            list.add(selectedFile.getName());
            Image image =  new Image("file:///"+selectedFile.getAbsolutePath());
            eventImg.setImage(image);
        } else {

            System.out.println("File is not Valid");
        }
        setImageFile(selectedFile);
    }

    public double xOffset,yOffset;
    @FXML
    void cancelUpdate(ActionEvent event) throws IOException {
        URL root_url = new File("src/Gui/Acceuil/Acceuil.fxml").toURI().toURL();
        Parent parent = FXMLLoader.load(root_url);
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        primaryStage.setTitle("JobHub Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
