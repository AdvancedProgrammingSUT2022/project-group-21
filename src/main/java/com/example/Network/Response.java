package com.example.Network;

import com.google.gson.Gson;

// status_code==0: successful
// status_code==1: not successful

public class Response {
	private int status_code;
	private String message;
	private String output;

	public Response(int status_code, String message) {
		this.status_code = status_code;
		this.message = message;
	}
	
	public Response(int status_code, String message, String output) {
		this.status_code = status_code;
		this.message = message;
		this.output = output;
	}
	

	public int getStatus_code() {
		return status_code;
	}

	public String getMessage() {
		return message;
	}

	public String getOutput(){
		return output;
	}

	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static Response fromJson(String json){
		Gson gson = new Gson();
		return (Response) gson.fromJson(json, Response.class);
	}
	
}
