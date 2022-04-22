package Models;

import Models.Resource.Resource;

import java.util.ArrayList;

public class TerrainFeature {
    private int food;
    private int production;
    private int gold;
    private int combat;
    private int MP;
    private boolean passable;
    private ArrayList<Resource> resources;
    private boolean needRiver;
    private Terrain onlyPlacedOn;
    private Boolean visible;

    TerrainFeature(int food, int production, int gold, int combat, int MP, boolean passable,
                   ArrayList<Resource> resources, boolean needRiver, Terrain onlyPlacedOn, boolean visible) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.combat = combat;
        this.MP = MP;
        this.passable = passable;
        this.resources = resources;
        this.needRiver = needRiver;
        this.onlyPlacedOn = onlyPlacedOn;
        this.visible = visible;
    }
}
