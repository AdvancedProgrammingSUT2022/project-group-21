package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Civilization {
    private ArrayList<City> cities;
    private City capitalCity;
    private HashSet<Technology>;
    private HashMap<Resource, Integer> resources;
    private int happiness;
    private boolean[][] fogOfWar;
    private ArrayList<Unit> units;

    public Civilization(Tile tile) {

    }

    public int hasTechnology(Technology technology) {}
    public int countResource(Resource resource) {}
    public boolean isTileVisible(int x, int y){}
}
