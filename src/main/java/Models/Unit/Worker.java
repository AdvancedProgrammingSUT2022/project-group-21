package Models.Unit;

import Models.Civilization;
import Models.WorkerProject;
import Models.Tile.Tile;

public class Worker extends CivilianUnit{
	
	public Worker(Civilization owner, Tile tile){
		super(UnitType.WORKER, owner, tile);
	}

	public boolean isWorking(){ return getTile().getWorkerProject()!=null;}

	public void work(){
		super.endTurn();
		if (getMP()==0) return ;
		WorkerProject project = getTile().getWorkerProject();
		if (project==null) return ;
		project.work();
		if (project.isFinnished()) project.doAction();
	}
	
}
