package com.example.Model.spfa;

import java.util.ArrayList;

import com.example.Model.Game;
import com.example.Model.tile.Tile;
import com.example.Model.unit.Unit;

public class ShortestPath {
	private static final int inf=1000000000;
	private final int W, H;
	private Tile sourceTile;
	private Unit unit;
	private int[][] dist;
	private Tile[][] par;

	public ShortestPath(Game game, Unit unit, int maxDistance){
		W=game.WIDTH;
		H=game.HEIGHT;
		this.sourceTile=unit.getTile();
		this.unit=unit;
		if (maxDistance==-1) maxDistance=inf;
		spfa(maxDistance);
	}
	
	// run spfa instead of dijkstra for shortest path
	private void spfa(int maxDistance){
		dist = new int[W][H];
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				dist[i][j]=inf;
			}
		}
		boolean inq[][] = new boolean[W][H];
		int maxQ=W*H, head=0, tail=0;
		Tile[] Q = new Tile[maxQ];
		par = new Tile[W][H];
		Q[tail++]=sourceTile;
		dist[sourceTile.X][sourceTile.Y]=0;
		while (head!=tail){
			Tile v=Q[head++];
			if (head==maxQ) head=0;
			inq[v.X][v.Y]=false;
			if (dist[v.X][v.Y]>=maxDistance) continue ;
			for (int i=0; i<6; i++){
				Tile u=v.getAdjTile(i);
				if (u==null || !u.isPassable()) continue ;
				int w=(unit==null?1:v.getMovementCostForUnit(unit, i));
				if (dist[u.X][u.Y]>dist[v.X][v.Y]+w){
					dist[u.X][u.Y]=dist[v.X][v.Y]+w;
					par[u.X][u.Y]=v;
					if (!inq[u.X][u.Y]){
						inq[u.X][u.Y]=true;
						Q[tail++]=u;
					}
				}
			}
		}
	}
	public int getDistance(Tile tile){ return dist[tile.X][tile.Y];}
	public ArrayList<Tile> getPath(Tile destinationTile){
		if (par[destinationTile.X][destinationTile.Y]==null) return null;
		return getPathRecursive(destinationTile);
	}
	private ArrayList<Tile> getPathRecursive(Tile destinationTile){
		if (destinationTile==sourceTile) return new ArrayList<>();
		ArrayList<Tile> path = getPathRecursive(par[destinationTile.X][destinationTile.Y]);
		path.add(destinationTile);
		return path;
	}
}
