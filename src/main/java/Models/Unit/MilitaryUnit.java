package Models.Unit;

import Models.Tile;

public class MilitaryUnit extends Unit{
	private int XP;
	private boolean isSleep;
	private boolean isOnFortify;
	private boolean isOnAlert;
	private boolean isOnGarrison;
	private final int RANGE;
	private int lastActionTurn; // TODO: update this

	public MilitaryUnit(int XP, boolean isSleep, boolean isOnFortify, boolean isOnAlert, boolean isOnGarrison,
						int range, int lastActionTurn, int HP, int MP, Tile tile, int combatStrength, int COST){
		super(HP, MP, tile, COST, combatStrength);
		this.XP = XP;
		this.isSleep = isSleep;
		this.isOnFortify = isOnFortify;
		this.isOnAlert = isOnAlert;
		this.isOnGarrison = isOnGarrison;
		this.RANGE = range;
		this.lastActionTurn = lastActionTurn;
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
		double number = this.getCombatStrength();
		if (this.isOnFortify){
			for (int i = lastActionTurn;i<thisTurn;i++){
				number = 1.25 * number;
			}
		}
		else if (this.isOnGarrison){
			for (int i = lastActionTurn;i<thisTurn;i++){
				number = 1.25 * number;
				// TODO: exponential?! 
			}
		}
		number = number + number * getTile().getCombatModifier();
		return number;
	}
	public void move(Tile tile) {
		this.setTile(tile);
		// TODO: change isGarrison ...
	}

}
