package com.example.Model.unit;

import com.example.Model.Civilization;
import com.example.Model.WorkerProject;
import com.example.Model.tile.Tile;

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
