package com.example.Contoller;

import com.example.Model.city.City;
import com.example.Model.tile.Tile;
import com.example.Model.unit.CivilianUnit;
import com.example.Model.unit.CombatType;
import com.example.Model.unit.MilitaryUnit;
import com.example.Model.unit.Unit;
import com.example.Model.unit.UnitAbility;
import com.example.Model.unit.UnitType;

public class CombatController {
	
	public static void meleeAttackUnit(MilitaryUnit unit, Tile tile){
		if (unit.getMP()==0) return ;
		if (!unit.unitType.hasAbility(UnitAbility.CAN_MOVE_AFTER_ATTACK)) unit.setMP(0);
		else unit.setMP(unit.getMP()-1);
		MilitaryUnit enemyUnit = tile.getMilitaryUnit();
		if (enemyUnit!=null){
			gainDamageUnit(enemyUnit, getDamageAgainstUnit(unit, enemyUnit));
			gainDamageUnit(unit, getDamageAgainstUnit(enemyUnit, unit));
		}
		if (tile.getMilitaryUnit()!=null) return ;
		// killed enemyUnit
		unit.moveSingleTile(tile);
		CivilianUnit civilianUnit = tile.getCivilianUnit();
		if (civilianUnit!=null){
			civilianUnit.changeOwner(unit.getOwner());
		}
	}
	
	public static void rangeAttackUnit(MilitaryUnit unit, Tile tile){
		if (unit.getMP()==0) return ;
		if (!unit.unitType.hasAbility(UnitAbility.CAN_MOVE_AFTER_ATTACK)) unit.setMP(0);
		else unit.setMP(unit.getMP()-1);
		double damage = unit.unitType.rangedCombatStrength;
		if (unit.unitType.hasAbility(UnitAbility.INDIRECT_FIRE)) damage*=1.5;
		if (tile.getCivilianUnit()!=null) gainDamageUnit(tile.getCivilianUnit(), damage);
		if (tile.getMilitaryUnit()!=null) gainDamageUnit(tile.getMilitaryUnit(), damage);
	}
	
	public static void meleeAttackCity(MilitaryUnit unit, City city){
		if (unit.getMP()==0) return ;
		if (!unit.unitType.hasAbility(UnitAbility.CAN_MOVE_AFTER_ATTACK)) unit.setMP(0);
		else unit.setMP(unit.getMP()-1);
		double damage = unit.unitType.rangedCombatStrength;
		if (unit.unitType.hasAbility(UnitAbility.INDIRECT_FIRE)) damage*=1.5;
		if (unit.unitType.hasAbility(UnitAbility.BONUS_VS_CITY_10)) damage*=1.1;
		if (unit.unitType.hasAbility(UnitAbility.PENALTY_VS_CITY_10)) damage*=0.9;
		gainDamageCity(city, damage);
		gainDamageUnit(unit, city.getCombatStrength());
		if (unit.getHP()<=0 || city.getHP()>0) return ;
		if (city.getCenter().getMilitaryUnit()!=null) city.getCenter().getMilitaryUnit().kill();
		if (city.getCenter().getCivilianUnit()!=null) city.getCenter().getCivilianUnit().changeOwner(unit.getOwner());
		city.setOwner(unit.getOwner());
		unit.moveSingleTile(city.getCenter());
	}
	
	public static void rangeAttackCity(MilitaryUnit unit, City city){
		if (unit.getMP()==0) return ;
		if (!unit.unitType.hasAbility(UnitAbility.CAN_MOVE_AFTER_ATTACK)) unit.setMP(0);
		else unit.setMP(unit.getMP()-1);

		double damage = unit.unitType.rangedCombatStrength;
		if (unit.unitType.hasAbility(UnitAbility.INDIRECT_FIRE)) damage*=1.5;
		if (unit.unitType.hasAbility(UnitAbility.BONUS_VS_CITY_10)) damage*=1.1;
		if (unit.unitType.hasAbility(UnitAbility.PENALTY_VS_CITY_10)) damage*=0.9;
		gainDamageCity(city, damage);
	}
	
	public static void cityAttackTile(City city, Tile tile){
		city.setAttackedInThisTurn(true);
		if (tile.getCivilianUnit()!=null) gainDamageUnit(tile.getCivilianUnit(), 60);
		if (tile.getMilitaryUnit()!=null) gainDamageUnit(tile.getMilitaryUnit(), 60);
	}

	private static void gainDamageUnit(Unit unit, double damage){
		if (unit instanceof CivilianUnit) damage = 8;
		else damage/=((MilitaryUnit) unit).getCombatStrength();
		double HP = unit.getHP()-damage;
		if (HP<=0){
			unit.kill();
			return ;
		}
		unit.setHP(HP);
	}

	private static void gainDamageCity(City city, double damage){
		damage/=city.getCombatStrength();
		city.setHP(city.getHP()-damage);
	}

	private static double getDamageAgainstUnit(MilitaryUnit unit, MilitaryUnit enemyUnit){
		double damage = unit.unitType.combatStrength;
		if (unit.unitType.hasAbility(UnitAbility.ROUGH_TERRAIN_PENALTY) && unit.getTile().getTerrain().isRoughTerrain()) damage*=0.75;
		if (unit.unitType.hasAbility(UnitAbility.BONUS_VS_MOUNTED_100) && enemyUnit.unitType.combatType==CombatType.MOUNTED) damage*=2;
		if (unit.unitType.hasAbility(UnitAbility.BONUS_VS_TANK_10) && enemyUnit.unitType==UnitType.TANK) damage*=1.1;
		return damage;
	}
}
