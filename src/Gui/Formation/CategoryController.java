package Gui.Formation;
import Services.Categorie_Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.lang.Object;
import Entities.categorie;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoryController implements Initializable {
    public ObservableList<categorie> currentProduct = FXCollections.observableArrayList();
    @FXML
    private TextField tfnom;
@FXML
        private TextField tfcouleur;

        @FXML
        private Button addcateg;


    @FXML
    private TableView<categorie> tvBooks;
    @FXML
    private TableColumn<categorie, Integer> id;
    @FXML
    private TableColumn<categorie, String> nom;
    @FXML
    private TableColumn<categorie, String> couleur;




        @Override
        public void initialize(URL url, ResourceBundle rb) {


            ObservableList<categorie> list = (ObservableList<categorie>) new Categorie_Service ().getAll();
            id.setCellValueFactory(new PropertyValueFactory<categorie,Integer>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<categorie, String>("nom"));
            couleur.setCellValueFactory(new PropertyValueFactory<categorie, String>("couleur"));







            tvBooks.setItems(list);







            addcateg.setOnAction(e -> {


                categorie p=new categorie();

                p.setTitref(tfnom.getText());
                p.setCouleur(tfcouleur.getText());


                new Categorie_Service().addcateg(p);

            });
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


