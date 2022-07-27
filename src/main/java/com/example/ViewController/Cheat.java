package com.example.ViewController;

import com.example.App;
import com.example.Contoller.GameController;
import com.example.Model.CheatCode;
import com.example.Model.Game;
import com.example.Model.UserAction.CivilizationActionType;
import com.example.Model.UserAction.CivilizationUserAction;
import com.example.Model.UserAction.UserActionQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class Cheat{
    public TextField CheatText;
    public Button cheatBtn;
    public ComboBox cheat_available;
    private ArrayList<String> cheat;

    public static void show() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("Cheat"));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    private void initialize() {
        cheat = new ArrayList<>();
        ArrayList<CheatCode> cheatCodes = CheatCode.getAllCheated();
        for (CheatCode cheatCode : cheatCodes) {
            cheat_available.getItems().add(cheatCode.name());
            cheat.add(cheatCode.name());
        }
    }
    public void CheatFunc(ActionEvent actionEvent) {
        checkToCheat();
    }

    private void checkToCheat() {
        String cheatStr = CheatText.getText();
        if (!cheat.contains(cheatStr)) {
            Dialog.error_message("Error", "Cheat not found!");
            return;
        }
        UserActionQuery userActionQuery = CivilizationUserAction.enterCheatCode(
                Game.getInstance().getCurrentPlayer().getUsername(), cheatStr);
        if (GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "cheated successfully!");
            close();
        }
    }

    public void OnKeyPressedCheatText(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            checkToCheat();
        }
    }

    private void close() {
        Stage stage = (Stage) cheatBtn.getScene().getWindow();
        stage.close();
    }
}


