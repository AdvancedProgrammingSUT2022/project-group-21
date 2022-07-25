package com.example;

import java.io.IOException;
import java.util.ArrayList;

import com.example.Contoller.GameController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("TechnologyTreePage"));
		// scene = new Scene(loadFXML("GamePage"));
		stage.setScene(scene);
		stage.show();

	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) throws Exception{
		// UserController.getInstance().registerUser("test2", "test1234", "Test2");
		// UserController.getInstance().registerUser("test", "test1234", "Test");
		
		ArrayList<String> users = new ArrayList<>();
		users.add("test");
		users.add("test2");
		GameController.getInstance().startNewGame(users, 1234);


		launch();
	}

}