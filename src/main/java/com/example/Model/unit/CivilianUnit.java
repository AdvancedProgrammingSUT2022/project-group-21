package com.example.Model.unit;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;

public abstract class CivilianUnit extends Unit{
	CivilianUnit(UnitType unitType, Civilization owner, Tile tile){
		super(unitType, owner, tile);
	}
	

	@Override
	protected void removeFromTile() {
		getTile().setCivilianUnit(null);
	}

	@Override
	protected void addToTile() {
		getTile().setCivilianUnit(this);
	}

	public void changeOwner(Civilization owner){
		getOwner().removeUnit(this);
		this.owner = owner;
		getOwner().addUnit(this);
	}
	
}
