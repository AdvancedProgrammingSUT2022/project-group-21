package Models.Unit;

import Models.City;
import Models.Tile;

public class Siege extends MilitaryUnit{
    private Tile targetTile;

    public void preAttack(Tile tile) {
        //TODO
    }
    public boolean isInRange(Tile tile) {
        //TODO
        return false;
    }
    public void attackToCity(City city) {
        //TODO
    }

    public Siege(int XP, boolean isSleep, boolean isOnFortify, boolean isOnAlert, boolean isOnGarrison, int range, int lastActionTurn, int HP, int MP, Tile tile, int combatStrength, int COST) {
        super(XP, isSleep, isOnFortify, isOnAlert, isOnGarrison, range, lastActionTurn, HP, MP, tile, combatStrength, COST);
    }
}
