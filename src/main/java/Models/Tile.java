package Models;

import Models.Unit.CivilianUnit;
import Models.Unit.MilitaryUnit;
import Models.Unit.Worker;

public class Tile {
	public final int X;
	public final int Y;
	private TileBorder borders[];

	private Terrain terrain;
	private TerrainFeature terrainFeature;
	private Improvement improvement;
	private Ruin ruin;
	private int roadType;
	
	private Civilization owner;
	private MilitaryUnit militaryUnit;
	private CivilianUnit civilianUnit;
	private Citizen workingCitizen;

	public Worker getWorker(){
		if (civilianUnit instanceof Worker) return (Worker) civilianUnit;
		return null;
	}
	public MilitaryUnit getMilitaryUnit(){ return militaryUnit;}
	public CivilianUnit getCivilianUnit(){ return civilianUnit;}
	public Citizen getWorkingCitizen(){ return workingCitizen;}
	


	public Tile(int X, int Y, Terrain terrain) {
		this.X = X;
		this.Y = Y;
		this.terrain = terrain;
		this.borders = new TileBorder[6];
	}

	private int countRivers(){
		int res=0;
		for (TileBorder tileBorder : borders)
			if (tileBorder.isRIVER()) res++;
		return res;
	}

	public boolean isTerrainFeatureCompatible(TerrainFeature terrainFeature) {
		if (terrain==null) return false; // why?
		if (!terrain.possibleFeatures.contains(terrainFeature)) return false;
		if (!terrainFeature.needRiver) return true;
		return countRivers()>0;
	}
	public void setTerrainFeature(TerrainFeature terrainFeature){
		assert(isTerrainFeatureCompatible(terrainFeature)); // TODO: dont know where this may be used
		this.terrainFeature=terrainFeature;
	}

	public int getFood(){
		if (terrainFeature==TerrainFeature.FOREST) return 1;
		int res=terrain.food;
		if (terrainFeature!=null) res+=terrainFeature.food;
		return res;
	}
	public int getProduction(){
		if (terrainFeature==TerrainFeature.FOREST) return 1;
		int res=terrain.production;
		if (terrainFeature!=null) res+=terrainFeature.production;
		return res;
	}
	public int getGold(){
		int res=terrain.gold;
		if (terrainFeature!=null) res+=terrainFeature.gold;
		res+=countRivers();
		return res;
	}
	public double getCombatModifier(){
		double res=terrain.combatModifier;
		if (terrainFeature!=null) res+=terrainFeature.combatModifier; // NOTE: add or multiply?
		return res;
	}
	public int getMovementCost(){
		int res=terrain.movementCost;
		if (terrainFeature!=null) res+=terrainFeature.movementCost;
		return res;
	}
	public boolean getPassable(){
		if (!terrain.passable){
			return false;
		}
		else {
			if (terrainFeature!=null && !terrainFeature.passable){
				return false;
			}
		}
		return true;
	}

}
