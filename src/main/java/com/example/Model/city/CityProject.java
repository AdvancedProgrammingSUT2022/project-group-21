package com.example.Model.city;

import com.example.Model.unit.UnitType;

public abstract class CityProject{
	protected City city;
	protected int productionRequired;
	protected int productionMade;
	
	protected CityProject(City city, int productionRequired){
		this.city=city;
		this.productionRequired=productionRequired;
		this.productionMade=0;
	}
	
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

	// TODO: check if production is made; for Unit also check if there is a valid near tile to spawn
	public abstract boolean doFinnishAction(); 

}
