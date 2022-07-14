package com.example.Model.UserAction;

import com.example.Model.Civilization;
import com.example.Model.Technology;

public class CivilizationUserAction extends UserAction{
	private static UserActionQuery createAction(String username, CivilizationUserAction techUserAction){
		return new UserActionQuery(username, null, null, techUserAction, null);
	}
	public static UserActionQuery setResearch(String username, Technology technology){
		CivilizationUserAction techUserAction = new CivilizationUserAction();
		techUserAction.technology = technology;
		return createAction(username, techUserAction);
	}
	
	// TODO: not just research; add end-turn;

	public Technology technology;

	@Override
	public void validateDoAction(Civilization civilization, boolean doAction) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
