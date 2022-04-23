package Models.Unit;

import Models.Tile;

public class Unit {
    private int HP;
    private int MP;
    private Tile tile;
    private int combatStrength;
    public final int COST;

    public Tile getTile() {
        return tile;
    }

    Unit(int HP, int MP, Tile tile, int combatStrength, int COST) {
        this.HP = HP;
        this.MP = MP;
        this.tile = tile;
        this.combatStrength = combatStrength;
        this.COST = COST;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getCombatStrength() {
        return combatStrength;
    }
    public boolean isTileVisible(int x, int y) {
        //TODO
        return false;
    }
}
