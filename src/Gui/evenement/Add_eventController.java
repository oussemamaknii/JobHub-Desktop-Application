/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.evenement;

import Entities.event;
import Services.evenementService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Add_eventController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfnbre_place;
    @FXML
    private DatePicker dfdate;
    @FXML
    private Button btn_addevent;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField tffile;
    @FXML
    private ImageView eventImg;

    private File selectedFile2;
    private FileChooser file;
    List<String> list = new ArrayList<>();
    private File getImageFile()
    {
        return this.selectedFile2=selectedFile2;
    }

    private void setImageFile(File file2)
    {
        this.selectedFile2=file2;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dfdate.setEditable(false);

        tfprix.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfprix.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        tfnbre_place.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfnbre_place.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        btn_addevent.setOnAction(e -> {
            if (testfields()) {
                LocalDate.now();
               event event = new event(tfnom.getText(),
                        dfdate.getValue(),
                        tfdesc.getText(),
                        Integer.parseInt(tfprix.getText()),
                        tfadresse.getText(),
                        null,
                        Integer.parseInt(tfnbre_place.getText()));
                event.setImage(list.get(0));
                File f=new File(getImageFile().getAbsolutePath());
                f.renameTo(new File("C:\\Users\\Ryaan\\Desktop\\master\\JobHub-Desktop-Application\\images"+list.get(0)));

                new evenementService().add(event);
                BoxBlur blur = new BoxBlur(3,3,3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Adding an event"));
                content.setBody(new Text("Your event\n : "+event.getNom()+"\nHas Been added successfully !"));
                JFXButton btn = new JFXButton("Done !");
                content.setActions(btn);
                pane.setEffect(blur);

            }
        });
    }

    public boolean testfields() {

        if (tfnom.getText().length() == 0) {
            tfnom.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfnom).play();
            return false;
        } else
            tfnom.setStyle(null);
        if (dfdate.getValue() == null || dfdate.getValue().isBefore(LocalDate.now())) {
            dfdate.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(dfdate).play();
            return false;
        } else
            dfdate.setStyle(null);
        if (tfdesc.getText().length() == 0) {
            tfdesc.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfdesc).play();
            return false;
        } else
            tfdesc.setStyle(null);
        if (tfprix.getText().length() == 0) {
            tfprix.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfprix).play();
            return false;
        } else
            tfprix.setStyle(null);

        if (tfadresse.getText().length() == 0) {
            tfadresse.setStyle("-fx-border-color: red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfadresse).play();
            return false;
        } else
            tfadresse.setStyle(null);


        if (tfnbre_place.getText().length() == 0) {
            tfnbre_place.setStyle("-fx-border-color :red ; -fx-border-width : 2px;");
            new animatefx.animation.Shake(tfnbre_place).play();
            return false;
        } else
            tfnbre_place.setStyle(null);

        return true;
    }


    @FXML
    private void filechooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Ryaan\\Desktop\\master\\JobHub-Desktop-Application\\images"));
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

    @FXML
    private void HandleButtonOption(ActionEvent event) {
    }


}
