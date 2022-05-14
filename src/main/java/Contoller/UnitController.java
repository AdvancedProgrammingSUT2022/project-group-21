package Contoller;

import Enums.Message;
import Models.Civilization;
import Models.Technology;
import Models.Tile.Tile;
import Models.Tile.Improvement;
import Models.Unit.CivilianUnit;
import Models.Unit.MilitaryUnit;
import Models.Unit.Unit;
import Models.Unit.UnitType;

public class UnitController {
	private static UnitController instance;
	public static UnitController getInstance(){
		if (instance==null) instance=new UnitController();
		return instance;
	}





	public Message createUnit(Civilization civilization, UnitType unitType, Tile tile){
		if (civilization.getGold()<unitType.cost) return Message.NOT_ENOUGH_GOLD;
		if (unitType==UnitType.WORKER || unitType==UnitType.SETTLER){
			if (tile.getCivilianUnit()!=null) return Message.FAIL;
		}
		else if (tile.getMilitaryUnit()!=null) return Message.FAIL;
		for (Technology technology : unitType.technologyRequired) {
			if (!civilization.hasTechnology(technology))
				return Message.TECHNOLOGY_FAIL;
		}
		civilization.addGold(-unitType.cost);
		Unit unit=unitType.createUnit(civilization, tile);
		civilization.addUnit(unit);
		unit.setTile(tile);
		if (unit instanceof MilitaryUnit) tile.setMilitaryUnit((MilitaryUnit) unit);
		else tile.setCivilianUnit((CivilianUnit) unit);
		return Message.SUCCESS;
	}
}
