package Models.Unit;

import Models.Tile;

public class Settler extends Unit{
    Settler(int HP, int MP, Tile tile, int combatStrength, int COST) {
        super(HP, MP, tile, combatStrength, COST);
    }
    public void findCity(Tile tile) {}
}
