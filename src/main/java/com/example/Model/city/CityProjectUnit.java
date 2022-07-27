package com.example.Model.city;

import com.example.Model.unit.UnitType;

public class CityProjectUnit extends CityProject{
	protected UnitType unitType;
	
	public CityProjectUnit(City city, UnitType unitType) {
		super(city, unitType.cost);
		this.unitType=unitType;
	}
	

	@Override
	public boolean similarProject(CityProject project) {
		if (!(project instanceof CityProjectUnit)) return false;
		return this.city==project.city && ((CityProjectUnit) project).unitType==unitType;
	}

	@Override
	public boolean doFinnishAction() {
		if (!city.CanSpawnUnit(unitType)) return false;
		city.spawnUnit(unitType);
		return true;
	}


	@Override
	public boolean isValid() {
		if (!city.getOwner().hasTechnologies(unitType.technologyRequired)) return false;
		return unitType.canBuildOnCity(city);
	}

	public UnitType getUnitType(){
		return unitType;
	}
	
}
