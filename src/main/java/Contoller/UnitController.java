package Contoller;

import java.util.HashMap;

import Enums.Message;
import Models.Citizen;
import Models.City;
import Models.Civilization;
import Models.Terrain;
import Models.Tile;
import Models.Unit.Unit;
import Models.Unit.UnitType;

public class UnitController {
	private static UnitController instance;
	public static UnitController getInstance(){
		if (instance==null) instance=new UnitController();
		return instance;
	}

	


}
