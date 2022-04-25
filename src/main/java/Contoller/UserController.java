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
        User newUser = new User(username, password, nickname);
        return Message.SIGNUP_SUCCESS;
    }
    public Message loginUser(String username, String password) {
        if (!doesUserNameExist(username))
            return Message.LOGIN_FAIL;
        if (!User.doesUsernameAndPasswordMatch(password, username))
            return Message.LOGIN_FAIL;
        loggedInUser = User.getUserByUsername(username);
        return Message.LOGIN_SUCCESS;
    }

    private Message changeNickname(String username) {
        //TODO
        return null;
    }
    private Message handleChangeNickname(String nickname) {
        //TODO
        return null;
    }
}
