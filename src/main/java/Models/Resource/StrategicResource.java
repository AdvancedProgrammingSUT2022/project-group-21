package Models.Resource;

import Models.Civilization;
import Models.Technology;
import Models.Improvement;

public class StrategicResource extends Resource{
    public Technology technologyNeeded;
    public StrategicResource(int food, int production, int gold, Improvement improvementNeeded,
                             Technology technologyNeeded) {
        super(food, production, gold, improvementNeeded);
        this.technologyNeeded = technologyNeeded;
    }
    public boolean isVisible(Civilization civilization) {
        //TODO
        return false;
    }
}
