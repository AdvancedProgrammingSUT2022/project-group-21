package View;

import Contoller.GameController;
import Enums.Message;
import Models.User;

import java.util.ArrayList;

public class GameMenu extends Menu{
	private static GameMenu instance;
	private GameController controller;

	private GameMenu() {
		this.controller = GameController.getInstance();
	}
	static private void setInstance(GameMenu instance) {
		GameMenu.instance = instance;
	}
	static GameMenu getInstance() {
		if (instance == null)   GameMenu.setInstance(new GameMenu());
		return GameMenu.instance;
	}




	@Override
	public Menu run() {
		while (true){
			String command = getInput();
			if (command.equals("menu exit"))
				return MainMenu.getInstance();
			else if (command.startsWith("menu enter"))
				System.out.println("menu navigation is not possible");
			else if (command.equals("menu show-current"))
				System.out.println("Game Menu");
			else {
				CommandExtractor extractor = CommandExtractor.extractCommand(command);
				if (extractor == null) {
					System.out.println(Message.INVALID_COMMAND);
					continue;
				}
				ArrayList<User> users = usernameToUser(extractor.flags);
				controller.startNewGame(users);
			}
		}
	}

	private ArrayList<User> usernameToUser(ArrayList<String> usernames) {
		ArrayList<User> users = new ArrayList<>();
		for (String username : usernames)
			users.add(User.getUserByUsername(username));
		return users;
	}

	@Override
	protected void showCurrentMenu() {

	}

	@Override
	protected void enterMenu() {

	}
}
