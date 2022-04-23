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
    private boolean doesUserNameExist (String username) {
        return User.getUserByUsername(username) != null;
    }
    public Message createUser(String username, String password, String nickname) {
        //TODO
        return null;
    }
    public Message loginUser(String username, String password) {
        //TODO
        return null;
    }
    public Message logout() {
        //TODO
        return null;
    }
    private Message handleChangeUsername(String username) {
        //TODO
        return null;
    }
    private Message handleChangeNickname(String nickname) {
        //TODO
        return null;
    }
}
