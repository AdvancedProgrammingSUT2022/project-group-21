package com.example;

import java.io.IOException;

import com.example.View.LoginPage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		LoginPage.show();
	}

	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) throws Exception{
		launch();
	}

}