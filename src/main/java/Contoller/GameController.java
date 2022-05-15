package Contoller;

import java.util.ArrayList;

import Enums.Message;
import Models.*;
import Models.Tile.Tile;

public class GameController {
	private static GameController instance;
	private static void setInstance(GameController instance) {
		GameController.instance = instance;
	}
	public static GameController getInstance() {
		if (instance == null) GameController.setInstance(new GameController());
		return instance;
	}

	private Game game;

	public Message startNewGame(ArrayList<User> players){
		game=new Game(50, 50, players);
		// TODO: create map, set up game, ...
		return null;
	}

	public Game getGame(){ return game; }

	public void changeTileOwner(Tile tile, Civilization civilization){
		if (tile.getOwner()!=null) tile.getOwner().removeTile(tile);
		tile.setOwner(civilization);
		civilization.addTile(tile);
	}



}
