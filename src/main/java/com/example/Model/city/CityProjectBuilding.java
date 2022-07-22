package com.example.Model.city;

public class CityProjectBuilding extends CityProject{
	private Building building;
	

	public CityProjectBuilding(City city, Building building) {
		super(city, building.cost);
		this.building=building;
	}


	@Override
	public boolean similarProject(CityProject project){
		if (!(project instanceof CityProjectBuilding)) return false;
		return this.city==project.city && ((CityProjectBuilding) project).building==building;
	}


	@Override
	public boolean doFinnishAction() {
		// city.addBuilding(building);
		building.addBuildingToCity(city);
		return true;
	}


	@Override
	public boolean isValid() {
		if (city.hasBuilding(building)) return false;
		return building.canBuildOnCity(city);
	}

	
	
}
