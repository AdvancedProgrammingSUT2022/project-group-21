package Models.Resource;

import Models.Improvement;

public class Resource {
    private int food;
    private int production;
    private int gold;
    public Improvement improvementNeeded;

    public Resource(int food, int production, int gold,
                    Improvement improvementNeeded) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.improvementNeeded = improvementNeeded;
    }
}
