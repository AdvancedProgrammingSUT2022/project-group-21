package com.example.Contoller;

import java.util.ArrayList;

import com.example.Model.Building;
import com.example.Model.City;
import com.example.Model.CityProject;
import com.example.Model.CityProjectBuilding;
import com.example.Model.CityProjectUnit;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.tile.Tile;
import com.example.Model.unit.UnitType;

public class CityActionController {
	private static CityActionController instance;
	public static CityActionController getInstance(){
		if (instance==null) instance=new CityActionController();
		return instance;
	}

	public void buyUnit(City city, UnitType unitType, boolean doAction) throws Exception{
		Civilization owner = city.getOwner();
		if (owner.getGold()<unitType.cost) throw new Exception("not enough gold");
		CityProject project = new CityProjectUnit(city, unitType);
		if (!project.isValid()) throw new Exception("you cant buy this unit");
		if (!city.CanSpawnUnit(unitType)) throw new Exception("you dont have empty tile to put a unit");
		if (doAction){
			owner.addGold(-unitType.cost);
			city.spawnUnit(unitType);	
		}
	}
	
	public void produceUnit(City city, UnitType unitType, boolean doAction) throws Exception{
		CityProject project = new CityProjectUnit(city, unitType);
		if (!project.isValid()) throw new Exception("you cant produce this unit");
		if (!city.CanSpawnUnit(unitType)) throw new Exception("you dont have empty tile to put a unit");
		if (doAction){
			city.setProduction(project);
		}
	}
	
	public void buyBuilding(City city, Building building, boolean doAction) throws Exception{
		Civilization owner = city.getOwner();
		if (owner.getGold()<building.cost) throw new Exception("not enough gold");
		CityProject project = new CityProjectBuilding(city, building);
		if (!project.isValid()) throw new Exception("you cant buy this building");
		if (doAction){
			owner.addGold(-building.cost);
			city.addBuilding(building);	
		}
	}
	
	public void produceBuilding(City city, Building building, boolean doAction) throws Exception{
		CityProject project = new CityProjectBuilding(city, building);
		if (!project.isValid()) throw new Exception("you cant produce this building");
		if (doAction){
			city.setProduction(project);
		}
	}
	
	public void buyTile(City city, int x2, int y2, boolean doAction) throws Exception{
		Civilization owner = city.getOwner();
		if (!Game.getInstance().checkTileCoordinates(x2, y2)) throw new Exception("target tile does not exist");
		Tile tile = Game.getInstance().getTile(x2, y2);
		ArrayList<Tile> buyableTiles = city.getPossibleTilesToBuy();
		if (!buyableTiles.contains(tile)) throw new Exception("you can not buy this tile");
		if (owner.getGold()<50) throw new Exception("not enough gold");
		if (doAction){
			owner.addGold(-50);
			city.addTile(tile);	
		}
	}

	public void lockUnlockCitizenToTile(City city, int x2, int y2, boolean doAction) throws Exception{
		if (!Game.getInstance().checkTileCoordinates(x2, y2)) throw new Exception("target tile does not exist");
		Tile tile = Game.getInstance().getTile(x2, y2);
		if (!city.hasTile(tile)) throw new Exception("this tile doesnt belong to your city");
		if (doAction){
			city.lockUnlockCitizen(tile);
		}
	}

	public void shootTile(City city, int x2, int y2, boolean doAction) throws Exception{
		if (city.hasAttackedInThisTurn()) throw new Exception("this city has already shot a tile in this turn");
		if (!Game.getInstance().checkTileCoordinates(x2, y2)) throw new Exception("target tile does not exist");
		Tile tile = Game.getInstance().getTile(x2, y2);
		if (!city.isTileInRange(tile)) throw new Exception("tile is not in range of 2");
		if (doAction){
			CombatController.getInstance().cityAttackTile(city, tile);
		}
	}

}
