package Models.Unit;

import Models.City;
import Models.Civilization;
import Models.Tile;

public class MeleeUnit extends MilitaryUnit{

	public void attackToUnit(Tile tile) {
		//TODO
	}
	public void attackToCity(City city) {
		//TODO
	}


	public MeleeUnit(UnitType unitType, Civilization owner, Tile tile) {
		super(unitType, owner, tile);
	}
}
