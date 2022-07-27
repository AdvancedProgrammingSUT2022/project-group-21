package com.example.Model.UserAction;

import com.example.Model.Civilization;

public abstract class UserAction {
	public abstract void validateDoAction(Civilization civilization, boolean doAction) throws Exception;

}
