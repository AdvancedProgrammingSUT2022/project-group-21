package Models.Unit;

import Models.Tile;

public class MilitaryUnit extends Unit{
    private int XP;
    private boolean isSleep;
    private boolean isOnFortify;
    private boolean isOnAlert;
    private boolean isOnGarrison;
    private final int RANGE;
    private int lastActionTurn;

    public MilitaryUnit(int XP, boolean isSleep, boolean isOnFortify, boolean isOnAlert, boolean isOnGarrison,
                        int range, int lastActionTurn, int HP, int MP, Tile tile, int combatStrength, int COST) {
        super(HP, MP, tile, combatStrength, COST);
        this.XP = XP;
        this.isSleep = isSleep;
        this.isOnFortify = isOnFortify;
        this.isOnAlert = isOnAlert;
        this.isOnGarrison = isOnGarrison;
        this.RANGE = range;
        this.lastActionTurn = lastActionTurn;
    }
    public void sleep() {

    }
    public void fortify() {}
    public void alert() {}
    public void garrison() {}

}
