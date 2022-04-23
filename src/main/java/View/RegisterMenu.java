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
        if (RegisterMenu.instance == null)      RegisterMenu.setInstance(new RegisterMenu());
        return RegisterMenu.instance;
    }

    private void createUser(String command) {

    }
    private void login(String command) {}
    private void logout() {}

    @Override
    public void run() {
        while (true) {
            String command = getInput();
            if (command.contains("menu enter"))             System.out.println("please login first");
            else if (command.equals("menu exit"))           System.exit(0);
            else if (command.equals("menu show-current"))   System.out.println("Login Menu");
        }
    }

    @Override
    protected void showCurrentMenu() {

    }

    @Override
    protected void enterMenu() {

    }
}
