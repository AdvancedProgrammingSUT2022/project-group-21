package Models;

import Models.Resource.Resource;

import java.util.ArrayList;
import java.util.Arrays;

public enum TerrainFeature{
	// use the names from main english charts.
	
	FLOOD_PLAINS(2, 0, 0, -0.33, 1, new ArrayList<Resource>(Arrays.asList(Resource.WHEAT,Resource.SUGAR)), true, true, true),
	FOREST(1, 1, 0, +0.25, 2, new ArrayList<Resource>(Arrays.asList(Resource.COLOR,Resource.SILK)), true, false, false),
	ICE(0, 0, 0, 0, 0, new ArrayList<Resource>(), false, false, true),
	JUNGLE(1, -1, 0, +0.25, 2, new ArrayList<Resource>(Arrays.asList(Resource.BANANA,Resource.GEMSTONE,Resource.COLOR)), true, false, false),
	MARSH(-1, 0, 0, -0.33, 2, new ArrayList<Resource>(Arrays.asList(Resource.SUGAR)), true, false, true),
	OASIS(3, 0, 1, -0.33, 1, new ArrayList<Resource>(Arrays.asList()), true, false, true);
	// TODO: complete possibleResources

	
	public final int food;
	public final int production;
	public final int gold;
	public final double combatModifier;
	public final int movementCost;
	public final ArrayList<Resource> possibleResources;
	public final boolean passable;
	public final boolean needRiver;
	public final Boolean visible;
	
	private TerrainFeature(int food, int production, int gold, double combatModifier, int movementCost, ArrayList<Resource> possibleResources, boolean passable, boolean needRiver, boolean visible) {
		this.food = food;
		this.production = production;
		this.gold = gold;
		this.combatModifier = combatModifier;
		this.movementCost = movementCost;
		this.possibleResources = possibleResources;
		this.passable = passable;
		this.needRiver = needRiver;
		this.visible = visible;
	}

}

