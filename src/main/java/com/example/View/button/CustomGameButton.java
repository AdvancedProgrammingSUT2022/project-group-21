package com.example.View.button;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;

public abstract class CustomGameButton {
	protected Tile tile;
	
	public CustomGameButton(Tile tile){
		this.tile=tile;
	}

	public abstract void update(Civilization civilization);
}
