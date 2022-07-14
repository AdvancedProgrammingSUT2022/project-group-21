package com.example.Model.UserAction;

import com.example.Model.CheatCode;
import com.example.Model.Civilization;
import com.example.Model.Technology;

public class CivilizationUserAction extends UserAction{
	private static UserActionQuery createAction(String username, CivilizationUserAction civUserAction){
		return new UserActionQuery(username, null, null, civUserAction, null);
	}
	public static UserActionQuery setResearch(String username, Technology technology){
		CivilizationUserAction civUserAction = new CivilizationUserAction();
		civUserAction.actionType = CivilizationActionType.RESEARCH;
		civUserAction.technology = technology;
		return createAction(username, civUserAction);
	}
	public static UserActionQuery enterCheatCode(String username, String cheatCode){
		CivilizationUserAction civUserAction = new CivilizationUserAction();
		civUserAction.actionType = CivilizationActionType.CHEAT;
		civUserAction.cheatCode = cheatCode;
		return createAction(username, civUserAction);
	}
	public static UserActionQuery endTurn(String username){
		CivilizationUserAction civUserAction = new CivilizationUserAction();
		civUserAction.actionType = CivilizationActionType.END_TURN;
		return createAction(username, civUserAction);
	}


	
	public CivilizationActionType actionType;
	public Technology technology;
	public String cheatCode;

	@Override
	public void validateDoAction(Civilization civilization, boolean doAction) throws Exception {
		if (actionType==null)
			throw new Exception("invalid query: actionType is null");
		
		// TODO Auto-generated method stub
		
	}

}
