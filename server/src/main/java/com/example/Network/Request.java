package com.example.Network;

import java.util.HashMap;

import com.google.gson.Gson;

public class Request{
	private RequestType requestType;
	private String uuid;
	private HashMap<String, String> params;
	
	public Request(RequestType requestType, String uuid) {
		this.requestType = requestType;
		this.uuid = uuid;
		this.params = new HashMap<>();
	}

	public void setParameter(String key, String value){
		params.put(key, value);
	}


	public RequestType getRequestType(){
		return requestType;
	}

	public String getUUID(){
		return uuid;
	}

	public String getParameter(String key){
		return params.get(key);
	}
	
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static Request fromJson(String json){
		Gson gson = new Gson();
		return (Request) gson.fromJson(json, Request.class);
	}	
	
}
