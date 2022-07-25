package com.example.View.button;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;

import javafx.scene.shape.Rectangle;

public abstract class CustomGameButton extends Rectangle{
	protected Tile tile;
	
	public CustomGameButton(Tile tile){
		this.tile=tile;
	}

	public abstract void update(Civilization civilization);
}
