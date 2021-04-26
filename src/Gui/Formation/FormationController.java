package Gui.Formation;

import Entities.formation;
import Services.Formation_Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FormationController implements Initializable {

    @FXML
    private Button addformation;
    @FXML
    private ChoiceBox<String> choicecateg;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tformateur;
    @FXML
    private TextField tfdescription;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfprix;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicecateg.setItems(FXCollections.observableArrayList(new Formation_Service().getCateg(choicecateg.getValue())));

        addformation.setOnAction(e -> {
            formation p = new formation(new Formation_Service().getCategId(choicecateg.getValue()), tfnom.getText(),
                    tformateur.getText(),
                    tfdescription.getText(),
                    date_debut.getValue(),
                    date_fin.getValue(),
                    tfmail.getText(),
                    tftel.getText(),
                    tfprix.getText());
            new Formation_Service().addformation(p);

        });
    }
}
