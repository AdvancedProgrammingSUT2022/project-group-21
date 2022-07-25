package com.example.Model.tile;

import com.example.Model.Technology;
import com.example.Model.unit.WorkerProject;

import java.util.ArrayList;

public enum Improvement{
	
	CAMP(0, 0, 0, Technology.TRAPPING){
		public boolean canBeBuiltOn(Tile tile){
			Terrain terrain=tile.getTerrain();
			if (terrain==Terrain.TUNDRA || terrain==Terrain.HILL || terrain==Terrain.PLAINS) return true;
			TerrainFeature feature=tile.getTerrainFeature();
			if (feature==TerrainFeature.FOREST || feature==TerrainFeature.JUNGLE) return true;
			return false;
		}
		@Override
		public String toString() {
			return "CAMP";
		}
	},

	FARM(1, 0, 0, Technology.AGRICULTURE){
		public boolean canBeBuiltOn(Tile tile){
			Terrain terrain=tile.getTerrain();
			if (terrain==Terrain.DESERT || terrain==Terrain.PLAINS || terrain==Terrain.GRASSLAND) return true;
			return false;
		}
		@Override
		public String toString() {
			return "FARM";
		}
	},
	LUMBER_MILL(0, 1, 0, Technology.ENGINEERING){
		public boolean canBeBuiltOn(Tile tile){
			TerrainFeature feature=tile.getTerrainFeature();
			if (feature==TerrainFeature.FOREST || feature==TerrainFeature.JUNGLE) return true;
			return false;
		}
		@Override
		public String toString() {
			return "LUMBER_MILL";
		}
	},
	MINE(0, 1, 0, Technology.MINING){
		public boolean canBeBuiltOn(Tile tile){
			Terrain terrain=tile.getTerrain();
			if (terrain==Terrain.TUNDRA || terrain==Terrain.HILL || terrain==Terrain.PLAINS || terrain==Terrain.DESERT || terrain==Terrain.GRASSLAND || terrain==Terrain.SNOW) return true;
			TerrainFeature feature=tile.getTerrainFeature();
			if (feature==TerrainFeature.FOREST || feature==TerrainFeature.JUNGLE || feature==TerrainFeature.MARSH) return true;
			return false;
		}
		@Override
		public String toString() {
			return "MINE";
		}
	},
	PASTURE(0, 0, 0, Technology.ANIMAL_HUSBANDRY){
		public boolean canBeBuiltOn(Tile tile){
			Terrain terrain=tile.getTerrain();
			if (terrain==Terrain.TUNDRA || terrain==Terrain.HILL || terrain==Terrain.PLAINS || terrain==Terrain.DESERT || terrain==Terrain.GRASSLAND || terrain==Terrain.SNOW) return true;
			return false;
		}
		@Override
		public String toString() {
			return "PASTURE";
		}
	},
	PLANTATION(0, 0, 0, Technology.CALENDAR){
		public boolean canBeBuiltOn(Tile tile){
			Terrain terrain=tile.getTerrain();
			if (terrain==Terrain.PLAINS || terrain==Terrain.DESERT || terrain==Terrain.GRASSLAND) return true;
			TerrainFeature feature=tile.getTerrainFeature();
			if (feature==TerrainFeature.FOREST || feature==TerrainFeature.JUNGLE || feature==TerrainFeature.MARSH || feature==TerrainFeature.FLOOD_PLAINS) return true;
			return false;
		}
		@Override
		public String toString() {
			return "PLANTATION";
		}
	},
	QUARRY(0, 0, 0, Technology.MASONRY){
		public boolean canBeBuiltOn(Tile tile){
			Terrain terrain=tile.getTerrain();
			if (terrain==Terrain.PLAINS || terrain==Terrain.TUNDRA || terrain==Terrain.GRASSLAND || terrain==Terrain.DESERT || terrain==Terrain.HILL) return true;
			return false;
		}
		@Override
		public String toString() {
			return "QUARRY";
		}
	},
	TRADING_POST(0, 0, 1, Technology.TRAPPING){
		public boolean canBeBuiltOn(Tile tile){
			Terrain terrain=tile.getTerrain();
			if (terrain==Terrain.PLAINS || terrain==Terrain.TUNDRA || terrain==Terrain.GRASSLAND || terrain==Terrain.DESERT) return true;
			return false;
		}
		@Override
		public String toString() {
			return "TRADING_POST";
		}
	},
	MANUFACTORY(0, 2, 0, Technology.ENGINEERING){
		public boolean canBeBuiltOn(Tile tile){
			Terrain terrain=tile.getTerrain();
			if (terrain==Terrain.PLAINS || terrain==Terrain.TUNDRA || terrain==Terrain.GRASSLAND || terrain==Terrain.DESERT || terrain==Terrain.SNOW) return true;
			return false;
		}
		@Override
		public String toString() {
			return "TRADING_POST";
		}
	};
	
	
	public final int food;
	public final int gold;
	public final int production;
	public final Technology prequisiteTech;
	
	Improvement(int food, int production, int gold, Technology prequisiteTech){
		this.food=food;
		this.gold=gold;
		this.production=production;
		this.prequisiteTech=prequisiteTech;
	}

	public abstract boolean canBeBuiltOn(Tile tile);
	
	// TODO: should add many shit ifs based on doc
	public int countNeededTurns(Tile tile){
		if (tile.getImprovement()!=null) return 4;
		return 3;
	}
	public static ArrayList<Improvement> getAllType() {
		ArrayList<Improvement> out = new ArrayList<>();
		for (Improvement improvement : Improvement.values()) {
			out.add(improvement);
		}
		return out;
	}
}
