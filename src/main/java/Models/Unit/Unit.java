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

    Unit(int HP, int MP, Tile tile, int COST, int combatStrength) {
        this.HP = HP;
        this.MP = MP;
        this.tile = tile;
        this.COST = COST;
        this.combatStrength = combatStrength;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getMP() {
        return MP;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public boolean isTileVisible(int x, int y) {
        //TODO
        return false;
    }
    public void move(Tile tile){
        this.tile = tile;
    }
    public void setMP(int MP) {
        this.MP = MP;
    }
}
