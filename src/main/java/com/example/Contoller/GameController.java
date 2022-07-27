package com.example.Contoller;

import java.util.ArrayList;

import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.Technology;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;
import com.example.View.hexagons.MapPaneMaker;
import com.example.ViewController.Dialog;
import com.example.ViewController.GamePageViewController;

public class GameController {
	private static GameController instance = new GameController();
	public static GameController getInstance(){ return instance;}

	public void startNewGame(ArrayList<String> usernames, long seed) throws Exception{
		for (String username : usernames) {
			User user = UserDatabase.getInstance().getUserByUsername(username);
			if (user == null) throw new Exception("no user with this username found: " + username);
		}
		new Game(20, 25, usernames, seed);
	}

	public boolean handleQueryFromView(UserActionQuery query){
		System.out.println(query.toJson());
		System.out.println("current user: " + Game.getInstance().getCurrentPlayer().getUsername());
		for (User user : Game.getInstance().getPlayers()) {
			System.out.println(user.getUsername());
		}
		System.out.println();


		try {
			query.validate();
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			Dialog.error_message("Error", e.getMessage());
			// TODO: show a graphic notification instead :::: Do it :D
			return false;
		}
		Game.getInstance().gameHistory.addAction(query);
		Game.getInstance().gameHistory.saveOnFile();
		
		query.doAction();
		Game.getInstance().getCurrentPlayer().getCivilization().calculateVisibleTiles();
		checkWinLoseConditions();
		if (Game.getInstance()!=null){
			// TODO: must update graphics
		}
		updateGraphic();
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
		Dialog.information_message("",
				"Congratulation to " + user.getUsername() + "You are Win the Game!");

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

	public void updateGraphic(){
		MapPaneMaker.updateButtons(Game.getInstance().getCurrentPlayer().getCivilization());
		GamePageViewController.showInfo();
		GamePageViewController.recenterMap(Game.getInstance(), Game.getInstance().getCurrentPlayer().getCivilization().getCapitalCity().getCenter());
	}
}
