package com.example;

import java.io.IOException;
import java.util.ArrayList;

import com.example.Contoller.GameController;
import com.example.Contoller.UserController;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.unit.UnitType;
import com.example.View.GamePage;
import com.example.View.LoginPage;
import com.example.View.TechnologyTree;
import com.example.View.popup.CitySelectedView;
import com.example.View.popup.Popup;
import com.example.View.popup.TileSelectedView;
import com.example.View.popup.UnitSelectedView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
//		TO run Game Page uncomment this
//		GamePage.show(stage);

//		Popup popup = new UnitSelectedView();
//		popup.show();


//		LoginPage.show();
		//UnitSelectedView.show();
	}

	public static void main(String[] args) throws Exception{
		launch();
	}

}