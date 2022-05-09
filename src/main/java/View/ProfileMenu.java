package View;
import Contoller.UserController;
import Enums.InputCommand;
import Enums.Message;

public class ProfileMenu extends Menu{
	private static ProfileMenu instance;
	private final UserController controller;

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

	private void changeNickname(String newNickname) {
		Message message = controller.changeNickname(newNickname);
		if (message == Message.SUCCESS)
			System.out.println("nickname changed successfully!");
		else System.out.println("user with nickname " + newNickname + " already exists");
	}
	private void changePassword(String currentPassword, String newPassword) {
		Message message = controller.changePassword(currentPassword, newPassword);
		if (message == Message.SUCCESS)
			System.out.println("password changed successfully!");
		else if (message == Message.FAIL)
			System.out.println("current password is invalid");
		else System.out.println("please enter a new password");
	}


	@Override
	public Menu run() {
		while (true) {
			String command = getInput();
			switch (command) {
				case "menu enter Register Menu":
				case "menu enter Game Menu":
					System.out.println("menu navigation is not possible");
					break;
				case "menu enter Main Menu":
				case "menu exit":
					return MainMenu.getInstance();
				case "menu show-current":
					showCurrentMenu();
					continue;
			}
			CommandExtractor extractor = CommandExtractor.extractCommand(command);
			if (extractor == null) {
				System.out.println(Message.INVALID_COMMAND);
				continue;
			}
			InputCommand inputCommand = extractor.command;
			if (inputCommand == InputCommand.CHANGE_NICKNAME)
				changeNickname(extractor.args.get("nickname"));
			else if (inputCommand == InputCommand.CHANGE_PASSWORD)
				changePassword(extractor.args.get("currentPassword"), extractor.args.get("newPassword"));
		}

	}

	@Override
	protected void showCurrentMenu() {
		System.out.println("Profile Menu");
	}
}
