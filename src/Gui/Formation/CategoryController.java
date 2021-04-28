package Gui.Formation;
import Entities.formation;
import Services.Categorie_Service;
import Services.Formation_Service;
import Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.lang.Object;
import Entities.Category;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import org.controlsfx.control.Notifications;

public class CategoryController implements Initializable {
    public ObservableList<Category> currentProduct = FXCollections.observableArrayList();

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tfcouleur;

        @FXML
        private Button addcateg;
    @FXML
    private Button btnremove;
    @FXML
    private Button editbtn;


    @FXML
    private TableView<Category> tvBooks;
    @FXML
    private TableColumn<Category, Integer> id;
    @FXML
    private TableColumn<Category, String> nom;
    @FXML
    private TableColumn<Category, String> description;

    int idC;




    @Override
        public void initialize(URL url, ResourceBundle rb) {


            ObservableList<Category> list = (ObservableList<Category>) new Categorie_Service ().getAll();
            id.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<Category, String>("titre"));
            description.setCellValueFactory(new PropertyValueFactory<Category, String>("descriptionc"));
            tvBooks.setItems(list);
            addcateg.setOnAction(e -> {


                Category p=new Category();
                p.setDescriptionc(tfdescription.getText());
                p.setTitre(tfnom.getText());
                p.setCouleur(tfcouleur.getText());


                new Categorie_Service().addcateg(p);
                readEvents(e);

            });

        }
    private void readEvents(ActionEvent event) {


        ObservableList<Category> list = (ObservableList<Category>) new Categorie_Service().getAll();
        id.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));

        nom.setCellValueFactory(new PropertyValueFactory<Category, String>("titre"));


        description.setCellValueFactory(new PropertyValueFactory<Category, String>("descriptionc"));

        tvBooks.setItems(list);

    }
    @FXML
    private void deleteAction(MouseDragEvent event) {

    }

    @FXML
    private void removeAction(ActionEvent event) {
        Category s = tvBooks.getSelectionModel().getSelectedItem();
        Categorie_Service crs = new Categorie_Service();
        crs.supprimer(s);
        Notifications notificationBuild = Notifications.create()
                .title("Traitement category")
                .text("la categorie a été supprimé avec succes")
                .graphic(null)
                //.hideAfter(Duration.Hours(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("click here");
                    }

                });
        readEvents(event);


    }

    @FXML
    private void updateAction(ActionEvent event) {


        Category s = tvBooks.getSelectionModel().getSelectedItem();
        idC = s.getId();
        id.setText(String.valueOf(s.getId()));
        nom.setText(String.valueOf(s.getTitre()));
        description.setText(String.valueOf(s.getDescriptionc()));

        tfcouleur.setText(s.getCouleur());
        tfdescription.setText(s.getDescriptionc());
        tfnom.setText(s.getTitre());

    }


    @FXML
    private void editAction(ActionEvent event) {
        Category cat = new Category(tfnom.getText(),tfdescription.getText(),tfcouleur.getText());
        new Categorie_Service().updatecat(cat,idC);

        /*Notifications notificationBuild = Notifications.create()
                .title("Traitement category")
                .text("la category a été modifé avec succes")
                .graphic(null)
                //.hideAfter(Duration.Hours(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("click here");
                    }

                });*/

        readEvents(event);

    }





}


/**
    @FXML
    private void removeAction(ActionEvent event) {
        Category s = tvBooks.getSelectionModel().getSelectedItem();
        Categorie_Service crs = new Categorie_Service();
        crs.supprimer(s);
        Notifications notificationBuild = Notifications.create()
                .title("Traitement categorie")
                .text("la salle a été supprimé avec succes")
                .graphic(null)
                //.hideAfter(Duration.Hours(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("click here");
                    }

                });
        notificationBuild.show();
        readEvents(event);

    }

    @FXML
    private void updateAction(ActionEvent event) {


        Category s = tvBooks.getSelectionModel().getSelectedItem();

        nom.setText(String.valueOf(s.getTitre()));
        description.setText(String.valueOf(s.getDescriptionc()));

    }






    @FXML
    private void editAction(ActionEvent event) {




        String titre = String.valueOf(Integer.parseInt(titre.getText()));

        Categorie_Service s = new Categorie_Service();

        s.updatecat(titre);
        Notifications notificationBuild = Notifications.create()
                .title("Traitement salle")
                .text("la categorie a été modifé avec succes")
                .graphic(null)
                //.hideAfter(Duration.Hours(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("click here");
                    }

                });
        notificationBuild.show();
        readEvents(event);


    }




    }

           /** @FXML
            private void afficherPersonnes(ActionEvent rb) {
                new Categorie_Service().getAll()
                        .stream()
                        .forEach(p-> {
                            lbResult.setText(lbResult.getText()+"\n"+p.toString());
                        });**/

      /**  public  ObservableList<categorie> getAll() {

            ObservableList<categorie> categorie= FXCollections.observableArrayList();
            String req = "SELECT * FROM category";
            try {
                Statement st = cnx.createStatement();
                ResultSet rst = st.executeQuery(req);

                while (rst.next()){
                    categorie s= new categorie();
                    s.setId(rst.getString("id"));
                    s.setTitref(rst.getString("category"));
                    s.setCouleur(rst.getInt("couleur"));

                    categorie.addcateg(s);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Categorie_Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            return (categorie);
        }**/



/**     @Override
     public list<categorie> readAll() throws SQLException {
         list<categorie> arr=new ArrayList<>();
         ste=cnx.createStatement();
         ResultSet rs=ste.executeQuery("select * from categorie");
         while (rs.next()) {
             int id=rs.getInt("id");
             String nom=rs.getString("nom");
             String descreption=rs.getString("descreption");

             categorie p=new categorie(id, nom, couleur);
             arr.add(p);
         }
         return arr;
     }
}
 private void list() {
     try {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Offre Emploi/Category.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("JobHub Application");
         stage.setScene(new Scene(root1));
         stage.show();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }**/

      /**  @FXML
        private void afficherPersonnes(ActionEvent event) {
            list.setText(new Categorie_Service().getAll().toString());
        }**/


