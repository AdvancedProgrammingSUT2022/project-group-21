package Contoller;

import Models.City;
import Models.Tile.Tile;
import Models.Unit.MilitaryUnit;

public class CombatController {
	private static CombatController instance;
	public static CombatController getInstance(){
		if (instance==null) instance=new CombatController();
		return instance;
	}

	public void meleeAttackUnit(MilitaryUnit unit, Tile tile){
		// TODO
	}
	
	public void rangeAttackUnit(MilitaryUnit unit, Tile tile){
		// TODO
	}
	
	public void meleeAttackCity(MilitaryUnit unit, Tile tile){
		// TODO
	}
	
	public void rangeAttackCity(MilitaryUnit unit, Tile tile){
		// TODO
	}
	
	public void cityAttackTile(City city, Tile tile){
		// TODO
	}
}
