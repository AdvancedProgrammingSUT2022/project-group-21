package View;

import Contoller.MainController;
import Enums.Message;

public class MainMenu extends Menu{
	private static MainMenu instance;
	private final MainController controller;

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
			switch (command) {
				case "user logout":
				case "menu exit":
					logout();
					return RegisterMenu.getInstance();
				case "menu show-current":
					showCurrentMenu();
					continue;
				case "menu enter Profile Menu":
					return ProfileMenu.getInstance();
				case "menu enter Game Menu":
					return GameMenu.getInstance();
			}
			System.out.println(Message.INVALID_COMMAND);
		}
	}

	@Override
	protected void showCurrentMenu() {
		System.out.println("Main Menu");
	}
}
