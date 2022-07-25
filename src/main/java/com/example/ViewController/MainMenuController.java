package com.example.ViewController;

import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;
import com.example.View.GamePage;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainMenuController {
    @FXML
    public Pane mainMenuPane;
    @FXML
    public ImageView startButton;
    @FXML
    public ImageView exitButton;
    @FXML
    public ImageView loadButton;
    @FXML
    public ImageView extraButton;
    @FXML
    public void startFunction(MouseEvent mouseEvent) throws Exception {
        int n;
        ArrayList<String> users =new ArrayList<>();
        try {
            n = Integer.parseInt(Dialog.AskQuestion("Number of players", "How many players are there?"));
        }
        catch (Exception e) {
            Dialog.error_message(":(", "You weren't supposed to do that!");
            return;
        }
        for (int i = 0; i < n; i++) {
          String string=Dialog.AskQuestion("Username","Enter "+  i+1 +"th username please!");
          if(UserDatabase.getInstance().getUserByUsername(string)==null) {
              Dialog.error_message(":(", "No such user!");
              return;
          }
          users.add(string);
        }
//        users.add("Mohammad");
//        users.add("Aryan");
//        users.add("Ali");
        exitFunction(null);
        GamePage.show(users);
    }

    @FXML
    public void extraFunction(MouseEvent mouseEvent) {
    }

    @FXML
    public void exitFunction(MouseEvent mouseEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void loadFunction(MouseEvent mouseEvent) {
    }

    public void mouseEnter(MouseEvent mouseEvent) {
        startButton.setCursor(Cursor.HAND);
    }

    public void mouseExit(MouseEvent mouseEvent) {
        startButton.setCursor(Cursor.DEFAULT);
    }
}
