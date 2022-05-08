package Models.Unit;

import Models.City;
import Models.Civilization;
import Models.Tile;

public class Settler extends CivilianUnit{
	
	public Settler(Civilization owner, Tile tile){
		super(UnitType.SETTLER, owner, tile);
	}

	public void findCity(Tile tile){
		City city = new City(tile.getFood(),tile.getFood(),tile, owner);
		// TODO
	}
}
