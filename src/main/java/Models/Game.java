package Models;

import java.util.ArrayList;

public class Game {
	public final int WIDTH;
	public final int HEIGHT;
	public int PLAYER_COUNT;
	//private Tile[100][100] tiles;
	private ArrayList<User> players;
	private User currentPlayer;
	private int currentTurn;
	private int countTurns;

	public Game(int WIDTH, int HEIGHT, ArrayList<User> players) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.players = players;
	}
	private void generateRandomMap() {}
	private Tile[][] cloneMapforUser() {
		return null;
	}
	public void nextTurn() {}
}
