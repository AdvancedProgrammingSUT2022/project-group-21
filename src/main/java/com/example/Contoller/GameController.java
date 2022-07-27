package com.example.Contoller;

import java.util.ArrayList;

import com.example.App;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.GameHistory;
import com.example.Model.Technology;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;
import com.example.Network.NetworkController;
import com.example.Network.Request;
import com.example.Network.Response;
import com.example.View.hexagons.MapPaneMaker;
import com.example.ViewController.Dialog;
import com.example.ViewController.GamePageViewController;

public class GameController {
	private static GameController instance = new GameController();
	public static GameController getInstance(){ return instance;}

	public void startNewGame(ArrayList<String> usernames, long seed) throws Exception{
		Request request = Request.startGameRequest(seed, usernames);
		Response response = NetworkController.makeQuery(request);
		if (response.getStatus_code()!=0) throw new Exception(response.getMessage());
		new Game(20, 20, usernames, seed);
		App.openGamePage();
	}
	
	public void joinGame() throws Exception{
		Request request = Request.joinGameRequest();
		Response response = NetworkController.makeQuery(request);
		if (response.getStatus_code()!=0) throw new Exception(response.getMessage());
		updateGameFromHistory(GameHistory.fromJson(response.getOutput()));
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Request request = Request.getActionsRequest();
					Response response = NetworkController.makeQuery(request);
					updateGameFromHistory(GameHistory.fromJson(response.getOutput()));
				}
			}
		}).start();
	}

	public synchronized boolean handleQueryFromView(UserActionQuery query){
		try {
			query.validate();
			Request request = Request.gameActionRequest(query);
			Response response = NetworkController.makeQuery(request);
			if (response.getStatus_code()!=0) throw new Exception(response.getMessage());
		} catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return false;
		}
		Game.getInstance().gameHistory.addAction(query);

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

	public void updateGameFromHistory(GameHistory gameHistory){
		if (Game.getInstance()==null){
			new Game(gameHistory.width, gameHistory.height, gameHistory.usernames, gameHistory.seed);
		}
		Game.getInstance().gameHistory.updateFromNewHistory(gameHistory);
	}
}
