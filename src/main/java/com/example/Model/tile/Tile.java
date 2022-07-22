package com.example.Model.tile;

import com.example.Model.Civilization;
import com.example.Model.city.City;
import com.example.Model.resource.Resource;
import com.example.Model.unit.CivilianUnit;
import com.example.Model.unit.CombatType;
import com.example.Model.unit.MilitaryUnit;
import com.example.Model.unit.Unit;
import com.example.Model.unit.UnitAbility;
import com.example.Model.unit.UnitType;
import com.example.Model.unit.Worker;
import com.example.Model.unit.WorkerProject;

public class Tile {
	public final int X;
	public final int Y;
	private Tile adjTiles[];
	private boolean rivers[];

	private Terrain terrain;
	private TerrainFeature terrainFeature;
	private Resource resource;
	private City cityOnTile;
	private Improvement improvement;
	private boolean pillage; // is tile improvement damaged?
	private WorkerProject workerProject;
	

	private Civilization owner;
	private MilitaryUnit militaryUnit;
	private CivilianUnit civilianUnit;

	public Worker getWorker(){
		if (civilianUnit instanceof Worker)
			return (Worker) civilianUnit;
		return null;
	}

	
	public void setMilitaryUnit(MilitaryUnit militaryUnit){ this.militaryUnit=militaryUnit; }
	public void setCivilianUnit(CivilianUnit civilianUnit){ this.civilianUnit=civilianUnit; }
	public MilitaryUnit getMilitaryUnit(){ return militaryUnit; }
	public CivilianUnit getCivilianUnit(){ return civilianUnit; }
	public boolean canPutUnit(Unit unit){
		return canPutUnit(unit.unitType);
	}
	public boolean canPutUnit(UnitType unitType){
		if (getTerrain()==Terrain.MOUNTAIN || getTerrain()==Terrain.OCEAN) return false;
		if (getCityOnTile()!=null && getCityOnTile().getOwner()!=getOwner()) return false;
		if (unitType.combatType==CombatType.CIVILIAN) return getCivilianUnit()==null;
		return getMilitaryUnit()==null;
	}
	
	
	public Terrain getTerrain(){ return terrain;}
	public TerrainFeature getTerrainFeature(){ return terrainFeature;}
	public void setTerrainFeature(TerrainFeature terrainFeature){ this.terrainFeature = terrainFeature;}
	

	public void setImprovement(Improvement improvement){ this.improvement = improvement;} // TODO: and maybe "setPillaged(false)"
	public Improvement getImprovement(){ return improvement;}
	
	public void setPillaged(boolean pillage){ this.pillage=pillage;}
	public boolean isPillaged(){ return pillage;}

	public void setResource(Resource resource){ this.resource=resource;}
	public Resource getResource(){ return this.resource;}

	public void setWorkerProject(WorkerProject workerProject){ this.workerProject=workerProject;}
	public WorkerProject getWorkerProject(){ return workerProject;}


	public City getCityOnTile(){ return cityOnTile;}
	public void setCityOnTile(City city){ this.cityOnTile=city;}


	public int getMovementCostForUnit(Unit unit, int direction){
		if (unit.unitType.hasAbility(UnitAbility.IGNORE_TERRAIN_COST)) return 1;
		return getAdjTile(direction).getMovementCost()+(isRiver(direction)?1:0);
	}

	

	public Tile(int X, int Y, Terrain terrain) {
		this.X = X;
		this.Y = Y;
		this.terrain = terrain;
		this.adjTiles = new Tile[6];
		this.rivers = new boolean[6];
	}

	public int countRivers() {
		int res = 0;
		for (int i = 0; i < 6; i++)
			res += (rivers[i] ? 1 : 0);
		return res;
	}

	public void setRiver(int i, boolean val){ rivers[i] = val;}
	public void setAdjTile(int i, Tile val){ adjTiles[i] = val;}
	public boolean isRiver(int i){ return rivers[i];}
	public Tile getAdjTile(int i){ return adjTiles[i];}
	public boolean isNeighbourWith(Tile tile){
		for (Tile tile2 : adjTiles) {
			if (tile2==tile)
				return true;
		}
		return false;
	}

	
	public boolean isTerrainFeatureCompatible(TerrainFeature terrainFeature) {
		if (terrain == null)
			return false; // why?
		if (!terrain.possibleFeatures.contains(terrainFeature))
			return false;
		if (!terrainFeature.needRiver)
			return true;
		return countRivers() > 0;
	}

	public int getFood() {
		int res = terrain.food;
		if (terrainFeature != null) res += terrainFeature.food;
		if (terrainFeature == TerrainFeature.FOREST) res=1;
		if (improvement != null && !isPillaged()) res+=improvement.food;
		if (resource!=null) res+=resource.food;

		return res;
	}

	public int getProduction() {
		int res = terrain.production;
		if (terrainFeature != null) res += terrainFeature.production;
		if (terrainFeature == TerrainFeature.FOREST) return 1;
		if (improvement != null && !isPillaged()) res+=improvement.production;
		if (resource!=null) res+=resource.production;

		return res;
	}

	public int getGold() {
		int res = terrain.gold;
		if (terrainFeature != null) res += terrainFeature.gold;
		if (improvement != null && !isPillaged()) res+=improvement.gold;
		res += countRivers();
		if (resource!=null) res+=resource.gold;
		return res;
	}

	public double getCombatModifier(){
		double res = terrain.combatModifier + 1;
		if (terrainFeature != null)
			res *= terrainFeature.combatModifier + 1;
		return res;
	}

	public int getMovementCost() {
		int res = terrain.movementCost;
		if (terrainFeature != null)
			res += terrainFeature.movementCost;
		return res;
	}

	public boolean isPassable() {
		if (!terrain.passable)
			return false;
		return terrainFeature == null || terrainFeature.passable;
	}

	public boolean canSeeOver() {
		if (!terrain.visible)
			return false;
		return terrainFeature == null || terrainFeature.visible;
	}


	public void setOwner(Civilization owner){ this.owner = owner; }
	public Civilization getOwner(){ return this.owner; }

}
