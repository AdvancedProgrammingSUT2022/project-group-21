package com.example.Contoller;

import java.util.ArrayList;

import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.Technology;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;

public class GameController {
	private static GameController instance = new GameController();
	public static GameController getInstance(){ return instance;}

	public void startNewGame(ArrayList<String> usernames, long seed) throws Exception{
		ArrayList<User> players = new ArrayList<>();
		for (String username : usernames) {
			User user = UserDatabase.getInstance().getUserByUsername(username);
			if (user == null) throw new Exception("no user with this username found: " + username);
			players.add(user);
		}
		new Game(25, 20, players, seed);
	}

	public boolean handleQueryFromView(UserActionQuery query){
		try {
			query.validate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: show a graphic notification instead
			return false;
		}
		Game.getInstance().gameHistory.addAction(query);
		query.doAction();
		checkWinLoseConditions();
		if (Game.getInstance()!=null){
			// TODO: must update graphics
		}
		return true;
	}

	private void checkWinLoseConditions(){
		ArrayList<User> players = Game.getInstance().getPlayers();
		if (Game.getInstance().getYear()>=2050){
			int maxScore = 0;
			User winner = null;
			for (User user : players) {
				Civilization civilization = user.getCivilization();
				int score = civilization.getScore();
				if (score>maxScore){
					maxScore = score;
					winner = user;
				}
			}
			setWinner(winner);
			return ;
		}
		for (User user : players) {
			Civilization civilization = user.getCivilization();
			if (civilization.getCapitalCity().getOwner()!=civilization){
				setLoser(user);
			}
			if (civilization.countResearchedTechnologies()==Technology.values().length){
				setWinner(user);
			}
		}
		if (players.size()==1) setWinner(players.get(0));
	}

	private void setWinner(User user){
		// TODO: show notification
		System.out.println("Winner is " + user.getUsername());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		Game.getInstance().endGame();
		// TODO: graphics
		// TODO: must update graphics
	}

	private void setLoser(User user){
		Game.getInstance().removePlayer(user);
		// TODO: on phase3, show notification
	}

}
