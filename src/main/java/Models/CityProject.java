package Models;

import Models.Unit.UnitType;

public abstract class CityProject{
	protected City city;
	protected int productionRequired;
	protected int productionMade;
	
	protected CityProject(City city, int productionRequired){
		this.city=city;
		this.productionRequired=productionRequired;
		this.productionMade=0;
		// this.unitType=unitType;
	}

	// public static CityProject createUnitProject(City city, UnitType unitType){
	// 	// return new CityProject(city, unitType.cost, unitType, null);
	// }
	// public static CityProject createBuildingProject(City city, Building building){
	// 	// return new CityProject(city, building.cost, null, building);
	// }
	
	public abstract boolean similarProject(CityProject project);

	public abstract boolean isValid();

	public void makeProduction(int production){
		productionMade+=production;
	}

	public int getProductionMade(){
		return productionMade;
	}
	public int getProductionRequired(){
		return productionRequired;
	}
	public boolean isFinnished(){
		return productionMade>=productionRequired;
	}

	public boolean isSettlerProject(){
		if (!(this instanceof CityProjectBuilding)) return false;
		return ((CityProjectUnit) this).unitType==UnitType.SETTLER;
	}

	// TODO: check if production is made; for Unit also check if there is a valid near plave to spawn
	public abstract boolean doFinnishAction(); 

}
