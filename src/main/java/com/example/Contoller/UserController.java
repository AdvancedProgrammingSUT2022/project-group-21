package com.example.Contoller;

import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;
import com.example.Network.NetworkController;
import com.example.Network.Request;
import com.example.Network.RequestType;
import com.example.Network.Response;

public class UserController {
	private static UserController instance;
	private synchronized static void setInstance(){
		if (instance==null) instance = new UserController();
	}
	public static UserController getInstance() {
		if (instance==null) setInstance();
		return instance;
	}
	
	private User loggedInUser;

	public User getLoggedInUser(){ return loggedInUser; }

	public void registerUser(String username, String password, String nickname) throws Exception {
		if (username.isEmpty() || password.isEmpty() || nickname.isEmpty()) throw new Exception("username, password, nickname cant be empty");
		if (!username.matches("[a-z0-9]+")) throw new Exception("username should consist of small letters or digits");
		// if (UserDatabase.getInstance().getUserByUsername(username)!=null) throw new Exception("username already exist");
		// UserDatabase.getInstance().addUser(new User(username, password, nickname));
		Request request = Request.loginRequest(username, password);
		Response response = NetworkController.makeQuery(request);
		if (response.getStatus_code()!=0) throw new Exception(response.getMessage());
		loggedInUser = response.getOutput(); // TODO
	}
	public void loginUser(String username, String password) throws Exception {
		User user = UserDatabase.getInstance().getUserByUsername(username);
		if (user == null) throw new Exception("username does not exist");
		// if (!user.isPasswordEqualTo(password)) throw new Exception("wrong password");
		loggedInUser = user;
	}
}
