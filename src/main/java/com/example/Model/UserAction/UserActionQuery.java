package com.example.Model.UserAction;

import com.example.Model.Game;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class UserActionQuery {
	private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@Expose private String username;
	@Expose private CityUserAction cityUserAction;
	@Expose private DiplomacyUserAction diplomacyUserAction;
	@Expose private CivilizationUserAction civUserAction;
	@Expose private UnitUserAction unitUserAction;

	public UserActionQuery(String username, CityUserAction cityUserAction, DiplomacyUserAction diplomacyUserAction, CivilizationUserAction civUserAction, UnitUserAction unitUserAction){
		this.username=username;
		this.cityUserAction=cityUserAction;
		this.diplomacyUserAction=diplomacyUserAction;
		this.civUserAction=civUserAction;
		this.unitUserAction=unitUserAction;
	}

	private User getUser(){
		return UserDatabase.getInstance().getUserByUsername(username);
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

	
	public String toJson(){
		return gson.toJson(this);
	}
	public static UserActionQuery fromJson(String json){
		return gson.fromJson(json, UserActionQuery.class);
	}

}
