package Models;


import DataBases.UserDataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
	private static HashMap<String, User> allUsers = new HashMap<>();
	private String username;
	private String password;
	private String nickname;
	private Civilization civilization;

	public User(String username, String password, String nickname) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		allUsers.put(username, this);
		UserDataBase.addToDataBase(allUsers);
	}

	public static boolean doesUsernameAndPasswordMatch(String password, String username) {
		User user = allUsers.get(username);
		// System.out.println("real password: " + user.password + " given password: " + password);
		return user.password.equals(password);
	}

	public static User getUserByUsername(String username) {
		return allUsers.get(username);
	}

	public static boolean doesNicknameExist(String nickname) {
		for (Map.Entry<String, User> e : allUsers.entrySet())
			if (e.getValue().getNickname().equals(nickname))    return true;
		return false;
	}

	public String getNickname() {
		return nickname;
	}

	public void changePassword(String newPassword, String currentPassword) {
		if (password.equals(currentPassword))
			password = newPassword;
	}
	public static ArrayList<User> getUserList() {
		ArrayList<User> users = new ArrayList<>();

		for (Map.Entry<String, User> stringUserEntry : allUsers.entrySet())
			users.add(stringUserEntry.getValue());

		return users;
	}

	public void changeNickname(String newNickname) { this.nickname = newNickname;}
	
	public String getUsername(){ return username;}

	public void setCivilization(Civilization civilization){ this.civilization=civilization;}
	public Civilization getCivilization(){ return this.civilization;}
	
}
