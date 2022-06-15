package Models.UserAction;

import Models.Building;
import Models.Civilization;
import Models.Unit.UnitType;

public class CityUserAction extends UserAction{
	private static UserActionQuery createAction(String username, CityUserAction cityUserAction){
		return new UserActionQuery(username, cityUserAction, null, null, null);
	}
	public static UserActionQuery shootTile(String username, int x1, int y1, int x2, int y2){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.x2=x2;
		cityUserAction.y2=y2;
		cityUserAction.actionType=CityActionType.SHOOT;
		return createAction(username, cityUserAction);
	}
	public static UserActionQuery produceBuilding(String username, int x1, int y1, Building building){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.building=building;
		cityUserAction.actionType=CityActionType.BUILDING_PRODUCTION;
		return createAction(username, cityUserAction);
	}
	public static UserActionQuery produceUnit(String username, int x1, int y1, UnitType unitType){
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
