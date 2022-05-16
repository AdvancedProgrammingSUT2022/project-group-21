package Contoller;

import Models.Game;
import Models.Tile.Tile;
import Models.Unit.CivilianUnit;
import Models.Unit.MilitaryUnit;
import Models.Unit.Unit;

import java.util.ArrayList;

import Enums.Message;

public class MoveController {
	private static MoveController instance;
	private static void setInstance(MoveController instance) {
		MoveController.instance = instance;
	}
	public static MoveController getInstance() {
		if (instance == null) MoveController.setInstance(new MoveController());
		return instance;
	}

	public Message moveUnit(int x, int y){
		Unit selectedUnit = SelectController.getInstance().getSelectedUnit();
		Tile selectedTile = GameController.getInstance().getGame().getTile(x,y);
		if (selectedUnit == null)
			return Message.FAIL;
		if (selectedUnit.owner != GameController.getInstance().getGame().getCurrentPlayer().getCivilization())
			return Message.FAIL;
		if (!GameController.getInstance().getGame().getCurrentPlayer().getCivilization().isTileVisible(x,y)){
			return Message.FAIL;
		}
		ArrayList<Tile> path = getPath(selectedUnit.getTile(),selectedTile);
		int i;
		int j;
		int MP = selectedUnit.getMP();
		if (path == null) return Message.FAIL;
		if (selectedUnit instanceof MilitaryUnit){
			while (selectedUnit.getMP() != 0 && !path.get(0).equals(selectedTile)){
				for (i = 1;i<path.size();i++) {
					if (path.get(i).getMilitaryUnit() == null) {
						break;
						//We have no path to go
					}
				}
				if (i == path.size()){
					return Message.FAIL;
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
					selectedUnit.setTile(path.get(i));
					selectedUnit.setMP(MP);
					for (int k = 0;k<i;k++){
						path.remove(0);
					}
				}
				else {
					return Message.FAIL;
					//Aborting
				}
			}

		}
		if (selectedUnit instanceof CivilianUnit){
			while (selectedUnit.getMP() != 0 && !path.get(0).equals(selectedTile)){
				for (i = 1;i<path.size();i++) {
					if (path.get(i).getCivilianUnit() == null) {
						break;
					}
				}
				if (i == path.size()){
					return Message.FAIL;
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
					selectedUnit.setTile(path.get(i));
					selectedUnit.setMP(MP);
					for (int k = 0;k<i;k++){
						path.remove(0);
					}
				}
				else {
					return Message.FAIL;
					//Aborting
				}
			}
		}
		// TODO
		// move selected unit to tile (x, y)
		// if tile was not visible: FAIL
		// if move was possible in one turn: move
		// else go as far as possible
		return null;
	}



	


	public ArrayList<Tile> getPath(Tile firstTile, Tile lastTile){
		// run spfa for shortest path
		Game game=GameController.getInstance().getGame();
		int W=game.WIDTH, H=game.HEIGHT;
		int dist[][] = new int[W][H];
		boolean inq[][] = new boolean[W][H];
		int maxQ=W*H, head=0, tail=0;
		// NOTE: maybe allocate stupid memory
		Tile[] Q = new Tile[maxQ];
		Tile[][] par = new Tile[W][H];
		Q[tail++]=firstTile;
		while (head!=tail){
			Tile v=Q[head++];
			if (head==maxQ) head=0;
			inq[v.X][v.Y]=false;
			for (int i=0; i<6; i++){
				boolean river=v.isRiver(i);
				Tile u=v.getAdjTile(i);
				if (u==null) continue ;
				int w=u.getMovementCost()+(river?1:0); // NOTE: maybe change cost function
				if (dist[u.X][u.Y]>dist[v.X][v.Y]+w){
					dist[u.X][u.Y]=dist[v.X][v.Y]+w;
					par[u.X][u.Y]=v;
					if (!inq[u.X][u.Y]){
						inq[u.X][u.Y]=true;
						Q[tail++]=u;
					}
				}
			}
		}
		if (par[lastTile.X][lastTile.Y]==null) return null;
		ArrayList<Tile> tmp = new ArrayList<>();
		while (lastTile!=firstTile){
			tmp.add(lastTile);
			lastTile=par[lastTile.X][lastTile.Y];
		}
		int len=tmp.size();
		ArrayList<Tile> path = new ArrayList<>(len);
		for (int i=0; i<len; i++) path.set(i, tmp.get(len-i-1));
		return path;
		// TODO: check usage of this function
	}

	public boolean riverPass(Tile firstTile, Tile secondTile){
		for (int i = 0;i<6;i++){
			if (firstTile.getAdjTile(i).equals(secondTile) && firstTile.isRiver(i)){
				return true;
			}
		}
		return false;
	}
}
