package com.example.Network;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Model.UserAction.UserActionQuery;
import com.google.gson.Gson;

public class Request{
	private RequestType requestType;
	private String uuid;
	private HashMap<String, String> params;
	
	private Request(RequestType requestType) {
		this.requestType = requestType;
		this.params = new HashMap<>();
	}

	private void setParameter(String key, String value){
		params.put(key, value);
	}
	public String getParameter(String key){
		return params.get(key);
	}
	
	public RequestType getRequestType(){
		return requestType;
	}

	public void setUUID(String uuid){ this.uuid = uuid; }
	public String getUUID(){ return uuid; }

	
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	public static Request fromJson(String json){
		Gson gson = new Gson();
		return (Request) gson.fromJson(json, Request.class);
	}	
	


	public static Request loginRequest(String username, String password){
		Request request = new Request(RequestType.LOGIN);
		request.setParameter("username", username);
		request.setParameter("password", password);
		return request;
	}
	
	public static Request registerRequest(String username, String password, String nickname){
		Request request = new Request(RequestType.REGISTER);
		request.setParameter("username", username);
		request.setParameter("password", password);
		request.setParameter("nickname", nickname);
		return request;
	}
	
	public static Request startGameRequest(long seed, ArrayList<String> usernames){
		Request request = new Request(RequestType.START_GAME);
		request.setParameter("seed", Long.toString(seed));
		request.setParameter("usernamesJson", new Gson().toJson(usernames));
		return request;
	}
	
	public static Request joinGameRequest(){
		Request request = new Request(RequestType.JOIN_GAME);
		return request;
	}
	
	public static Request gameActionRequest(UserActionQuery query){
		Request request = new Request(RequestType.GAME_ACTION);
		String queryJson = query.toJson();
		request.setParameter("queryJson", queryJson);
		return request;
	}
	public static Request getActionsRequest(){
		Request request = new Request(RequestType.GET_ACTIONS);
		return request;
	}

}
