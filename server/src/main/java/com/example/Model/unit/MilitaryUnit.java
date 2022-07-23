package com.example.Model.unit;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;

public class MilitaryUnit extends Unit{
	public MilitaryUnit(UnitType unitType, Civilization owner, Tile tile){
		super(unitType, owner, tile);
	}
	
	public double getCombatStrength() {
		double res = tile.getCombatModifier() + Integer.max(unitType.combatStrength, unitType.combatStrength);
		if (getUnitState()==UnitState.FORTIFY && !unitType.hasAbility(UnitAbility.NO_DEFENSIVE_BONUS)) res*=1.5;
		if (unitType.hasAbility(UnitAbility.NO_DEFENSIVE_BONUS) && getTile().getTerrain().isRoughTerrain()) res*=0.5;
		return res;
	}

	public void pillage(){
		getTile().setPillaged(true);
		
	}


	public void heal(){
		if (HP<=0){
			HP=0;
			return ;
		}
		if (getUnitState()==UnitState.FORTIFY){
			if (tile.getOwner()!=owner) HP+=1;
			else if (tile.getCityOnTile()!=null) HP+=3;
			else HP+=2;
		}
		if (HP>maxHP) HP=maxHP;
	}


	

	@Override
	public void removeFromTile() {
		getTile().setMilitaryUnit(null);
	}

	@Override
	protected void addToTile() {
		getTile().setMilitaryUnit(this);
	}
}
