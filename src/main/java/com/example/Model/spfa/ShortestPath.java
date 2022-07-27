package com.example.Model.spfa;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Model.Game;
import com.example.Model.tile.Tile;
import com.example.Model.unit.Unit;

public class ShortestPath {	
	private final int W, H;
	private Tile sourceTile;
	private Unit unit;
	private HashMap<Tile, Integer> dist;
	private ArrayList<Tile> reachableTiles;

	public ShortestPath(Game game, Unit unit){
		W=game.WIDTH;
		H=game.HEIGHT;
		this.sourceTile=unit.getTile();
		this.unit=unit;
		spfa(unit.getMP());
	}
	
	// run spfa instead of dijkstra for shortest path
	private void spfa(int maxDistance){
		dist = new HashMap<>();
		reachableTiles = new ArrayList<>();
		boolean inq[][] = new boolean[W][H];
		int maxQ=W*H, head=0, tail=0;
		Tile[] Q = new Tile[maxQ];
		Q[tail++]=sourceTile;
		dist.put(sourceTile, 0);
		while (head!=tail){
			Tile v=Q[head++];
			if (!reachableTiles.contains(v)) reachableTiles.add(v);
			if (head==maxQ) head=0;
			inq[v.X][v.Y]=false;
			if (dist.get(v)>=maxDistance) continue ;
			for (int i=0; i<6; i++){
				Tile u=v.getAdjTile(i);
				if (u==null || !u.canSeeOver()) continue ;
				int w=(unit==null?1:v.getMovementCostForUnit(unit, i));
				if (dist.get(u)==null || dist.get(u)>dist.get(v)+w){
					dist.put(u, dist.get(v)+w);
					if (!inq[u.X][u.Y]){
						inq[u.X][u.Y]=true;
						Q[tail++]=u;
					}
				}
			}
		}
	}
	public ArrayList<Tile> getReachableTiles(){
		return reachableTiles;
	}
	public int getCost(Tile tile){
		return dist.get(tile);
	}
}
