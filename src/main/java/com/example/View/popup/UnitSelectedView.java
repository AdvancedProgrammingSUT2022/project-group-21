package com.example.View.popup;

import com.example.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UnitSelectedView extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }
    public static void show() throws IOException {
        Scene scene = new Scene(loadFXML("UnitSelectedPopup"));
        Stage stage = new Stage();
        stage.setTitle("selected unit");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
