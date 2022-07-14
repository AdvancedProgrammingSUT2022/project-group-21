package com.example.Model.unit;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;

public abstract class CivilianUnit extends Unit{
	CivilianUnit(UnitType unitType, Civilization owner, Tile tile){
		super(unitType, owner, tile);
	}
	
	@Override
	public void moveToTile(Tile tile){
		// this.tile.setCivilianUnit(null);
		super.moveToTile(tile);
		// this.tile.setCivilianUnit(this);
	}

	@Override
	protected void removeFromTile() {
		getTile().setCivilianUnit(null);
	}

	@Override
	protected void addToTile() {
		getTile().setCivilianUnit(this);
	}
}
