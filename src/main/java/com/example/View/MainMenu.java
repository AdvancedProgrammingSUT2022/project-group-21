package com.example.View;

import com.example.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainMenu extends Application {
    public final static Stage stage = new Stage();

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainMenu/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @Override
    public void start(Stage stage) throws Exception {
        show();
    }
    public static void show() throws IOException {
        Scene scene = new Scene(loadFXML("MainMenu"));
        stage.setTitle("MainMenu");
        stage.setScene(scene);
        stage.show();
    }
}
