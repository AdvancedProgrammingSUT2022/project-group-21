package Models.UserAction;

public class DiplomacyUserAction {
	
	// for now, diplomacy is just declaring war
	public static UserAction declareWar(String username1, String username2){
		DiplomacyUserAction diplomacyUserAction = new DiplomacyUserAction();
		diplomacyUserAction.username2=username2;
		return new UserAction(username1, null, diplomacyUserAction, null, null);
	}

	public String username2; // second players username
}
