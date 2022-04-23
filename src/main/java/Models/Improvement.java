package Models;

import java.util.ArrayList;
import java.util.Arrays;

public enum Improvement{
	// TODO: handle terrain
	CAMP(0, 0, 0, Technology.TRAPPING,
		new ArrayList<Terrain>(Arrays.asList())
	),
	FARM(1, 0, 0, Technology.AGRICULTURE,
		new ArrayList<Terrain>(Arrays.asList())
	),
	LUMBER_MILL(0, 1, 0, Technology.ENGINEERING,
		new ArrayList<Terrain>(Arrays.asList())
	),
	MINE(0, 1, 0, Technology.MINING,
		new ArrayList<Terrain>(Arrays.asList())
	),
	PASTURE(0, 0, 0, Technology.ANIMAL_HUSBANDRY,
		new ArrayList<Terrain>(Arrays.asList())
	),
	PLANTATION(0, 0, 0, Technology.CALENDAR,
		new ArrayList<Terrain>(Arrays.asList())
	),
	QUARRY(0, 0, 0, Technology.MASONRY,
		new ArrayList<Terrain>(Arrays.asList())
	),
	TRADING_POST(0, 0, 1, Technology.TRAPPING,
		new ArrayList<Terrain>(Arrays.asList())
	),
	MANUFACTORY(0, 2, 0, Technology.ENGINEERING,
		new ArrayList<Terrain>(Arrays.asList())
	);
	
	
	public final int food;
	public final int gold;
	public final int production;
	public final Technology prequisiteTech;
	public final ArrayList<Terrain> possibleTerrains;
	
	Improvement(int food, int production, int gold, Technology prequisiteTech, ArrayList<Terrain> possibleTerrains){
		this.food=food;
		this.gold=gold;
		this.production=production;
		this.prequisiteTech=prequisiteTech;
		this.possibleTerrains=possibleTerrains;
	}

}
