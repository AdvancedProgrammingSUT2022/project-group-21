package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Contoller.GameController;
import com.example.Model.Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
	private static App instance;
	public static App getInstance(){ return instance;}

	private static Scene scene;
	private static Stage stage;

	@Override
	public void start(Stage stage2) throws Exception {
		instance = this;
		stage=stage2;
		scene = new Scene(loadFXML("LoginPage"));
		stage.setTitle("Login to game");
		stage.initStyle(StageStyle.UTILITY);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();

		// GameController.getInstance().startNewGame(new ArrayList<>(Arrays.asList("ali", "aryan")));
		// Game.getInstance().getPlayers().get(0).getCivilization().addGold(1000);
		// setRootFromFXML("GamePage");

	}

	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void setRootFromFXML(String fxml) throws IOException {
		if (fxml.equals("MainMenu")) stage.setWidth(960);
		if (fxml.equals("MainMenu")) stage.setHeight(600);
		scene.setRoot(loadFXML(fxml));
	}

	public static void main(String[] args) throws Exception{
		launch();
	}

	public static Stage getStage() {
		return stage;
	}
}