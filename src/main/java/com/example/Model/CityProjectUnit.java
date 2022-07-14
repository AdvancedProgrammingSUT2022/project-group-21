package com.example.Model;

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
		// TODO Auto-generated method stub
		// TODO: create Unit and find a valid place to put
		return false;
	}


	@Override
	public boolean isValid() {
		if (!city.getOwner().hasTechnologies(unitType.technologyRequired)) return false;
		if (unitType==UnitType.SETTLER && city.countCitizens()<2) return false; // SETTLER can be made at city with at least 2 citizens
		return true;
	}
	
}
