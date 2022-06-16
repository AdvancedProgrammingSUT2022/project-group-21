package Contoller;

import java.util.ArrayList;

import Models.City;
import Models.Civilization;
import Models.Game;
import Models.ShortestPath;
import Models.Tile.Tile;
import Models.Unit.CombatType;
import Models.Unit.MilitaryUnit;
import Models.Unit.Unit;
import Models.Unit.Unit.UnitState;

public class UnitActionController {
	private static UnitActionController instance;
	public static UnitActionController getInstance(){
		if (instance==null) instance=new UnitActionController();
		return instance;
	}

	public void delete(Unit unit, boolean doAction){
		if (doAction) unit.kill();
	}
	public void sleepWake(Unit unit, boolean doAction){
		if (doAction){
			if (unit.getUnitState()==UnitState.SLEEP || unit.getUnitState()==UnitState.ALERT) unit.setUnitState(UnitState.WAKE);
			else unit.setUnitState(UnitState.SLEEP);
		}
	}
	public void alert(MilitaryUnit militaryUnit, boolean doAction) throws Exception{
		if (militaryUnit.getUnitState()==UnitState.SLEEP) throw new Exception("unit is sleeping; wake him up first!");
		if (doAction) militaryUnit.setUnitState(UnitState.ALERT);
	}
	public void fortify(MilitaryUnit militaryUnit, boolean doAction) throws Exception{
		if (militaryUnit.getUnitState()==UnitState.SLEEP) throw new Exception("unit is sleeping; wake him up first!");
		if (doAction) militaryUnit.setUnitState(UnitState.ALERT);
	}
	public void pillage(MilitaryUnit militaryUnit, boolean doAction) throws Exception{
		if (militaryUnit.getUnitState()==UnitState.SLEEP) throw new Exception("unit is sleeping; wake him up first!");
		Tile tile = militaryUnit.getTile();
		if (tile.getImprovement()==null) throw new Exception("this tile has no improvement to pillage");
		if (tile.isPillaged()) throw new Exception("this tile is already pillaged");
		if (doAction) militaryUnit.pillage();
	}

	// my civilization, target tile
	private void checkDiplomacyShit(Civilization civilization, Tile tile) throws Exception{
		Civilization enemy = null;
		City city = tile.getCityOnTile();
		if (city!=null && city.getOwner()!=civilization) enemy = city.getOwner();
		Unit unit = tile.getMilitaryUnit();
		if (unit==null) unit=tile.getCivilianUnit();
		if (unit!=null && unit.getOwner()!=civilization) enemy = unit.getOwner();
		if (enemy!=null && !civilization.isEnemy(enemy))
			throw new Exception("cant do Action because target tile is not enemy");
	}
	public void meleeAttack(MilitaryUnit militaryUnit, int x2, int y2, boolean doAction) throws Exception{
		if (!Game.getInstance().checkTileCoordinates(x2, y2)) throw new Exception("target tile does not exist");
		if (!militaryUnit.getOwner().isTileRevealed(x2, y2)) throw new Exception("target tile is not revealed");
		
		Tile tile = militaryUnit.getTile();
		Tile tile2 = Game.getInstance().getTile(x2, y2);
		checkDiplomacyShit(militaryUnit.getOwner(), tile2);
		if (!tile.isNeighbourWith(tile2)) throw new Exception("target tile should be adjacent for Melee Attack");
		if (militaryUnit.getMP()<=0) throw new Exception("no MovementPoint");		
		if (doAction){
			// TODO: attack!
		}
	}
	public void rangeAttack(MilitaryUnit militaryUnit, int x2, int y2, boolean doAction) throws Exception{
		if (!Game.getInstance().checkTileCoordinates(x2, y2)) throw new Exception("target tile does not exist");
		if (!militaryUnit.getOwner().isTileRevealed(x2, y2)) throw new Exception("target tile is not revealed");
		
		Tile tile = militaryUnit.getTile();
		Tile tile2 = Game.getInstance().getTile(x2, y2);
		checkDiplomacyShit(militaryUnit.getOwner(), tile2);
		// TODO: check distance
		// if (!tile.isNeighbourWith(tile2)) throw new Exception("target tile should be adjacent for Melee Attack");
		if (militaryUnit.getMP()<=0) throw new Exception("no Movement Point");
		if (militaryUnit.unitType.combatType==CombatType.SIEGE && militaryUnit.getUnitState()!=UnitState.PRE_ATTACK)
			throw new Exception("siege unit should set-up before attack");
		if (doAction) {
			// TODO: fire!
		}
	}

	public void siegePreAttack(MilitaryUnit militaryUnit, boolean doAction) throws Exception {
		if (militaryUnit.getUnitState()==UnitState.SLEEP) throw new Exception("unit is sleeping!");
		if (militaryUnit.getUnitState()==UnitState.PRE_ATTACK) throw new Exception("unit is already set for attack");
		if (militaryUnit.getMP()<=0) throw new Exception("no Movement Point");
		if (doAction){
			militaryUnit.setUnitState(UnitState.PRE_ATTACK);
			militaryUnit.decreaseMP(1);
		}
	}

	public void move(Unit unit, int x2, int y2, boolean doAction) throws Exception{
		if (unit.getUnitState()==UnitState.SLEEP) throw new Exception("unit is sleeping! wake him up first.");
		Tile tile1 = unit.getTile();
		Tile tile2 = Game.getInstance().getTile(x2, y2);
		if (tile1 == tile2)
			throw new Exception("you cant move to your current tile!");
		if (!unit.getOwner().isTileRevealed(x2, y2))
			throw new Exception("destination tile is not revealed");
		if (!tile2.canPutUnit(unit))
			throw new Exception("destination contains another unit/city");
		ArrayList<Tile> path = new ShortestPath(Game.getInstance(), unit).getPath(tile2);
		if (path==null) throw new Exception("no path found");
		if (doAction){
			unit.setUnitState(UnitState.WAKE);
			unit.setPath(path);
			unit.moveOnPath();
		}
	}
}
