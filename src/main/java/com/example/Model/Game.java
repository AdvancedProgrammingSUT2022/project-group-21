package com.example.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.example.Contoller.RandomMapGenerator;
import com.example.Model.tile.Tile;
import com.example.Model.user.User;

public class Game {
	private static Game instance;
	public static Game getInstance(){ return instance;} // TODO: maybe I should remove singleton?

	private HashMap<String, Civilization> civilizations = new HashMap<>();
	public final GameHistory gameHistory;
	public final int WIDTH;
	public final int HEIGHT;
	private Tile[][] tiles;
	private ArrayList<User> players;
	private User currentPlayer;
	private int totalTurnsCount, year;

	public Game(int WIDTH, int HEIGHT, ArrayList<User> players, long seed) {
		instance=this;
		
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.players = players;
		this.gameHistory = new GameHistory(WIDTH, HEIGHT, getUsernamesOfPlayers(players), seed);
		this.tiles = new Tile[WIDTH][HEIGHT];
		Random random = new Random(System.nanoTime());
		RandomMapGenerator.getInstance().generateRandomMap(this, this.tiles, seed);
		putPlayersOnMap(random);

		currentPlayer = players.get(0);
	}

	private static ArrayList<String> getUsernamesOfPlayers(ArrayList<User> players){
		ArrayList<String> usernames = new ArrayList<>();
		for (User user : players) {
			usernames.add(user.getUsername());
		}
		return usernames;
	}
	
	private void putPlayersOnMap(Random random){
		for (User user: players){
			while (true){
				int x=random.nextInt(WIDTH);
				int y=random.nextInt(WIDTH);
				if (tiles[x][y].getOwner()!=null) continue ;
				Civilization civilization = new Civilization(tiles[x][y], WIDTH, HEIGHT);
				civilizations.put(user.getUsername(), civilization);
				break ;
			}
		}
	}
	public Civilization getCivilization(String username){
		return civilizations.get(username);
	}


	public boolean checkTileCoordinates(int x, int y){
		return 0<=x && x<WIDTH && 0<=y && y<HEIGHT;
	}
	public Tile getTile(int x, int y){
		if (!checkTileCoordinates(x, y)) return null;
		return tiles[x][y];
	}
	


	public User getCurrentPlayer() {
		return currentPlayer;
	}
	public int getTotalTurnsCount(){
		return totalTurnsCount;
	}
	
	public void nextTurn(){
		int currentTurn = players.indexOf(currentPlayer)+1;
		if (currentTurn==players.size()){
			currentTurn=0;
			totalTurnsCount++;
			year+=10;
		}
		currentPlayer=players.get(currentTurn);
		// TODO?
	}

	public boolean isUserPlayingGame(User user){
		return players.contains(user);
	}

	public int getYear(){
		return year;
	}

	public ArrayList<User> getPlayers(){
		return players;
	}

	public void removePlayer(User user){
		if (user==currentPlayer) nextTurn();
		players.remove(user);
	}

	public void endGame(){
		instance = null;
		// TODO: update scoreboard
	}

}
