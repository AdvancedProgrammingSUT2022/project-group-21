package Models.Unit;

import Models.Civilization;
import Models.Tile.Tile;

public abstract class CivilianUnit extends Unit{
    CivilianUnit(UnitType unitType, Civilization owner, Tile tile){
        super(unitType, owner, tile);
    }
}
