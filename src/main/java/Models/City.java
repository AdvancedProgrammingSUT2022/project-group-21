package Models;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Tile.Tile;
import Models.Unit.MilitaryUnit;
import Models.Unit.UnitType;

public class City {
	private static HashMap<Tile, City> allCities=new HashMap<>();
	
	public static City getCityOnTile(Tile tile){ return allCities.get(tile);}
	

	private Civilization owner;
	private ArrayList<Tile> tiles = new ArrayList<>();
	private ArrayList<Tile> lockedTiles = new ArrayList<>(); // places where citizens work
	private ArrayList<Building> buildings = new ArrayList<>();
	private boolean capital;
	private int population=1;
	private Tile center;
	private double HP;
	private int food;
	private int production;
	private ArrayList<CityProject> allProjects = new ArrayList<>();
	private CityProject activeProject;

	// TODO: add CityProject, Combat shit, buy Tile(maybe elsewhere), 

	public City(Tile tile, Civilization owner){
		this.center = tile;
		this.owner = owner;
		allCities.put(tile, this);
		initializeCity();
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


	// TODO: not sure about the calculation, just made smth that depends on all that it should :(
	public double getCombatStrength(){
		double res=60;
		MilitaryUnit unit = center.getMilitaryUnit();
		if (unit!=null) res+=unit.getCombatStrength(5);
		res*=center.getCombatModifier();
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
		tile.setOwner(owner);
	}
	public ArrayList<Tile> getTiles(){
		return tiles;
	}
	

	
	public boolean hasLockedCitizen(Tile tile){
		return lockedTiles.contains(tile) || tile==center;
	}
	public void lockunlockCitizen(Tile tile){
		if (hasLockedCitizen(tile)){
			lockedTiles.remove(tile);
		}
		else{
			if (lockedTiles.size()==population){
				lockedTiles.remove(0);
			}
			lockedTiles.add(tile);
		}
		// TODO: update graphics
	}
	public int countUnemployedCitizens(){
		return population-lockedTiles.size();
	}
	public int countCitizens(){
		return population;
	}
	public void growCity(){
		population++;
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
		return  getTileToSpawnUnit(unitType)!=null;
	}
	public boolean spawnUnit(UnitType unitType){
		Tile tile = getTileToSpawnUnit(unitType);
		if (tile==null) return false;
		unitType.createUnit(owner, tile);
		return true;
	}


	

	public Civilization getOwner(){ return owner;}
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
		if (res>0 && owner.getHappiness()<0) res/=3; // NOTE: affect of unhappiness on food
		if (res>0 && activeProject!=null && activeProject.isSettlerProject()) res=0; // affect of settler on food
		return res;
	}

	public int getProductionOut(Civilization civilization) {
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

	public int getGoldOut(Civilization civilization) {
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

	public int getScienceOut(Civilization civilization) {
		int res=countCitizens() + (isCapital()?3:0);
		for (Building building : buildings) {
			// TODO
		}
		return res;
	}
}
