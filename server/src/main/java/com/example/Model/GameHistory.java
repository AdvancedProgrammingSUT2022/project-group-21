package com.example.Model;

import java.util.ArrayList;

import com.example.Model.UserAction.UserActionQuery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class GameHistory {
	private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@Expose public int width;
	@Expose public int height;
	@Expose public ArrayList<String> usernames;
	@Expose public long seed;
	@Expose public ArrayList<UserActionQuery> allActions;

	public GameHistory(int width, int height, ArrayList<String> usernames, long seed) {
		this.width = width;
		this.height = height;
		this.usernames = usernames;
		this.seed = seed;
		allActions = new ArrayList<>();
	}

	public void addAction(UserActionQuery query){
		allActions.add(query);
	}

	
	public String toJson(){
		return gson.toJson(this);
	}
	public static GameHistory gameHistory(String json){
		return gson.fromJson(json, GameHistory.class);
	}


}
