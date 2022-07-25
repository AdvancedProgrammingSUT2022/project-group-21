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
		if (!civilization.isTileRevealed(tile.X, tile.Y)){
			setVisible(false);
			setOnMouseClicked(null);
			return ;
		}
		setVisible(true);
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				// todo: terrain, terin feature, resource, improvement, ownner
				String test = "";
				test += "position: (" + tile.X + ", " + tile.Y + ")\n";
				test += "terrain name: " + tile.getTerrain().name() + "\n";
				test += "terrain gold: " + tile.getTerrain().gold + "\n";
				test += "terrain food: " + tile.getTerrain().food + "\n";
				test += "terrain production: " + tile.getTerrain().production + "\n";
				try {
					test += "terrain feature name: " + tile.getTerrainFeature().name() + "\n";
				}catch (Exception e) {}
				try{
					test += "terrain feature gold: " + tile.getTerrainFeature().gold + "\n";
				}catch (Exception e) {}
				try{
					test += "terrain feature food: " + tile.getTerrainFeature().food + "\n";
				}catch (Exception e) {}
				try{
					test += "terrain feature production: " + tile.getTerrainFeature().production + "\n";
				}catch (Exception e) {}
				try{
					test += "Improvement: " + tile.getImprovement().name() + "\n";
				}catch (Exception e) {}
				try{
					test += " Improvement gold: " + tile.getImprovement().gold + "\n";
				}catch (Exception e) {}
				try{
						test += " Improvement food: " + tile.getImprovement().food + "\n";
				}catch (Exception e) {}
				try{
					test += "prequisiteTech Improvement: " + tile.getImprovement().prequisiteTech.name + "\n";
				}catch (Exception e) {}
				try{
					test += "is pillaged: " + tile.isPillaged() + "\n";
				}catch (Exception e) {}
				try{
					test += "count rivers: " + tile.countRivers() + "\n";
				}catch (Exception e) {}
				try{
					test += "movement cost: " + tile.getMovementCost() + "\n";
				}catch (Exception e) {}
				Dialog.information_message("status", test);
			}
		});
	}
	
	

}
