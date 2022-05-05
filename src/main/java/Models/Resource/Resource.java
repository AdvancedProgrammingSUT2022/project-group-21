package Models.Resource;

import Models.Civilization;
import Models.Improvement;
import Models.Technology;

public enum Resource {
	MOZ1(ResourceType.BONUS, -1, -1, -1, Improvement.MANUFACTORY, Technology.CHEMISTRY),

	MOZ2(ResourceType.STRATEGIC, -1, -1, -1, Improvement.MANUFACTORY, Technology.CHEMISTRY),
	
	MOZ3(ResourceType.LUXURY, -1, -1, -1, Improvement.MANUFACTORY, Technology.CHEMISTRY),
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
