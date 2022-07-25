package com.example.View.popup;

import com.example.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TileSelectedView extends Application implements Popup {
    @Override
    public void start(Stage stage) throws Exception {

    }
    public void show() throws IOException {
        Scene scene = new Scene(loadFXML("TileSelectedPopup"));
        Stage stage = new Stage();
        stage.setTitle("selected tile");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
