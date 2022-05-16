package Contoller;

import java.util.ArrayList;
import java.util.HashMap;

import Enums.Message;
import Models.Citizen;
import Models.City;
import Models.Civilization;
import Models.Game;
import Models.Tile.Terrain;
import Models.Tile.Tile;
import Models.Unit.Unit;
import Models.Unit.UnitType;
import Models.Unit.Worker;

public class CityController {
	private static CityController instance;
	public static CityController getInstance(){
		if (instance==null) instance=new CityController();
		return instance;
	}

	
	private HashMap<Tile, City> cities = new HashMap<>();
	private ArrayList<Worker> improvementProjects = new ArrayList<>();

	public void addImprovementProject(Worker worker){ improvementProjects.add(worker);}
	public void removeImprovementProject(Worker worker){ improvementProjects.remove(worker);}
	public void handleImprovementProjects(){
		for (Worker worker : improvementProjects) {
			worker.decreaseProjectTurnsLeft();
		}
	}


	public Message findCity(){
		Unit unit = SelectController.getInstance().getSelectedUnit();
		Tile tile = unit.getTile();
		Civilization civilization = GameController.getInstance().getGame().getCurrentPlayer().getCivilization();
		if (unit.unitType!=UnitType.SETTLER) return Message.UNIT_NOT_SETTLER;
		if (tile.getOwner()!=civilization) return Message.TILE_NOT_OWNED;
		if (tile.getTerrain().equals(Terrain.OCEAN) || tile.getTerrain().equals(Terrain.MOUNTAIN)) return Message.UNSUITABLE_TERRAIN;
		City city = new City(tile, civilization);
		civilization.addCity(city);
		cities.put(tile, city);
		return Message.SUCCESS;
	}

	public City getCityOnTile(Tile tile){ return cities.get(tile);}

	public Message assignCitizenToTile(int x, int y){
		City selectedCity = SelectController.getInstance().getSelectedCity();
		Tile tile = GameController.getInstance().getGame().getTile(x, y);
		if (selectedCity==null) return Message.NO_CITY_ON_TILE;
		if (!selectedCity.hasTile(tile)) return Message.FAIL;
		if (selectedCity.countCitizens()==0) return Message.FAIL;
		if (tile.getWorkingCitizen()!=null) return Message.ALREADY_ASSIGNED;
		Citizen citizen=selectedCity.getCitizenToAssign();
		tile.setCitizen(citizen);
		citizen.setWorkingTile(tile);
		return Message.SUCCESS;
	}

	public Message removeCitizenFromWork(int x, int y){
		// TODO
		return Message.SUCCESS;
	}


	public int getFoodOut(Civilization civilization){
		// TODO
		return -1;
	}
	public int getProductionOut(Civilization civilization){
		// TODO
		return -1;
	}
	public int getGoldOut(Civilization civilization){
		// TODO
		return -1;
	}
	public int getScienceOut(Civilization civilization){
		// TODO
		return -1;
	}

	
	public Message buyTile(int x, int y){
		City city = SelectController.getInstance().getSelectedCity();
		Tile tile = GameController.getInstance().getGame().getTile(x, y);
		if (tile.getOwner()!=null) return Message.FAIL;
		Civilization civilization=city.getCivilization();
		int cost=5;
		if (civilization.getGold()<cost) return Message.NOT_ENOUGH_GOLD;
		civilization.addGold(-cost);
		civilization.addTile(tile);
		city.addTile(tile);
		tile.setOwner(civilization);
		return Message.SUCCESS;
	}
	
	public String getCityInfo(City city){
		// TODO
		return null;
	}

}
