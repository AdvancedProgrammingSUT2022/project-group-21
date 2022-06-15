package Models.UserAction;

import Models.Game;
import Models.User;

public class UserActionQuery {
	public String username;
	public CityUserAction cityUserAction;
	public DiplomacyUserAction diplomacyUserAction;
	public CivilizationUserAction civUserAction;
	public UnitUserAction unitUserAction;

	public UserActionQuery(String username, CityUserAction cityUserAction, DiplomacyUserAction diplomacyUserAction, CivilizationUserAction civUserAction, UnitUserAction unitUserAction){
		this.username=username;
		this.cityUserAction=cityUserAction;
		this.diplomacyUserAction=diplomacyUserAction;
		this.civUserAction=civUserAction;
		this.unitUserAction=unitUserAction;
	}

	private User getUser(){
		return User.getUserByUsername(username);
	}

	public void validate() throws Exception{
		User user = getUser();
		if (user==null) throw new Exception("user does not exist");
		if (!Game.getInstance().isUserPlayingGame(user)) throw new Exception("user exists, but is not playing this game");
		int ted=0;
		UserAction action = null;
		for (UserAction userAction : new UserAction[]{cityUserAction, diplomacyUserAction, civUserAction, unitUserAction}) {
			if (userAction!=null){
				action=userAction;
				ted++;
			}
		}
		if (ted!=1) throw new Exception("number of UserActions in query should is not 1");
		action.validateDoAction(user.getCivilization(), false);
	}

	public void doAction(){
		UserAction action = null;
		for (UserAction userAction : new UserAction[]{cityUserAction, diplomacyUserAction, civUserAction, unitUserAction}) {
			if (userAction!=null){
				action=userAction;
			}
		}
		try {
			action.validateDoAction(getUser().getCivilization(), true);
		} catch (Exception e) {
			// either I didnt validate or some shit bug exists
			e.printStackTrace();
		}
	}
}
