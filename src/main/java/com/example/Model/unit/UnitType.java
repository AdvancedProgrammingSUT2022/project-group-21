package com.example.Model.unit;

import com.example.Model.Civilization;
import com.example.Model.Technology;
import com.example.Model.resource.Resource;
import com.example.Model.tile.Tile;

public enum UnitType {
	SETTLER(CombatType.CIVILIAN, 89, -1, -1, -1, 2, null,
			new Technology[] {},
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.FOUND_CITY },
			new UnitAbility[] {}),

	WORKER(CombatType.CIVILIAN, 70, -1, -1, -1, 2, null,
			new Technology[] {},
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.WORKER_ACTION },
			new UnitAbility[] {}),

	ARCHER(CombatType.ARCHERY, 70, 4, 6, 2, 2, null,
			new Technology[] { Technology.ARCHERY },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.RANGE_ATTACK },
			new UnitAbility[] {}),

	CHARIOTARCHER(CombatType.MOUNTED, 60, 3, 6, 2, 4, Resource.HORSE,
			new Technology[] { Technology.THE_WHEEL },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.RANGE_ATTACK },
			new UnitAbility[] {UnitAbility.ROUGH_TERRAIN_PENALTY, UnitAbility.NO_DEFENSIVE_BONUS}),

	SCOUT(CombatType.RECON, 25, 4, -1, -1, 2, null,
			new Technology[] {},
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.IGNORE_TERRAIN_COST}),

	SPEARMAN(CombatType.MELEE, 50, 7, -1, -1, 2, null,
			new Technology[] { Technology.BRONZE_WORKING },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.BONUS_VS_MOUNTED_100}),

	WARRIOR(CombatType.MELEE, 40, 6, -1, -1, 2, null,
			new Technology[] {},
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {}),

	CATAPULT(CombatType.SIEGE, 100, 4, 14, 2, 2, Resource.IRON,
			new Technology[] { Technology.MATHEMATICS },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.RANGE_ATTACK, UnitActionType.PRE_ATTACK_SETUP },
			new UnitAbility[] {UnitAbility.BONUS_VS_CITY_10, UnitAbility.NO_DEFENSIVE_BONUS, UnitAbility.LIMITED_VISIBILITY}),

	HORSEMAN(CombatType.MOUNTED, 80, 12, -1, -1, 4, Resource.HORSE,
			new Technology[] { Technology.HORSEBACK_RIDING },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.NO_DEFENSIVE_BONUS, UnitAbility.CAN_MOVE_AFTER_ATTACK}),

	SWORDSMAN(CombatType.MELEE, 80, 11, -1, -1, 2, Resource.IRON,
			new Technology[] { Technology.IRON_WORKING },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {}),

	CROSSBOWMAN(CombatType.ARCHERY, 120, 6, 12, 2, 2, null,
			new Technology[] { Technology.MACHINERY },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.RANGE_ATTACK },
			new UnitAbility[] {}),

	KNIGHT(CombatType.MOUNTED, 150, 18, -1, -1, 3, Resource.HORSE,
			new Technology[] { Technology.CHIVALRY },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.NO_DEFENSIVE_BONUS, UnitAbility.CAN_MOVE_AFTER_ATTACK}),

	LONGSWORDSMAN(CombatType.MELEE, 150, 18, -1, -1, 3, Resource.IRON,
			new Technology[] { Technology.STEEL },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {}),

	PIKEMAN(CombatType.MELEE, 100, 10, -1, -1, 2, null,
			new Technology[] { Technology.CIVIL_SERVICE },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.BONUS_VS_MOUNTED_100}),

	TREBUCHET(CombatType.SIEGE, 170, 6, 20, 2, 2, Resource.IRON,
			new Technology[] { Technology.PHYSICS },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.RANGE_ATTACK, UnitActionType.PRE_ATTACK_SETUP },
			new UnitAbility[] {UnitAbility.BONUS_VS_CITY_10, UnitAbility.NO_DEFENSIVE_BONUS, UnitAbility.LIMITED_VISIBILITY, }),

	CANON(CombatType.SIEGE, 250, 10, 26, 2, 2, null,
			new Technology[] { Technology.CHEMISTRY },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.RANGE_ATTACK, UnitActionType.PRE_ATTACK_SETUP },
			new UnitAbility[] {UnitAbility.BONUS_VS_CITY_10, UnitAbility.NO_DEFENSIVE_BONUS}),

	CAVALRY(CombatType.MOUNTED, 260, 25, -1, -1, 3, Resource.HORSE,
			new Technology[] { Technology.MILITARY_SCIENCE },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.NO_DEFENSIVE_BONUS, UnitAbility.CAN_MOVE_AFTER_ATTACK}),

	LANCER(CombatType.MOUNTED, 220, 22, -1, -1, 4, Resource.HORSE,
			new Technology[] { Technology.METALLURGY },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.NO_DEFENSIVE_BONUS, UnitAbility.CAN_MOVE_AFTER_ATTACK}),

	MUSKETMAN(CombatType.GUNPOWDER, 120, 16, -1, -1, 2, null,
			new Technology[] { Technology.GUNPOWDER },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {}),

	RIFLEMAN(CombatType.GUNPOWDER, 200, 25, -1, -1, 2, null,
			new Technology[] { Technology.RIFLING },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {}),

	ANTITANKGUN(CombatType.GUNPOWDER, 300, 32, -1, -1, 2, null,
			new Technology[] { Technology.REPLACEABLE_PARTS },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.BONUS_VS_TANK_10}),

	ARTILLERY(CombatType.SIEGE, 420, 16, 32, 3, 2, null,
			new Technology[] { Technology.DYNAMITE },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.RANGE_ATTACK, UnitActionType.PRE_ATTACK_SETUP },
			new UnitAbility[] {UnitAbility.INDIRECT_FIRE, UnitAbility.BONUS_VS_CITY_10, UnitAbility.NO_DEFENSIVE_BONUS}),

	INFANTRY(CombatType.GUNPOWDER, 300, 36, -1, -1, 2, null,
			new Technology[] { Technology.REPLACEABLE_PARTS },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {}),

	PANZER(CombatType.ARMORED, 450, 60, -1, -1, 5, null,
			new Technology[] { Technology.COMBUSTION },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.NO_DEFENSIVE_BONUS, UnitAbility.LIMITED_VISIBILITY, UnitAbility.CAN_MOVE_AFTER_ATTACK}),

	TANK(CombatType.ARMORED, 450, 50, -1, -1, 4, null,
			new Technology[] { Technology.COMBUSTION },
			new UnitActionType[] { UnitActionType.DELETE, UnitActionType.MOVE, UnitActionType.SLEEP_WAKE,
					UnitActionType.ALERT, UnitActionType.FORTIFY, UnitActionType.PILLAGE,
					UnitActionType.MELEE_ATTACK },
			new UnitAbility[] {UnitAbility.PENALTY_VS_CITY_10, UnitAbility.NO_DEFENSIVE_BONUS, UnitAbility.CAN_MOVE_AFTER_ATTACK});

	public final CombatType combatType;
	public final int cost;
	public final int combatStrength;
	public final int rangedCombatStrength;
	public final int range;
	public final int MP;
	public final Resource resourceRequired;
	public final Technology technologyRequired[];
	public final UnitActionType actionTypes[];
	public final UnitAbility unitAbilities[];

	private UnitType(CombatType combatType, int cost, int combatStrength, int rangedCombatStrength, int range, int MP,
			Resource resourceRequired, Technology technologyRequired[], UnitActionType actionTypes[],
			UnitAbility unitAbilities[]) {
		this.combatType = combatType;
		this.cost = cost;
		this.combatStrength = combatStrength;
		this.rangedCombatStrength = rangedCombatStrength;
		this.range = range;
		this.MP = MP;
		this.resourceRequired = resourceRequired;
		this.technologyRequired = technologyRequired;
		this.actionTypes = actionTypes;
		this.unitAbilities = unitAbilities;
	}

	public Unit createUnit(Civilization owner, Tile tile) {
		return combatType.createUnit(this, owner, tile);
	}

	public boolean isActionAppliable(UnitActionType actionType) {
		for (UnitActionType unitActionType : actionTypes) {
			if (unitActionType == actionType)
				return true;
		}
		return false;
	}
	public boolean hasAbility(UnitAbility ability){
		for (UnitAbility unitAbility : unitAbilities) {
			if (unitAbility==ability){
				return true;
			}
		}
		return false;
	}
}
