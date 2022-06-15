package Models;

import Models.Tile.Improvement;
import Models.Tile.TerrainFeature;
import Models.Tile.Tile;

public class WorkerProject {
	private Tile tile;
	private WorkerProjectType projectType;
	private Improvement improvement; // (projectType == BUILD_IMPROVEMENT)
	private int turnsLeft;
	
	private WorkerProject(Tile tile, WorkerProjectType projectType, Improvement improvement, int countTurns){
		this.tile=tile;
		this.projectType=projectType;
		this.improvement=improvement;
		this.turnsLeft=countTurns;
		tile.setWorkerProject(this);
	}


	public static boolean canRemoveJungle(Tile tile){
		TerrainFeature feature = tile.getTerrainFeature();
		return feature==TerrainFeature.FOREST || feature==TerrainFeature.JUNGLE;
	}
	public static boolean canRepairImprovement(Tile tile){
		return tile.isPillaged()==true;
	}
	public static boolean canBuildImprovement(Tile tile, Improvement improvement){
		return improvement.canBeBuiltOn(tile);
	}
	

	public static void removeJungle(Tile tile){
		new WorkerProject(tile, WorkerProjectType.REMOVE_JUNGLE, null, 2);
	}
	public static void repairImprovement(Tile tile){
		new WorkerProject(tile, WorkerProjectType.REPAIR_IMPROVEMENT, null, 3);
	}
	public static void buildImprovement(Tile tile, Improvement improvement){
		new WorkerProject(tile, WorkerProjectType.BUILD_IMPROVEMENT, improvement, improvement.countNeededTurns(tile));
	}
	

	public boolean isFinnished(){ return turnsLeft<=0;} // why not *==0 ?!
	
	public void work(){ turnsLeft--;}

	public void doAction(){
		tile.setWorkerProject(null);
		if (projectType==WorkerProjectType.REMOVE_JUNGLE){
			tile.setTerrainFeature(null);
		}
		if (projectType==WorkerProjectType.REPAIR_IMPROVEMENT){
			tile.setPillaged(false);
		}
		if (projectType==WorkerProjectType.BUILD_IMPROVEMENT){
			tile.setImprovement(improvement);
		}
	}

	public enum WorkerProjectType{
		REMOVE_JUNGLE,
		REPAIR_IMPROVEMENT,
		BUILD_IMPROVEMENT,
	}
}
