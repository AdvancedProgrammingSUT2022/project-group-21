package Models.Unit;

import Contoller.CityController;
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

	public boolean isWorking(){ return projectType!=null;}

	public int getProjectTurnsLeft(){ return projectTurnsLeft;}
	public void decreaseProjectTurnsLeft(){
		projectTurnsLeft--;
		if (projectTurnsLeft>0) return ;
		CityController.getInstance().removeImprovementProject(this);
		if (projectType==ProjectType.BUILD_IMPROVEMENT){
			tile.setImprovement(projectImprovement);
		}
		else if (projectType==ProjectType.BUILD_ROAD){
			tile.setRoad(true);
		}
		else if (projectType==ProjectType.REMOVE_JUNGLE){
			tile.setTerrainFeature(null);
		}
	}


	public void buildRoad(){
		projectType=ProjectType.BUILD_ROAD;
		projectTurnsLeft=3;
		CityController.getInstance().addImprovementProject(this);
	}
	public void buildImprovement(Improvement improvement){
		projectType=ProjectType.BUILD_IMPROVEMENT;
		projectTurnsLeft=5;
		projectImprovement=improvement;
		CityController.getInstance().addImprovementProject(this);
	}
	public void removeJungle(){
		projectType=ProjectType.REMOVE_JUNGLE;
		projectTurnsLeft=2;
		CityController.getInstance().addImprovementProject(this);
	}
	public void removeRoad(){

	}
	public void cancelProject(){
		projectType=null;
		projectTurnsLeft=-1;
		CityController.getInstance().removeImprovementProject(this);
	}
}

enum ProjectType{
	BUILD_ROAD,
	BUILD_IMPROVEMENT,
	REMOVE_JUNGLE;
}
