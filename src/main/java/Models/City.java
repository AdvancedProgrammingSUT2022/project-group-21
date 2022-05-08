package Models;

import Models.Unit.MilitaryUnit;
import Models.Unit.UnitType;

import java.util.ArrayList;

public class City {
	private final ArrayList<Tile> tiles = new ArrayList<>();
	private Civilization civilization;
	private int goldOut;
	private int foodOut;
	private boolean isCapital;
	private ArrayList<Citizen> citizens;
	private Tile center;
	private MilitaryUnit garrisonUnit;
	private int combatStrength;

	public City(Tile tile, Civilization civilization) {
		this.center = tile;
		this.civilization = civilization;
	}
	
	public double getCombatStrength(int thisTurn) {
		// TODO: exponential
		double number = this.combatStrength;
		if (this.center.getMilitaryUnit().isOnGarrison()){
			for (int i = this.center.getMilitaryUnit().getLastActionTurn(); i<thisTurn; i++){
				number = 1.25 * number;
			}
		}
		return number;
	}
	
	public void addCitizen(Citizen citizen){ citizens.add(citizen);}
	public void removeCitizen(Citizen citizen){ citizens.remove(citizen);}
	public int countCitizens(){ return citizens.size();}
	public Citizen getCitizenToAssign(){
		for (Citizen citizen : citizens){
			if (!citizen.isWorking())
				return citizen;
		}
		int index = (int)(Math.random() * citizens.size());
		return citizens.get(index);
	}

	
	
	public void addTile(Tile tile){ tiles.add(tile);}
	public boolean hasTile(Tile tile){ return tiles.contains(tile);}
	

	public Civilization getCivilization(){ return civilization;}
}
