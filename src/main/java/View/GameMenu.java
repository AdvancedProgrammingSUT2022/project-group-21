package View;

import Contoller.GameController;
import Enums.Message;
import Models.User;
import com.sun.org.apache.bcel.internal.generic.Select;
import javafx.scene.layout.BackgroundRepeat;

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
		while (true) {
			String command = getInput();
			if (command.equals("menu exit"))
				return MainMenu.getInstance();
			if (command.startsWith("menu enter")) {
				System.out.println("menu navigation is not possible");
				continue;
			}
			if (command.equals("menu show-current")) {
				System.out.println("Game Menu");
				continue;
			}
			CommandExtractor extractor = CommandExtractor.extractCommand(command);
			if (extractor == null) {
				System.out.println(Message.INVALID_COMMAND);
				continue;
			}
			ArrayList<User> users = usernameToUser(extractor.flags);
			controller.startNewGame(users);
            startGame();

		}
	}

    private void startGame() {
        while (true) {
            //TODO: show map
            String command = getInput();
			taskHandler(command);
        }
    }

	private void taskHandler(String task) {
		String[] tokens = task.split(" ");
		if (tokens[0].equals("Unit")) {
			switch (tokens[1]) {
				case "MOVETO":
					//TODO: move unit to the position
					break;
				case "SLEEP":
					//TODO: make the unit sleep
					break;
				case "ALERT":
					//TODO:
					break;
				case "FORTIFY":
					//TODO:
					break;
				case "GARRISON":
					break;
				case "SETUP":
					// TODO:
					break;
				case "ATTACK":
					// TODO:
					break;
				case "FOUND":
					//TODO:
					break;
				case "CANCLE":
					//TODO:
					break;
				case "WAKE":
					//TODO:
					break;
				case "DELETE":
					//TODO:
					break;
				case "BUILD":
					//TODO:
					switch (tokens[2]) {
						case "ROAD":
							//TODO:
							break;
						case "RAILROAD":
							//TODO:
							break;
						case "FARM":
							//TODO:
							break;
						case "MINE":
							//TODO:
							break;
						case "TRAIDINGPOST":
							//TODO:
							break;
					}
					break;
				case "REMOVE":
					//TODO:
					break;
				case "REPAIR":
					//TODO:
					break;
				default:
					System.out.println(Message.INVALID_COMMAND);
					break;
			}
			return;
		}
		if (tokens[0].equals("INFO")) {
			switch (tokens[1]) {
				case "RESEARCH":
					//TODO:
					break;
				case "UNITS":
					//TODO:
					break;
				case "CITIES":
					//TODO:
					break;
				case "DIPLOMACY":
					//TODO:
					break;
				case "VICTORY":
					//TODO:
					break;
				case "DEMOGRAPHICS":
					//TODO:
					break;
				case "NOTIFICATIONS":
					//TODO:
					break;
				case "MILITARY":
					//TODO:
					break;
				case "ECONOMIC":
					//TODO:
					break;
				case "DIPLOMATIC":
					//TODO:
					break;
				case "DEALS":
					//TODO:
					break;
				default:
					System.out.println(Message.INVALID_COMMAND);
					break;
			}
			return;
		}
		if (tokens[0].equals("SELECT")) {
			switch (tokens[1]) {
				case "UNIT":
					//TODO:
					break;
				case "CITY":
					//TODO:
					break;
				default:
					System.out.println(Message.INVALID_COMMAND);
					break;
			}
			return;
		}
		if (tokens[0].equals("MAP")) {
			switch (tokens[1]) {
				case "SHOW":
					//TODO:
					break;
				case "MOVE":
					//TODO:
					break;
				default:
					System.out.println(Message.INVALID_COMMAND);
					break;
			}
			return;
		}
		System.out.println(Message.INVALID_COMMAND);
	}

	private ArrayList<User> usernameToUser(ArrayList<String> usernames) {
		ArrayList<User> users = new ArrayList<>();
		for (String username : usernames)
			users.add(User.getUserByUsername(username));
		return users;
	}

	@Override
	protected void showCurrentMenu() {
		System.out.println("Game Menu");
	}
}
