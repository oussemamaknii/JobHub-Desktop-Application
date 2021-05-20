/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui.evenement;


import Entities.AutoComplete;
import Entities.Offre_Emploi;
import Entities.event;
import Services.Offre_Emploi_Service;
import Services.evenementService;

import java.io.IOException;
import java.lang.*;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.controlsfx.control.textfield.TextFields;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXHamburger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;


public class List_eventController implements Initializable {

    public static int idEvent;
    @FXML
    private AnchorPane ListAnchorPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private GridPane gridP;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Button panier;

    public static int id2;

    @FXML
    private TextField search;

    public int Id;
    @FXML
    private AnchorPane growPane;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }


    @FXML
    private Text testText;

    public void setText(String p) {
        testText.setText(p);
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScrollPane1.setStyle("-fx-background-color:transparent;");
        ScrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ScrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        allEvents();
    }
    @FXML
    void fiterEvents(KeyEvent event) throws SQLException  {
        evenementService es = new evenementService();
        AutoComplete ac = new AutoComplete();
        String[] a = ac.MotsAutoComplete();
        TextFields.bindAutoCompletion(search,a);
        gridP.getChildren().clear();
        filteredEvents(es.filterEventList(search.getText()));

        if (search.getText().length() == 0) {
            gridP.getChildren().clear();
           allEvents();
        }
    }

    private void allEvents(){
        try {
            evenementService ps = new evenementService();
            List<event> lp = ps.allEventList();

            gridP.setHgap(450);
            gridP.setVgap(300);

            int cols = 1, colCnt = 0, rowCnt = 0;

            for (int i = 0; i < lp.size(); i++) {

                int idEvent2=lp.get(i).getId();
                event selectedEvent=lp.get(i);

                System.out.println(lp.get(i).getImage());



                Label NomEvent = new Label(lp.get(i).getNom());

                NomEvent.setStyle("-fx-min-heigt:10;");
                NomEvent.setFont(Font.font("Arial", 20));
                NomEvent.setStyle("-fx-fill:black");


                Text Price = new Text(String.valueOf(lp.get(i).getPrix()));
                Price.setFont(Font.font("Ubuntu", 18));
                Price.setStyle("-fx-fill:black");

                Text Dollar = new Text("$");
                Dollar.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
                Dollar.setStyle("-fx-fill:black");
                Dollar.setOpacity(0.5);






                JFXButton ParticiperButton = new JFXButton("Participer");
                ParticiperButton.setStyle("-fx-background-color:#8CD5EF;-fx-background-radius:0;-fx-min-width:80;");

                ParticiperButton.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                ParticiperButton.setMinWidth(10);


                JFXButton EditEventBtn = new JFXButton("Edit Event");
                EditEventBtn.setStyle("-fx-background-color:#8CD5EF;-fx-background-radius:0;-fx-min-width:80;");

                EditEventBtn.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                EditEventBtn.setMinWidth(50);


                Circle cir2 = new Circle(80,80,80);
                cir2.setStroke(Color.SEAGREEN);
                Image im = new Image("file:///C:\\Users\\Ryaan\\Desktop\\test\\JobHub-Desktop-Application\\images\\" + lp.get(i).getImage());
                cir2.setFill(new ImagePattern(im));
                cir2.setTranslateY(15);

                cir2.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
                    cir2.setOpacity(0.3);
                });

                cir2.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
                    cir2.setOpacity(1);
                });


                cir2.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            {
                                try {
                                    idEvent=idEvent2;
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/evenement/event_details.fxml"));
                                    Parent root;
                                    root = loader.load();
                                    eventDetailsController pv = loader.getController();
                                    evenementService pss = new evenementService();
                                    event p = pss.searchByName(NomEvent.getText());
                                    pv.setNom(p.getNom());
                                    pv.setImage(p.getImage());
                                    pv.setPrix(p.getPrix().toString());
                                    pv.setDescription(p.getDescription());
                                    Stage primaryStage = new Stage();
                                    Scene scene = new Scene(root);
                                    primaryStage.setTitle("View Selected Event");
                                    primaryStage.setScene(scene);

                                    primaryStage.show();

                                } catch (SQLException | IOException ex) {
                                    Logger.getLogger(List_eventController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }

                );

                ParticiperButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            {

                                try {
                                    evenementService es=new evenementService();
                                    selectedEvent.setNbre_place(selectedEvent.getNbre_place()-1);
                                    es.UpdateEvent(selectedEvent);
                                    gridP.getChildren().clear();
                                    allEvents();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }

                        }
                );


                EditEventBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            {
                                try {
                                    idEvent=idEvent2;
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/evenement/edit_event.fxml"));
                                    Parent root;
                                    root = loader.load();
                                    edit_EventController pv = loader.getController();
                                    evenementService pss = new evenementService();
                                    Stage primaryStage = new Stage();
                                    Scene scene = new Scene(root);
                                    primaryStage.setTitle("View Selected Event");
                                    primaryStage.setScene(scene);
                              ((Node)event.getSource()).getScene().getWindow().hide();
                                    primaryStage.show();

                                } catch ( IOException ex) {
                                    Logger.getLogger(List_eventController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                );


                Label Separateur = new Label();





                HBox PriceDollar = new HBox();
                PriceDollar.getChildren().add(Dollar);
                PriceDollar.getChildren().add(Price);
                Dollar.setTranslateX(20);
                Price.setTranslateX(20);



                PriceDollar.setAlignment(Pos.CENTER);
                HBox btnBox=new HBox();
                btnBox.getChildren().add(ParticiperButton);
                btnBox.getChildren().add(EditEventBtn);





                HBox EventBox = new HBox();
                EventBox.setStyle("-fx-background-color:white;");


                EventBox.setMinWidth(400);
                EventBox.setMinHeight(200);

                EventBox.getChildren().add(cir2);
                EventBox.getChildren().add(Separateur);
                Separateur.setTranslateY(20);
                Separateur.setTranslateX(10);
                Separateur.setStyle("-fx-background-color:#F3F3F3;-fx-min-height:1");
                Separateur.setMaxHeight(150);
                Separateur.setMinWidth(3);

                NomEvent.setTranslateY(15);
                NomEvent.setTranslateX(20);
                PriceDollar.setTranslateX(-40);
                PriceDollar.setTranslateY(80);
                ParticiperButton.setTranslateY(100);
                ParticiperButton.setTranslateX(20);
                EditEventBtn.setTranslateY(100);
                EditEventBtn.setTranslateX(40);

                VBox EventInfo = new VBox();
                EventInfo.getChildren().add(NomEvent);
                EventInfo.getChildren().add(PriceDollar);

                Text Places = new Text(lp.get(i).getNbre_place()+" places");
                Places.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
                Places.setStyle("-fx-fill:white");
                Places.setOpacity(1);
                Places.setTranslateX(30);


                Image img = new Image("file:///C:\\Users\\Ryaan\\Desktop\\master\\JobHub-Desktop-Application\\images\\crossIcon.png");
                ImageView view = new ImageView(img);
                view.setFitHeight(20);

                view.setPreserveRatio(true);

                Label removeEvent = new Label();

                removeEvent.setGraphic(view);
                removeEvent.setTranslateY(-80);
                removeEvent.setTranslateX(230);

                removeEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            {

                                try {
                                    idEvent=idEvent2;
                                    evenementService es=new evenementService();
                                    es.DeleteEvent(idEvent);
                                    gridP.getChildren().clear();
                                    allEvents();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }

                        }
                );


                Places.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
                Places.setStyle("-fx-fill:white");
                Places.setOpacity(1);
                Places.setTranslateX(30);


                EventInfo.getChildren().add(Places);
                EventInfo.getChildren().add(removeEvent);
                EventInfo.getChildren().add(btnBox);


                EventBox.getChildren().add(EventInfo);
                EventBox.setStyle("-fx-background-color:#2460A7FF");

                gridP.add(EventBox, colCnt, rowCnt);

                colCnt++;
                if (colCnt > cols) {
                    rowCnt++;
                    colCnt = 0;
                    growPane.setMinHeight(growPane.getMinHeight() + 350);
                }


            }
        } catch (SQLException ex) {
            Logger.getLogger(List_eventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    private void filteredEvents(List<event> lp){



            gridP.setHgap(450);
            gridP.setVgap(300);

            int cols = 1, colCnt = 0, rowCnt = 0;

            for (int i = 0; i < lp.size(); i++) {

                int idEvent2=lp.get(i).getId();
                event selectedEvent=lp.get(i);
                System.out.println(lp.get(i).getImage());
                Image image = new Image("file:///C:\\Users\\Ryaan\\Desktop\\master\\JobHub-Desktop-Application\\images\\" + lp.get(i).getImage());
                ImageView imageView = new ImageView(image);
                imageView.setTranslateY(30);
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                imageView.setStyle("-fx-background-radius:30;");


                Label NomEvent = new Label(lp.get(i).getNom());

                NomEvent.setStyle("-fx-min-heigt:10;");
                NomEvent.setFont(Font.font("Arial", 20));
                NomEvent.setStyle("-fx-fill:black");


                Text Price = new Text(String.valueOf(lp.get(i).getPrix()));
                Price.setFont(Font.font("Ubuntu", 18));
                Price.setStyle("-fx-fill:black");

                Text Dollar = new Text("$");
                Dollar.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
                Dollar.setStyle("-fx-fill:black");
                Dollar.setOpacity(0.5);






                JFXButton ParticiperButton = new JFXButton("Participer");
                ParticiperButton.setStyle("-fx-background-color:#8CD5EF;-fx-background-radius:0;-fx-min-width:80;");

                ParticiperButton.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                ParticiperButton.setMinWidth(10);


                JFXButton EditEventBtn = new JFXButton("Edit Event");
                EditEventBtn.setStyle("-fx-background-color:#8CD5EF;-fx-background-radius:0;-fx-min-width:80;");

                EditEventBtn.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                EditEventBtn.setMinWidth(50);


                Circle cir2 = new Circle(80,80,80);
                cir2.setStroke(Color.SEAGREEN);
                Image im = new Image("file:///C:\\Users\\Ryaan\\Desktop\\master\\JobHub-Desktop-Application\\images\\" + lp.get(i).getImage());
                cir2.setFill(new ImagePattern(im));
                cir2.setTranslateY(15);

                cir2.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
                    cir2.setOpacity(0.3);
                });

                cir2.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
                    cir2.setOpacity(1);
                });


                cir2.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            {
                                try {
                                    idEvent=idEvent2;
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/evenement/event_details.fxml"));
                                    Parent root;
                                    root = loader.load();
                                    eventDetailsController pv = loader.getController();
                                    evenementService pss = new evenementService();
                                    event p = pss.searchByName(NomEvent.getText());
                                    pv.setNom(p.getNom());
                                    pv.setImage(p.getImage());
                                    pv.setPrix(p.getPrix().toString());
                                    pv.setDescription(p.getDescription());
                                    Stage primaryStage = new Stage();
                                    Scene scene = new Scene(root);
                                    primaryStage.setTitle("View Selected Event");
                                    primaryStage.setScene(scene);

                                    primaryStage.show();

                                } catch (SQLException | IOException ex) {
                                    Logger.getLogger(List_eventController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }

                );

                ParticiperButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            {

                                try {
                                    evenementService es=new evenementService();
                                    selectedEvent.setNbre_place(selectedEvent.getNbre_place()-1);
                                    es.UpdateEvent(selectedEvent);
                                    gridP.getChildren().clear();
                                    allEvents();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }

                        }
                );


                EditEventBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            {
                                try {
                                    idEvent=idEvent2;
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/evenement/edit_event.fxml"));
                                    Parent root;
                                    root = loader.load();
                                    edit_EventController pv = loader.getController();
                                    evenementService pss = new evenementService();
                                    Stage primaryStage = new Stage();
                                    Scene scene = new Scene(root);
                                    primaryStage.setTitle("View Selected Event");
                                    primaryStage.setScene(scene);
                                    ((Node)event.getSource()).getScene().getWindow().hide();
                                    primaryStage.show();

                                } catch ( IOException ex) {
                                    Logger.getLogger(List_eventController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                );

                Label Separateur = new Label();


                imageView.setTranslateY(-20);


                HBox PriceDollar = new HBox();
                PriceDollar.getChildren().add(Dollar);
                PriceDollar.getChildren().add(Price);
                Dollar.setTranslateX(20);
                Price.setTranslateX(20);



                PriceDollar.setAlignment(Pos.CENTER);
                HBox btnBox=new HBox();
                btnBox.getChildren().add(ParticiperButton);
                btnBox.getChildren().add(EditEventBtn);





                HBox EventBox = new HBox();
                EventBox.setStyle("-fx-background-color:white;");

                imageView.setTranslateY(20);
                EventBox.setMinWidth(400);
                EventBox.setMinHeight(200);

                EventBox.getChildren().add(cir2);
                EventBox.getChildren().add(Separateur);
                Separateur.setTranslateY(20);
                Separateur.setTranslateX(10);
                Separateur.setStyle("-fx-background-color:#F3F3F3;-fx-min-height:1");
                Separateur.setMaxHeight(150);
                Separateur.setMinWidth(3);

                NomEvent.setTranslateY(15);
                NomEvent.setTranslateX(20);
                PriceDollar.setTranslateX(-40);
                PriceDollar.setTranslateY(80);
                ParticiperButton.setTranslateY(100);
                ParticiperButton.setTranslateX(20);
                EditEventBtn.setTranslateY(100);
                EditEventBtn.setTranslateX(40);

                VBox EventInfo = new VBox();
                EventInfo.getChildren().add(NomEvent);
                EventInfo.getChildren().add(PriceDollar);

                Text Places = new Text(lp.get(i).getNbre_place()+" places");
                Places.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
                Places.setStyle("-fx-fill:white");
                Places.setOpacity(1);
                Places.setTranslateX(30);


                Image img = new Image("file:///C:\\Users\\Ryaan\\Desktop\\master\\JobHub-Desktop-Application\\images\\crossIcon.png");
                ImageView view = new ImageView(img);
                view.setFitHeight(20);

                view.setPreserveRatio(true);

                Label removeEvent = new Label();

                removeEvent.setGraphic(view);
                removeEvent.setTranslateY(-80);
                removeEvent.setTranslateX(230);

                removeEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            {

                                try {
                                    idEvent=idEvent2;
                                    evenementService es=new evenementService();
                                    es.DeleteEvent(idEvent);
                                    gridP.getChildren().clear();
                                    allEvents();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }

                        }
                );


                Places.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
                Places.setStyle("-fx-fill:white");
                Places.setOpacity(1);
                Places.setTranslateX(30);


                EventInfo.getChildren().add(Places);
                EventInfo.getChildren().add(removeEvent);
                EventInfo.getChildren().add(btnBox);


                EventBox.getChildren().add(EventInfo);
                EventBox.setStyle("-fx-background-color:#2460A7FF");

                gridP.add(EventBox, colCnt, rowCnt);

                colCnt++;
                if (colCnt > cols) {
                    rowCnt++;
                    colCnt = 0;
                    growPane.setMinHeight(growPane.getMinHeight() + 350);
                }


            }


    }
}





