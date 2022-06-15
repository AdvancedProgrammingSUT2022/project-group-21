package Models.UserAction;

import Models.Civilization;

public abstract class UserAction {
	public abstract void validateDoAction(Civilization civilization, boolean doAction) throws Exception;

}
