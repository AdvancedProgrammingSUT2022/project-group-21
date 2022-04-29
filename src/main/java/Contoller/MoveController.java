package Contoller;

import Models.Tile;
import Models.Unit.CivilianUnit;
import Models.Unit.MilitaryUnit;
import Models.Unit.Unit;

public class MoveController {
	private static MoveController instance;
	private static void setInstance(MoveController instance) {
		MoveController.instance = instance;
	}
	public static MoveController getInstance() {
		if (instance == null) MoveController.setInstance(new MoveController());
		return instance;
	}
	
	//Felan mojaver ...
	private Tile firstTile;
	private Tile lastTile;
	private Unit unit;

	
	public boolean move(Tile firstTile, Tile lastTile, Unit unit){
		this.firstTile=firstTile;
		this.lastTile=lastTile;
		this.unit=unit;

		Tile tile = firstTile;
		while (!tile.equals(lastTile) && tile.isPassable()){
			tile = nextTile(tile);
			if (unit.getMP() == 0){
				return false;
			}
			if (unit instanceof MilitaryUnit){
				if (tile.getMilitaryUnit() == null){
					if (riverPass(unit.getTile(),tile)){
						((MilitaryUnit) unit).move(tile);
						unit.setMP(0);
					}
					else {
						((MilitaryUnit) unit).move(tile);
						if (unit.getMP() - tile.getMovementCost() < 0){
							unit.setMP(0);
						}
						else {
							unit.setMP(unit.getMP() - tile.getMovementCost());

						}
					}
				}
				else {
					if (!tile.equals(lastTile) && unit.getMP() >= nextTile(tile).getMovementCost() && !riverPass(unit.getTile(),tile) && nextTile(tile).isPassable()){
                        ((MilitaryUnit) unit).move(tile);
                        unit.setMP(unit.getMP() - tile.getMovementCost());
                    }
				}
			}
			else if (unit instanceof CivilianUnit){
				if (tile.getCivilianUnit() == null){
					if (riverPass(unit.getTile(),tile)){
						((CivilianUnit) unit).move(tile);
						unit.setMP(0);
					}
					else {
						((CivilianUnit) unit).move(tile);
						if (unit.getMP() - tile.getMovementCost() < 0){
							unit.setMP(0);
						}
						else {
							unit.setMP(unit.getMP() - tile.getMovementCost());
						}
					}
				}
                else {
                    if (!tile.equals(lastTile) && unit.getMP() >= nextTile(tile).getMovementCost() && !riverPass(unit.getTile(),tile) && nextTile(tile).isPassable()){
                        ((CivilianUnit) unit).move(tile);
                        unit.setMP(unit.getMP() - tile.getMovementCost());
                    }
                }
			}
		}
		return true;
	}
	public Tile nextTile(Tile tile){
		//TODO
		return null;
	}
	public boolean riverPass(Tile firstTile, Tile secondTile){
		for (int i = 0;i<6;i++){
			if (firstTile.getBorder(i).getOtherSide(firstTile).equals(secondTile) && firstTile.getBorder(i).isRIVER()){
				return true;
			}
		}
		return false;
	}
}
