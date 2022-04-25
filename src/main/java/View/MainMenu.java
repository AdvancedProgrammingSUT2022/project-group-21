package View;

import Contoller.MainController;
import Enums.Message;

public class MainMenu extends Menu{
    private static MainMenu instance;
    private MainController controller;

    private MainMenu() {
        controller = MainController.getInstance();
    }
    private static void setInstance(MainMenu instance) {
        MainMenu.instance = instance;
    }
    public static MainMenu getInstance() {
        if (instance == null)      MainMenu.setInstance(new MainMenu());
        return instance;
    }
    private void logout() {
        System.out.println(controller.logout());
    }

    @Override
    public Menu run() {
        while (true){
            String command = getInput();
            if (command.equals("user logout") || command.equals("menu exit")) {
                logout();
                return RegisterMenu.getInstance();
            } else if (command.equals("menu show-current")) {
                showCurrentMenu();
            } else if (command.equals("menu enter Profile Menu"))
                return ProfileMenu.getInstance();
            else if (command.equals("menu enter Game Menu"))
                return GameMenu.getInstance();
            System.out.println(Message.INVALID_COMMAND);
        }
    }

    @Override
    protected void showCurrentMenu() {
        System.out.println("Main Menu");
    }

    @Override
    protected void enterMenu() {

    }
}
