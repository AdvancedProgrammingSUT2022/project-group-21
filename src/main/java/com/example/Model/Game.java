package com.example.Model;

import java.util.ArrayList;
import java.util.Random;

import com.example.Contoller.RandomMapGenerator;
import com.example.Model.tile.Tile;

public class Game {
	private static Game instance;
	public static Game getInstance(){ return instance;} // TODO: maybe I should remove singleton?

	public final int WIDTH;
	public final int HEIGHT;
	private Tile[][] tiles;
	private ArrayList<User> players;
	private User currentPlayer;
	private int currentTurn;
	private int totalTurnsCount;

	public Game(int WIDTH, int HEIGHT, ArrayList<User> players) {
		instance=this;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.players = players;
		Random random = new Random(System.nanoTime());
		long seed=random.nextInt();
		RandomMapGenerator.getInstance().generateRandomMap(this, this.tiles, seed);
		putPlayersOnMap(random);
	}
	
	private void putPlayersOnMap(Random random){
		for (User user: players){
			while (true){
				int x=random.nextInt(WIDTH);
				int y=random.nextInt(WIDTH);
				if (tiles[x][y].getOwner()!=null) continue ;
				Civilization civilization = new Civilization(tiles[x][y], WIDTH, HEIGHT);
				user.setCivilization(civilization);
				break ;
			}
		}
	}
	public boolean checkTileCoordinates(int x, int y){
		return 0<=x && x<WIDTH && 0<=y && y<HEIGHT;
	}
	public Tile getTile(int x, int y){
		return tiles[x][y];
	}
	public void changeTileOwner(Tile tile, Civilization civilization){
		if (tile.getOwner()!=null) tile.getOwner().removeTile(tile);
		tile.setOwner(civilization);
		civilization.addTile(tile);
	}


	public User getCurrentPlayer() {
		return currentPlayer;
	}
	public int getTotalTurnsCount(){
		return totalTurnsCount;
	}
	
	// TODO: maybe move to controller
	public void nextTurn(){
		currentTurn++;
		if (currentTurn==players.size()){
			currentTurn=0;
			totalTurnsCount++;
		}
		currentPlayer=players.get(currentTurn);

		// Civilization civilization=currentPlayer.getCivilization();
		// CityController.getInstance().handleImprovementProjects();
		// TODO
	}

	public boolean isUserPlayingGame(User user){
		return players.contains(user);
	}

}
