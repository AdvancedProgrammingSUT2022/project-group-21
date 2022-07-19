package com.example.Contoller;

import java.util.ArrayList;

import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.city.City;
import com.example.Model.spfa.ShortestPath;
import com.example.Model.spfa.ShortestPathSmall;
import com.example.Model.tile.Tile;
import com.example.Model.unit.CombatType;
import com.example.Model.unit.MilitaryUnit;
import com.example.Model.unit.Unit;
import com.example.Model.unit.UnitState;

public class UnitActionController {
	private static UnitActionController instance;
	public static UnitActionController getInstance(){
		if (instance==null) instance=new UnitActionController();
		return instance;
	}

	public void delete(Unit unit, boolean doAction){
		if (doAction){
			unit.kill();
			unit.getOwner().addGold(unit.unitType.cost/10);
		}
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
		if (militaryUnit.getMP()<=0) throw new Exception("no MovementPoint");		
		
		Tile tile = militaryUnit.getTile();
		Tile tile2 = Game.getInstance().getTile(x2, y2);
		if (!tile.isNeighbourWith(tile2)) throw new Exception("target tile should be adjacent for Melee Attack");
		checkDiplomacyShit(militaryUnit.getOwner(), tile2);
		
		if (tile2.getCityOnTile()!=null){
			City city = tile2.getCityOnTile();
			if (city.getOwner()==militaryUnit.getOwner()) throw new Exception("no enemy on this tile");
			if (doAction) CombatController.meleeAttackCity(militaryUnit, city);
			return ;
		}
		Unit enemyUnit = tile2.getMilitaryUnit();
		if (enemyUnit==null || enemyUnit.getOwner()==militaryUnit.getOwner()) throw new Exception("no enemy on this tile");
		if (doAction) CombatController.meleeAttackUnit(militaryUnit, tile);
	}

	public void rangeAttack(MilitaryUnit militaryUnit, int x2, int y2, boolean doAction) throws Exception{
		if (!Game.getInstance().checkTileCoordinates(x2, y2)) throw new Exception("target tile does not exist");
		if (!militaryUnit.getOwner().isTileRevealed(x2, y2)) throw new Exception("target tile is not revealed");
		if (militaryUnit.getMP()<=0) throw new Exception("no Movement Point");
		if (militaryUnit.unitType.combatType==CombatType.SIEGE && militaryUnit.getUnitState()!=UnitState.PRE_ATTACK)
			throw new Exception("siege unit should set-up before attack");
		
		Tile tile = militaryUnit.getTile();
		Tile tile2 = Game.getInstance().getTile(x2, y2);
		checkDiplomacyShit(militaryUnit.getOwner(), tile2);
		ShortestPathSmall shortestPathSmall = new ShortestPathSmall(Game.getInstance(), tile, militaryUnit.unitType.range);
		if (shortestPathSmall.getDistance(tile2)>militaryUnit.unitType.range)
			throw new Exception("target tile is not in range");
		
		if (tile2.getCityOnTile()!=null){
			City city = tile2.getCityOnTile();
			if (city.getOwner()==militaryUnit.getOwner()) throw new Exception("no enemy on this tile");
			if (doAction) CombatController.rangeAttackCity(militaryUnit, city);
			return ;
		}
		Unit enemyUnit = tile2.getMilitaryUnit();
		if (enemyUnit==null) enemyUnit = tile2.getCivilianUnit();
		if (enemyUnit==null || enemyUnit.getOwner()==militaryUnit.getOwner()) throw new Exception("no enemy on this tile");
		if (doAction) CombatController.rangeAttackUnit(militaryUnit, tile);
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
		ArrayList<Tile> path = new ShortestPath(Game.getInstance(), unit, -1).getPath(tile2);
		if (path==null) throw new Exception("no path found");
		if (doAction){
			unit.setUnitState(UnitState.WAKE);
			unit.setPath(path);
			unit.moveOnPath();
		}
	}

}
