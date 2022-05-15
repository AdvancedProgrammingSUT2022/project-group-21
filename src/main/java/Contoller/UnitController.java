package Contoller;

import Enums.Message;
import Models.City;
import Models.Civilization;
import Models.Technology;
import Models.Tile.Improvement;
import Models.Tile.Tile;
import Models.Unit.*;

public class UnitController {
	private static UnitController instance;

	public static UnitController getInstance(){
		if (instance==null) instance=new UnitController();
		return instance;
	}

	public String  moveUnit(int x, int y) {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		Tile selectedTile = GameController.getInstance().getGame().getTile(x, y);
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		int mp = selectedUnit.getMP();
		//TODO
		if (Math.abs(selectedUnit.getTile().X - x) + Math.abs(selectedUnit.getTile().Y - y) > mp)
			return Message.OUT_OF_MP.toString();
		selectedUnit.setTile(selectedTile);
		return Message.SUCCESS.toString();
	}

	public Message sleep() {
		//TODO
		return null;
	}

	//TODO: Alert, Fortify(,heal),

	public String setupForRangedAttack(int x, int y) {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.unitType != UnitType.Trebuchet || selectedUnit.unitType != UnitType.Artillery ||
				selectedUnit.unitType != UnitType.Canon || selectedUnit.unitType != UnitType.CATAPULT)
			return "Selected Unit does not pre attack";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		MilitaryUnit siegeUnit = (MilitaryUnit) selectedUnit;
		siegeUnit.siegePreAttack(GameController.getInstance().getGame().getTile(x, y));
		return "Success";
	}

	public String garrison() {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		Tile unitsTile = SelectController.getInstance().getSelectedUnit().getTile();
		City capital = unitsTile.getCapitalCity();
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		if (capital == null)
			return "This Unit is not on any City";
		if (!(selectedUnit instanceof MilitaryUnit))
			return "Selected Unit is not Military";
		//TODO: check if city is this turn's Civilization
		//TODO: define a function for MilitaryUnit :garrison
		return Message.SUCCESS.toString();
	}

	public String attack(int x, int y) {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		Tile selectedTile = GameController.getInstance().getGame().getTile(x, y);
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		if (!(selectedUnit instanceof MilitaryUnit))
			return "Selected Unit is not Military";
		MilitaryUnit miliUnit = (MilitaryUnit) selectedUnit;
		if (!miliUnit.isInRange(selectedTile))
			return "Out of Range!";
		//TODO: check other conditions
		if (selectedTile.getCapitalCity() == null)
			miliUnit.attackToUnit(selectedTile);
		else
			miliUnit.attackToCity(selectedTile.getCapitalCity());
		return Message.SUCCESS.toString();
	}

	public String foundCity() {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		if (selectedUnit.unitType != UnitType.SETTLER)
			return "Selected unit is not a settler";
		//TODO: Check other conditions
		//TODO: define function for settler to find a city
		return Message.SUCCESS.toString();
	}

	//TODO: Cancel mission method
	//TODO: wake Unit from sleep

	public String delete() {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		GameController.getInstance().getGame().getCurrentPlayer().getCivilization().removeUnit(selectedUnit);
		return Message.SUCCESS.toString();
	}

	public String buildRoad() {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		if (selectedUnit.unitType != UnitType.WORKER)
			return "Selected unit is not a worker!";
		if (selectedUnit.getTile().hasRoad())
			return "already constructed";
		Worker selectedWorker = (Worker)selectedUnit;
		selectedWorker.creatRoad();
		return Message.SUCCESS.toString();
	}

	public String buildImprovement(Improvement improvement) {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		if (selectedUnit.unitType != UnitType.WORKER)
			return "Selected unit is not a worker!";
		if (selectedUnit.getTile().getImprovement() != null)
			return "Improvement already constructed here!";
		Improvement[] improvements = Improvement.values();
		//TODO: check required technologies and terrain and Terrain features
		return Message.SUCCESS.toString();
	}

	public  String removeRoad() {
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		if (selectedUnit.unitType != UnitType.WORKER)
			return "Selected unit is not a worker!";
		if (!selectedUnit.getTile().hasRoad())
			return "No route in this tile!";
		//TODO
		return null;
	}

	public String removeJungle() {
		//TODO
		return null;
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
