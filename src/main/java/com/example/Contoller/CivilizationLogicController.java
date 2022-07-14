package com.example.Contoller;

import com.example.Model.City;
import com.example.Model.Civilization;
import com.example.Model.tile.Tile;
import com.example.Model.unit.Settler;

public class CivilizationLogicController {
	private Civilization civ;

	public CivilizationLogicController(Civilization civ){
		this.civ=civ;
	}

	// TODO: review completely after finnishing SETTLER
	public void foundCity(Settler settler){
		Tile tile = settler.getTile();
		City city = new City(tile, civ);
		civ.addCity(city);
		settler.kill();
	}
	

	// TODO: 
	// private boolean canResearchTechnology(Civilization civilization, Technology technology){
	// 	for (Technology prerequisiteTech : technology.getPrequisiteTechs())
	// 		if (!civilization.hasTechnology(prerequisiteTech)) return false;
	// 	return true;
	// }
	// public ArrayList<Technology> getAvailableTechnologies(Civilization civilization){
	// 	ArrayList<Technology> availableTechnologies = new ArrayList<Technology>();
	// 	for (Technology technology : Technology.values()) {
	// 		if (civilization.hasTechnology(technology)) continue;
	// 		if (!canResearchTechnology(civilization, technology)) continue;
	// 		availableTechnologies.add(technology);
	// 	}
	// 	return availableTechnologies;
	// }
	// public Message researchTechnology(Civilization civilization, Technology technology){
	// 	// TODO
	// 	return null;
	// }

	// public int calculateHappiness(Civilization civilization){
	// 	// TODO
	// 	return -1;
	// }
	
}
