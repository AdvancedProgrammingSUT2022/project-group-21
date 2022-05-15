package Models.Unit;

import Models.Civilization;
import Models.Tile.Tile;

public class Worker extends CivilianUnit{
	
	private int projectTurnsLeft;
	

	public Worker(Civilization owner, Tile tile){
		super(UnitType.WORKER, owner, tile);
	}

	public int getProjectTurnsLeft(){ return projectTurnsLeft;}



	public void creatRoad() {}
	public void creatFarm() {}
	public void leaveOlderAndCreatNewer() {}
	public void repairRoad() {}
	public void createMine() {}
	public void createTradingPost() {}
	public void createWoodFactory() {}
	public void createCamp() {}
	public void createPasture() {}
	public void removeRoad() {}
	public void removeForest() {}
	public void removeJungle() {}
	public void removeMarsh() {}
	public void createQuarry() {}
}
