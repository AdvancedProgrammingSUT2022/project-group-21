package com.example.ViewController;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    public void startFunction(MouseEvent mouseEvent) {
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
