package Contoller;

import Enums.Message;
import Models.City;
import Models.Civilization;
import Models.Technology;
import Models.Tile.Improvement;
import Models.Tile.TerrainFeature;
import Models.Tile.Tile;
import Models.Unit.*;

import java.util.ArrayList;

public class UnitController {
	private static UnitController instance;

	public static UnitController getInstance(){
		if (instance==null) instance=new UnitController();
		return instance;
	}

	public Message sleep(Unit unit){
		//TODO
		return null;
	}
	public Message wake(Unit unit){
		//TODO
		return null;
	}
	public Message alert(Unit unit){
		//TODO
		return null;
	}
	
	public Message fortify(Unit unit){
		// TODO
		return null;
	}


	public String setupForRangedAttack(Unit unit, int x, int y) {
		// TODO: fix error
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.unitType.combatType!=CombatType.SIEGE)
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

	public String foundCity(){
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

	public String delete(){
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
			return "You have not selected any unit";
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		GameController.getInstance().getGame().getCurrentPlayer().getCivilization().removeUnit(selectedUnit);
		return Message.SUCCESS.toString();
	}

<<<<<<< HEAD
	public String buildRoad(Unit unit){
		// TODO: fix error
		if (unit == null)
=======
	public String buildRoad(){
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
>>>>>>> 3a7b99b1bf9801e70f036ef459c83736e24a57f8
			return "You have not selected any unit";
		if (unit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		if (unit.unitType != UnitType.WORKER)
			return "Selected unit is not a worker!";
		if (unit.getTile().hasRoad())
			return "already constructed";
		Worker selectedWorker = (Worker)unit;
		selectedWorker.buildRoad();
		return Message.SUCCESS.toString();
	}

<<<<<<< HEAD
	public String buildImprovement(Unit unit, Improvement improvement){
		if (unit == null)
=======
	public String buildImprovement(Improvement improvement){
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
>>>>>>> 3a7b99b1bf9801e70f036ef459c83736e24a57f8
			return "You have not selected any unit";
		if (unit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		if (unit.unitType != UnitType.WORKER)
			return "Selected unit is not a worker!";
		if (unit.getTile().getImprovement() != null)
			return "Improvement already constructed here!";
		Technology technology = improvement.prequisiteTech;
		if (unit.owner.hasTechnology(technology) && improvement.canBeBuiltOn(unit.getTile()))
			return Message.SUCCESS.toString();
		return "";
	}

<<<<<<< HEAD
	public String removeRoad(Unit unit){
		// TODO: fix error
		if (unit == null)
=======
	public String removeRoad(){
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		if (selectedUnit == null)
>>>>>>> 3a7b99b1bf9801e70f036ef459c83736e24a57f8
			return "You have not selected any unit";
		else if (unit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return "this unit is not yours";
		else if (unit.unitType != UnitType.WORKER)
			return "Selected unit is not a worker!";
		else if (!unit.getTile().hasRoad())
			return "No route in this tile!";
		//TODO
		else {
			((Worker) unit).removeRoad();
			return "Road removed succesfully";
		}
	}

	public String removeJungle(Unit unit){
		if (unit == null){
			return "You have not selected any unit";
		}
		else if (unit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization()){
			return "this unit is not yours";
		}
		else if (unit.unitType != UnitType.WORKER){
			return "Selected unit is not worker!";
		}
		else if (unit.getTile().getTerrainFeature().equals(TerrainFeature.FOREST)){
			return "There is no Jungle on this tile!";
		}
		else {
			((Worker) unit).removeJungle();
			return "Jungle removed succesfully!";
		}
	}


	public Message createUnit(Civilization civilization, Tile tile, UnitType unitType){
		// TODO
		
		return Message.SUCCESS;
	}
	
	public Message buyUnit(Civilization civilization, City city, UnitType unitType){
		if (civilization.getGold()<unitType.cost) return Message.NOT_ENOUGH_GOLD;
		for (Technology technology : unitType.technologyRequired) {
			if (!civilization.hasTechnology(technology))
				return Message.TECHNOLOGY_FAIL;
		}
		ArrayList<Tile> CityTiles = city.getTiles();
		for (Tile cityTile : CityTiles) {
			if (unitType == UnitType.WORKER || unitType == UnitType.SETTLER){
				if (cityTile.getCivilianUnit() != null){
					return Message.FAIL;
				}
			}
			else if (cityTile.getMilitaryUnit() != null){
				return Message.FAIL;
			}
			else if (!cityTile.getOwner().equals(civilization)){
				return Message.TILE_NOT_OWNED;
			}
			else {
				civilization.addGold(-unitType.cost);
				Unit unit=unitType.createUnit(civilization, cityTile);
				civilization.addUnit(unit);
				unit.setTile(cityTile);
				if (unit instanceof MilitaryUnit) cityTile.setMilitaryUnit((MilitaryUnit) unit);
				else cityTile.setCivilianUnit((CivilianUnit) unit);
				return Message.SUCCESS;
			}
		}
		return Message.SUCCESS;
	}
}
