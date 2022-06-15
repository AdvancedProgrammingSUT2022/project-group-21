package Contoller;

import java.util.ArrayList;

import Models.Civilization;
import Models.Game;
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

	public String startNewGame(ArrayList<String> usernames){
		ArrayList<User> players = new ArrayList<>();
		for (String username : usernames) {
			User user = User.getUserByUsername(username);
			if (user == null) return "user does not exist";
			players.add(user);
		}
		game=new Game(50, 50, players);
		// TODO: create map, set up game, ...
		return null;
	}

	public Game getGame(){ return game; }


	public void beginTurn(Civilization civilization){
		// call this when a players turn starts
		// TODO
	}
	public void endTurn(Civilization civilization){
		// call this when a players turn ends
		// TODO
	}


}
