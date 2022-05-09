package Models;


import DataBases.UserDataBase;

import java.util.HashMap;
import java.util.Map;

public class User {
	private static final HashMap<String, User> ALL_USERS = new HashMap<>();
	private final String username;
	private String nickname;
	private String password;
	private Civilization civilization;

	public User(String username, String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
		this.username = username;
		ALL_USERS.put(username, this);
		UserDataBase.addToDataBase(ALL_USERS);
	}

	public static boolean doesUsernameAndPasswordMatch(String password, String username) {
		return ALL_USERS.get(username).password.equals(password);
	}

	public static User getUserByUsername(String username) {
		return ALL_USERS.get(username);
	}

	public static boolean doesNicknameExist(String nickname) {
		for (Map.Entry<String, User> e : ALL_USERS.entrySet())
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

	public void changeNickname(String newNickname) { this.nickname = newNickname;}
	
	public String getUsername(){ return username;}

	public void setCivilization(Civilization civilization){ this.civilization=civilization;}
	public Civilization getCivilization(){ return this.civilization;}
	
}
