package Models.Unit;

import Models.Civilization;
import Models.Tile.Tile;

public class MilitaryUnit extends Unit{
	private int XP;

	public MilitaryUnit(UnitType unitType, Civilization owner, Tile tile){
		super(unitType, owner, tile);
	}
	
	public double getCombatStrength(int thisTurn) {
		double res = tile.getCombatModifier() + unitType.combatStrength;
		// TODO
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
	public void moveToTile(Tile tile){
		// this.tile.setMilitaryUnit(null);
		super.moveToTile(tile);
		// this.tile.setMilitaryUnit(this);
	}

	@Override
	protected void removeFromTile() {
		getTile().setMilitaryUnit(null);
	}

	@Override
	protected void addToTile() {
		getTile().setMilitaryUnit(this);
	}


}
