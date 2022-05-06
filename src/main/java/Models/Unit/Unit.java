package Models.Unit;

import Models.Civilization;
import Models.Tile;

public abstract class Unit {
	final public UnitType unitType;
	final public Civilization owner;
	private Tile tile;
	private int HP, MP;
	protected int lastActionTurn; // TODO: update this
	
	Unit(UnitType unitType, Civilization owner, Tile tile){
		this.unitType=unitType;
		this.owner=owner;
		this.tile=tile;
		this.HP=10;
		this.MP=unitType.MP;
	}
	
	public Tile getTile(){ return tile;}
	public void setTile(Tile tile){ this.tile=tile;}

	public int getCombatStrength(){ return unitType.combatStrength;}
	public int getMP(){ return this.MP;}
	public void resetMP(){ this.MP=unitType.MP;}
	public void setMP(int MP){ this.MP=MP;}

	public int getHP(){ return this.HP;}
	public void setHP(Tile tile){ this.tile=tile;}

	public int getLastActionTurn(){ return lastActionTurn;}
	public void setLastActionTurn(int turn){ this.lastActionTurn=turn;}

}
