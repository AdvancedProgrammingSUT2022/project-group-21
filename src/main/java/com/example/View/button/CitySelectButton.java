package com.example.View.button;

import com.example.Model.Civilization;
import com.example.Model.city.City;
import com.example.Model.city.CityProject;
import com.example.Model.city.CityProjectBuilding;
import com.example.Model.city.CityProjectUnit;
import com.example.Model.tile.Tile;
import com.example.View.popup.CitySelectedView;
import com.example.View.popup.Popup;
import com.example.ViewController.Dialog;
import com.example.ViewController.popupController.CitySelectedController;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class CitySelectButton extends CustomGameButton {

	public CitySelectButton(Tile tile) {
		super(tile);
	}

	public void update(Civilization civilization) {
		City city = tile.getCityOnTile();
		setOnMouseClicked(null);
		if (city == null) {
			setVisible(false);
			return;
		}
		if (!civilization.isTileRevealed(tile.X, tile.Y)) {
			setVisible(false);
			return;
		} else {
			setVisible(true);
		}

		if (city.getOwner()!=civilization){
			setCursor(Cursor.DEFAULT);
			return ;
		}
		setCursor(Cursor.HAND);
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.isShiftDown()){
					String text="";
					text+="Gold Out: " + city.getGoldOut()+"\n";
					text+="Food Out: " + city.getFoodOut()+"\n";
					text+="Production Out: " + city.getProductionOut()+"\n";
					text+="Current Production: ";
					CityProject project = city.getProductionProject();
					if (project==null) text+="null!";
					else if (project instanceof CityProjectBuilding) text+="Building " + ((CityProjectBuilding) project).getBuilding().name;
					else text+="Unit " + ((CityProjectUnit) project).getUnitType();
					text+="\n";

					Dialog.information_message("City Info", text);
				}
				else{
					CitySelectedController.setCity(tile.getCityOnTile());
					Popup popup = new CitySelectedView();
					try {
						popup.show();
					} catch (Exception e) {
						Dialog.error_message("Error", e.getMessage());
					}
				}
			}
		});
	}

}
