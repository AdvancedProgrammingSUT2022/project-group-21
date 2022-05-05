package Models.Unit;

import Models.Civilization;
import Models.Tile;

public enum CombatType{
	CIVILIAN{
		public Unit createUnit(UnitType unitType, Civilization owner, Tile tile){
			// TODO: maybe raise error?
			return null;
		}
	},
	MELEE{
		public Unit createUnit(UnitType unitType, Civilization owner, Tile tile){
			return new MeleeUnit(unitType, owner, tile);
		}
	},
	RANGED{
		public Unit createUnit(UnitType unitType, Civilization owner, Tile tile){
			return new RangedUnit(unitType, owner, tile);
		}
	},
	SIEGE{
		public Unit createUnit(UnitType unitType, Civilization owner, Tile tile){
			return new SiegeUnit(unitType, owner, tile);
		}
	};

	public abstract Unit createUnit(UnitType unitType, Civilization owner, Tile tile);
	
}
