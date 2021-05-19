package Gui.evenement;

import Entities.comment;
import Services.CommentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static Gui.evenement.List_eventController.idEvent;

public class eventDetailsController implements Initializable {

    @FXML
    private Label eventName;

    @FXML
    private ImageView eventImage;

    @FXML
    private Label prixEvent;

    @FXML
    private Label descriptionEn;

    @FXML
    private ScrollPane commentScrollPane;

    @FXML
    private AnchorPane commentAnchorPane;
    static Map<String, String[]> words = new HashMap<>();

    static int largestWordLength = 0;

    @FXML
    private TextField commentField;

    @FXML
    private Button addCommentBtn;


    public void setImage(String i)
    {
        Image image =  new Image("file:///C:\\Users\\Ryaan\\Desktop\\projet\\JobHub-Desktop-Application\\images"+i);

        eventImage.setImage(image);
        eventImage.setStyle("-fx-background-color:transparent;");
    }

    public void setNom(String n)
    {
        eventName.setText(n);
    }
    public void setPrix(String p)
    {
        prixEvent.setText("Prix :"+p);
    }
    public void setDescription(String d)
    {
      descriptionEn.setText(d);
    }
    VBox commentContainer=new VBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadConfigs();
        List<comment> eventComments= new ArrayList<>();
        try {
            CommentService cs=new CommentService();

            eventComments = cs.searchEventComments(String.valueOf(idEvent));

            for (int i = 0; i < eventComments.size(); i++) {

                VBox commentBox=new VBox();

                Label commentOwner =new Label(eventComments.get(i).getName());
                Label comment=new Label(eventComments.get(i).getMessage());
                Label commentDateTime=new Label(eventComments.get(i).getCreated_at().toString());

                commentBox.getChildren().add(commentOwner);
                commentBox.getChildren().add(comment);
             //   commentBox.getChildren().add(commentDateTime);

                commentContainer.getChildren().add(commentBox);
               commentAnchorPane.setMinHeight(commentAnchorPane.getMinHeight() + 50);
            }

            commentAnchorPane.getChildren().add(commentContainer);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static void loadConfigs() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv").openConnection().getInputStream()));
            String line = "";
            int counter = 0;
            while((line = reader.readLine()) != null) {
                counter++;
                String[] content = null;
                try {
                    content = line.split(",");
                    if(content.length == 0) {
                        continue;
                    }
                    String word = content[0];
                    String[] ignore_in_combination_with_words = new String[]{};
                    if(content.length > 1) {
                        ignore_in_combination_with_words = content[1].split("_");
                    }

                    if(word.length() > largestWordLength) {
                        largestWordLength = word.length();
                    }

                    words.put(word.replaceAll(" ", ""), ignore_in_combination_with_words);

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Loaded " + counter + " words to filter out");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String checkWord(String input){
        input = input.replaceAll("1","i");
        input = input.replaceAll("!","i");
        input = input.replaceAll("3","e");
        input = input.replaceAll("4","a");
        input = input.replaceAll("@","a");
        input = input.replaceAll("5","s");
        input = input.replaceAll("7","t");
        input = input.replaceAll("0","o");
        input = input.replaceAll("9","g");
        return input;
    };

    @FXML
    void addNewComment(ActionEvent event) {
        String filterComment="";
        ArrayList<String> badWords = badWordsFound(commentField.getText());
        if(badWords.size() > 0) {
            for (int i = 0; i <  badWords.size(); i++) {

                commentField.setText(checkWord(commentField.getText()));
                System.out.println(commentField.getText().replaceAll(" ",""));


                if(commentField.getText().replaceAll(" ","").contains(badWords.get(i))){
                    System.out.println("REPLACE");
                    filterComment=commentField.getText().replaceAll(" ","").replace(badWords.get(i)," ***** ");
                    commentField.setText(filterComment);
                }
            }
            // System.out.println("This message was blocked because a bad word was found. If you believe this word should not be blocked, please message support.");
        }

        comment newComment = new comment();
        newComment.setMessage(filterComment);
        if(!filterComment.equals("")){

            newComment.setMessage(filterComment);
        }
        else{
            newComment.setMessage(commentField.getText());
        }
        newComment.setName("Zied");
        newComment.setEmail("zied.nefzaoui@esprit.tn");
        newComment.setIdEvent(Gui.evenement.List_eventController.idEvent);
        newComment.setPhone(3636);
        CommentService cs=new CommentService();

        cs.insert(newComment);

        commentAnchorPane.getChildren().remove(commentAnchorPane);
        commentField.clear();
        VBox commentBox=new VBox();

        Label commentOwner =new Label(newComment.getName());
        Label comment=new Label(newComment.getMessage());
        java.sql.Date dc= Date.valueOf(LocalDate.now());
        Label commentDateTime=new Label(dc.toString());
        commentBox.getChildren().add(commentOwner);
        commentBox.getChildren().add(comment);
        commentContainer.getChildren().add(commentBox);
        commentAnchorPane.setMinHeight(commentAnchorPane.getMinHeight() + 50);
       // commentAnchorPane.getChildren().add(commentBox);

    }


    private void newCommentList(){
        List<comment> eventComments= new ArrayList<>();
        try {
            CommentService cs=new CommentService();
            System.out.println("idEvent = " + idEvent);
            VBox commentContainer=new VBox();
            eventComments = cs.searchEventComments(String.valueOf(idEvent));
            System.out.println(eventComments.size());
            for (int i = 0; i < eventComments.size(); i++) {

                VBox commentBox=new VBox();

                Label commentOwner =new Label(eventComments.get(i).getName());
                Label comment=new Label(eventComments.get(i).getMessage());
                Label commentDateTime=new Label(eventComments.get(i).getCreated_at().toString());

                commentBox.getChildren().add(commentOwner);
                commentBox.getChildren().add(comment);
                //   commentBox.getChildren().add(commentDateTime);

                commentContainer.getChildren().add(commentBox);
                 commentAnchorPane.setMinHeight(commentAnchorPane.getMinHeight() + 100);
            }

            commentAnchorPane.getChildren().add(commentContainer);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void checkComment(KeyEvent event) {

    }




    /**
     * Iterates over a String input and checks whether a cuss word was found in a list, then checks if the word should be ignored (e.g. bass contains the word *ss).
     * @param input
     * @return
     */

    public static ArrayList<String> badWordsFound(String input) {
        if(input == null) {
            return new ArrayList<>();
        }

        // don't forget to remove leetspeak, probably want to move this to its own function and use regex if you want to use this

        input = input.replaceAll("1","i");
        input = input.replaceAll("!","i");
        input = input.replaceAll("3","e");
        input = input.replaceAll("4","a");
        input = input.replaceAll("@","a");
        input = input.replaceAll("5","s");
        input = input.replaceAll("7","t");
        input = input.replaceAll("0","o");
        input = input.replaceAll("9","g");


        ArrayList<String> badWords = new ArrayList<>();
        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");
        // iterate over each letter in the word
        for(int start = 0; start < input.length(); start++) {
            // from each letter, keep going to find bad words until either the end of the sentence is reached, or the max word length is reached.
            for(int offset = 1; offset < (input.length()+1 - start) && offset < largestWordLength; offset++)  {
                String wordToCheck = input.substring(start, start + offset);

                if(words.containsKey(wordToCheck)) {
                    // for example, if you want to say the word bass, that should be possible.
                    String[] ignoreCheck = words.get(wordToCheck);
                    boolean ignore = false;
                    for(int s = 0; s < ignoreCheck.length; s++ ) {
                        if(input.contains(ignoreCheck[s])) {
                            ignore = true;
                            break;
                        }
                    }
                    if(!ignore) {

                        badWords.add(wordToCheck);
                    }
                }
            }
        }


        for(String s: badWords) {
            System.out.println(s + " qualified as a bad word in a username");
        }
        return badWords;

    }




}
