package com.example.ViewController;

import com.example.Contoller.GameController;
import com.example.Contoller.UserController;
import com.example.Model.CheatCode;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.Technology;
import com.example.Model.UserAction.CivilizationUserAction;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.user.User;
import com.example.View.hexagons.MapPaneMaker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class GamePageViewController {
	private static GamePageViewController instance;

	@FXML
	private AnchorPane infoAnchorPane;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private void initialize() throws IOException {
		instance=this;
		scrollPane.setContent(MapPaneMaker.createScrollPane(Game.getInstance()));
		
		System.out.println("initialize done");
		GameController.getInstance().updateGraphic();
	}

	private boolean pdateGraphicForFirstTime = false;
	@FXML
	private void updateGraphicForFirstTime() {
		if (pdateGraphicForFirstTime)return;
		GameController.getInstance().updateGraphic();
		pdateGraphicForFirstTime = true;
	}

	private void addInfo(User user) {
		Label Label = new Label(user.getNickname());
		if (UserController.getInstance().getLoggedInUser() == user){
			Label.setText(Label.getText() + " (you)");
		}
		infoAnchorPane.getChildren().clear();
		Label.setLayoutX(0);
		Label.setLayoutY(infoAnchorPane.getHeight() / 2);
		infoAnchorPane.getChildren().add(Label);
		Label = new Label("Gold: " + Integer.toString(user.getCivilization().getGold()));
		Label.setLayoutX(infoAnchorPane.getWidth() / 5);
		Label.setLayoutY(infoAnchorPane.getHeight() / 2);
		infoAnchorPane.getChildren().add(Label);
		Label = new Label("Happiness: " + Integer.toString(user.getCivilization().getHappiness()));
		Label.setLayoutX(infoAnchorPane.getWidth() * 2 / 5);
		Label.setLayoutY(infoAnchorPane.getHeight() / 2);
		infoAnchorPane.getChildren().add(Label);
		Label = new Label("Score: " + Integer.toString(user.getCivilization().getScore()));
		Label.setLayoutX(infoAnchorPane.getWidth() * 3 / 5);
		Label.setLayoutY(infoAnchorPane.getHeight() / 2);
		infoAnchorPane.getChildren().add(Label);
		Label = new Label("Science: " + Integer.toString(user.getCivilization().getScience()));
		Label.setLayoutX(infoAnchorPane.getWidth() * 4 / 5);
		Label.setLayoutY(infoAnchorPane.getHeight() / 2);
		infoAnchorPane.getChildren().add(Label);
	}

	public static void showInfo(){
		instance.addInfo(Game.getInstance().getCurrentPlayer());
	}

    public void cheatFunc(MouseEvent mouseEvent) {
		CheatCode cheatCode = null;
		try {
			ArrayList<String> list = new ArrayList<>();
			ArrayList<CheatCode> bf = CheatCode.getAllCheated();
			for (CheatCode c : bf) {
				list.add(c.name());
			}
			String type = Dialog.selectFromComboBox("select a Cheat", list);
			for (CheatCode c : bf) {
				if (c.name().equals(type)) {
					cheatCode = c;
					break;
				}
			}
		} catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return;
		}
		cheatCode.apply(Game.getInstance().getCurrentPlayer().getCivilization());
		Dialog.information_message("", "cheated successfully!");
		GameController.getInstance().updateGraphic();
    }

    public void endTurn(MouseEvent mouseEvent) {
		UserActionQuery userActionQuery = CivilizationUserAction.endTurn(
				Game.getInstance().getCurrentPlayer().getUsername()
		);
		if (GameController.getInstance().handleQueryFromView(userActionQuery)) {
			Dialog.information_message("", "end turned successfully!");
			GameController.getInstance().updateGraphic();
		}
    }

	public void TechTreeFunc(MouseEvent mouseEvent) {
	}

	public void researchFun(MouseEvent mouseEvent) {
		Civilization civilization = Game.getInstance().getCurrentPlayer().getCivilization();
		if (civilization == null) {
			Dialog.error_message("Error", "please select a civilization first!");
			return;
		}
		Technology technology = null;
		try {
			ArrayList<String> list = new ArrayList<>();
			ArrayList<Technology> bf = Technology.getAllPossibleTechnology(civilization);
			for (Technology t : bf) {
				list.add(t.name());
			}
			String type = Dialog.selectFromComboBox("select a Technology", list);
			for (Technology t : bf) {
				if (t.name().equals(type)) {
					technology = t;
					break;
				}
			}
		} catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return;
		}
		UserActionQuery userActionQuery = CivilizationUserAction.setResearch(
				Game.getInstance().getCurrentPlayer().getUsername(), technology
		);
		if (GameController.getInstance().handleQueryFromView(userActionQuery)) {
			Dialog.information_message("", "done successfully!");
			GameController.getInstance().updateGraphic();
		}
	}
}
