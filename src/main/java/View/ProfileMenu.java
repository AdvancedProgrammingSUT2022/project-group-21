package View;
import Contoller.UserController;

public class ProfileMenu extends Menu{
    private static ProfileMenu instance;
    private UserController controller;

    public ProfileMenu() {
        this.controller = UserController.getInstance();
    }

    private static void setInstance(ProfileMenu instance) {
        ProfileMenu.instance = instance;
    }
    static ProfileMenu getInstance() {
        if (instance == null)   ProfileMenu.setInstance(new ProfileMenu());
        return instance;
    }

    private void changeNickname(String command) {
        //TODO
    }
    private void changePassword(String newPassword) {
        //TODO
    }


    @Override
    public Menu run() {
        while (true) {

        }
    }

    @Override
    protected void showCurrentMenu() {

    }

    @Override
    protected void enterMenu() {

    }
}
