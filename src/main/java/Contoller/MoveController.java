package Contoller;

import Models.Tile;
import Models.Unit.CivilianUnit;
import Models.Unit.MilitaryUnit;
import Models.Unit.Unit;

public class MoveController {
    //Felan mojaver ...
    private Tile firstTile;
    private Tile lastTile;
    private Unit unit;

    public MoveController(Tile firstTile, Tile lastTile, Unit unit) {
        this.firstTile = firstTile;
        this.lastTile = lastTile;
        this.unit = unit;
    }

    public void move(){
        //Felan mojaver ...
        Tile tile = firstTile;
        while (!tile.equals(lastTile)){
            tile = nextTile(tile);
            if (unit.getMP() < tile.getMovementCost()){
                break;
            }
            else {
                if (unit instanceof MilitaryUnit){
                    MilitaryUnit militaryUnit = (MilitaryUnit) unit;
                    if (tile.getMilitaryUnit() == null){
                        militaryUnit.move(tile);
                        militaryUnit.setMP(militaryUnit.getMP() - tile.getMovementCost());
                    }
                }
                else if (unit instanceof CivilianUnit){
                    CivilianUnit civilianUnit = (CivilianUnit) unit;
                    if (tile.getCivilianUnit() == null){
                        civilianUnit.move(tile);
                        civilianUnit.setMP(civilianUnit.getMP() - tile.getMovementCost());
                    }

                }
            }
        }
    }
    public Tile nextTile(Tile tile){
        return null;
    }
}
