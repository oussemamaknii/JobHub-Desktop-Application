package Gui.Backoffice;

import Entities.Commande;
import Entities.Produit;
import Gui.Produit.AddProductController;
import Services.ServiceCommande;
import Services.ServiceProduit;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BackofficeController implements Initializable {
    @FXML
    private Button btnHome;

    @FXML
    private Button btnGUser;

    @FXML
    private Button btnGProduits;

    @FXML
    private Button btnGCommande;

    @FXML
    private Button btnGFormation;

    @FXML
    private Button btnGEvent;

    @FXML
    private Button btnGRating;

    @FXML
    private Pane pnStatus;

    @FXML
    private Label lbStatus;

    @FXML
    private Label lbStatusMin;

    @FXML
    private Button btnClose;

    @FXML
    private GridPane pnProducts;

    @FXML
    private GridPane pnCommandes;

    @FXML
    private GridPane pnUsers;

    @FXML
    private GridPane pnFormations;

    @FXML
    private GridPane pnHome;

    @FXML
    private FontAwesomeIcon faSearch;

    @FXML
    private TextField tfSearch;


    @FXML
    private FontAwesomeIcon faRefresh;

    @FXML
    private TextField tfSearchOrder;

    @FXML
    private TableView<Commande> tableOrder;

    @FXML
    private TableColumn<Commande, String> totalColOrder;

    @FXML
    private TableColumn<Commande, String> stateColOrder;

    @FXML
    private TableColumn<Commande, String> DateColOrder;

    @FXML
    private TableColumn<Commande, String> idUserColOrder;

    @FXML
    private GridPane productGrid;


    private List<Produit> productsList;
    ObservableList <Produit> dataList;



    Produit productTab = null ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataInPaneProduit();

    }

    @FXML
    private void handleClicks(ActionEvent event){

        if(event.getSource() == btnHome){
            lbStatusMin.setText("/Home");
            lbStatus.setText("Home");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(40,95,60), CornerRadii.EMPTY, Insets.EMPTY)));
            pnHome.toFront();
        }
        else
        if(event.getSource() == btnGUser){
            lbStatusMin.setText("/Home/GUsers");
            lbStatus.setText("Users");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnUsers.toFront();
        }
        else
        if(event.getSource() == btnGProduits){
            lbStatusMin.setText("/Home/Gestion Produits");
            lbStatus.setText("Produits");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43,99,63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnProducts.toFront();
        }
        else
        if(event.getSource() == btnGCommande){
            lbStatusMin.setText("/Home/Gestion Commande");
            lbStatus.setText("Commandes");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43,63,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnCommandes.toFront();
            searchOrder();
        }
        else
        if(event.getSource() == btnGFormation){
            lbStatusMin.setText("/Home/Gestion Formation");
            lbStatus.setText("Formation");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99,43,63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnFormations.toFront();
        }
        else
        if(event.getSource() == btnGEvent){
            lbStatusMin.setText("/Home/Gestion Evenements");
            lbStatus.setText("Evenements");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99,63,43), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else
        if(event.getSource() == btnGRating){
            lbStatusMin.setText("/Home/Gestion Ratings");
            lbStatus.setText("Ratings");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63,99,43), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    void handleClose(MouseEvent event) {
        if(event.getSource() == btnClose){
            System.exit(0);
        }
    }

    void loadDataInPaneProduit(){
        productsList = new ArrayList<>(getProducts());
        int column = 0;
        int row =1;

        try {
            for (Produit prod : productsList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Book.fxml"));

                HBox hBoxItem = fxmlLoader.load();
                BookController bookController = fxmlLoader.getController();
                bookController.setData(prod);
                if (column == 1){
                    column=0;
                    ++row;
                }

                productGrid.add(hBoxItem,column++,row);
                GridPane.setMargin(hBoxItem, new Insets(10));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addP(MouseEvent event) {
        try {

            final double[] xOffset = new double[1];
            final double[] yOffset = new double[1];
            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/Produit/AddProduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage= new Stage();
            parent.setOnMousePressed(e -> {
                xOffset[0] = e.getSceneX();
                yOffset[0] = e.getSceneY();
            });

            parent.setOnMouseDragged(e -> {
                stage.setX(e.getScreenX() - xOffset[0]);
                stage.setY(e.getScreenY() - yOffset[0]);
            });
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        loadDataInPaneProduit();
    }

    @FXML
    void refresh(MouseEvent event) {
        if(event.getSource()== faRefresh){
            loadDataInPaneProduit();
        }
    }

    @FXML
    void searchOrder(){
        try {
            ObservableList<Commande> commandes = new ServiceCommande().getAll();
            totalColOrder.setCellValueFactory(new PropertyValueFactory<>("totalPayment"));
            stateColOrder.setCellValueFactory(new PropertyValueFactory<>("state"));
            DateColOrder.setCellValueFactory(new PropertyValueFactory<>("date"));
            idUserColOrder.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            tableOrder.setItems(commandes);

            FilteredList<Commande> filteredData = new FilteredList<>(commandes,b->true);
            tfSearchOrder.textProperty().addListener((Observable,oldValue,newValue)->{
               filteredData.setPredicate(order->{
                   if (newValue == null || newValue.isEmpty()){
                       return  true;
                   }
                   String lowerCaseFilter = newValue.toLowerCase();
                   if (order.getDate().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                       return true; //filter matches name
                   }else if (String.valueOf(order.isState()).indexOf(lowerCaseFilter)!=-1){
                       return true; //filter matches ref
                   }
                   else if (String.valueOf(order.getIdUser()).indexOf(lowerCaseFilter)!=-1)
                       return true; // filter matches qty
                        else
                            return false; // does not match
               });
            });

            SortedList<Commande> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableOrder.comparatorProperty());
            tableOrder.setItems(sortedData);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Produit> getProducts(){
        List<Produit> lp = new ArrayList<>();
        ArrayList<Produit> products = new ServiceProduit().getAll();

        for (int i = 0; i < products.size(); i++){
            Produit product = new Produit();
            product.setName(products.get(i).getName());
            product.setPrice(products.get(i).getPrice());
            product.setImage("/Gui/Images/"+products.get(i).getImage());
            product.setDescription(products.get(i).getDescription());
            product.setQuantity(products.get(i).getQuantity());
            product.setRef(products.get(i).getRef());
            product.setId(products.get(i).getId());
            lp.add(product);
        }

        return lp;
    }

}
