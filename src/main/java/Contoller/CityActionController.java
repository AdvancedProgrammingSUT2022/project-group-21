package Contoller;

import Models.Building;
import Models.City;
import Models.CityProject;
import Models.CityProjectBuilding;
import Models.CityProjectUnit;
import Models.Civilization;
import Models.Unit.UnitType;

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
	
	

}
