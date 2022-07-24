package com.example.Contoller;

import java.util.HashMap;

import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;

public class UserController {
	private static UserController instance;
	private static synchronized void setInstance(){
		if (instance==null) instance = new UserController();
	}
	public static UserController getInstance() {
		if (instance==null) setInstance();
		return instance;
	}

	private HashMap<String, User> uuids = new HashMap<>();

	public User getUserByUUID(String uuid){
		return uuids.get(uuid);
	}
	
	public void registerUser(String username, String password, String nickname) throws Exception {
		if (username.isEmpty() || password.isEmpty() || nickname.isEmpty()) throw new Exception("username, password, nickname cant be empty");
		if (!username.matches("[a-z0-9]+")) throw new Exception("username should consist of small letters or digits");
		if (UserDatabase.getInstance().getUserByUsername(username)!=null) throw new Exception("username already exist");
		UserDatabase.getInstance().addUser(new User(username, password, nickname)); // Thread lock in here
	}
	public synchronized void loginUser(String username, String password, String uuid) throws Exception {
		User user = UserDatabase.getInstance().getUserByUsername(username);
		if (user == null) throw new Exception("username does not exist");
		if (!user.isPasswordEqualTo(password)) throw new Exception("wrong password");
		uuids.put(uuid, user);
	}
	public synchronized void logout(String uuid) throws Exception{
		User user = getUserByUUID(uuid);
		if (user==null) throw new Exception("you are not logged in!"); // TODO: maybe remove this
		uuids.remove(uuid);
	}

	// public void changePassword(User user, String currentPassword, String newPassword) throws Exception {
	// 	if (currentPassword.equals(newPassword)) throw new Exception("new password is same as old one");
	// 	if (!user.isPasswordEqualTo(currentPassword)) throw new Exception("wrong password");
	// 	user.setPassword(newPassword, currentPassword);
	// }
}
