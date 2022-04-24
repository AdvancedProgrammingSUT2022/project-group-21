package Models;

import Models.Unit.MilitaryUnit;

import java.util.ArrayList;

public class City {
    private Civilization civilization;
    private int gold;
    private int food;
    private boolean isCapital;
    private ArrayList<Citizen> citizens;
    private Tile tile;
    private String name;
    private MilitaryUnit garrisonUnit;
    private int combatStrength;

    public City(int gold, int food, String name, Tile tile) {
        this.gold = gold;
        this.food = food;
        this.name = name;
        this.tile = tile;
    }
    public boolean isTileVisible(int x, int y) {
        //TODO
        return false;
    }

    public double getCombatStrength(int thisTurn) {
        double number = this.combatStrength;
        if (this.tile.getMilitaryUnit().isOnGarrison()){
            for (int i = this.tile.getMilitaryUnit().getLastActionTurn();i<thisTurn;i++){
                number = 1.25 * number;
            }
        }
        return number;
    }


}
