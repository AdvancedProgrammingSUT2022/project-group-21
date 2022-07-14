package com.example.Model.unit;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;

public enum CombatType{
	CIVILIAN{
		@Override
		public Unit createUnit(UnitType unitType, Civilization owner, Tile tile){
			if (unitType==UnitType.SETTLER) return new Settler(owner, tile);
			if (unitType==UnitType.WORKER) return new Worker(owner, tile);
			assert(false);
			return null;
		}
	},
	MELEE,
	ARCHERY,
	SIEGE,
	MOUNTED,
	RECON,
	GUNPOWDER,
	ARMORED
	;

	public Unit createUnit(UnitType unitType, Civilization owner, Tile tile){
		return new MilitaryUnit(unitType, owner, tile);
	}
}
