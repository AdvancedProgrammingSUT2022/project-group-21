package Models.Resource;

public class Resource {
    private int food;
    private int production;
    private int gold;
    public improvement improvementNeeded;

    public Resource(int food, int production, int gold,
                    improvement improvementNeeded) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.improvementNeeded = improvementNeeded;
    }
}
