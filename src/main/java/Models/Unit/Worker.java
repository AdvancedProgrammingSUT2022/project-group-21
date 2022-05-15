package Models.Unit;

import Models.Civilization;
import Models.Tile.Improvement;
import Models.Tile.Tile;

public class Worker extends CivilianUnit{
	
	private ProjectType projectType;
	private int projectTurnsLeft;
	private Improvement projectImprovement; // if profectType==BUILD_IMPROVEMENT

	public Worker(Civilization owner, Tile tile){
		super(UnitType.WORKER, owner, tile);
	}

	public int getProjectTurnsLeft(){ return projectTurnsLeft;}
	public void decreaseProjectTurnsLeft(){
		projectTurnsLeft--;
		if (projectTurnsLeft>0) return ;
		// TODO
	}


	public void buildRoad(){
		projectType=ProjectType.BUILD_ROAD;
		projectTurnsLeft=3;
		// TODO
	}
	public void buildImprovement(Improvement improvement){
		// TODO
	}
	public void removeJungle(){
		// TODO
	}
	public void cancelProject(){
		// TODO
	}
	
	
	
}

enum ProjectType{
	BUILD_ROAD,
	BUILD_IMPROVEMENT,
	REMOVE_JUNGLE;
}
