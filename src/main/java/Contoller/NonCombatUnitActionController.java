package Contoller;

import Models.WorkerProject;
import Models.Tile.Improvement;
import Models.Tile.Terrain;
import Models.Tile.TerrainFeature;
import Models.Tile.Tile;
import Models.Unit.Settler;
import Models.Unit.Unit;
import Models.Unit.Worker;

public class NonCombatUnitActionController {
	private static NonCombatUnitActionController instance;
	public static NonCombatUnitActionController getInstance(){
		if (instance==null) instance = new NonCombatUnitActionController();
		return instance;
	}
	
	public void foundCity(Unit unit, boolean doAction) throws Exception{
		if (!(unit instanceof Settler)) throw new Exception("unit is not a settler");
		Tile tile = unit.getTile();
		if (tile.getOwner()!=null) throw new Exception("city tile should not have owner");
		if (tile.getTerrain()==Terrain.MOUNTAIN || tile.getTerrain()==Terrain.OCEAN)
			throw new Exception("cant build city on mountain and ocean");
		// TODO: check distance of 4 from other cities
		if (doAction) unit.getOwner().getLogicController().foundCity((Settler) unit);
	}

	public void buildImprovement(Unit unit, Improvement improvement, boolean doAction) throws Exception{
		if (!(unit instanceof Worker)) throw new Exception("unit is not a worker");
		Worker worker = (Worker) unit;
		Tile tile = worker.getTile();
		if (tile.getOwner()!=unit.getOwner()) throw new Exception("you can build improvements only on your tiles");
		if (!improvement.canBeBuiltOn(tile)) throw new Exception("cant build this improvement on this tile");
		if (improvement.prequisiteTech!=null && !unit.getOwner().hasTechnology(improvement.prequisiteTech))
			throw new Exception("you need technology of " + improvement.prequisiteTech + "to build this improvement");
		if (doAction) WorkerProject.buildImprovement(tile, improvement);
	}

	public void removeJungle(Unit unit, boolean doAction) throws Exception{
		if (!(unit instanceof Worker)) throw new Exception("unit is not a worker");
		Worker worker = (Worker) unit;
		Tile tile = worker.getTile();
		TerrainFeature feature = tile.getTerrainFeature();
		if (feature!=TerrainFeature.JUNGLE && feature!=TerrainFeature.FOREST)
			throw new Exception("there is no forest/jungle to remove!");
		if (doAction) WorkerProject.removeJungle(tile);
	}
	
	public void repairImprovement(Unit unit, boolean doAction) throws Exception{
		if (!(unit instanceof Worker)) throw new Exception("unit is not a worker");
		Worker worker = (Worker) unit;
		Tile tile = worker.getTile();
		if (tile.getImprovement()==null || !tile.isPillaged())
			throw new Exception("there is pillaged improvement to repair");
		if (doAction) WorkerProject.repairImprovement(tile);
	}
	
}
