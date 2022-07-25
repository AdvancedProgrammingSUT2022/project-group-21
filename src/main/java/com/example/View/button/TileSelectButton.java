package com.example.View.button;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;
import com.example.ViewController.Dialog;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TileSelectButton extends CustomGameButton {

	public TileSelectButton(Tile tile) {
		super(tile);
	}

	@Override
	public void update(Civilization civilization) {
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				// todo: terrain, terin feature, resource, improvement, ownner
				String test = "";
				test += tile.getTerrain().name() + "\n";
				test += "(" + tile.X + ", " + tile.Y + ")\n";
				Dialog.information_message("status", test);
			}
		});
		
	}
	
	

}
