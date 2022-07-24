package com.example.View.popup;

import com.example.App;
import com.example.Model.city.City;
import com.example.Model.tile.Tile;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class CitySelectedView extends Application implements Popup {

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void show() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML("CitySelectedPopup"));
        stage.setTitle("selected City");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
