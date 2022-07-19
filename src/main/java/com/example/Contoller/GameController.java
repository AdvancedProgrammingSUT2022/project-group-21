package com.example.Contoller;

import java.util.ArrayList;

import com.example.Model.Game;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;

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

	public void startNewGame(ArrayList<String> usernames) throws Exception{
		ArrayList<User> players = new ArrayList<>();
		for (String username : usernames) {
			User user = UserDatabase.getInstance().getUserByUsername(username);
			if (user == null) throw new Exception("no user with this username found: " + username);
			players.add(user);
		}
		game=new Game(50, 50, players);
		// TODO: create map, set up game, ...
	}

	public Game getGame(){ return game; }

}
