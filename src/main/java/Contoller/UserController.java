package Contoller;

import Enums.Message;
import Models.User;

public class UserController {
	private static User loggedInUser;
	private static UserController instance = null;

	private static void setInstance(UserController instance) {
		UserController.instance = instance;
	}
	public static UserController getInstance() {
		if (UserController.instance == null)    UserController.setInstance(new UserController());
		return UserController.instance;
	}


	public static User getLoggedInUser() {
		return UserController.loggedInUser;
	}
	public static void setLoggedInUser(User loggedInUser) {
		UserController.loggedInUser = loggedInUser;
	}

	private boolean doesUserNameExist (String username) {
		return User.getUserByUsername(username) != null;
	}
	private boolean doesNickNameExist (String nickname) {
		return User.doesNicknameExist(nickname);
	}
	public Message createUser(String username, String password, String nickname) {
		if (doesUserNameExist(username))
			return Message.USERNAME_EXISTS;
		if (doesNickNameExist(nickname))
			return Message.NICKNAME_EXISTS;
		new User(username, password, nickname);
		return Message.SIGNUP_SUCCESS;
	}
	public Message loginUser(String username, String password) {
		if (!doesUserNameExist(username)){
			return Message.LOGIN_FAIL;
		}
		if (!User.doesUsernameAndPasswordMatch(password, username)){
			System.out.println("shit");
			return Message.LOGIN_FAIL;
		}
		loggedInUser = User.getUserByUsername(username);
		return Message.LOGIN_SUCCESS;
	}

	public Message changeNickname(String nickname) {
		if (!doesNickNameExist(nickname))
			return Message.FAIL;
		loggedInUser.changeNickname(nickname);
		return Message.SUCCESS;
	}
	public Message changePassword(String current, String newP) {
		if (!User.doesUsernameAndPasswordMatch(current, loggedInUser.getUsername()))
			return Message.FAIL;
		else if (current.equals(newP))
			return null;
		loggedInUser.changePassword(newP, current);
		return Message.SUCCESS;
	}
}
