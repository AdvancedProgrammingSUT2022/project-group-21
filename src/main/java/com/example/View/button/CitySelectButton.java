package com.example.View.button;

import com.example.Model.Civilization;
import com.example.Model.city.City;
import com.example.Model.tile.Tile;

import com.example.View.popup.CitySelectedView;
import com.example.View.popup.Popup;
import com.example.View.popup.TileSelectedView;
import com.example.View.popup.UnitSelectedView;
import com.example.ViewController.Dialog;
import com.example.ViewController.popupController.CitySelectedController;
import com.example.ViewController.popupController.TileSelectedController;
import com.example.ViewController.popupController.UnitSelectedController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.IOException;


public class CitySelectButton extends CustomGameButton {

	public CitySelectButton(Tile tile) {
		super(tile);
	}
	public void update(Civilization civilization) {
		if(!civilization.isTileRevealed(tile.X, tile.Y))
		{
			this.setVisible(false);
			this.setOnMouseClicked(null);
			this.setCursor(Cursor.DEFAULT);
			return;
		}
		else
		{
			this.setVisible(true);
		}
		if(tile.getOwner()!=civilization) {
			this.setOnMouseClicked(null);
			this.setCursor(Cursor.DEFAULT);
			return;
		}
		else{
			this.setCursor(Cursor.HAND);
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (mouseEvent.isShiftDown()) {
						CitySelectedController.setCity(tile.getCityOnTile());
						Popup popup = new CitySelectedView();
						try {
							popup.show();
						} catch (Exception e) {
							Dialog.error_message("Error", e.getMessage());
						}
					} else {
						TileSelectedController.setCivilization(tile.getOwner());
						Popup popup = new TileSelectedView();
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
	
}
