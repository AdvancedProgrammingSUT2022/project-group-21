package Models.UserAction;

import Models.WorkerProject.WorkerProjectType;
import Models.Tile.Improvement;

public class UnitUserAction {
	private static UserAction createAction(String username, UnitUserAction unitUserAction){
		return new UserAction(username, null, null, null, unitUserAction);
	}
	public static UserAction workerAction(String username, int x1, int y1, WorkerProjectType projectType, Improvement improvement){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=false;
		unitUserAction.actionType=UnitActionType.WORKER_ACTION;
		unitUserAction.workerProjectType=projectType;
		unitUserAction.improvement=improvement;
		return createAction(username, unitUserAction);
	}
	public static UserAction foundCity(String username, int x1, int y1){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=false;
		unitUserAction.actionType=UnitActionType.FOUND_CITY;
		return createAction(username, unitUserAction);
	}
	public static UserAction move(String username, int x1, int y1, int x2, int y2, boolean isMilitary){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.x2=x2;
		unitUserAction.y2=y2;
		unitUserAction.isMilitary=isMilitary;
		unitUserAction.actionType=UnitActionType.MOVE;
		return createAction(username, unitUserAction);
	}
	public static UserAction sleepWake(String username, int x1, int y1, int x2, int y2, boolean isMilitary){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=isMilitary;
		unitUserAction.actionType=UnitActionType.SLEEP_WAKE;
		return createAction(username, unitUserAction);
	}
	public static UserAction rangeAttack(String username, int x1, int y1, int x2, int y2){
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
	public static UserAction singleTileMilitary(String username, int x1, int y1, UnitActionType actionType){
		UnitUserAction unitUserAction = new UnitUserAction();
		unitUserAction.x1=x1;
		unitUserAction.y1=y1;
		unitUserAction.isMilitary=true;
		unitUserAction.actionType=actionType;
		return createAction(username, unitUserAction);
	}

	public int x1, y1; // unit coordinates
	public int x2, y2; // destination coordinates(move, attack)
	public boolean isMilitary; // combat or non-combat unit 
	public UnitActionType actionType;
	
	// only for worker:
	public WorkerProjectType workerProjectType;
	public Improvement improvement;


	public enum UnitActionType{
		// melee-attack is same as move
		MOVE,
		RANGE_ATTACK,
		PRE_ATTACK_SETUP,
		SLEEP_WAKE,
		ALERT,
		FORTIFY,
		PILLAGE,
		
		FOUND_CITY,		
		WORKER_ACTION;
	}

	
}
