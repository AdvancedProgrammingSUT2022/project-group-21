package com.example.Model.unit;

import java.util.ArrayList;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;

public abstract class Unit {
	protected final static int maxHP=10;

	public final UnitType unitType;
	protected Civilization owner;
	protected Tile tile;
	protected int HP, MP;
	protected UnitState unitState;
	private ArrayList<Tile> path; // multiple-turn movement
	
	public void setUnitState(UnitState unitState){
		path=null; // abort movement
		this.unitState=unitState;
	}
	public UnitState getUnitState(){ return unitState;}
	
	protected Unit(UnitType unitType, Civilization owner, Tile tile){
		this.unitType=unitType;
		this.owner=owner;
		this.tile=tile;
		this.HP=maxHP;
		this.MP=unitType.MP;
		owner.addUnit(this);
		addToTile();
	}
	
	public Tile getTile(){ return tile;}
	public void moveToTile(Tile tile){ // NOTE: tile is adjacent to this.tile
		this.tile=tile;
		// TODO: maybe some graphis shit
	}

	public int getMP(){ return this.MP;}
	public void setMP(int MP){
		if (MP<0) MP=0;
		this.MP=MP;
	}
	public void decreaseMP(int movementCost){ this.MP-=movementCost;}
	public void resetMP(){ this.MP=unitType.MP;}

	public int getHP(){ return this.HP;}
	public void setHP(int HP){ this.HP=HP;}

	public void kill(){
		owner.removeUnit(this);
		// TODO: show some graphic shit? make a notification?
	}

	protected abstract void removeFromTile() ;
	protected abstract void addToTile() ;

	public void setPath(ArrayList<Tile> path){ this.path=path;}
	public void moveOnPath(){
		int l=path.lastIndexOf(getTile()), r=l+1;
		int cost=0;
		while (r<path.size() && cost<getMP()) r++;
		while (r>l && !path.get(r).canPutUnit(this)) r--;
		if (l==r){
			path=null; // abort movement
			return ;
		}
		removeFromTile();
		while (l<r){
			moveToTile(path.get(++l));
		}
		addToTile();
		setMP(getMP()-cost);
		if (r==path.size()-1) path=null; // task done
	}
	
	public void beginTurn(){
		// TODO
		// NOTE: I think there is absolutely nothing to do here
	}
	public void endTurn(){
		if (getUnitState()==UnitState.SLEEP) setMP(0);
		if (getMP()==0) return ; // without MP, unit wont heal
		if (getUnitState()==UnitState.WAKE && path!=null) moveOnPath();
		if (this instanceof MilitaryUnit){
			((MilitaryUnit) this).heal();
		}
		if (this instanceof Worker){
			((Worker) this).work();
		}
	}


	public Civilization getOwner(){
		return owner;
	}

	public enum UnitState{
		WAKE,
		SLEEP,
		FORTIFY,
		ALERT,
		PRE_ATTACK;
	}
}
