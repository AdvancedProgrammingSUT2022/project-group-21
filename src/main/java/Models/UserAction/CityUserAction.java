package Models.UserAction;

import Contoller.CityActionController;
import Models.Building;
import Models.City;
import Models.Civilization;
import Models.Game;
import Models.Tile.Tile;
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
	public static UserActionQuery buyUnit(String username, int x1, int y1, UnitType unitType){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.unitType=unitType;
		cityUserAction.actionType=CityActionType.BUY_UNIT;
		return createAction(username, cityUserAction);
	}
	public static UserActionQuery buyBuilding(String username, int x1, int y1, Building building){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.building=building;
		cityUserAction.actionType=CityActionType.BUY_BUILDING;
		return createAction(username, cityUserAction);
	}
	public static UserActionQuery buyTile(String username, int x1, int y1, int x2, int y2){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.x2=x2;
		cityUserAction.y2=y2;
		cityUserAction.actionType=CityActionType.BUY_TILE;
		return createAction(username, cityUserAction);
	}
	public static UserActionQuery lockUnlockCitizenToTile(String username, int x1, int y1, int x2, int y2){
		CityUserAction cityUserAction = new CityUserAction();
		cityUserAction.x1=x1;
		cityUserAction.y1=y1;
		cityUserAction.x2=x2;
		cityUserAction.y2=y2;
		cityUserAction.actionType=CityActionType.LOCK_UNLOCK_CITIZEN;
		return createAction(username, cityUserAction);
	}
	
	

	public int x1, y1; // city coordinates
	public int x2, y2; // target coordinates(shoot, lock citizen)
	public CityActionType actionType;
	public Building building;
	public UnitType unitType;

	public enum CityActionType{
		LOCK_UNLOCK_CITIZEN,
		SHOOT,
		UNIT_PRODUCTION,
		BUILDING_PRODUCTION,
		BUY_TILE,
		BUY_BUILDING,
		BUY_UNIT;
	}

	@Override
	public void validateDoAction(Civilization civilization, boolean doAction) throws Exception {
		if (actionType==null)
			throw new Exception("invalid query: actionType is null");

		Game game = Game.getInstance();
		if (!game.checkTileCoordinates(x1, y1))
			throw new Exception("city coordinates out of bounds");
		Tile tile1 = game.getTile(x1, y1);
		City city = tile1.getCityOnTile();
		if (city==null)
			throw new Exception("there is no city on this tile");
		if (city.getOwner()!=civilization)
			throw new Exception("this city is not yours");
		
		if (unitType == null && (actionType == CityActionType.UNIT_PRODUCTION || actionType == CityActionType.BUY_UNIT))
			throw new Exception("unitType is null");
		if (building == null && (actionType == CityActionType.BUILDING_PRODUCTION || actionType == CityActionType.BUY_BUILDING))
			throw new Exception("building is null");

		switch (actionType) {
			case LOCK_UNLOCK_CITIZEN:
				CityActionController.getInstance().lockUnlockCitizenToTile(city, x2, y2, doAction);
				break;
			case SHOOT:
				CityActionController.getInstance().shootTile(city, x2, y2, doAction);
				break;
			case UNIT_PRODUCTION:
				CityActionController.getInstance().produceUnit(city, unitType, doAction);
				break;
			case BUILDING_PRODUCTION:
				CityActionController.getInstance().produceBuilding(city, building, doAction);
				break;
			case BUY_TILE:
				CityActionController.getInstance().buyTile(city, x2, y2, doAction);
				break;
			case BUY_BUILDING:
				CityActionController.getInstance().buyBuilding(city, building, doAction);
				break;
			case BUY_UNIT:
				CityActionController.getInstance().buyUnit(city, unitType, doAction);
				break;
			default:
				break;
		}
		

		
	}


}
