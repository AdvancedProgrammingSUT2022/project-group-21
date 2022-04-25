package Contoller;

import Enums.Message;

public class MainController {
    private static MainController instance;
    private static void setInstance(MainController instance) {
        MainController.instance = instance;
    }
    public static MainController getInstance() {
        if (MainController.instance == null)        MainController.setInstance(new MainController());
        return MainController.instance;
    }

    public Message logout() {
        UserController.setLoggedInUser(null);
        return Message.LOGOUT_SUCCESS;
    }
}
