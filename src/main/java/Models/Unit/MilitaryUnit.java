package Models.Unit;

import Models.City;
import Models.Civilization;
import Models.Tile.Tile;

public class MilitaryUnit extends Unit{
	private int XP;
	private boolean isSleep;
	private boolean isOnFortify;
	private boolean isOnAlert;
	private boolean isOnGarrison;
	private Tile targetTile;

	public MilitaryUnit(UnitType unitType, Civilization owner, Tile tile){
		super(unitType, owner, tile);
	}
	
	public void sleep(){ this.isSleep=true;}
	public void fortify(){ this.isOnFortify=true;}
	public void alert(){ this.isOnAlert = true;}
	public void garrison(){ this.isOnGarrison = true;}
	public void wakeUp(){ this.isSleep = false;}


	public boolean isOnGarrison() {
		return isOnGarrison;
	}

	public double getCombatStrength(int thisTurn) {
		// TODO: not exponential
		double number = getCombatStrength();
		if (this.isOnFortify){
			for (int i = lastActionTurn;i<thisTurn;i++){
				number = 1.25 * getCombatStrength();
			}
		}
		else if (this.isOnGarrison){
			for (int i = lastActionTurn;i<thisTurn;i++){
				number = 1.25 * getCombatStrength();
			}
		}
		number = number + number * getTile().getCombatModifier();
		return number;
	}

	@Override
	public void setTile(Tile tile) {
		super.setTile(tile);
		//TODO garrison and fortify...
	}



	public void siegePreAttack(Tile tile){
		//TODO
	}
	public boolean isInRange(Tile tile) {
		//TODO
		return false;
	}
	public void rangeAttack(Tile tile) {
		// TODO
	}
	public void attackToUnit(Tile tile) {
		//TODO
	}
	public void attackToCity(City city) {
		// TODO
	}


}
