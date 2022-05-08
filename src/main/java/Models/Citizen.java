package Models;

import java.util.ArrayList;

public class Citizen {
	private City city;
	private Tile workingTile;

	public Citizen(City city) {
		this.city = city;
	}

	public void assignToTile(Tile tile) {
		if (workingTile!=null) workingTile.setCitizen(null);
		tile.setCitizen(this);
		workingTile = tile;
	}

	public void die() {
		workingTile.setCitizen(null);
		city.removeCitizen(this);
	}

	public City getCity(){ return city;}
	public void setCity(City city){ this.city = city;}

	
	
	public boolean isWorking(){ return workingTile!=null; }
	public Tile getWorkingTile(){ return workingTile;}
	public void setWorkingTile(Tile workingTile){ this.workingTile = workingTile;}


}
