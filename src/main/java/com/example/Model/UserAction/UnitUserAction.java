package com.example.Model.UserAction;

import com.example.Contoller.NonCombatUnitActionController;
import com.example.Contoller.UnitActionController;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.tile.Improvement;
import com.example.Model.tile.Tile;
import com.example.Model.unit.CivilianUnit;
import com.example.Model.unit.MilitaryUnit;
import com.example.Model.unit.Unit;
import com.example.Model.unit.WorkerProject.WorkerProjectType;
import com.google.gson.annotations.Expose;

public class UnitUserAction extends UserAction{
	private static UserActionQuery createAction(String username, UnitUserAction unitUserAction){
		return new UserActionQuery(username, null, null, null, unitUserAction);
	}
	public static UserActionQuery workerAction(
			String username, int x1, int y1, WorkerProjectType projectType, Improvement improvement){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=false;
		unitUserAction.actionType=UnitActionType.WORKER_ACTION;
		unitUserAction.workerProjectType=projectType;
		unitUserAction.improvement=improvement;
		return createAction(username, unitUserAction);
	}
	public static UserActionQuery foundCity(String username, int x1, int y1){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=false;
		unitUserAction.actionType=UnitActionType.FOUND_CITY;
		return createAction(username, unitUserAction);
	}
	public static UserActionQuery move(String username, int x1, int y1, int x2, int y2, boolean isMilitary){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.x2=x2;
		unitUserAction.y2=y2;
		unitUserAction.isMilitary=isMilitary;
		unitUserAction.actionType=UnitActionType.MOVE;
		return createAction(username, unitUserAction);
	}
	public static UserActionQuery sleepWake(String username, int x1, int y1, boolean isMilitary){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=isMilitary;
		unitUserAction.actionType=UnitActionType.SLEEP_WAKE;
		return createAction(username, unitUserAction);
	}
	public static UserActionQuery delete(String username, int x1, int y1, boolean isMilitary){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=isMilitary;
		unitUserAction.actionType=UnitActionType.DELETE;
		return createAction(username, unitUserAction);
	}
	public static UserActionQuery rangeAttack(String username, int x1, int y1, int x2, int y2){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.x2=x2;
		unitUserAction.y2=y2;
		unitUserAction.isMilitary=true;
		unitUserAction.actionType=UnitActionType.RANGE_ATTACK;
		return createAction(username, unitUserAction);
	}
	// pre-attack, allert, fortify, pillage
	public static UserActionQuery singleTileMilitary(String username, int x1, int y1, UnitActionType actionType){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=true;
		unitUserAction.actionType=actionType;
		return createAction(username, unitUserAction);
	}



	@Expose public int x1, y1; // unit coordinates
	@Expose public int x2, y2; // destination coordinates(move, attack)
	@Expose public boolean isMilitary; // combat or non-combat unit 
	@Expose public UnitActionType actionType;
	
	// only for worker:
	@Expose public WorkerProjectType workerProjectType;
	@Expose public Improvement improvement;


	@Override
	public void validateDoAction(Civilization civilization, boolean doAction) throws Exception {
		if (actionType==null)
			throw new Exception("invalid query: actionType is null");

		Game game = Game.getInstance();
		if (!game.checkTileCoordinates(x1, y1))
			throw new Exception("unit coordinates out of bounds");
		Tile tile1 = game.getTile(x1, y1);
		Unit unit;
		MilitaryUnit militaryUnit=null;
		CivilianUnit civilianUnit=null;
		if (isMilitary){
			militaryUnit=tile1.getMilitaryUnit();
			unit=militaryUnit;
		}
		else{
			civilianUnit=tile1.getCivilianUnit();
			unit=civilianUnit;
		}
		if (unit==null) 
			throw new Exception("there is no unit");
		if (!unit.unitType.isActionAppliable(actionType))
			throw new Exception("this action is not defined for this unit");
		if (unit.getOwner()!=civilization)
			throw new Exception("this unit does not belong to you!");
		
		switch (actionType){
			case DELETE:
				UnitActionController.getInstance().delete(unit, doAction);
				break;
			case MOVE:
				UnitActionController.getInstance().move(unit, x2, y2, doAction);
				break;
			case MELEE_ATTACK:
				UnitActionController.getInstance().meleeAttack(militaryUnit, x2, y2, doAction);
				break;
			case RANGE_ATTACK:
				UnitActionController.getInstance().rangeAttack(militaryUnit, x2, y2, doAction);
				break;
			case PRE_ATTACK_SETUP:
				UnitActionController.getInstance().siegePreAttack(militaryUnit, doAction);
				break;
			case SLEEP_WAKE:
				UnitActionController.getInstance().sleepWake(unit, doAction);
				break;
			case ALERT:
				UnitActionController.getInstance().alert(militaryUnit, doAction);
				break;
			case FORTIFY:
				UnitActionController.getInstance().fortify(militaryUnit, doAction);
				break;
			case PILLAGE:
				UnitActionController.getInstance().pillage(militaryUnit, doAction);
				break;
			case FOUND_CITY:
				NonCombatUnitActionController.getInstance().foundCity(unit, doAction);
				break;
			case WORKER_ACTION:
				if (workerProjectType == null)
					throw new Exception("invalid query: workerProjectType is null");
				switch (workerProjectType) {
					case BUILD_IMPROVEMENT:
						if (improvement == null)
							throw new Exception("invalid query: improvement is null");
						NonCombatUnitActionController.getInstance().buildImprovement(unit, improvement, doAction);
						break;
					case REMOVE_JUNGLE:
						NonCombatUnitActionController.getInstance().removeJungle(unit, doAction);
						break;
					case REPAIR_IMPROVEMENT:
						NonCombatUnitActionController.getInstance().repairImprovement(unit, doAction);
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
	}
}
