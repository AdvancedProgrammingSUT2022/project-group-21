package com.example.Model.city;

import java.util.ArrayList;

import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.spfa.ShortestPath;
import com.example.Model.tile.Terrain;
import com.example.Model.tile.Tile;
import com.example.Model.unit.MilitaryUnit;
import com.example.Model.unit.UnitType;

// NOTE: I set the price of buying any tile to 50 coins
// NOTE: the food required for growth is multiplied by 1.5 on each growth
public class City {
	private static final int maxHP = 20;
	
	private Civilization owner;
	private ArrayList<Tile> tiles = new ArrayList<>();
	private ArrayList<Tile> lockedTiles = new ArrayList<>(); // places where citizens work
	private ArrayList<Building> buildings = new ArrayList<>();
	private boolean capital;
	private Tile center;
	private int population=1;
	private int food, foodToGrow=5;
	private int production;
	private ArrayList<CityProject> notActiveProjects = new ArrayList<>();
	private CityProject activeProject;
	private ShortestPath shortestPath;

	private double HP;
	private boolean attackedInThisTurn; // TODO: remember to reset it


	public City(Tile tile, Civilization owner){
		this.center = tile;
		this.owner = owner;
		initializeCity();
		this.shortestPath=new ShortestPath(Game.getInstance(), null, 2); // every city can buy tiles of radius 2
	}
	public void initializeCity(){
		center.setCityOnTile(this);
		addTile(center);
		for (int i=0; i<6; i++){
			Tile tile2 = center.getAdjTile(i);
			if (tile2!=null && tile2.getOwner()==null)
				addTile(tile2);
		}
	}

	
	public void endTurn(){
		food+=getFoodOut();
		production+=getProductionOut();
		if (food>=foodToGrow){
			food-=foodToGrow;
			growCity();
		}
		activeProject.makeProduction(production);
		production=0;
		if (activeProject.isFinnished()){
			production=activeProject.getProductionMade()-activeProject.getProductionRequired();
			endActiveProject();
		}
		setHP(getHP()+1);
		if (getHP()>maxHP) setHP(maxHP);
		setAttackedInThisTurn(false);
	}



	public void setProductionProject(CityProject newProject){
		if (activeProject==null){
			activeProject=newProject;
			return ;
		}
		if (newProject.similarProject(activeProject)) return ;
		for (CityProject project : notActiveProjects){
			if (newProject.similarProject(project)){
				notActiveProjects.add(activeProject);
				activeProject=project;
				notActiveProjects.remove(project);
				return ;
			}
		}
		notActiveProjects.add(activeProject);
		activeProject=newProject;
	}
	public CityProject getProductionProject(){
		return activeProject;
	}
	public void endActiveProject(){
		if (activeProject==null) return ;
		if (activeProject.doFinnishAction()){
			activeProject = null;
		}
		// TODO: should I do smth in else?
	}



	public void addBuilding(Building building){
		buildings.add(building);
	}
	public boolean hasBuilding(Building building){
		return buildings.contains(building);
	}


	// TODO: not sure about the calculation, just made smth that depends on all that it should :(
	public double getCombatStrength(){
		double res=15;
		MilitaryUnit unit = center.getMilitaryUnit();
		if (unit!=null) res+=unit.getCombatStrength();
		res*=center.getCombatModifier();
		if (center.getTerrain()==Terrain.HILL) res*=1.1;
		return res;
	}


	
	public Tile getCenter(){
		return center;
	}
	public boolean hasTile(Tile tile) {
		return tiles.contains(tile);
	}
	public void addTile(Tile tile){
		tiles.add(tile);
		owner.addTile(tile);
		tile.setOwner(owner);
	}
	public ArrayList<Tile> getTiles(){
		return tiles;
	}

	public ArrayList<Tile> getPossibleTilesToBuy(){
		ArrayList<Tile> res = new ArrayList<>();
		for (Tile tile : tiles) {
			for (int i=0; i<6; i++){
				Tile tile2 = tile.getAdjTile(i);
				if (tile2==null || tile2.getOwner()!=null || shortestPath.getDistance(tile2)>2) continue ;
				// so, we can buy this tile
				if (!res.contains(tile2)) res.add(tile2);
			}
		}
		return res;
	}




	// range=2: used for visibility and attack
	public boolean isTileInRange(Tile tile){
		return shortestPath.getDistance(tile)<=2;
	}
	public boolean hasAttackedInThisTurn(){
		return attackedInThisTurn;
	}
	public void setAttackedInThisTurn(boolean val){
		attackedInThisTurn=val;
	}
	
	


	public boolean hasLockedCitizen(Tile tile){
		return lockedTiles.contains(tile) || tile==center;
	}
	public void lockUnlockCitizen(Tile tile){
		if (hasLockedCitizen(tile)){
			lockedTiles.remove(tile);
		}
		else{
			if (lockedTiles.size()==population){
				lockedTiles.remove(0);
			}
			lockedTiles.add(tile);
		}
		// TODO: update graphics?
	}
	public int countUnemployedCitizens(){
		return population-lockedTiles.size();
	}
	public int countCitizens(){
		return population;
	}
	public void growCity(){
		population++;
		foodToGrow*=1.5;
	}




	private Tile getTileToSpawnUnit(UnitType unitType){
		if (center.canPutUnit(unitType)) return center;
		for (Tile tile : tiles){
			if (tile.canPutUnit(unitType)){
				return tile;
			}
		}
		return null;
	}
	public boolean CanSpawnUnit(UnitType unitType){
		return getTileToSpawnUnit(unitType)!=null;
	}
	public boolean spawnUnit(UnitType unitType){
		Tile tile = getTileToSpawnUnit(unitType);
		if (tile==null) return false;
		unitType.createUnit(owner, tile);
		return true;
	}



	public double getHP(){ return HP;}
	public void setHP(double HP){ this.HP=HP;}
	


	public Civilization getOwner(){ return owner;}
	public void setOwner(Civilization owner){
		getOwner().removeCity(this);
		this.owner = owner;
		getOwner().addCity(this);
		getOwner().addHappiness(-10); // -10 happiness for capturing other city
		for (Tile tile : tiles) {
			tile.setOwner(owner);
		}
	}


	public boolean isCapital(){ return capital;}
	public void setCapital(boolean capital){ this.capital=capital;}



	public int getFoodOut() {
		int res=0;
		for (Tile tile : lockedTiles) {
			res+=tile.getFood();
		}
		res+=center.getFood();
		for (Building building : buildings) {
			// TODO
		}
		res-=2*population;
		if (res>0 && owner.getHappiness()<0) res/=3; // affect of unhappiness on food
		if (res>0 && activeProject!=null && activeProject.isSettlerProject()) res=0; // affect of settler on food
		return res;
	}

	public int getProductionOut() {
		int res=0;
		for (Tile tile : lockedTiles) {
			res+=tile.getProduction();
		}
		res+=center.getProduction();
		for (Building building : buildings) {
			// TODO
		}
		return res;
	}

	public int getGoldOut() {
		int res=0;
		for (Tile tile : lockedTiles) {
			res+=tile.getProduction();
		}
		res+=center.getProduction();
		for (Building building : buildings) {
			// TODO
		}
		return res;
	}

	public int getScienceOut() {
		int res=countCitizens() + (isCapital()?3:0);
		for (Building building : buildings) {
			// TODO
		}
		return res;
	}

}
