package Gui.Commande;

import Entities.Cart;
import Entities.Paiement;
import Gui.Produit.ShopController;
import Services.ServiceCommande;
import Services.ServicePaiment;
import animatefx.animation.Bounce;
import animatefx.animation.FadeInDown;
import com.itextpdf.text.DocumentException;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import paypal.api.payments.Amount;
import paypal.api.payments.Details;
import paypal.api.payments.Item;
import paypal.api.payments.ItemList;
import paypal.api.payments.Links;
import paypal.api.payments.Payer;
import paypal.api.payments.Payment;
import paypal.api.payments.RedirectUrls;
import paypal.api.payments.Transaction;
import paypal.base.rest.APIContext;
import paypal.base.rest.PayPalRESTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentController implements Initializable {
    @FXML
    private TextField cardNumber;

    @FXML
    private Pane banner;

    @FXML
    private TextField moiExpiration;

    @FXML
    private TextField securityCode;

    @FXML
    private TextField anneeExpiration;

    @FXML
    private TextField cardHolderName;

    @FXML
    private WebView webview;
    AnchorPane centerContent;

    private boolean cardNumberBool, cardHolderNameBool, securityCodeBool, moiExpirationBool, anneeExpirationBool;
    ObservableList<Cart> panier = FXCollections.observableArrayList();
    int idCommande = 1;
    float Total = 5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Bounce(banner).play();
        webview.setVisible(false);
        cardNumberBool = cardHolderNameBool = securityCodeBool = moiExpirationBool = anneeExpirationBool = true;

        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage("Champ obligatoire");
        ValidatorBase cardHolderNameValidator = new ValidatorBase() {
            @Override
            protected void eval() {
                setMessage("Pas de caractères spéciaux ");
                if (cardHolderName.getText().matches("^^[a-zA-Z0-9éèà]+$")) {
                    cardHolderNameBool = true;
                    hasErrors.set(false);
                } else {
                    hasErrors.set(true);
                    cardHolderNameBool = false;
                }
            }
        };
        ValidatorBase cardNumberValidator = new ValidatorBase() {
            @Override
            protected void eval() {
                setMessage("le numéro doit contenir 4 chiffres uniquement");
                if (cardNumber.getText().matches("^[1-9]+$") && cardNumber.getText().length() == 4) {
                    hasErrors.set(false);
                    cardNumberBool = true;
                } else {
                    hasErrors.set(true);
                    cardNumberBool = false;
                }
            }
        };
        ValidatorBase securityCodeValidator = new ValidatorBase() {
            @Override
            protected void eval() {
                setMessage("Le code de sécurité doit contenir uniquement 5 chiffres");
                if (securityCode.getText().matches("^[1-9]+$") && securityCode.getText().length() == 5) {
                    hasErrors.set(false);
                    securityCodeBool = true;
                } else {
                    hasErrors.set(true);
                    securityCodeBool = false;
                }
            }
        };
        ValidatorBase moiExpirationValidator = new ValidatorBase() {
            @Override
            protected void eval() {
                setMessage("le moi doit être compris entre 1 et 12");
                if (moiExpiration.getText().matches("^[1-9]+$") && Integer.parseInt(moiExpiration.getText()) <= 12) {
                    hasErrors.set(false);
                    moiExpirationBool = true;
                } else {
                    hasErrors.set(true);
                    moiExpirationBool = false;
                }
            }
        };
        ValidatorBase anneeExpirationValidator = new ValidatorBase() {
            @Override
            protected void eval() {
                setMessage("entrer une année supérieure ou égale à 2020");
                if (Integer.parseInt(anneeExpiration.getText()) >= 2020) {
                    hasErrors.set(false);
                    anneeExpirationBool = true;
                } else {
                    hasErrors.set(true);
                    anneeExpirationBool = false;
                }
            }
        };

        /*
        cardHolderName.getValidators().addAll(requiredFieldValidator, cardHolderNameValidator);
        cardNumber.getValidators().addAll(requiredFieldValidator, cardNumberValidator);
        anneeExpiration.getValidators().addAll(requiredFieldValidator, anneeExpirationValidator);
        securityCode.getValidators().addAll(requiredFieldValidator, securityCodeValidator);
        moiExpiration.getValidators().addAll(requiredFieldValidator, moiExpirationValidator);

        cardHolderName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                cardHolderName.validate();
            }
        });
        */
    }
    public void redirection(AnchorPane c, ObservableList<Cart> p, float total, int idcommande) {
        centerContent = c;
        Total = total;
        panier = p;
        idCommande = idcommande;
    }

    @FXML
    void payerAction(ActionEvent event) throws SQLException, DocumentException, FileNotFoundException {

            if (cardHolderNameBool && cardNumberBool && securityCodeBool && anneeExpirationBool && moiExpirationBool) {

                String clientId = "Aa7JVpnYEjFYul3wCVGnTwAkSI3zQkkA7yqS3Ulj2mHU6XjrNA03P-xM1qvu161L2li_T66FfU7CKm0x";
                String clientSecret = "EIYp_ydvtX0CJjVR43zN4InnynQVm6QcSKF7sRwXLIGsfW6LnTe5EnmUtPTKAbhBKgqPl9Cgb-rrAAzs";
                ServiceCommande serviceCommande2 = new ServiceCommande();
                System.out.println("idCommande:" + idCommande);
                int subtotal = 0;
                ItemList itemList = new ItemList();
                List<Item> items = new ArrayList<Item>();
                for (Cart panier1 : panier) {
                    Item item = new Item();
                    item.setName(panier1.getNomProduit());
                    item.setQuantity(Integer.toString(panier1.getQuantite()));
                    item.setCurrency("USD");
                    item.setPrice(Float.toString(panier1.getPrix()));
                    subtotal += panier1.getQuantite() * panier1.getPrix();
                    items.add(item);
                    System.out.println("quantite " + panier1.getQuantite());
                    System.out.println("prix " + panier1.getPrix());

                }

                itemList.setItems(items);
                System.out.println("itemList" + itemList);
                Details details = new Details();
                details.setShipping("350");
                details.setSubtotal(Integer.toString(subtotal));
                details.setTax("500");
                Amount amount = new Amount();
                amount.setCurrency("USD");
                System.out.println("subtotal " + subtotal);
                int total = 350 + 500 + subtotal;
                System.out.println("total variable locale" + total);
                amount.setDetails(details);
                amount.setTotal(Integer.toString(total));
                //System.out.println("Details" + amount.getDetails());
                //System.out.println("Total" + amount.getTotal());

                Transaction transaction = new Transaction();
                transaction.setItemList(itemList);
                transaction.setAmount(amount);
                transaction.setDescription("Achat des produits de cyclePro App");
                List<Transaction> transactions = new ArrayList<Transaction>();
                transactions.add(transaction);
                Payer payer = new Payer();
                payer.setPaymentMethod("paypal");
                Payment payment = new Payment();
                payment.setIntent("sale");
                payment.setPayer(payer);
                payment.setTransactions(transactions);
                RedirectUrls redirectUrls = new RedirectUrls();
                redirectUrls.setCancelUrl("http://127.0.0.1:8000/login");
                redirectUrls.setReturnUrl("http://127.0.0.1:8000/login");
                payment.setRedirectUrls(redirectUrls);
                try {
                    APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
                    Payment createdPayment = payment.create(apiContext);
                    Iterator<Links> links = createdPayment.getLinks().iterator();
                    String paypalRedirectLink = "";
                    while (links.hasNext()) {
                        Links link = links.next();
                        if (link.getRel().equalsIgnoreCase("approval_url")) {
                            paypalRedirectLink = link.getHref();
                            WebEngine engine = webview.getEngine();
                            System.out.println("là");
                            engine.executeScript("window.location=\"" + paypalRedirectLink + "\";");
                            webview.setVisible(true);
                            new FadeInDown(webview).play();
                            engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                                if (Worker.State.SUCCEEDED.equals(newValue)) {
                                    System.out.println("l'écoute du changement de la page fonctionne très bien");
                                    System.out.println(engine.getLocation());
                                    System.out.println(engine.getLocation().contains(redirectUrls.getReturnUrl()));
                                    if (engine.getLocation().contains(redirectUrls.getReturnUrl())) {
                                        System.out.println("je suis dans mon site de cyclepro");
                                        ServicePaiment servicePayment = new ServicePaiment();
                                        Paiement p = new Paiement(cardHolderName.getText(), Integer.parseInt(cardNumber.getText()), Integer.parseInt(securityCode.getText()), Integer.parseInt(moiExpiration.getText()), Integer.parseInt(anneeExpiration.getText()), idCommande);
                                        try {
                                            servicePayment.add(p);
                                            /*Notifications notificationBuilder = Notifications.create()
                                                    .title("Confrimation Paiement")
                                                    .text("Paiement effectué avec succès")
                                                    .graphic(null)
                                                    .hideAfter(Duration.seconds(5))
                                                    .position(Pos.BOTTOM_RIGHT);
                                            notificationBuilder.showConfirm();*/
                                            System.out.println("Paiement");
                                            //JOptionPane.showMessageDialog(null, "Paiement effectué");

                                            Timeline timeline = new Timeline(
                                                    new KeyFrame(Duration.seconds(1.5), (ActionEvent e) -> {
                                                        try {
                                                            ServiceCommande serviceCommande = new ServiceCommande();
                                                            try {
                                                                serviceCommande.update(idCommande);
                                                                /*Notifications notificationBuilder3 = Notifications.create()
                                                                        .title("Confrimation Mise à jour de la table commande")
                                                                        .text("Commande mis à jour à 'payée'")
                                                                        .graphic(null)
                                                                        .hideAfter(Duration.seconds(5))
                                                                        .position(Pos.BOTTOM_RIGHT);
                                                                notificationBuilder3.showConfirm();*/
                                                                System.out.println("Commande");
                                                                //JOptionPane.showMessageDialog(null, "Mis à jour de la table commande à 'commande payée' ");

                                                            } catch (SQLException ex) {
                                                                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                            serviceCommande.historique(idCommande, panier);
                                                           /* Notifications notificationBuilder2 = Notifications.create()
                                                                    .title("Confrimation Génération Facture")
                                                                    .text("Facture  générée avec succès")
                                                                    .graphic(null)
                                                                    .hideAfter(Duration.seconds(5))
                                                                    .position(Pos.BOTTOM_RIGHT);
                                                            notificationBuilder2.showConfirm();*/
                                                            System.out.println("Facture");
                                                            //JOptionPane.showMessageDialog(null, "Facture généréed ");

                                                            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/Stock/Shop2.fxml"));
                                                            Parent fxml = Loader.load();
                                                            ShopController s = Loader.getController();
                                                            //s.redirectionFromPayment(centerContent, user);
                                                            s.redirectionFromPayment(centerContent);
                                                            centerContent.getChildren().removeAll();
                                                            new FadeInDown(fxml).play();
                                                            centerContent.getChildren().setAll(fxml);
                                                        } catch (IOException ex) {
                                                            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                                                        } catch (DocumentException ex) {
                                                            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                                                        } catch (SQLException ex) {
                                                            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                                                        }

                                                    }));
                                            timeline.play();

                                        } catch (SQLException ex) {
                                            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    }
                                }
                            });
                        }
                    }
                    System.out.println(createdPayment);
                } catch (PayPalRESTException e) {
                    System.out.println(e.getMessage());
                } catch (Exception ex) {
                    System.out.println("manger");
                    System.out.println(ex.getMessage());
                }
            }
        }

}
