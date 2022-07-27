package com.example.Model.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class UserDatabase {
	private static UserDatabase instance;
	public static UserDatabase getInstance(){
		if (instance==null){
			synchronized(UserDatabase.class){
				if (instance==null){
					instance = new UserDatabase();
					instance.initialize();
				}
			}
		}
		return instance;
	}

	private UserDatabase(){
	}
	

	private Gson gson;
	
	@Expose private ArrayList<User> allUsers = new ArrayList<>();
	
	
	private void initialize(){
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}


	public synchronized void addUser(User user){
		allUsers.add(user);
	}
	
	public User getUserByUsername(String username) {
		for (User user : allUsers) {
			if (user.getUsername().equals(username))
				return user;
		}
		addUser(new User(username, username));
		return getUserByUsername(username);
	}

	public ArrayList<User> getUserList() {
		return allUsers;
	}
	

}
