package View;

import Contoller.UserController;
import Enums.InputCommand;
import Enums.Message;

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
	void login(String username, String password) {
		System.out.println(controller.loginUser(username, password));
	}


	@Override
	public Menu run() {
		while (true) {
			String command = getInput();
			if (command.matches("menu enter (Main Menu)|(Play Game Menu)|(Profile Menu)")) {
				System.out.println("please login first");
				continue;
			}
			else if (command.equals("menu show-current")) {
				showCurrentMenu();
				continue;
			}
			else if (command.equals("menu exit"))    return null;
			CommandExtractor extractor = CommandExtractor.extractCommand(command);
			if (extractor == null) {
				System.out.println(Message.INVALID_COMMAND);
				continue;
			}
			InputCommand inputCommand = extractor.command;
			if (inputCommand == InputCommand.USER_CREATE) {
				createUser(extractor.args.get("username"), extractor.args.get("nickname"), extractor.args.get("password"));
				continue;
			}
			if (inputCommand == InputCommand.USER_LOGIN) {
				login(extractor.args.get("username"), extractor.args.get("password"));
				if (UserController.getLoggedInUser() != null)
					return MainMenu.getInstance();
			}
		}
	}

	@Override
	protected void showCurrentMenu() {
		System.out.println("Login Menu");
	}
}
