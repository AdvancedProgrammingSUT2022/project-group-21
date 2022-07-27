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
	
	private static final String fileName = "users.json";
	private Gson gson;
	
	@Expose private ArrayList<User> allUsers = new ArrayList<>();
	
	
	private void initialize(){
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		loadUsersFromFile();
	}

	private void writeToFile(String text) throws FileNotFoundException{
		File file = new File(fileName);
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.write(text);
		printWriter.close();
	}
	private String readFromFile() throws IOException{
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		String text = new String(inputStream.readAllBytes());
		inputStream.close();
		return text;
	}

	private void loadUsersFromFile(){
		try {
			String json = readFromFile();
			allUsers = gson.fromJson(json, UserDatabase.class).allUsers;
		} catch (IOException e) {
			System.out.println("error: file users.json not found");
		}
		if (allUsers==null) allUsers = new ArrayList<>();

	}
	private void saveUsersToFile(){
		try {
			System.out.println(this.allUsers.size());
			writeToFile(gson.toJson(this, UserDatabase.class));
		} catch (FileNotFoundException e) {
			System.out.println("error: cant write to file users.json");
			e.printStackTrace();
		}
	}

	public synchronized void addUser(User user){
		System.out.println("Added User" + user.getUsername());
		allUsers.add(user);
		saveUsersToFile();
	}
	
	public User getUserByUsername(String username) {
		for (User user : allUsers) {
			if (user.getUsername().equals(username))
				return user;
		}
		return null;
	}

	public ArrayList<User> getUserList() {
		return allUsers;
	}
	

}
