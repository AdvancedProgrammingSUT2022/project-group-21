package com.example.Model;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Contoller.CivilizationLogicController;
import com.example.Model.resource.Resource;
import com.example.Model.tile.Tile;
import com.example.Model.unit.Unit;

public class Civilization {
	private final int W, H;
	private CivilizationLogicController logicController;
	
	private ArrayList<Tile> tiles = new ArrayList<>();
	private ArrayList<City> cities = new ArrayList<>();
	private City capitalCity;
	private ArrayList<Technology> technologies = new ArrayList<>();
	private HashMap<Resource, Integer> resources = new HashMap<>();
	private ArrayList<Unit> units;
	private boolean[][] revealed;
	private boolean[][] visible;

	private int gold;
	private int happiness;

	private ArrayList<Civilization> enemies = new ArrayList<>();



	public Civilization(Tile tile, int W, int H){
		this.capitalCity = new City(tile, this);
		addCity(this.capitalCity);
		this.revealed = new boolean[W][H];
		this.visible = new boolean[W][H];
		this.W=W;
		this.H=H;
		this.logicController = new CivilizationLogicController(this);
	}

	public CivilizationLogicController getLogicController(){ return logicController;}

	public int getHappiness(){
		return happiness;
	}

	public boolean hasTechnology(Technology technology){
		return technologies.contains(technology);
	}
	public boolean hasTechnologies(Technology[] technologies){
		for (Technology technology : technologies) {
			if (!hasTechnology(technology))
				return false;
		}
		return true;
	}

	public void addEnemy(Civilization enemy){ enemies.add(enemy);}
	public void removeEnemy(Civilization enemy){ enemies.remove(enemy);}
	public boolean isEnemy(Civilization civilization){ return enemies.contains(civilization);}
	

	public int countResource(Resource resource) {
		return resources.get(resource);
	}
	
	private void setVisible(Tile tile, int radius, boolean onHill, boolean[][] revealed){
		assert(radius<=2); // NOTE: replace with bfs
		revealed[tile.X][tile.Y]=true;
		if (!tile.canSeeOver() && !onHill) return ;
		if (radius<=0) return ;
		for (int i=0; i<6; i++){
			Tile tile2=tile.getAdjTile(i);
			if (tile2==null) continue ;
			setVisible(tile2, radius-1, onHill, revealed);
		}
	}

	public void addTile(Tile tile){
		tiles.add(tile);
		setVisible(tile, 1, true, revealed);
	}
	public void removeTile(Tile tile){ tiles.remove(tile); }

	public void calculateVisibleTiles(){
		for (int i=0; i<W; i++) for (int j=0; j<H; j++) visible[i][j]=false;
		for (Tile tile : tiles) setVisible(tile, 1, true, visible);
		for (Unit unit: units) setVisible(unit.getTile(), 2, unit.getTile().canSeeOver(), visible);
		for (City city : cities) setVisible(city.getCenter(), 3, true, visible);
	}

	public boolean isTileRevealed(int x, int y){ return revealed[x][y];}
	public boolean[][] getVisibleTiles(){
		calculateVisibleTiles();
		boolean[][] out = new boolean[W][H];
		for (int i=0; i<W; i++) for (int j=0; j<H; j++)
			out[i][j]=visible[i][j];
		return out;
	}

	public void addCity(City city) {
		cities.add(city);
		setVisible(city.getCenter(), 3, true, revealed);
		calculateVisibleTiles();
	}
	public void removeCity(City city){
		// NOTE: used for destroyed cities
		cities.remove(city);
	}

	public int getGold(){ return gold;}
	public void addGold(int gold){ this.gold+=gold;}

	public void addUnit(Unit unit){ units.add(unit);}
	public void removeUnit(Unit unit){ units.remove(unit);}

	public City getCapitalCity(){ return capitalCity;}
}
