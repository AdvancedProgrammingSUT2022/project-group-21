package com.example.Contoller;

import java.util.ArrayList;

import com.example.Model.Game;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;

public class GameController {
	private static GameController instance = new GameController();
	public static GameController getInstance(){ return instance;}

	public void startNewGame(ArrayList<String> usernames) throws Exception{
		ArrayList<User> players = new ArrayList<>();
		for (String username : usernames) {
			User user = UserDatabase.getInstance().getUserByUsername(username);
			if (user == null) throw new Exception("no user with this username found: " + username);
			players.add(user);
		}
		new Game(20, 20, players, 123456789);
		// TODO: create map, set up game, ...
	}

	public void handleQueryFromView(UserActionQuery query){
		try {
			query.validate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: show a graphic notification instead
		}
		Game.getInstance().gameHistory.addAction(query);
		query.doAction();
	}

}
