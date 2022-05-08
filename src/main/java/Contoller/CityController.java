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

public class CityController {
	private static CityController instance;
	public static CityController getInstance(){
		if (instance==null) instance=new CityController();
		return instance;
	}

	
	private HashMap<Tile, City> cities = new HashMap<>();

	public Message findCity(Civilization civilization, Tile tile, Unit unit){
		if (unit.unitType!=UnitType.SETTLER) return Message.UNIT_NOT_SETTLER;
		if (tile.getOwner()!=civilization) return Message.TILE_NOT_OWNED;
		if (tile.getTerrain().equals(Terrain.OCEAN) || tile.getTerrain().equals(Terrain.MOUNTAIN)) return Message.UNSUITABLE_TERRAIN;
		City city = new City(tile, civilization);
		civilization.addCity(city);
		cities.put(tile, city);
		return Message.SUCCESS;
	}

	public City getCityOnTile(Tile tile){ return cities.get(tile);}

	public Message assignCitizenToTile(Tile tile, City city){
		if (city==null) return Message.NO_CITY_ON_TILE;
		if (!city.hasTile(tile)) return Message.FAIL;
		if (city.countCitizens()==0) return Message.FAIL;
		if (tile.getWorkingCitizen()!=null) return Message.ALREADY_ASSIGNED;
		Citizen citizen=city.getCitizenToAssign();
		tile.setCitizen(citizen);
		citizen.setWorkingTile(tile);
		return Message.SUCCESS;
	}

	public Message removeCitizenFromWork(Tile tile){
		// TODO
		return Message.SUCCESS;
	}



	public int getFoodOut(){
		// TODO
		return -1;
	}
	public int getProductionOut(){
		// TODO
		return -1;
	}
	public int getGoldOut(){
		// TODO
		return -1;
	}
	public int getScienceOut(){
		// TODO
		return -1;
	}

	
	public Message buyTile(Tile tile, City city){
		// TODO
		return Message.SUCCESS;
	}
	
	
	public Message createUnit(City city, UnitType unitType){
		// TODO
		return Message.SUCCESS;
	}
	





}
