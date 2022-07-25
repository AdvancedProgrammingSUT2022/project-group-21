package com.example.ViewController;

import java.util.ArrayList;

import com.example.App;
import com.example.Contoller.GameController;
import com.example.Contoller.UserController;
import com.example.Model.GameHistory;
import com.example.Model.user.UserDatabase;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuController {
	@FXML
	public Pane mainMenuPane;
	@FXML
	public ImageView startButton;
	@FXML
	public ImageView exitButton;
	@FXML
	public ImageView loadButton;
	@FXML
	public ImageView extraButton;

	@FXML
	public void startFunction(MouseEvent mouseEvent){
		int n;
		ArrayList<String> users = new ArrayList<>();
		users.add(UserController.getInstance().getLoggedInUser().getUsername());
		
		try {
			n = Integer.parseInt(Dialog.AskQuestion("Number of players", "How many players are there?"));
		} catch (Exception e) {
			Dialog.error_message(":(", "You weren't supposed to do that!");
			return;
		}
		
		for (int i = 2; i <= n; i++) {
			String string = Dialog.AskQuestion("Username", "Enter " + i + "th username please!");
			if (string==null) return ;
			if (UserDatabase.getInstance().getUserByUsername(string) == null) {
				Dialog.error_message(":(", "No such user!");
				return ;
			}
			if (users.contains(string)){
				Dialog.error_message(":(", "Duplicate User");
				return ;
			}
			users.add(string);
		}
		if (users.size() < 2) {
			Dialog.error_message("Error", "at least 2 player required!");
			return;
		}
		try {
			GameController.getInstance().startNewGame(users);
			App.setRootFromFXML("GamePage");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void extraFunction(MouseEvent mouseEvent) {
	}

	@FXML
	public void exitFunction(MouseEvent mouseEvent) {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void loadFunction(MouseEvent mouseEvent) {
		GameHistory.loadFromFile();
		// TODO: debug
	}
	public void mouseEnter(MouseEvent mouseEvent) {
		startButton.setCursor(Cursor.HAND);
	}

	public void mouseExit(MouseEvent mouseEvent) {
		startButton.setCursor(Cursor.DEFAULT);
	}
}
