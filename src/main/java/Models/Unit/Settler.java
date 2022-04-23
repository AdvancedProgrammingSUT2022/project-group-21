package Models.Unit;

import Models.City;
import Models.Tile;

public class Settler extends Unit{
    Settler(int HP, int MP, Tile tile, int combatStrength, int COST) {
        super(HP, MP, tile, combatStrength, COST);
    }
    public void findCity(Tile tile) {
        City city = new City(tile.getFood(),tile.getFood(),"",tile);
    }
    public boolean isMovePossible(Tile tile) {
        if (tile.getWorker() == null){
            return true;
        }
        return false;
    }
    public void move(Tile tile) {
        if (isMovePossible(tile)){
            this.setTile(tile);
        }
    }
}
