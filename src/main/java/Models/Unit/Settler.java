package Models.Unit;

import Models.City;
import Models.Civilization;
import Models.Tile.Tile;

public class Settler extends CivilianUnit{
	
	public Settler(Civilization owner, Tile tile){
		super(UnitType.SETTLER, owner, tile);
	}

}
