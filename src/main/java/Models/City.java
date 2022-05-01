package Models;

import Models.Unit.MilitaryUnit;
import java.util.ArrayList;

public class City {
	private final ArrayList<Tile> tiles = new ArrayList<>();
	private Civilization civilization;
	private int gold;
	private int food;
	private boolean isCapital;
	private ArrayList<Citizen> citizens;
	private Tile tile;
	private MilitaryUnit garrisonUnit;
	private int combatStrength;

	public City(int gold, int food, Tile tile) {
		this.gold = gold;
		this.food = food;
		this.tile = tile;
	}
	
	public double getCombatStrength(int thisTurn) {
		// TODO: exponential
		double number = this.combatStrength;
		if (this.tile.getMilitaryUnit().isOnGarrison()){
			for (int i = this.tile.getMilitaryUnit().getLastActionTurn();i<thisTurn;i++){
				number = 1.25 * number;
			}
		}
		return number;
	}

	public void lockCitizen(Tile tile) {
		Citizen citizen = getCitizenToAssign();
		citizen.assignToTile(tile);
	}
	private Citizen getCitizenToAssign(){
		for (Citizen citizen : citizens){
			if (!citizen.isWorking())
				return citizen;
		}
		int index = (int)(Math.random() * citizens.size());
		return citizens.get(index);
	}

	public void removeCitizen(Citizen citizen) {
		citizens.remove(citizen);
	}

	public void removeCitizenFromWork(Citizen citizen){
		citizen.getWorkingTile().setCitizen(null);
		citizen.setWorkingTile(null);
	}

	public void addTile(Tile tile) {
		tiles.add(tile);
	}

	public void addCitizen() {
		citizens.add(new Citizen(this));
	}

}
