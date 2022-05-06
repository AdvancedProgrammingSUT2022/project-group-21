package Models.Unit;

import Models.Civilization;
import Models.Technology;
import Models.Tile;
import Models.Resource.Resource;

public enum UnitType{
	SETTLER(CombatType.CIVILIAN, 89, -1, -1, -1, 2, null, false, new Technology[]{}),
	WORKER(CombatType.CIVILIAN, 70, -1, -1, -1, 2, null, false, new Technology[]{}),


	ARCHER(CombatType.RANGED, 70, 4, 6, 2, 2, null, false,
		new Technology[]{Technology.ARCHERY}
	),
	
	;

	public final CombatType combatType;
	public final int cost;
	public final int combatStrength;
	public final int rangedCombatStrength;
	public final int range;
	public final int MP;
	public final Resource resourceRequired;
	public final boolean mayMeleeAttack;
	public final Technology technologyRequired[];
	


	private UnitType(CombatType combatType, int cost, int combatStrength, int rangedCombatStrength, int range, int MP, Resource resourceRequired, boolean mayMeleeAttack, Technology technologyRequired[]){
		this.combatType=combatType;
		this.cost=cost;
		this.combatStrength=combatStrength;
		this.rangedCombatStrength=rangedCombatStrength;
		this.range=range;
		this.MP=MP;
		this.resourceRequired=resourceRequired;
		this.mayMeleeAttack=mayMeleeAttack;
		this.technologyRequired=technologyRequired;
	}
	
	public Unit createUnit(Civilization owner, Tile tile){
		return combatType.createUnit(this, owner, tile);
	}
}
