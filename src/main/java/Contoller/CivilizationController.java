package Contoller;

import java.util.ArrayList;

import Enums.Message;
import Models.Civilization;
import Models.Game;
import Models.Technology;

public class CivilizationController{
	private static CivilizationController instance;
	private static void setInstance(CivilizationController instance) {
		CivilizationController.instance = instance;
	}
	public static CivilizationController getInstance() {
		if (instance == null) CivilizationController.setInstance(new CivilizationController());
		return instance;
	}

	private Game getGame(){ return GameController.getInstance().getGame();}

	private boolean canResearchTechnology(Technology technology){
		Civilization civilization=getGame().getCurrentPlayer().getCivilization();
		for (Technology prerequisiteTech : technology.getPrequisiteTechs())
			if (!civilization.hasTechnology(prerequisiteTech)) return false;
		return true;
	}
	public ArrayList<Technology> getAvailableTechnologies() {
		Civilization civilization=getGame().getCurrentPlayer().getCivilization();
		ArrayList<Technology> availableTechnologies = new ArrayList<Technology>();
		for (Technology technology : Technology.values()) {
			if (civilization.hasTechnology(technology)) continue;
			if (!canResearchTechnology(technology)) continue;
			availableTechnologies.add(technology);
		}
		return availableTechnologies;
	}
	public Message researchTechnology(Technology technology){
		// TODO
		return null;
	}

	public int calculateHappiness(Civilization civilization){
		// TODO
		return -1;
	}



}
