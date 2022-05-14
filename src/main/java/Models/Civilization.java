package Models;

import Models.Resource.Resource;
import Models.Tile.Tile;
import Models.Unit.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Civilization {
	private final int W, H;
	private ArrayList<Tile> tiles;
	private ArrayList<City> cities;
	private City capitalCity;
	private ArrayList<Technology> technologies;
	private HashMap<Resource, Integer> resources;
	private ArrayList<Unit> units;
	private boolean[][] fogOfWar;
	private boolean[][] Visible;
	private boolean visibleTileFindFlag;

	private int gold;
	private int food;
	private int production;

	public Civilization(Tile tile, int W, int H){
		this.capitalCity = new City(tile, this);
		this.fogOfWar = new boolean[W][H];
		this.Visible = new boolean[W][H];
		this.W=W;
		this.H=H;
	}


	public boolean hasTechnology(Technology technology){
		return technologies.contains(technology);
	}

	public int countResource(Resource resource) {
		return resources.get(resource);
	}
	
	public void resetVisibleTiles(){ visibleTileFindFlag=false; }

	private void setVisible(Tile tile, int radius, boolean onHill, boolean[][] fogOfWar){
		assert(radius<=2); // NOTE: replace with bfs
		fogOfWar[tile.X][tile.Y]=true;
		if (!tile.canSeeOver() && !onHill) return ;
		if (radius<=0) return ;
		for (int i=0; i<6; i++){
			Tile tile2=tile.getAdjTile(i);
			if (tile2==null) continue ;
			setVisible(tile2, radius-1, onHill, fogOfWar);
		}
	}

	public void addTile(Tile tile){
		resetVisibleTiles();
		tiles.add(tile);
		setVisible(tile, 1, true, fogOfWar);
	}
	public void removeTile(Tile tile){ tiles.remove(tile); }

	private void findVisibleTiles(){
		if (!visibleTileFindFlag) return ;
		visibleTileFindFlag=true;
		for (int i=0; i<W; i++) for (int j=0; j<H; j++) Visible[i][j]=false;
		for (Tile tile : tiles) setVisible(tile, 1, true, Visible);
		for (Unit unit: units) setVisible(unit.getTile(), 2, unit.getTile().canSeeOver(), Visible);
	}

	public boolean isTileVisible(int x, int y){
		findVisibleTiles();
		return Visible[x][y];
	}

	public void addCity(City city) {
		cities.add(city);
	}

	public int getGold(){ return gold;}
	public int getFood(){ return food;}
	public int getProduction(){ return production;}

	public void addGold(int amount){ gold+=amount;}
	public void addFood(int amount){ food+=amount;}
	public void addUnit(Unit unit){ units.add(unit);}
	public void removeUnit(Unit unit){ units.remove(unit);}

	public City getCapitalCity(){ return capitalCity;}
}
