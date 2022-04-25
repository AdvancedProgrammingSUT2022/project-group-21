package Models.Unit;

import Models.Tile;

public class CivilianUnit extends Unit{
    CivilianUnit(int HP, int MP, Tile tile, int combatStrength, int COST) {
        super(HP, MP, tile, combatStrength, COST);
    }
    public void move(Tile tile) {
        this.setTile(tile);
    }
}
