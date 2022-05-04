package Models.Resource;

import Models.Civilization;
import Models.Improvement;
import Models.Technology;
import Models.Terrain;

public enum Resource {
	; // TODO
	final public ResourceType resourceType;
	final public int food;
	final public int production;
	final public int gold;
	final public Terrain canBeFoundOn[];
	final public Improvement improvementNeeded;
	final public Technology technologyNeeded;

	private Resource(ResourceType resourceType, int food, int production, int gold, Terrain canBeFoundOn[], Improvement improvementNeeded, Technology technologyNeeded){
		this.resourceType=resourceType;
		this.food=food;
		this.production=production;
		this.gold=gold;
		this.canBeFoundOn=canBeFoundOn;
		this.improvementNeeded=improvementNeeded;
		this.technologyNeeded=technologyNeeded;
	}

	public boolean isVisible(Civilization civilization) {
		if (resourceType!=ResourceType.STRATEGIC) return true;
		//TODO
		return false;
	}
}
