package Models;

import java.util.ArrayList;

public class Terrain {
    private int food;
    private int production;
    private int gold;
    private int combat;
    private int MP;
    private boolean passable;
    private ArrayList<Resource> resources;
    private TerrainFeature terrainFeature;
    private Improvement improvement;
    private boolean visible;

    public Terrain(int food, int production, int gold, int combat, int MP, boolean passable,
                   ArrayList<Resource> resources, boolean visible) {
        this.food = food;
        this.production = production;
        this.combat = combat;
        this.gold = gold;
        this.MP = MP;
        this.passable = passable;
        this.resources = resources;
        this.visible = visible;
    }
    public Terrain clone() {
        return null;
    }
}
