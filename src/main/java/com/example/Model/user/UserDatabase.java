package com.example.Model.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserDatabase {
	private static UserDatabase instance;
	public static UserDatabase getInstance(){
		if (instance==null){
			synchronized(UserDatabase.class){
				if (instance==null) instance = new UserDatabase();
			}
		}
		return instance;
	}

	private static final String fileName = "users.json";
	private Gson gson;
	private HashMap<String, User> allUsers;

	private UserDatabase(){
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
			allUsers = (HashMap<String, User>) gson.fromJson(json, HashMap.class);
		} catch (IOException e) {
			System.out.println("error: file users.json not found");
			allUsers = new HashMap<>();
		}
	}
	private void saveUsersToFile(){
		try {
			writeToFile(gson.toJson(allUsers));
		} catch (FileNotFoundException e) {
			System.out.println("error: cant write to file users.json");
			e.printStackTrace();
		}
	}

	public synchronized void addUser(User user){
		allUsers.put(user.getUsername(), user);
		saveUsersToFile();
	}
	
	public User getUserByUsername(String username) {
		return allUsers.get(username);
	}

	public ArrayList<User> getUserList() {
		ArrayList<User> users = new ArrayList<>();
	
		for (Map.Entry<String, User> stringUserEntry : allUsers.entrySet())
			users.add(stringUserEntry.getValue());
	
		return users;
	}
	

}
