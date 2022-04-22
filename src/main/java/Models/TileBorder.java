package Models;

public class TileBorder {
	private final Tile FIRST;
	private final Tile SECOND;
	private final boolean RIVER;

	public TileBorder(Tile FIRST, Tile SECOND, boolean RIVER) {
		this.FIRST = FIRST;
		this.SECOND = SECOND;
		this.RIVER = RIVER;
	}
	public Tile getOtherSide(Tile tile){ return tile==FIRST?SECOND:FIRST; }
	public boolean isRIVER(){ return RIVER; }
}
