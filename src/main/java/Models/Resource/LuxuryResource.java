package Models.Resource;

import Models.Improvement;

public class LuxuryResource extends Resource{
    public LuxuryResource(int food, int production, int gold,
                          Improvement improvementNeeded) {
        super(food, production, gold, improvementNeeded);
    }
}
