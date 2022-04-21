package Models.Unit;

import Models.Tile;

public class Unit {
    private int HP;
    private int MP;
    private Tile tile;
    private int combatStrength;
    public final int COST;

    Unit(int HP, int MP, Tile tile, int combatStrength, int COST) {
        this.HP = HP;
        this.MP = MP;
        this.tile = tile;
        this.combatStrength = combatStrength;
        this.COST = COST;
    }
    public boolean isMovePossible(int x, int y) {}
    public boolean move() {}
    public int getCombatStrength() {
        return combatStrength;
    }
    public boolean isTileVisible(int x, int y) {}
}
