package Models.Unit;

import Models.Civilization;
import Models.Tile.Tile;

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
