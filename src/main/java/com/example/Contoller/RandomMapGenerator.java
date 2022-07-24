package com.example.Contoller;

import java.util.ArrayList;
import java.util.Random;

import com.example.Model.Game;
import com.example.Model.resource.Resource;
import com.example.Model.tile.Terrain;
import com.example.Model.tile.TerrainFeature;
import com.example.Model.tile.Tile;

public class RandomMapGenerator {
	private static RandomMapGenerator instance;
	public static RandomMapGenerator getInstance(){
		if (instance==null) instance = new RandomMapGenerator();
		return instance;
	}

	public void generateRandomMap(Game game, Tile[][] tiles, long seed){
		Random random = new Random(seed);
		Terrain[] terrains=Terrain.values();
		for (int i=0; i<game.WIDTH; i++){
			for (int j=0; j<game.HEIGHT; j++){
				tiles[i][j]=new Tile(i, j, terrains[random.nextInt(terrains.length)]);
			}
		}
		setAdjTiles(game, tiles);
		setRivers(game, tiles, random, 20); // 1/20 of all borders
		setTerrainFeatures(game, tiles, random, 3); // 1/3 of tiles have feature
		setResources(game, tiles, random);
	}
	private void setAdjTiles(Game game, Tile[][] tiles){
		for (int x=0; x<game.WIDTH; x++){
			for (int y=0; y<game.HEIGHT; y++){
				setAdjTile(game, x, y, 0, x+1, y);
				setAdjTile(game, x, y, 3, x-1, y);
				if (y%2==1){
					setAdjTile(game, x, y, 1, x+1, y+1);
					setAdjTile(game, x, y, 2, x, y+1);
					setAdjTile(game, x, y, 4, x, y-1);
					setAdjTile(game, x, y, 5, x+1, y-1);
				}
				else{
					setAdjTile(game, x, y, 1, x, y+1);
					setAdjTile(game, x, y, 2, x-1, y+1);
					setAdjTile(game, x, y, 4, x-1, y-1);
					setAdjTile(game, x, y, 5, x, y-1);
				}
			}
		}
	}

	private void setAdjTile(Game game, int x, int y, int i, int x2, int y2){
		game.getTile(x, y).setAdjTile(i, game.getTile(x2, y2));
	}

	private void setRivers(Game game, Tile[][] tiles, Random random, int prob){
		for (int i=0; i<game.WIDTH; i++){
			for (int j=0; j<game.HEIGHT; j++){
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

	private void setTerrainFeatures(Game game, Tile[][] tiles, Random random, int prob){
		for (int i=0; i<game.WIDTH; i++){
			for (int j=0; j<game.HEIGHT; j++){
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
	private void setResources(Game game, Tile[][] tiles, Random random){
		for (int i=0; i<game.WIDTH; i++){
			for (int j=0; j<game.HEIGHT; j++){
				ArrayList<Resource> possibleResources = new ArrayList<>();
				possibleResources.add(null);
				Tile tile = tiles[i][j];
				for (Resource resource : tile.getTerrain().possibleResources) {
					possibleResources.add(resource);
				}
				if (tile.getTerrainFeature()!=null){
					for (Resource resource : tile.getTerrainFeature().possibleResources) {
						possibleResources.add(resource);
					}
				}
				tile.setResource(possibleResources.get(random.nextInt(possibleResources.size())));
			}
		}
	}
}
