package Models;

public class CityProjectBuilding extends CityProject{
	private Building building;
	

	public CityProjectBuilding(City city, int productionRequired, Building building) {
		super(city, productionRequired);
		this.building=building;
	}


	@Override
	public boolean similarProject(CityProject project){
		if (!(project instanceof CityProjectBuilding)) return false;
		return this.city==project.city && ((CityProjectBuilding) project).building==building;
	}


	@Override
	public boolean doFinnishAction() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
