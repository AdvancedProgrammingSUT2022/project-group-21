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
        while (!tile.equals(lastTile) && tile.getPassable()){
            tile = nextTile(tile);
            if (unit.getMP() == 0){
                break;
            }
            else {
                if (unit instanceof MilitaryUnit){
                    MilitaryUnit militaryUnit = (MilitaryUnit) unit;
                    if (tile.getMilitaryUnit() == null){
                        if (riverPass()){
                            militaryUnit.move(tile);
                            militaryUnit.setMP(0);
                        }
                        else {
                            militaryUnit.move(tile);
                            if (militaryUnit.getMP() - tile.getMovementCost() < 0){
                                militaryUnit.setMP(0);
                            }
                            else {
                                militaryUnit.setMP(militaryUnit.getMP() - tile.getMovementCost());

                            }
                        }
                    }
                }
                else if (unit instanceof CivilianUnit){
                    CivilianUnit civilianUnit = (CivilianUnit) unit;
                    if (tile.getCivilianUnit() == null){
                        if (riverPass()){
                            civilianUnit.move(tile);
                            civilianUnit.setMP(0);
                        }
                        else {
                            civilianUnit.move(tile);
                            if (civilianUnit.getMP() - tile.getMovementCost() < 0){
                                civilianUnit.setMP(0);
                            }
                            else {
                                civilianUnit.setMP(civilianUnit.getMP() - tile.getMovementCost());
                            }
                        }
                    }

                }
            }
        }
    }
    public Tile nextTile(Tile tile){
        //TODO
        return null;
    }
    public boolean riverPass(){
        //TODO
        return false;
    }
}
