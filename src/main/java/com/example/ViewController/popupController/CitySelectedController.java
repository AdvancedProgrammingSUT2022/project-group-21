package com.example.ViewController.popupController;

import java.util.ArrayList;

import com.example.Contoller.GameController;
import com.example.Model.Game;
import com.example.Model.UserAction.CityUserAction;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.city.Building;
import com.example.Model.city.City;
import com.example.Model.tile.Tile;
import com.example.Model.unit.UnitType;
import com.example.ViewController.Dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CitySelectedController {
	@FXML
	public Button LOCK_UNLOCK_CITIZEN;
	@FXML
	public Button SHOOT;
	@FXML
	public Button UNIT_PRODUCTION;
	@FXML
	public Button back;
	@FXML
	public Button building_production;
	@FXML
	public Button buy_building;
	@FXML
	public Button buy_unit;
	@FXML
	private AnchorPane pane;

	private static City city;

	public static void setCity(City c) {
		city = c;
	}

	public void back(MouseEvent mouseEvent) {
		Stage stage = (Stage) back.getScene().getWindow();
		stage.close();
	}

	public void LOCK_UNLOCK_CITIZEN_func(MouseEvent mouseEvent) {
		if (city == null) {
			Dialog.error_message("Error", "please select a city first!");
			return;
		}
		int x1 = city.getCenter().X;
		int y1 = city.getCenter().Y;
		int x2 = 0;
		int y2 = 0;
		try {
			ArrayList<Tile> tiles = city.getLockedTiles();
			ArrayList<String> str = new ArrayList<>();
			for (Tile tile : tiles) {
				str.add("Tile on(" + tile.X + ", " + tile.Y + ")");
			}
			String selected = Dialog.selectFromComboBox("select a tile", str);
			for (int i = 0; i < str.size(); i++) {
				String s = "Tile on(" + tiles.get(i).X + ", " + tiles.get(i).Y + ")";
				if (s.equals(selected)) {
					x2 = tiles.get(i).X;
					y2 = tiles.get(i).Y;
					break;
				}
			}
			if (selected.equals("") || selected.equals("null") || selected == null) {
				Dialog.error_message("Error", "some error occur!");
				return;
			}
		}catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
		}
		UserActionQuery userAction = CityUserAction
				.lockUnlockCitizenToTile(Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, x2, y2);
		if (GameController.getInstance().handleQueryFromView(userAction)) {
			Dialog.information_message("", "done successfully");
		}
	}

	public void SHOOT_func(MouseEvent mouseEvent) {
		if (city == null) {
			Dialog.error_message("Error", "please select a city first!");
			return;
		}
		int x1 = city.getCenter().X;
		int y1 = city.getCenter().Y;
		int x2;
		int y2;
		try {
			x2 = Integer.parseInt(
					Dialog.AskQuestion("", "x2:"));
			y2 = Integer.parseInt(
					Dialog.AskQuestion("", "y2:"));
		} catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return;
		}
		UserActionQuery userAction = CityUserAction.shootTile(Game.getInstance().getCurrentPlayer().getUsername(), x1,
				y1, x2, y2);
		if (GameController.getInstance().handleQueryFromView(userAction)) {
			Dialog.information_message("", "done successfully");
		}
	}

	public void UNIT_PRODUCTION_func(MouseEvent mouseEvent) {
		if (city == null) {
			Dialog.error_message("Error", "please select a city first!");
			return;
		}
		int x1 = city.getCenter().X;
		int y1 = city.getCenter().Y;
		UnitType unitType = null;
		try {
			ArrayList<String> list = new ArrayList<>();
			ArrayList<UnitType> bf = UnitType.getAllPossibleUnitsForCity(city);
			for (UnitType u : bf) {
				list.add(u.name());
				// todo: check to work correctly
			}
			String type = Dialog.selectFromComboBox("select a unit type", list);
			for (UnitType u : bf) {
				if (u.name().equals(type)) {
					unitType = u;
					break;
				}
			}
		} catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return;
		}
		UserActionQuery userAction = CityUserAction.produceUnit(Game.getInstance().getCurrentPlayer().getUsername(), x1,
				y1, unitType);
		if(GameController.getInstance().handleQueryFromView(userAction)) {
			Dialog.information_message("", "done successfully");
		}
	}

	public void building_production_func(MouseEvent mouseEvent) {
		if (city == null) {
			Dialog.error_message("Error", "please select a city first!");
			return;
		}
		int x1 = city.getCenter().X;
		int y1 = city.getCenter().Y;
		Building building = null;
		try {
			ArrayList<String> list = new ArrayList<>();
			ArrayList<Building> bf = Building.getAllPossibleBuildingsToBuild(city);
			for (Building b : bf) {
				list.add(b.name());
			}
			String type = Dialog.selectFromComboBox("select a building type", list);
			for (Building b : bf) {
				if (b.name().equals(type)) {
					building = b;
					break;
				}
			}
		} catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return;
		}
		UserActionQuery userAction = CityUserAction.produceBuilding(
				Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, building);
		if (GameController.getInstance().handleQueryFromView(userAction)) {
			Dialog.information_message("", "done successfully");
		}
	}

	public void buy_tile_func(MouseEvent mouseEvent) {
		if (city == null) {
			Dialog.error_message("Error", "please select a city first!");
			return;
		}
		int x1 = city.getCenter().X;
		int y1 = city.getCenter().Y;
		int x2 = 0;
		int y2 = 0;
		try {
			ArrayList<Tile> tiles = city.getPossibleTilesToBuy();
			ArrayList<String> str = new ArrayList<>();
			for (Tile tile : tiles) {
				str.add("Tile on(" + tile.X + ", " + tile.Y + ")");
			}
			String selected = Dialog.selectFromComboBox("select a tile to buy", str);
			for (int i = 0; i < str.size(); i++) {
				String s = "Tile on(" + tiles.get(i).X + ", " + tiles.get(i).Y + ")";
				if (s.equals(selected)) {
					x2 = tiles.get(i).X;
					y2 = tiles.get(i).Y;
					break;
				}
			}
			if (selected.equals("") || selected.equals("null") || selected == null) {
				Dialog.error_message("Error", "some error occur!");
				return;
			}
		}catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
		}
		UserActionQuery userAction = CityUserAction.buyTile(Game.getInstance().getCurrentPlayer(
		).getUsername(), x1, y1, x2, y2);
		if(GameController.getInstance().handleQueryFromView(userAction)) {
			Dialog.information_message("", "done successfully");
		}
	}

	public void buy_building_func(MouseEvent mouseEvent) {
		if (city == null) {
			Dialog.error_message("Error", "please select a city first!");
			return;
		}
		int x1 = city.getCenter().X;
		int y1 = city.getCenter().Y;
		Building building = null;
		try {
			ArrayList<String> list = new ArrayList<>();
			ArrayList<Building> bf = Building.getAllPossibleBuildingsToBuild(city);
			for (Building b : bf) {
				list.add(b.name());
				// todo: check to work correctly
			}
			String type = Dialog.selectFromComboBox("select a building type", list);
			for (Building b : bf) {
				if (b.name().equals(type)) {
					building = b;
					break;
				}
			}
		} catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return;
		}
		UserActionQuery userAction = CityUserAction.buyBuilding(Game.getInstance().getCurrentPlayer().getUsername(), x1,
				y1, building);
		if (GameController.getInstance().handleQueryFromView(userAction)) {
			Dialog.information_message("", "done successfully");
		}
	}

	public void buy_unit_func(MouseEvent mouseEvent) {
		if (city == null) {
			Dialog.error_message("Error", "please select a city first!");
			return;
		}
		int x1 = city.getCenter().X;
		int y1 = city.getCenter().Y;
		UnitType unitType = null;
		try {
			ArrayList<String> list = new ArrayList<>();
			ArrayList<UnitType> bf = UnitType.getAllPossibleUnitsForCity(city);
			for (UnitType u : bf) {
				list.add(u.name());
			}
			String type = Dialog.selectFromComboBox("select a unit type", list);
			for (UnitType u : bf) {
				if (u.name().equals(type)) {
					unitType = u;
					break;
				}
			}
		} catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return;
		}
		UserActionQuery userAction = CityUserAction.buyUnit(
				Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, unitType);
		if (GameController.getInstance().handleQueryFromView(userAction)) {
			Dialog.information_message("", "done successfully");
		}
	}
}
