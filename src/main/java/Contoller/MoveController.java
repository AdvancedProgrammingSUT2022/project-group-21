package Contoller;

import Models.Tile;
import Models.Unit.CivilianUnit;
import Models.Unit.MilitaryUnit;
import Models.Unit.Unit;

import java.util.ArrayList;

public class MoveController {
	private static MoveController instance;
	private static void setInstance(MoveController instance) {
		MoveController.instance = instance;
	}
	public static MoveController getInstance() {
		if (instance == null) MoveController.setInstance(new MoveController());
		return instance;
	}
	public boolean move(Unit unit,Tile firstTile,Tile lastTile){
		ArrayList<Tile> path = getPath(firstTile,lastTile);
		int i;
		int j;
		int MP = unit.getMP();
		if (path == null){
			return true;
		}
		if (unit instanceof MilitaryUnit){
			while (unit.getMP() != 0 && !path.get(0).equals(lastTile)){
				for (i = 1;i<path.size();i++) {
					if (path.get(i).getMilitaryUnit() == null) {
						break;
						//We have no path to go
					}
				}
				for (j = 0;j<=i-1;j++){
					if (MP == 0){
						break;
					}
					if (riverPass(path.get(j),path.get(j+1))){
						MP = 0;
					}
					else {
						MP = MP - path.get(j+1).getMovementCost();
						if (MP < 0){
							MP = 0;
						}
					}
				}
				if (j == i){
					((MilitaryUnit) unit).move(path.get(i));
					unit.setMP(MP);
					for (int k = 0;k<i;k++){
						path.remove(0);
					}
				}
				else {
					return true;
					//Aborting
				}
			}

		}
		if (unit instanceof CivilianUnit){
			while (unit.getMP() != 0 && !path.get(0).equals(lastTile)){
				for (i = 1;i<path.size();i++) {
					if (path.get(i).getCivilianUnit() == null) {
						break;
					}
				}
				for (j = 0;j<=i-1;j++){
					if (MP == 0){
						break;
					}
					if (riverPass(path.get(j),path.get(j+1))){
						MP = 0;
					}
					else {
						MP = MP - path.get(j+1).getMovementCost();
						if (MP < 0){
							MP = 0;
						}
					}
				}
				if (j == i){
					((CivilianUnit) unit).move(path.get(i));
					unit.setMP(MP);
					for (int k = 0;k<i;k++){
						path.remove(0);
					}
				}
				else {
					return true;
					//Aborting
				}
			}
		}
		return path.get(0).equals(lastTile);
		//We return true if we end the path or cant go to tile in the path
		//returning false for multi-turn movement that needs TODO...
	}
	public ArrayList<Tile> getPath(Tile firstTile, Tile lastTile){
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
