package Models.Unit;

import Models.Civilization;
import Models.Technology;
import Models.Tile;
import Models.Resource.Resource;

public enum UnitType{
	SETTLER(CombatType.CIVILIAN, 89, -1, -1, -1, 2, null, false, new Technology[]{}),
	WORKER(CombatType.CIVILIAN, 70, -1, -1, -1, 2, null, false, new Technology[]{}),


	ARCHER(CombatType.ARCHERY, 70, 4, 6, 2, 2, null, false,
		new Technology[]{Technology.ARCHERY}
	),
	CHARIOTARCHER(CombatType.MOUNTED,60,3,6,2,4,Resource.HORSE,false,
		new Technology[]{Technology.THE_WHEEL}
	),
	SCOUT(CombatType.RECON,25,4,-1,-1,2,null, false,
		null
	),
	SPEARMAN(CombatType.MELEE,50,7,-1,-1,2,null, true,
			new Technology[]{Technology.BRONZE_WORKING}
	),
	WARRIOR(CombatType.MELEE,40,6,-1,-1,2,null, true,
			null
	),
	CATAPULT(CombatType.SIEGE,100,4,14,2,2,Resource.IRON, false,
			new Technology[]{Technology.MATHEMATICS}
	),
	HORSEMAN(CombatType.MOUNTED,80,12,-1,-1,4,Resource.HORSE, true,
			new Technology[]{Technology.HORSEBACK_RIDING}
	),
	SWORDSMAN(CombatType.MELEE,80,11,-1,-1,2,Resource.IRON, true,
			new Technology[]{Technology.IRON_WORKING}
	),
	CROSSBOWMAN(CombatType.ARCHERY,120,6,12,2,2,null,false,
			new Technology[]{Technology.MACHINERY}
	),
	KNIGHT(CombatType.MOUNTED,150,18,-1,-1,3,Resource.HORSE, true,
			new Technology[]{Technology.CHIVALRY}
	),
	Longswordsman(CombatType.MELEE,150,18,-1,-1,3,Resource.IRON, true,
			new Technology[]{Technology.STEEL}
	),
	Pikeman(CombatType.MELEE,100,10,-1,-1,2,null, true,
			new Technology[]{Technology.CIVIL_SERVICE}
	),
	Trebuchet(CombatType.SIEGE,170,6,20,2,2,Resource.IRON, false,
			new Technology[]{Technology.PHYSICS}
	),
	Canon(CombatType.SIEGE,250,10,26,2,2,null, false,
			new Technology[]{Technology.CHEMISTRY}
	),
	Cavalry(CombatType.MOUNTED,260,25,-1,-1,3,Resource.HORSE, true,
			new Technology[]{Technology.MILITARY_SCIENCE}
	),
	Lancer(CombatType.MOUNTED,220,22,-1,-1,4,Resource.HORSE, true,
			new Technology[]{Technology.METALLURGY}
	),
	Musketman(CombatType.GUNPOWDER,120,16,-1,-1,2,null, true,
			new Technology[]{Technology.GUNPOWDER}
	),
	Rifleman(CombatType.GUNPOWDER,200,25,-1,-1,2,null, true,
			new Technology[]{Technology.RIFLING}
	),
	AntiTankGun(CombatType.GUNPOWDER,300,32,-1,-1,2,null, true,
			new Technology[]{Technology.REPLACEABLE_PARTS}
	),
	Artillery(CombatType.SIEGE,420,16,32,3,2,null, false,
			new Technology[]{Technology.DYNAMITE}
	),
	Infantry(CombatType.GUNPOWDER,300,36,-1,-1,2,null, true,
			new Technology[]{Technology.REPLACEABLE_PARTS}
	),
	Panzer(CombatType.ARMORED,450,60,-1,-1,5,null, true,
			new Technology[]{Technology.COMBUSTION}
	),
	Tank(CombatType.ARMORED,450,50,-1,-1,4,null, true,
			new Technology[]{Technology.COMBUSTION}
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
