package Models;

import Models.Resource.Resource;
import Models.Unit.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Civilization {
	private ArrayList<City> cities;
	private City capitalCity;
	private HashSet<Technology> technologies;
	private HashMap<Resource, Integer> resources;
	private int happiness;
	private boolean[][] fogOfWar;
	private ArrayList<Unit> units;


	public Civilization(Tile tile) {
		this.capitalCity = new City(0, 0 , "", tile);
	}

	public int hasTechnology(Technology technology) {
		//TODO
		return 0;
	}
	public int countResource(Resource resource) {
		return resources.get(resource);
	}
	public boolean isTileVisible(Tile tile){
		//TODO
		return false;
	}

	public void setVisible(int x, int y){
		// TODO
	}

}
