package Models.Unit;

import Models.City;
import Models.Tile;

public class Melee extends MilitaryUnit{

    public void attackToUnit(Tile tile) {
        //TODO
    }
    public void attackToCity(City city) {
        //TODO
    }


    public Melee(int XP, boolean isSleep, boolean isOnFortify, boolean isOnAlert, boolean isOnGarrison, int range, int lastActionTurn, int HP, int MP, Tile tile, int combatStrength, int COST) {
        super(XP, isSleep, isOnFortify, isOnAlert, isOnGarrison, range, lastActionTurn, HP, MP, tile, combatStrength, COST);
    }
}
