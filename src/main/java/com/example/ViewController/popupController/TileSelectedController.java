package com.example.ViewController.popupController;

import com.example.Contoller.GameController;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.Technology;
import com.example.Model.UserAction.CivilizationUserAction;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.city.Building;
import com.example.ViewController.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

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

    private static Civilization civilization;

    public static void setCivilization(Civilization c) {
        civilization = c;
    }

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
        if (civilization == null) {
            Dialog.error_message("Error", "please select a civilization first!");
            return;
        }
        Technology technology = null;
        try {
            ArrayList<String> list = new ArrayList<>();
            ArrayList<Technology> bf = Technology.getAllPossibleTechnology(civilization);
            for (Technology t : bf) {
                list.add(t.name());
            }
            String type = Dialog.selectFromComboBox("select a Technology", list);
            for (Technology t : bf) {
                if (t.name().equals(type)) {
                    technology = t;
                    break;
                }
            }
        } catch (Exception e) {
            Dialog.error_message("Error", e.getMessage());
            return;
        }
        UserActionQuery userActionQuery = CivilizationUserAction.setResearch(
                Game.getInstance().getCurrentPlayer().getUsername(), technology
        );
        if (GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "done successfully!");
        }
    }
    @FXML
    public void endTurn(MouseEvent mouseEvent) {
        UserActionQuery userActionQuery = CivilizationUserAction.endTurn(
                Game.getInstance().getCurrentPlayer().getUsername()
        );
        if (GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "end turned successfully!");
        }
    }
    @FXML
    public void back(MouseEvent mouseEvent) {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();
    }
}
