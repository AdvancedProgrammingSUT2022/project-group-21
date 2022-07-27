package com.example.Model.spfa;

import java.util.HashMap;

import com.example.Model.Game;
import com.example.Model.tile.Tile;

public class ShortestPathSmall {
	private final static int inf=1000000000;

	private final int W, H;
	private Tile sourceTile;
	private HashMap<Tile, Integer> dist;

	public ShortestPathSmall(Game game, Tile sourceTile, int maxDistance){
		W=game.WIDTH;
		H=game.HEIGHT;
		this.sourceTile=sourceTile;
		if (maxDistance==-1) maxDistance=inf;
		spfa(maxDistance);
	}
	
	// run spfa instead of dijkstra for shortest path
	private void spfa(int maxDistance){
		dist = new HashMap<>();
		boolean inq[][] = new boolean[W][H];
		int maxQ=W*H, head=0, tail=0;
		Tile[] Q = new Tile[maxQ];
		Q[tail++]=sourceTile;
		dist.put(sourceTile, 0);
		while (head!=tail){
			Tile v=Q[head++];
			if (head==maxQ) head=0;
			inq[v.X][v.Y]=false;
			if (dist.get(v)>=maxDistance) continue ;
			for (int i=0; i<6; i++){
				Tile u=v.getAdjTile(i);
				if (u==null || !u.canSeeOver()) continue ;
				if (dist.get(u)==null || dist.get(u)>dist.get(v)+1){
					dist.put(u, dist.get(v)+1);
					if (!inq[u.X][u.Y]){
						inq[u.X][u.Y]=true;
						Q[tail++]=u;
					}
				}
			}
		}
	}
	public int getDistance(Tile tile){
		Integer res = dist.get(tile);
		if (res==null) return inf;
		return res;
	}

	public int getNearestCityDistance(){
		int res=inf;
		for (Tile tile : dist.keySet()) {
			if (tile.getCityOnTile()==null) continue ;
			int d=dist.get(tile);
			if (d<res) res=d;
		}
		return res;
	}
}
