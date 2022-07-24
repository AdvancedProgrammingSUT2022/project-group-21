package com.example.ViewController.popupController;

import com.example.Contoller.GameController;
import com.example.Model.Game;
import com.example.Model.Technology;
import com.example.Model.UserAction.CivilizationUserAction;
import com.example.Model.UserAction.UserActionQuery;
import com.example.ViewController.Dialog;
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
        String cheatString = Dialog.AskQuestion("cheat", "enter cheatString");
        UserActionQuery userActionQuery = CivilizationUserAction.enterCheatCode(
                Game.getInstance().getCurrentPlayer().getUsername(), cheatString
        );
        if (GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "cheated successfully!");
        }
    }
    @FXML
    public void research(MouseEvent mouseEvent) {
        Technology technology = null;
        UserActionQuery userActionQuery = CivilizationUserAction.setResearch(
                Game.getInstance().getCurrentPlayer().getUsername(), technology
        );
        if (GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "next turned successfully!");
        }
    }
    @FXML
    public void endTurn(MouseEvent mouseEvent) {
        UserActionQuery userActionQuery = CivilizationUserAction.endTurn(
                Game.getInstance().getCurrentPlayer().getUsername()
        );
        if (GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "next turned successfully!");
        }
    }
    @FXML
    public void back(MouseEvent mouseEvent) {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();
    }
}
