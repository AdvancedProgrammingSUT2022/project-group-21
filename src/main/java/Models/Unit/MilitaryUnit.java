package Models.Unit;

import Models.Civilization;
import Models.Tile;

public abstract class MilitaryUnit extends Unit{
	private int XP;
	private boolean isSleep;
	private boolean isOnFortify;
	private boolean isOnAlert;
	private boolean isOnGarrison;

	public MilitaryUnit(UnitType unitType, Civilization owner, Tile tile){
		super(unitType, owner, tile);
	}
	
	public void sleep() {
		this.isSleep = true;
	}
	public void fortify() {
		this.isOnFortify = true;
	}
	public void alert() {
		this.isOnAlert = true;
	}
	public void garrison() {
		this.isOnGarrison = true;
	}
	public void wakeUp(){
		this.isSleep = false;
	}

	public int getLastActionTurn() {
		return lastActionTurn;
	}

	public boolean isOnGarrison() {
		return isOnGarrison;
	}

	public double getCombatStrength(int thisTurn) {
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
	public void move(Tile tile) {
		super.move(tile);
		//TODO garrison and fortify...
	}
}
