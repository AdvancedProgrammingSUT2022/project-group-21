package Contoller;

import java.util.ArrayList;

import Models.*;

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
	private Civilization currentTurnCivilization;

	public void startNewGame(ArrayList<User> players){
		game=new Game(20, 20, players);
		// TODO: create map, set up game, ...
	}

	public Game getGame(){ return game; }

	public ArrayList<Technology> getAvailableTechnologies() {
		ArrayList<Technology> availableTechnologies = new ArrayList<Technology>();
		Technology[] allTechnologies = Technology.values();
		outerloop:
		for (Technology technology : allTechnologies) {
			if (currentTurnCivilization.hasTechnology(technology))
				continue;
			for (Technology prerequisiteTech : technology.getPrequisiteTechs())
				if (!currentTurnCivilization.hasTechnology(prerequisiteTech)) 	continue outerloop;
			availableTechnologies.add(technology);
		}
		return availableTechnologies;
	}

	public void changeTileOwner(Tile tile, Civilization civilization){
		if (tile.getOwner()!=null) tile.getOwner().removeTile(tile);
		tile.setOwner(civilization);
		civilization.addTile(tile);
	}


}
