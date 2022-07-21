package com.example.View.popup;

import com.example.App;
import com.example.Model.city.City;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class CitySelectedView extends Application {
    private City city;
    static private boolean show = false;

    public CitySelectedView(City city) {
        this.city = city;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void show() throws IOException {
//        TODO just for test when click an a button
        Scene scene = new Scene(loadFXML("citySelectedPopup"));
        Stage stage = new Stage();
        stage.setTitle("selected City");
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
