package com.example.Model.UserAction;

import com.example.Contoller.CivilizationActionController;
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
	public static UserActionQuery enterCheatCode(String username, String cheatString){
		CivilizationUserAction civUserAction = new CivilizationUserAction();
		civUserAction.actionType = CivilizationActionType.CHEAT;
		civUserAction.cheatString = cheatString;
		return createAction(username, civUserAction);
	}
	public static UserActionQuery endTurn(String username){
		CivilizationUserAction civUserAction = new CivilizationUserAction();
		civUserAction.actionType = CivilizationActionType.END_TURN;
		return createAction(username, civUserAction);
	}


	
	public CivilizationActionType actionType;
	public Technology technology;
	public String cheatString;

	@Override
	public void validateDoAction(Civilization civilization, boolean doAction) throws Exception {
		if (actionType==null)
			throw new Exception("invalid query: actionType is null");
		
		switch (actionType) {
			case CHEAT:
				CivilizationActionController.getInstance().enterCheatCode(civilization, cheatString, doAction);
				break;
			case RESEARCH:
				CivilizationActionController.getInstance().setResearch(civilization, technology, doAction);
				break;
			case END_TURN:
				CivilizationActionController.getInstance().endTurn(civilization, doAction);
				break;
			default:
				break;
		}
	}

}
