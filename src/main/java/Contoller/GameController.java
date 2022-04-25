package Contoller;

import java.util.ArrayList;

import Models.Civilization;
import Models.Game;
import Models.Tile;
import Models.User;

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

	public void startNewGame(ArrayList<User> players){
		game=new Game(20, 20, players);
		// TODO: create map, set up game, ...
	}

	public Game getGame(){ return game; }

	public void changeTileOwner(Tile tile, Civilization civilization){
		if (tile.getOwner()!=null) tile.getOwner().removeTile(tile);
		tile.setOwner(civilization);
		civilization.addTile(tile);
	}


}
