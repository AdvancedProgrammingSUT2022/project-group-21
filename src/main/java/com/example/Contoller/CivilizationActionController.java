package com.example.Contoller;

import com.example.Model.CheatCode;
import com.example.Model.Civilization;
import com.example.Model.Technology;
import com.example.Model.city.City;
import com.example.Model.tile.Tile;

public class CivilizationActionController {
	private static CivilizationActionController instance;
	public static CivilizationActionController getInstance(){
		if (instance==null) instance=new CivilizationActionController();
		return instance;
	}

	public void endTurn(Civilization civilization, boolean doAction) throws Exception{
		if (civilization.getCurrentResearchTech()==null) throw new Exception("you should select a technology to research");
		for (City city : civilization.getCities()){
			if (city.getProductionProject()==null){
				Tile tile = city.getCenter();
				throw new Exception("you should select a production project for city at ("+tile.X+", "+tile.Y+")");
			}
		}
		// TODO: make sure everything is checked
		if (doAction){
			civilization.endTurn();
		}
	}
	
	public void setResearch(Civilization civilization, Technology technology, boolean doAction) throws Exception{
		if (technology==null) throw new Exception("technology is null");
		if (civilization.getCurrentResearchTech()!=null) throw new Exception("you have to finnish researching your last technology first");
		if (civilization.hasTechnology(technology)) throw new Exception("you already have this technology");
		if (!technology.canBeResearchedBy(civilization)) throw new Exception("you dont have all prequisit techs");
		if (doAction){
			civilization.setCurrentResearchTech(technology);
		}
	}
	
	public void enterCheatCode(Civilization civilization, String cheatString, boolean doAction) throws Exception{
		if (cheatString==null) throw new Exception("cheatString is null");
		CheatCode cheatCode = null;
		try {
			cheatCode = CheatCode.valueOf(cheatString);
		} catch (Exception e) {
			throw new Exception("No Cheat Code found");
		}
		if (doAction){
			cheatCode.apply(civilization);
		}
	}


}
