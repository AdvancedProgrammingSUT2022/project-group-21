package Models;

import Models.Unit.UnitType;

public class CityProjectUnit extends CityProject{
	protected UnitType unitType;
	
	protected CityProjectUnit(City city, int productionRequired, UnitType unitType) {
		super(city, productionRequired);
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
	
}
