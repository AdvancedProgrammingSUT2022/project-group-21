package com.example.ViewController;

import java.io.IOException;
import java.util.ArrayList;

import com.example.App;
import com.example.Contoller.GameController;
import com.example.Contoller.UserController;
import com.example.Model.Game;
import com.example.Model.GameHistory;
import com.example.Model.user.UserDatabase;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
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
	public Label start_label;
	public Label load_label;
	public Label extraLabel;
	public Label exit_label;

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
			GameController.getInstance().startNewGame(users,1234);
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
	public void loadFunction(MouseEvent mouseEvent) throws Exception {
		GameController.getInstance().joinGame();
	}
	public void mouseEnter(MouseEvent mouseEvent) {
		startButton.setCursor(Cursor.HAND);
		start_label.setCursor(Cursor.HAND);
	}

	public void mouseExit(MouseEvent mouseEvent) {
		startButton.setCursor(Cursor.DEFAULT);
		start_label.setCursor(Cursor.DEFAULT);
	}

	public void laodMouseEnter(MouseEvent mouseEvent) {
		loadButton.setCursor(Cursor.HAND);
		load_label.setCursor(Cursor.HAND);
	}

	public void ScoreBoardMouse_Enter(MouseEvent mouseEvent) {
		extraLabel.setCursor(Cursor.HAND);
		extraButton.setCursor(Cursor.HAND);
	}

	public void ExitMouseEnter(MouseEvent mouseEvent) {
		exitButton.setCursor(Cursor.HAND);
		exit_label.setCursor(Cursor.HAND);
	}

	public void laodMouseExit(MouseEvent mouseEvent) {
		load_label.setCursor(Cursor.DEFAULT);
		loadButton.setCursor(Cursor.DEFAULT);
	}

	public void ExitMouseExit(MouseEvent mouseEvent) {
		exitButton.setCursor(Cursor.DEFAULT);
		exit_label.setCursor(Cursor.DEFAULT);
	}

	public void ScoreBoardMouse_Exit(MouseEvent mouseEvent) {
		extraLabel.setCursor(Cursor.DEFAULT);
		extraButton.setCursor(Cursor.DEFAULT);
	}
}
