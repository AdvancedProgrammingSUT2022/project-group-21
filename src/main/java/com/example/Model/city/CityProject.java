package com.example.Model.city;

import com.example.Model.unit.CombatType;
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
		int speed = 100;
		if (city.hasBuilding(Building.STABLE)
			&& (this instanceof CityProjectUnit)
			&& ((CityProjectUnit) this).unitType.combatType==CombatType.MOUNTED)
			speed+=25;
			
		if (city.hasBuilding(Building.FORGE)
			&& (this instanceof CityProjectUnit))
			speed+=15;
		
		if (city.hasBuilding(Building.WORKSHOP)
			&& (this instanceof CityProjectUnit))
			speed+=20;
		
		if (city.hasBuilding(Building.WINDMILL))
			speed+=15;
		
		if (city.hasBuilding(Building.ARSENAL)
			&& (this instanceof CityProjectUnit))
			speed+=20;
		
		if (city.hasBuilding(Building.FACTORY))
			speed+=50;

		
		return productionRequired*100/speed;
	}
	public boolean isFinnished(){
		return getProductionMade()>=getProductionRequired();
	}

	public boolean isSettlerProject(){
		if (!(this instanceof CityProjectBuilding)) return false;
		return ((CityProjectUnit) this).unitType==UnitType.SETTLER;
	}

	public abstract boolean doFinnishAction(); 

}
