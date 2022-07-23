package com.example.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

public class GameHistory {
	private static String fileName = "saved-game.json";
	private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

	@Expose public int width;
	@Expose public int height;
	@Expose public ArrayList<User> players;
	@Expose public long seed;
	@Expose public ArrayList<UserActionQuery> allActions;

	public GameHistory(int width, int height, ArrayList<User> players, long seed) {
		this.width = width;
		this.height = height;
		this.players = players;
		this.seed = seed;
		allActions = new ArrayList<>();
	}

	public void addAction(UserActionQuery query){
		allActions.add(query);
	}

	public void loadGame(){
		new Game(width, height, players, seed);
		for (UserActionQuery query : allActions) {
			query.doAction();
		}
	}


	
	private static void writeToFile(String text) throws FileNotFoundException{
		File file = new File(fileName);
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.write(text);
		printWriter.close();
	}

	private static String readFromFile() throws IOException{
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		String text = new String(inputStream.readAllBytes());
		inputStream.close();
		return text;
	}

	public void saveOnFile(){
		try {
			writeToFile(gson.toJson(this));
		} catch (FileNotFoundException e) {
			System.out.println("Error saving game on file: cant write on file");
			e.printStackTrace();
		}
	}
	public static void loadFromFile(){
		GameHistory gameHistory;
		try {
			gameHistory = gson.fromJson(readFromFile(), GameHistory.class);
		} catch (JsonSyntaxException | IOException e) {
			System.out.println("Error loading game");
			e.printStackTrace();
			return ;
		}
		gameHistory.loadGame();
	}


}
