package Models;

public class Tile {
    public final int X;
    public final int Y;
    public Terrain TERRAIN;
    private TileBorder borders[6];
    private Civillization owner;
    private MilitaryUnit militaryUnit;
    private CivilianUnit civilianUnit;
    private Citizen workingCitizen;
    private Ruin ruin;
    private int roadType;

    public Tile(int X, int Y, Terrain TERRAIN) {
        this.X = X;
        this.Y = Y;
        this.TERRAIN = TERRAIN;
    }
    public boolean isTerrainFeatureCompatible(TerrainFeature terrainFeature) {
        return false;
    }
}
