package Models;

import java.util.ArrayList;
import java.util.Random;

import Contoller.SelectController;
import Models.Tile.Terrain;
import Models.Tile.TerrainFeature;
import Models.Tile.Tile;

public class Game {
	public final int WIDTH;
	public final int HEIGHT;
	public int PLAYER_COUNT;
	private Tile[][] tiles;
	private ArrayList<User> players;
	private User currentPlayer;
	private int currentTurn;
	private int countTurns;

	public Game(int WIDTH, int HEIGHT, ArrayList<User> players) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.players = players;
		Random random = new Random(System.nanoTime());
		generateRandomMap(random);
		putPlayersOnMap(random);
		SelectController.getInstance().reset();
	}
	
	private void generateRandomMap(Random random){
		Terrain[] terrains=Terrain.values();
		tiles = new Tile[WIDTH][HEIGHT];
		for (int i=0; i<WIDTH; i++){
			for (int j=0; j<HEIGHT; j++){
				tiles[i][j]=new Tile(i, j, terrains[random.nextInt(terrains.length)]);
			}
		}
		setAdjTiles();
		setRivers(random, 20); // 1/20 of all borders
		setTerrainFeatures(random, 3); // 1/3 of tiles have feature
	}

	private void setAdjTiles(){
		for (int i=0; i<WIDTH; i++){
			for (int j=0; j<HEIGHT; j++){
				if (j>0){
					tiles[i][j].setAdjTile(0, tiles[i][j-1]);
					tiles[i][j-1].setAdjTile(3, tiles[i][j]);
				}
				if (i%2==1) continue ;
				if (i+1<WIDTH){
					tiles[i][j].setAdjTile(1, tiles[i+1][j]);
					tiles[i+1][j].setAdjTile(4, tiles[i][j]);
				}
				if (i+1<WIDTH && j+1<HEIGHT){
					tiles[i][j].setAdjTile(2, tiles[i+1][j+1]);
					tiles[i+1][j+1].setAdjTile(5, tiles[i][j]);
				}
				if (i>0 && j+1<HEIGHT){
					tiles[i][j].setAdjTile(4, tiles[i-1][j+1]);
					tiles[i-1][j+1].setAdjTile(1, tiles[i][j]);
				}
				if (i>0){
					tiles[i][j].setAdjTile(5, tiles[i-1][j]);
					tiles[i-1][j].setAdjTile(2, tiles[i][j]);
				}
			}
		}
	}

	private void setRivers(Random random, int prob){
		for (int i=0; i<WIDTH; i++){
			for (int j=0; j<HEIGHT; j++){
				for (int d=0; d<3; d++){
					Tile x=tiles[i][j];
					Tile y=x.getAdjTile(d);
					if (y==null) continue ;
					if (random.nextInt(prob)==0){
						x.setRiver(d, true);
						y.setRiver(d+3, true);
					}
				}
			}
		}
	}

	private void setTerrainFeatures(Random random, int prob){
		for (int i=0; i<WIDTH; i++){
			for (int j=0; j<HEIGHT; j++){
				ArrayList<TerrainFeature> possibleTerrainFeatures = new ArrayList<>();
				for (TerrainFeature terrainFeature : TerrainFeature.values()){
					if (tiles[i][j].isTerrainFeatureCompatible(terrainFeature))
						possibleTerrainFeatures.add(terrainFeature);
				}
				if (possibleTerrainFeatures.isEmpty()) continue ;
				if (random.nextInt(prob)>0) continue ;
				tiles[i][j].setTerrainFeature(possibleTerrainFeatures.get(random.nextInt(possibleTerrainFeatures.size())));
			}
		}
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

	public Tile getTile(int x, int y){ return tiles[x][y];}

	public User getCurrentPlayer() {
		return currentPlayer;
	}

	public void nextTurn(){
		SelectController.getInstance().reset();
		currentTurn++;
		if (currentTurn==players.size()){
			currentTurn=0;
			countTurns++;
		}
		currentPlayer=players.get(currentTurn);
	}
}
