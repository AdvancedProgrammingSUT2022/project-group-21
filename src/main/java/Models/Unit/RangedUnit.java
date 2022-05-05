package Models.Unit;

import Models.City;
import Models.Civilization;
import Models.Tile;

public class RangedUnit extends MilitaryUnit{
    public void rangeAttack(Tile tile) {}
    public boolean isInRange(Tile tile) {
        //TODO
        return false;
    }
    public void attackToCity(City city) {}


    public RangedUnit(UnitType unitType, Civilization owner, Tile tile) {
        super(unitType, owner, tile);
    }
}
