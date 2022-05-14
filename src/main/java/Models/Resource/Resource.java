package Models.Resource;

import Models.Civilization;
import Models.Technology;
import Models.Tile.Improvement;

public enum Resource {

	BANANA(ResourceType.BONUS,1,0,0,Improvement.PLANTATION,null),

	COW(ResourceType.BONUS,1,0,0,Improvement.PASTURE,null),

	SHEEP(ResourceType.BONUS,2,0,0,Improvement.PASTURE,null),

	WHEAT(ResourceType.BONUS,1,0,0,Improvement.FARM,null),

	COAL(ResourceType.STRATEGIC,0,1,0,Improvement.MINE,Technology.SCIENTIFIC_THEORY),

	HORSE(ResourceType.STRATEGIC,0,1,0,Improvement.PASTURE,Technology.ANIMAL_HUSBANDRY),

	IRON(ResourceType.STRATEGIC,0,1,0,Improvement.MINE,Technology.IRON_WORKING),

	COTTON(ResourceType.LUXURY,0,0,2,Improvement.PLANTATION,null),

	COLOR(ResourceType.LUXURY,0,0,2,Improvement.PLANTATION,null),

	FUR(ResourceType.LUXURY,0,0,2,Improvement.CAMP,null),

	GEMSTONE(ResourceType.LUXURY,0,0,3,Improvement.MINE,null),

	GOLD(ResourceType.LUXURY,0,0,2,Improvement.MINE,null),

	FUMIGATION(ResourceType.LUXURY,0,0,2,Improvement.PLANTATION,null),

	IVORY(ResourceType.LUXURY,0,0,2,Improvement.CAMP,null),

	MARBLE(ResourceType.LUXURY,0,0,2,Improvement.QUARRY,null),

	SILK(ResourceType.LUXURY,0,0,2,Improvement.PLANTATION,null),

	SILVER(ResourceType.LUXURY,0,0,2,Improvement.MINE,null),

	SUGAR(ResourceType.LUXURY,0,0,2,Improvement.PLANTATION,null),


	; 
	
	
	final public ResourceType resourceType;
	final public int food;
	final public int production;
	final public int gold;
	// final public Terrain canBeFoundOn[];
	final public Improvement improvementNeeded;
	final public Technology technologyNeeded;

	private Resource(ResourceType resourceType, int food, int production, int gold, Improvement improvementNeeded, Technology technologyNeeded){
		this.resourceType=resourceType;
		this.food=food;
		this.production=production;
		this.gold=gold;
		// this.canBeFoundOn=canBeFoundOn;
		this.improvementNeeded=improvementNeeded;
		this.technologyNeeded=technologyNeeded;
	}

	public boolean isVisible(Civilization civilization) {
		if (resourceType!=ResourceType.STRATEGIC) return true;
		//TODO
		return false;
	}
}
