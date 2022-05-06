package Models;

import Models.Unit.CivilianUnit;
import Models.Unit.MilitaryUnit;
import Models.Unit.Worker;

public class Tile {
	public final int X;
	public final int Y;
	private Tile adjTiles[];
	private boolean rivers[];

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
		this.adjTiles = new Tile[6];
	}

	private int countRivers(){
		int res=0;
		for (int i=0; i<6; i++)
			res+=(rivers[i]?1:0);
		return res;
	}
	public void setRiver(int i, boolean val){ rivers[i]=val; }
	public void setAdjTile(int i, Tile val){ adjTiles[i]=val; }

	public boolean isRiver(int i){ return rivers[i]; }
	public Tile getAdjTile(int i){ return adjTiles[i]; }
	

	public boolean isTerrainFeatureCompatible(TerrainFeature terrainFeature) {
		if (terrain==null) return false; // why?
		if (!terrain.possibleFeatures.contains(terrainFeature)) return false;
		if (!terrainFeature.needRiver) return true;
		return countRivers()>0;
	}
	public void setTerrainFeature(TerrainFeature terrainFeature){
		assert(isTerrainFeatureCompatible(terrainFeature)); // NOTE: dont know where this may be used
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
		// NOTE: add or multiply?
		double res=terrain.combatModifier+1;
		if (terrainFeature!=null) res*=terrainFeature.combatModifier+1;
		return res;
	}
	public int getMovementCost(){
		int res=terrain.movementCost;
		if (terrainFeature!=null) res+=terrainFeature.movementCost;
		return res;
	}
	public boolean isPassable(){
		if (!terrain.passable) return false;
		return terrainFeature==null || terrainFeature.passable;
	}
	public boolean canSeeOver(){
		if (!terrain.visible) return false;
		return terrainFeature==null || terrainFeature.visible;
	}

	public void setCitizen(Citizen workingCitizen) {
		this.workingCitizen = workingCitizen;
	}

	public void setOwner(Civilization owner){ this.owner=owner; }
	public Civilization getOwner(){ return this.owner; }

}
