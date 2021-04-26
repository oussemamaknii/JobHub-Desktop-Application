package Gui.OffreEmploi;

import Entities.Offre_Emploi;
import com.pdfjet.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OffreCell extends ListCell<Offre_Emploi> implements Initializable {


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
    private Label desc;
    @FXML
    private Label categ;
    @FXML
    private Label maxsal;
    @FXML
    private Label minsal;
    @FXML
    private ImageView img;
    @FXML
    private ImageView tit;
    @FXML
    private ImageView pos;
    @FXML
    private ImageView cat;
    @FXML
    private ImageView lo;
    @FXML
    private ImageView em;
    @FXML
    private Button pdf;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Offre_Emploi student, boolean empty) {
        super.updateItem(student, empty);

        if (empty || student == null) {

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
            em.setImage(new Image("email.png"));
            tit.setImage(new Image("title.png"));
            pos.setImage(new Image("post.png"));
            cat.setImage(new Image("categpng.png"));
            lo.setImage(new Image("loc.png"));
            titre.setText(student.getTitre());
            poste.setText(student.getPoste());
            desc.setText(student.getDescription());
            location.setText(student.getLocation());
            email.setText(student.getEmail());
            categ.setText(student.getCatname());
            maxsal.setText(String.valueOf(student.getMax_salary()));
            minsal.setText(String.valueOf(student.getMin_salary()));

            setText(null);
            setGraphic(OffreCell);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pdf.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
            fc.setTitle("Save to PDF");
            fc.setInitialFileName("untitled.pdf");
            File f = fc.showSaveDialog(new Stage());
            if (f != null) {
                String str = f.getAbsolutePath();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(str);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                try {

                    PDF pdf = new PDF(fos);
                    Page page = new Page(pdf, A4.PORTRAIT);
                    Table table = new Table();
                    List<List<Cell>> tabledate = new ArrayList<>();
                    Font f2 = new Font(pdf, CoreFont.HELVETICA_BOLD);
                    Font f1 = new Font(pdf, CoreFont.HELVETICA);

                    List<Cell> tablerow0 = new ArrayList<>();
                    Cell titr = new Cell(f2, "Job Offer information : " + titre.getText());
                    tablerow0.add(titr);
                    tabledate.add(tablerow0);

                    List<Cell> tablerow = new ArrayList<>();
                    Cell cell = new Cell(f2, "titre");
                    tablerow.add(cell);
                    Cell cell1 = new Cell(f2, "poste");
                    tablerow.add(cell1);
                    Cell cell2 = new Cell(f2, "description");
                    tablerow.add(cell2);
                    Cell cell3 = new Cell(f2, "location");
                    tablerow.add(cell3);
                    Cell cell4 = new Cell(f2, "email");
                    tablerow.add(cell4);
                    Cell cell5 = new Cell(f2, "category");
                    tablerow.add(cell5);
                    Cell cell6 = new Cell(f2, "max salary");
                    tablerow.add(cell6);
                    Cell cell7 = new Cell(f2, "min salary");
                    tablerow.add(cell7);
                    tabledate.add(tablerow);
                    List<Cell> tablerow1 = new ArrayList<>();
                    Cell cel = new Cell(f1, titre.getText());
                    tablerow1.add(cel);
                    Cell cel1 = new Cell(f1, poste.getText());
                    tablerow1.add(cel1);
                    Cell cel2 = new Cell(f1, desc.getText());
                    tablerow1.add(cel2);
                    Cell cel3 = new Cell(f1, location.getText());
                    tablerow1.add(cel3);
                    Cell cel4 = new Cell(f1, email.getText());
                    tablerow1.add(cel4);
                    Cell cel5 = new Cell(f1, categ.getText());
                    tablerow1.add(cel5);
                    Cell cel6 = new Cell(f1, maxsal.getText());
                    tablerow1.add(cel6);
                    Cell cel7 = new Cell(f1, minsal.getText());
                    tablerow1.add(cel7);
                    tabledate.add(tablerow1);
                    table.setData(tabledate);
                    table.drawOn(page);
                    pdf.close();
                    fos.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}


