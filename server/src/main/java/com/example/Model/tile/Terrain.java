package com.example.Model.tile;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.Model.resource.Resource;

public enum Terrain {
	DESERT(0, 0, 0, -0.33, 1, true, true,
		new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.OASIS, TerrainFeature.FLOOD_PLAINS)),
		new ArrayList<Resource>(Arrays.asList(Resource.IRON,Resource.GOLD,Resource.SILVER,Resource.MARBLE,Resource.COTTON,Resource.FUMIGATION,Resource.SHEEP,Resource.GEMSTONE))
	),
	GRASSLAND(2, 0, 0, -0.33, 1, true, true,
		new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.MARSH)),
		new ArrayList<Resource>(Arrays.asList(Resource.IRON,Resource.COAL,Resource.HORSE,Resource.COW,Resource.GOLD,Resource.GEMSTONE,Resource.MARBLE,Resource.COTTON,Resource.SHEEP))
	),
	HILL(0, 2, 0, 0.25, 2, true, false,
		new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.JUNGLE)),
		new ArrayList<Resource>(Arrays.asList(Resource.IRON,Resource.COAL,Resource.SILVER,Resource.GOLD,Resource.GEMSTONE,Resource.MARBLE,Resource.SHEEP))
	),
	MOUNTAIN(0, 0, 0, 0.25, 0, false, false,
		new ArrayList<TerrainFeature>(),
		new ArrayList<Resource>()
	),
	OCEAN(0, 0, 0, 0.25, 0, false, true,
		new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.ICE)), // NOTE: english doc has ICE but in persian charts its empty
		new ArrayList<Resource>()
	),
	PLAINS(1, 1, 0, -0.33, 1, true, true,
		new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.JUNGLE)),
		new ArrayList<Resource>(Arrays.asList(Resource.IRON,Resource.COAL,Resource.HORSE,Resource.WHEAT,Resource.GOLD,Resource.GEMSTONE,Resource.MARBLE,Resource.COTTON,Resource.FUMIGATION,Resource.SHEEP,Resource.IVORY))
	),
	SNOW(0, 0, 0, -0.33, 1, true, true,
		new ArrayList<TerrainFeature>(Arrays.asList()),
		new ArrayList<Resource>(Arrays.asList(Resource.IRON))
	),
	TUNDRA(1, 0, 0, -0.33, 1, true, true,
		new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.FOREST)),
		new ArrayList<Resource>(Arrays.asList(Resource.IRON,Resource.HORSE,Resource.SILVER,Resource.GEMSTONE,Resource.MARBLE))
	);


	public final int food;
	public final int production;
	public final int gold;
	public final double combatModifier;
	public final int movementCost;
	public final boolean passable;
	public final boolean visible;
	public final ArrayList<TerrainFeature> possibleFeatures;
	public final ArrayList<Resource> possibleResources;

	private Terrain(int food, int production, int gold, double combatModifier, int movementCost, boolean passable, boolean visible, ArrayList<TerrainFeature> possibleFeatures, ArrayList<Resource> possibleResources) {
		this.food = food;
		this.production = production;
		this.gold = gold;
		this.combatModifier = combatModifier;
		this.movementCost = movementCost;
		this.passable = passable;
		this.visible = visible;
		this.possibleFeatures = possibleFeatures;
		this.possibleResources = possibleResources;
	}

	public boolean isRoughTerrain(){
		return this==HILL || this==SNOW;
	}
}
