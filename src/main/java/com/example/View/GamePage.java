package com.example.View;

import com.example.App;
import com.example.Contoller.GameController;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.unit.UnitType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GamePage extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void show(Stage stage) throws Exception {
        ArrayList<String> users = new ArrayList<>();
        users.add("test");
        users.add("test2");
        GameController.getInstance().startNewGame(users);
        Civilization civ = Game.getInstance().getCurrentPlayer().getCivilization();

        UnitType.WORKER.createUnit(civ, Game.getInstance().getTile(2, 2));
        UnitType.TANK.createUnit(civ, Game.getInstance().getTile(2, 3));

        Scene scene = new Scene(loadFXML("GamePage"));
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
