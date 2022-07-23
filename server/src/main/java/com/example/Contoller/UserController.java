package com.example.Contoller;

import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;

public class UserController {
	private static UserController instance;
	public static UserController getInstance() {
		if (instance==null) instance = new UserController();
		return instance;
	}
	
	private User loggedInUser;

	public User getLoggedInUser(){ return loggedInUser; }
	public void setLoggedInUser(User user){ loggedInUser = user; }

	public void registerUser(String username, String password, String nickname) throws Exception {
		if (username.isEmpty() || password.isEmpty() || nickname.isEmpty()) throw new Exception("username, password, nickname cant be empty");
		if (!username.matches("[a-z0-9]+")) throw new Exception("username should consist of small letters or digits");
		if (UserDatabase.getInstance().getUserByUsername(username)!=null) throw new Exception("username already exist");
		UserDatabase.getInstance().addUser(new User(username, password, nickname));
	}
	public void loginUser(String username, String password) throws Exception {
		User user = UserDatabase.getInstance().getUserByUsername(username);
		if (user == null) throw new Exception("username does not exist");
		if (!user.isPasswordEqualTo(password)) throw new Exception("wrong password");
		loggedInUser = user;
	}

	public void changePassword(User user, String currentPassword, String newPassword) throws Exception {
		if (currentPassword.equals(newPassword)) throw new Exception("new password is same as old one");
		if (!user.isPasswordEqualTo(currentPassword)) throw new Exception("wrong password");
		user.setPassword(newPassword, currentPassword);
	}
}
