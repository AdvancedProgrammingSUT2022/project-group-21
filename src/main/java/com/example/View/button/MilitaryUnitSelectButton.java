package com.example.View.button;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;
import com.example.View.popup.Popup;
import com.example.View.popup.UnitSelectedView;
import com.example.ViewController.Dialog;
import com.example.ViewController.popupController.UnitSelectedController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MilitaryUnitSelectButton extends CustomGameButton{

	public MilitaryUnitSelectButton(Tile tile) {
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
			return;
		}
		else{
			this.setVisible(true);
			this.setCursor(Cursor.HAND);
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					UnitSelectedController.setUnit(tile.getMilitaryUnit());
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
	
}
