package Contoller;

import Enums.Message;
import Models.City;
import Models.Civilization;
import Models.Game;
import Models.Tile.Tile;
import Models.Unit.Unit;

public class SelectController {
	private static SelectController instance;
	public static SelectController getInstance(){
		if (instance == null) instance = new SelectController();
		return instance;
	}

	private Game game;
	private Unit selectedUnit;
	private City selectedCity;

	public Unit getSelectedUnit(){ return selectedUnit;}
	public City getSelectedCity(){ return selectedCity;}

	public void reset(){
		game=GameController.getInstance().getGame();
		selectedUnit=null;
		selectedCity=null;
	}

	private boolean validateCordinates(int x, int y){
		return 0<=x && x<game.WIDTH && 0<=y && y<game.HEIGHT;
	}

	// TODO: check if selected unit/city belong to civilization


	public Message selectUnit(Civilization civilization, int x, int y, boolean isCombatUnit){
		if (!validateCordinates(x, y)) return Message.OUT_OF_BOUND;
		Tile tile=game.getTile(x, y);
		Unit unit=(isCombatUnit?tile.getMilitaryUnit():tile.getCivilianUnit());
		if (unit==null) return Message.FAIL;
		selectedUnit=unit;
		return Message.SUCCESS;
	}

	public Message selectCityByCoordination(Civilization civilization, int x, int y) {
		if (!validateCordinates(x, y)) return Message.OUT_OF_BOUND;
		Tile tile=game.getTile(x, y);
		City city=City.getCityOnTile(tile);
		if (city==null) return Message.FAIL;
		selectedCity=city;
		return null;
	}
}
