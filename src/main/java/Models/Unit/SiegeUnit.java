package Models.Unit;

import Models.City;
import Models.Civilization;
import Models.Tile;

public class SiegeUnit extends MilitaryUnit{
	private Tile targetTile;

	public void preAttack(Tile tile) {
		//TODO
	}
	public boolean isInRange(Tile tile) {
		//TODO
		return false;
	}
	public void attackToCity(City city) {
		//TODO
	}

	public SiegeUnit(UnitType unitType, Civilization owner, Tile tile){
		super(unitType, owner, tile);
	}
}
