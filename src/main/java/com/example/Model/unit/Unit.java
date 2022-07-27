package com.example.Model.unit;

import java.util.ArrayList;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;

public abstract class Unit {
	protected final static int maxHP=10;

	public final UnitType unitType;
	protected Civilization owner;
	protected Tile tile;
	protected double HP;
	protected int MP;
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
	protected void setTile(Tile tile){ // NOTE: tile is adjacent to this.tile
		this.tile=tile;
	}
	public void moveSingleTile(Tile tile){
		removeFromTile();
		setTile(tile);
		addToTile();
	}


	public int getMP(){ return this.MP;}
	public void setMP(int MP){
		if (MP<0) MP=0;
		this.MP=MP;
	}
	public void decreaseMP(int movementCost){ this.MP-=movementCost;}
	public void resetMP(){ this.MP=unitType.MP;}


	public double getHP(){ return this.HP;}
	public void setHP(double HP){ this.HP=HP;}
	public void resetHP(){ this.HP = maxHP;}

	
	public void kill(){
		owner.removeUnit(this);
		removeFromTile();
		setHP(0);
		// TODO: show some graphic shit? make a notification?
	}

	protected abstract void removeFromTile() ;
	protected abstract void addToTile() ;

	public void setPath(ArrayList<Tile> path){ this.path=path;}
	public void moveOnPath(){
		int l=path.lastIndexOf(getTile()), r=l;
		int cost=0;
		while (r+1<path.size() && cost<getMP()){
			Tile tile1=path.get(r);
			r++;
			Tile tile2=path.get(r);
			cost+=tile1.getMovementCostForUnit(this, tile1.getNeighbourDirection(tile2));
		}
		while (r>l && !path.get(r).canPutUnit(this)) r--;
		if (l==r){
			path=null; // abort movement
			return ;
		}
		removeFromTile();
		while (l<r){
			setTile(path.get(++l));
		}
		addToTile();
		setMP(getMP()-cost);
		if (r==path.size()-1) path=null; // task done
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
}
