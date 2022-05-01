package Models.Unit;

import Models.City;
import Models.Tile;

public class Settler extends CivilianUnit{
    Settler(int HP, int MP, Tile tile, int combatStrength, int COST) {
        super(HP, MP, tile, combatStrength, COST);
    }
    public void findCity(Tile tile) {
        City city = new City(tile.getFood(),tile.getFood(),tile);
        // TODO
    }
}
