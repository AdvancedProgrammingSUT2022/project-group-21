package Models.Unit;

import Models.Civilization;
import Models.Tile;

public abstract class Unit {
	final public UnitType unitType;
	final public Civilization owner;
	protected Tile tile;
	protected int HP;
	protected int lastActionTurn; // TODO: update this
	
	Unit(UnitType unitType, Civilization owner, Tile tile){
		this.unitType=unitType;
		this.owner=owner;
		this.tile=tile;
		this.HP=10;
	}
	// TODO: fix the rest
	
	public Tile getTile(){ return tile;}
	public void setTile(Tile tile){ this.tile=tile;}

	public int getMP() {
		return MP;
	}

	public int getCombatStrength() {
		return combatStrength;
	}

	public boolean isTileVisible(int x, int y) {
		//TODO
		return false;
	}
	public void move(Tile tile){
		this.tile = tile;
	}
	public void setMP(int MP) {
		this.MP = MP;
	}
}
