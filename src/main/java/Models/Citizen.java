package Models;

import java.util.ArrayList;

public class Citizen {
	private City city;
	private boolean isWorking;
	private Tile workingTile;

	public Citizen(City city) {
		this.city = city;
		isWorking = false;
	}

	public void assignToTile(Tile tile) {
		if (isWorking)
			workingTile.setCitizen(null);
		isWorking = true;
		tile.setCitizen(this);
		workingTile = tile;
	}

	public void die() {
		workingTile.setCitizen(null);
		city.removeCitizen(this);
	}


	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean working) {
		isWorking = working;
	}

	public Tile getWorkingTile() {
		return workingTile;
	}

	public void setWorkingTile(Tile workingTile) {
		this.workingTile = workingTile;
	}


}
