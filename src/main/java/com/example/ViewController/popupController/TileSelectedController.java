package com.example.ViewController.popupController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TileSelectedController {
    @FXML
    public Button cheat_btn;
    @FXML
    public Button research_btn;
    @FXML
    public Button back_btn;
    @FXML
    public ImageView img;
    public Button endturn_btn;

    @FXML
    public void cheat(MouseEvent mouseEvent) {
    }
    @FXML
    public void research(MouseEvent mouseEvent) {
    }
    @FXML
    public void endTurn(MouseEvent mouseEvent) {
    }
    @FXML
    public void back(MouseEvent mouseEvent) {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();
    }
}
