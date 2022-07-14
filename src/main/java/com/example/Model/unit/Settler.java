package com.example.Model.unit;

import com.example.Model.Civilization;
import com.example.Model.city.City;
import com.example.Model.tile.Tile;

public class Settler extends CivilianUnit{
	
	public Settler(Civilization owner, Tile tile){
		super(UnitType.SETTLER, owner, tile);
	}

}
