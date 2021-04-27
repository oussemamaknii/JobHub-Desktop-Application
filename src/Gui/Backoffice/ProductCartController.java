package Gui.Backoffice;

import Entities.Cart;
import Entities.Panier;
import Services.ServicePanier;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProductCartController {

    @FXML
    private Label refOrder;

    @FXML
    private TableView<Panier> tableCart;

    @FXML
    private TableColumn<Panier, String> idP;

    @FXML
    private TableColumn<Panier, String> QtyP;

    @FXML
    private ImageView close;

    public void setRecords(int idC){

        refOrder.setText("B4"+ String.valueOf(idC)+"J4");
        ObservableList<Panier> panierCart = new ServicePanier().getPanier(idC);

        idP.setCellValueFactory(new PropertyValueFactory<Panier, String>("idProduit"));
        QtyP.setCellValueFactory(new PropertyValueFactory<Panier, String>("quantity"));

        tableCart.setItems(panierCart);
    }
    @FXML
    void cancel(MouseEvent event) {
        Stage stg = (Stage) close.getScene().getWindow();
        stg.close();
    }
}
