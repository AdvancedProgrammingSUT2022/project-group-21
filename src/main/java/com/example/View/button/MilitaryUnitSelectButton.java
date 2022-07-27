package com.example.View.button;

import java.io.IOException;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;
import com.example.Model.unit.Unit;
import com.example.View.popup.Popup;
import com.example.View.popup.UnitSelectedView;
import com.example.ViewController.popupController.UnitSelectedController;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MilitaryUnitSelectButton extends CustomGameButton {

	public MilitaryUnitSelectButton(Tile tile) {
		super(tile);
	}

	public void update(Civilization civilization) {
		Unit unit = tile.getMilitaryUnit();
		setOnMouseClicked(null);
		setCursor(Cursor.DEFAULT);
		if (unit == null) {
			setFill(Color.TRANSPARENT);
			return;
		}
		setFill(unit.unitType.imagePattern);
		if (!civilization.isTileRevealed(tile.X, tile.Y)) {
			setVisible(false);
			return;
		}
		setVisible(true);
		if (tile.getOwner() != civilization)
			return;

		setVisible(true);
		setCursor(Cursor.HAND);
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				UnitSelectedController.setIsMilitary(true);
				UnitSelectedController.setUnit(unit);
				Popup popup = new UnitSelectedView();
				try {
					popup.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
