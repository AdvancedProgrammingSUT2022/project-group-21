package View;

import Contoller.UserController;
import Enums.Message;
import Models.User;

public class RegisterMenu extends Menu{
    private static RegisterMenu instance = null;
    private final UserController controller;

    private RegisterMenu() {
        this.controller = UserController.getInstance();
    }
    private static void setInstance(RegisterMenu instance) {
        RegisterMenu.instance = instance;
    }
    public static RegisterMenu getInstance() {
        if (RegisterMenu.instance == null)  RegisterMenu.setInstance(new RegisterMenu());

        return RegisterMenu.instance;
    }

    void createUser(String username, String nickname, String password) {
        System.out.println(controller.createUser(username, password, nickname));
    }
    void login(String username, String password) {}
    private void logout() {}

    @Override
    public Menu run() {
        while (true) {
            String command = getInput();
            if (command.contains("menu enter")) {
                enterMenu();
                continue;
            }
            else if (command.equals("menu show-current")) {
                showCurrentMenu();
                continue;
            }
            else if (command.equals("menu exit"))    return null;
            Message message = CommandExtractor.registerAnalyse(command);
            if (message == Message.LOGIN_MATCHED &&
                    UserController.getLoggedInUser() != null)
                break;
            System.out.println(Message.INVALID_COMMAND);
        }
        return MainMenu.getInstance();
    }

    @Override
    protected void showCurrentMenu() {
        System.out.println("Login Menu");
    }

    @Override
    protected void enterMenu() {
        System.out.println("please login first");
    }
}
