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
    public void checkingGarrison(){
        //TODO
    }
}
