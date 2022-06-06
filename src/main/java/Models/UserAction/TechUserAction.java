package Models.UserAction;

import Models.Technology;

public class TechUserAction {
	private static UserAction createAction(String username, TechUserAction techUserAction){
		return new UserAction(username, null, null, techUserAction, null);
	}
	public static UserAction setResearch(String username, Technology technology){
		TechUserAction techUserAction = new TechUserAction();
		techUserAction.technology = technology;
		return createAction(username, techUserAction);
	}

	public Technology technology;
	
}
