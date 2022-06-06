package Models.UserAction;

import Models.Building;
import Models.Unit.UnitType;

public class CityUserAction {
	private static UserAction createAction(String username, CityUserAction cityUserAction){
		return new UserAction(username, cityUserAction, null, null, null);
	}
	public static UserAction shootTile(String username, int x1, int y1, int x2, int y2){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.x2=x2;
		cityUserAction.y2=y2;
		cityUserAction.actionType=CityActionType.SHOOT;
		return createAction(username, cityUserAction);
	}
	public static UserAction produceBuilding(String username, int x1, int y1, Building building){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.building=building;
		cityUserAction.actionType=CityActionType.BUILDING_PRODUCTION;
		return createAction(username, cityUserAction);
	}
	public static UserAction produceUnit(String username, int x1, int y1, UnitType unitType){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.unitType=unitType;
		cityUserAction.actionType=CityActionType.UNIT_PRODUCTION;
		return createAction(username, cityUserAction);
	}
	

	public int x1, y1; // city coordinates
	public int x2, y2; // target coordinates(shoot, lock citizen)
	public CityActionType actionType;
	public Building building;
	public UnitType unitType;

	public enum CityActionType{
		LOCK_CITIZEN,
		SHOOT,
		UNIT_PRODUCTION,
		BUILDING_PRODUCTION;
	}
}
