/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Demande;

import Entities.Demande_Recrutement;
import Entities.Offre_Emploi;
import Gui.OffreEmploi.OffreCell;
import Services.Demande_Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class AddDemandeController implements Initializable {

    @FXML
    private Button apply;
    @FXML
    private Button delete;
    @FXML
    private Button seeapps;
    @FXML
    private ListView<Demande_Recrutement> table2;
    @FXML
    private ListView<Offre_Emploi> table;
    @FXML
    private ChoiceBox<String> tri;
    @FXML
    private Label lab;
    @FXML
    private Label text;
    @FXML
    private Pane pane;
    @FXML
    private StackPane effect;

    int alluserapps = new Demande_Service().countalluserapps(5);
    int from = 0, to = 0, itemperpage = 5;
    int iduser = 17;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Post", "Categprie", "Date Expiration");
        tri.setItems(list);
        table.setVisible(false);
        tri.setVisible(false);
        lab.setVisible(false);
        effect.setDisable(true);
        table2.setVisible(false);
        showapplies(iduser);

        tri.setOnAction(e -> {
            showofferstri(tri.getValue());
        });

        apply.setOnAction(e -> {
            table.setVisible(true);
            tri.setVisible(true);
            lab.setVisible(true);
            table2.setVisible(false);
            showoffers();
            Offre_Emploi offre = table.getSelectionModel().getSelectedItem();
            if (offre != null) {
                new Demande_Service().apply(offre.getId(), iduser);

                effect.setDisable(false);
                BoxBlur blur = new BoxBlur(3, 3, 3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("WAIT"));
                content.setBody(new Text("WE ARE TREATING YOUR APPLICATION !!!"));
                JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
                JFXButton btn = new JFXButton("Done !");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        fialog.close();
                        pane.setEffect(null);
                        effect.setDisable(true);
                        if(sendemail(iduser, offre.getTitre())){
                            table.setVisible(false);
                            table2.setVisible(true);
                            effect.setDisable(false);
                            BoxBlur blur = new BoxBlur(3, 3, 3);
                            JFXDialogLayout content = new JFXDialogLayout();
                            content.setHeading(new Text("Applying to a Job offer"));
                            content.setBody(new Text("Your Applied successfully to this job : " + offre.getTitre() + " \nAnd an E-mail been sent to your to confirm your application !"));
                            JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
                            JFXButton btn = new JFXButton("OK !");
                            btn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    fialog.close();
                                    pane.setEffect(null);
                                    effect.setDisable(true);
                                    table.getSelectionModel().setSelectionMode(null);
                                    showapplies(iduser);
                                }
                            });
                            content.setActions(btn);
                            pane.setEffect(blur);
                            fialog.show();
                        }
                    }
                });
                content.setActions(btn);
                pane.setEffect(blur);
                fialog.show();
            }
        });

        delete.setOnAction(e -> {
            table.setVisible(false);
            tri.setVisible(false);
            lab.setVisible(false);
            table2.setVisible(true);
            showapplies(iduser);
            Demande_Recrutement demande = table2.getSelectionModel().getSelectedItem();
            if (demande != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deleting a job application");
                alert.setContentText("Do you really wanna delete this application ?");
                ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        new Demande_Service().deletapp(demande.getId());
                        showapplies(iduser);
                    }
                });
            } else {
                effect.setDisable(false);
                BoxBlur blur = new BoxBlur(3, 3, 3);
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Error"));
                content.setBody(new Text("Select An Application So You Can  \n Delete it !!"));
                JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
                JFXButton btn = new JFXButton("Done !");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        fialog.close();
                        pane.setEffect(null);
                        effect.setDisable(true);
                    }
                });
                content.setActions(btn);
                pane.setEffect(blur);
                fialog.show();
            }
        });

        seeapps.setOnAction(e -> {
            table.setVisible(false);
            tri.setVisible(false);
            lab.setVisible(false);
            table2.setVisible(true);
            showapplies(iduser);
        });
    }

    private Boolean sendemail(int iduser, String titre) {
        String to = "flawnflawn@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "jobhubwebsiteesprit@gmail.com";
        final String password = "jobhub0000";

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtps.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session sess = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage mes = new MimeMessage(sess);
            mes.setFrom(new InternetAddress(username));
            mes.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            mes.setSubject("Applying To A Job : ");
            mes.setContent(email(titre), "text/html");
            Transport.send(mes);
            System.out.println("sent");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void showofferstri(String value) {
        ObservableList<Offre_Emploi> offres = new Demande_Service().tri(value);
        table.setItems(offres);
        table.setCellFactory(studentListView -> new OffreCell());
    }

    public void showoffers() {
        text.setText("Job Offers To Apply To");
        table.setVisible(true);
        table2.setVisible(false);
        ObservableList<Offre_Emploi> offres = new Demande_Service().get_not_applied_jobs();
        table.setItems(offres);
        table.setCellFactory(studentListView -> new OffreCell());
    }

    public void showapplies(int id) {
        text.setText("YourJob Application");
        table.setVisible(false);
        table2.setVisible(true);
        ObservableList<Demande_Recrutement> offres = new Demande_Service().getAllUser(5);
        table2.setItems(offres);
        table2.setCellFactory(DemandeListView -> new DemandeCell());
        /*page.setPageCount((alluserapps / itemperpage) + 1);
        page.setPageFactory(this::createtable);*/
    }

    public Node createtable(int pageindex) {
        from = pageindex * itemperpage;
        to = itemperpage;
        table2.setItems(FXCollections.observableList(new Demande_Service().getAllUser(5/*,from, to*/)));
        return table2;
    }

    public String email(String titre) {
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "  <head>\n" +
                "    <title>\n" +
                "      Template mailing Alsacreations\n" +
                "    </title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <meta content=\"width=device-width\" />\n" +
                "    <style type=\"text/css\">\n" +
                "      /* Fonts and Content */\n" +
                "\n" +
                "      body, td { font-family: 'Helvetica Neue', Arial, Helvetica, Geneva,\n" +
                "      sans-serif; font-size: 14px; }\n" +
                "\n" +
                "      body { background-color: #2A374E; margin: 0; padding: 0;\n" +
                "      -webkit-text-size-adjust: none; -ms-text-size-adjust: none; }\n" +
                "\n" +
                "      h2 { padding-top: 12px; /* ne fonctionnera pas sous Outlook 2007+ */\n" +
                "      color: #0E7693; font-size: 22px; }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "\n" +
                "  <body style=\"margin:0px; padding:0px; -webkit-text-size-adjust:none;\">\n" +
                "    <table width=\"100%\"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "      border=\"0\"\n" +
                "      style=\"background-color:rgb(42, 55, 78)\">\n" +
                "      <tbody>\n" +
                "        <tr>\n" +
                "          <td align=\"center\" bgcolor=\"#2A374E\">\n" +
                "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                  <td class=\"w640\" width=\"640\" height=\"10\"></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                  <td class=\"w640\" width=\"640\" height=\"10\"></td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- entete -->\n" +
                "                <tr class=\"pagetoplogo\">\n" +
                "                  <td class=\"w640\" width=\"640\">\n" +
                "                    <table class=\"w640\"\n" +
                "                      width=\"640\"\n" +
                "                      cellpadding=\"0\"\n" +
                "                      cellspacing=\"0\"\n" +
                "                      border=\"0\"\n" +
                "                      bgcolor=\"#F2F0F0\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td class=\"w30\" width=\"30\"></td>\n" +
                "                          <td class=\"w580\"\n" +
                "                            width=\"580\"\n" +
                "                            valign=\"middle\"\n" +
                "                            align=\"left\">\n" +
                "                            <div class=\"pagetoplogo-content\">\n" +
                "                              <h5>\n" +
                "                                JobHub\n" +
                "                              </h5>\n" +
                "                            </div>\n" +
                "                          </td>\n" +
                "                          <td class=\"w30\" width=\"30\"></td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- separateur horizontal -->\n" +
                "                <tr>\n" +
                "                  <td class=\"w640\"\n" +
                "                    width=\"640\"\n" +
                "                    height=\"1\"\n" +
                "                    bgcolor=\"#d7d6d6\"></td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- contenu -->\n" +
                "                <tr class=\"content\">\n" +
                "                  <td class=\"w640\" class=\"w640\" width=\"640\" bgcolor=\"#ffffff\">\n" +
                "                    <table class=\"w640\"\n" +
                "                      width=\"640\"\n" +
                "                      cellpadding=\"0\"\n" +
                "                      cellspacing=\"0\"\n" +
                "                      border=\"0\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td class=\"w30\" width=\"30\"></td>\n" +
                "                          <td class=\"w580\" width=\"580\">\n" +
                "                            <!-- une zone de contenu -->\n" +
                "                            <table class=\"w580\"\n" +
                "                              width=\"580\"\n" +
                "                              cellpadding=\"0\"\n" +
                "                              cellspacing=\"0\"\n" +
                "                              border=\"0\">\n" +
                "                              <tbody>\n" +
                "                                <tr>\n" +
                "                                  <td class=\"w580\" width=\"580\">\n" +
                "                                    <h2 style=\"color:#0E7693; font-size:22px; padding-top:12px;\">\n" +
                "                                      Nouveau recrutement\n" +
                "                                    </h2>\n" +
                "\n" +
                "                                    <div align=\"left\" class=\"article-content\">\n" +
                "                                      <p>\n" +
                "                                        Vous avez été recruté a in nouveau offre\n" +
                "                                        d'emploi au nom de : " + titre + "\n" +
                "                                      </p>\n" +
                "                                    </div>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                  <td class=\"w580\"\n" +
                "                                    width=\"580\"\n" +
                "                                    height=\"1\"\n" +
                "                                    bgcolor=\"#c7c5c5\"></td>\n" +
                "                                </tr>\n" +
                "                              </tbody>\n" +
                "                            </table>\n" +
                "                          </td>\n" +
                "                          <td class=\"w30\" class=\"w30\" width=\"30\"></td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- separateur horizontal de 15px de haut -->\n" +
                "                <tr>\n" +
                "                  <td class=\"w640\"\n" +
                "                    width=\"640\"\n" +
                "                    height=\"15\"\n" +
                "                    bgcolor=\"#ffffff\"></td>\n" +
                "                </tr>\n" +
                "                <!-- pied de page -->\n" +
                "                <tr class=\"pagebottom\">\n" +
                "                  <td class=\"w640\" width=\"640\">\n" +
                "                    <table class=\"w640\"\n" +
                "                      width=\"640\"\n" +
                "                      cellpadding=\"0\"\n" +
                "                      cellspacing=\"0\"\n" +
                "                      border=\"0\"\n" +
                "                      bgcolor=\"#c7c7c7\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td colspan=\"5\" height=\"10\"></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td class=\"w30\" width=\"30\"></td>\n" +
                "                          <td class=\"w30\" width=\"30\"></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                          <td colspan=\"5\" height=\"10\"></td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                  <td class=\"w640\" width=\"640\" height=\"60\"></td>\n" +
                "                </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>\n";
    }

}
