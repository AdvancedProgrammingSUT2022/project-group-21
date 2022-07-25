package com.example.View;

import com.example.App;
import com.example.Contoller.GameController;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.unit.UnitType;
import com.example.Model.user.User;
import com.example.ViewController.Dialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GamePage {
	// TODO: remove class
	public static void show(ArrayList<String> users) throws Exception {
		// Dialog.information_message("", "please wait to loading...");
		Stage stage = new Stage();
		GameController.getInstance().startNewGame(users);
		Civilization civ = Game.getInstance().getCurrentPlayer().getCivilization();

		UnitType.WORKER.createUnit(civ, Game.getInstance().getTile(2, 2));
		UnitType.TANK.createUnit(civ, Game.getInstance().getTile(2, 3));

		Scene scene = new Scene(App.loadFXML("GamePage"));
		stage.setScene(scene);
		stage.show();
	}
}
