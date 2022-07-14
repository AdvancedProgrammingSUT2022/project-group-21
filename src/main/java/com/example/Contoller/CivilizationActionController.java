package com.example.Contoller;

import com.example.Model.CheatCode;
import com.example.Model.Civilization;
import com.example.Model.Technology;

public class CivilizationActionController {
	private static CivilizationActionController instance;
	public static CivilizationActionController getInstance(){
		if (instance==null) instance=new CivilizationActionController();
		return instance;
	}

	public void endTurn(Civilization civilization, boolean doAction) throws Exception{
		// TODO: check everything is done
		if (doAction){
			// TODO
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
