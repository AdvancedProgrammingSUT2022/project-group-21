package com.example.Model.UserAction;

import com.example.Model.Civilization;

public class DiplomacyUserAction extends UserAction{
	
	// for now, diplomacy is just declaring war
	public static UserActionQuery declareWar(String username1, String username2){
		DiplomacyUserAction diplomacyUserAction = new DiplomacyUserAction();
		diplomacyUserAction.username2=username2;
		return new UserActionQuery(username1, null, diplomacyUserAction, null, null);
	}

	public String username2; // second players username

	@Override
	public void validateDoAction(Civilization civilization, boolean doAction) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
