package Gui.Commande;

import Entities.Commande;
import Entities.Produit;
import Services.ServiceCommande;
import Services.ServiceProduit;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class CommandeController implements Initializable {

    @FXML
    private TableView<Commande> tableViewCom;

    @FXML
    private TableColumn<?, ?> numCommande;

    @FXML
    private TableColumn<?, ?> totalPaiement;

    @FXML
    private TableColumn<?, ?> state;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> idUser;

    @FXML
    private TextField tfstate;
    @FXML
    private Button removeBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button setUpdateBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicReference<Commande> commandeAtR = new AtomicReference<>(new Commande());
        showCommandes();

        updateBtn.setOnAction(e->{
            Commande cmd = tableViewCom.getSelectionModel().getSelectedItem();
            if(cmd != null){
                commandeAtR.set(cmd);
                tfstate.setText(String.valueOf(cmd.isState()));
               /* tfdesc.setText(produit.getDescription());
                tfref.setText(produit.getRef());
                tfprice.setText(String.valueOf(produit.getPrice()));
                tfimage.setText(produit.getImage());
                tfquantity.setText(String.valueOf(produit.getQuantity()));*/
            }
        });
        setUpdateBtn.setOnAction(e->{
            ServiceCommande updateSer = new ServiceCommande();
            try {
                if(updateSer.update(Integer.parseInt(numCommande.getText()))){
                    showCommandes();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        });

    }

    public void showCommandes() {
        try {
            ObservableList<Commande> commandes = new ServiceCommande().getAll();
            numCommande.setCellValueFactory(new PropertyValueFactory<>("id"));
            totalPaiement.setCellValueFactory(new PropertyValueFactory<>("totalPayment"));
            state.setCellValueFactory(new PropertyValueFactory<>("state"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            tableViewCom.setItems(commandes);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}