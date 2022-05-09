package View;

import Contoller.GameController;
import Contoller.SelectController;
import Contoller.UnitController;
import Enums.Message;
import Models.User;

import java.util.ArrayList;

public class GameMenu extends Menu{
	private static GameMenu instance;
	private GameController gameController;
	private UnitController unitController;
	private SelectController selectController;

	private GameMenu() {
		this.gameController = GameController.getInstance();
		this.unitController = UnitController.getInstance();
		this.selectController = SelectController.getInstance();
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
			gameController.startNewGame(users);
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

	/*private void taskHandler(String task) {
		String[] tokens = task.split(" ");
		int x,y;
		if (tokens[0].equals("Unit")) {
			switch (tokens[1]) {
				case "MOVETO":

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
					if (!isPositionValid(tokens[3])) {
						System.out.println(Message.INVALID_COMMAND);
						return;
					}
					x = extractPosition(tokens[3],"x");
					y = extractPosition(tokens[3], "y");
					if (tokens[2].equals("COMBAT")) {
						System.out.println(selectController.selectUnit(x, y, true));
						return;
					}
					if (tokens[2].equals("NONCOMBAT")) {
						System.out.println(selectController.selectUnit(x, y, false));
						return;
					}
					System.out.println(Message.INVALID_COMMAND);
					break;
				case "CITY":
					//select by name
					if (tokens[2].matches("[a-z]{2,}")) {
						System.out.println(selectController.selectCityByName(tokens[2]));
						return;
					}
					//select by coordination
					if (tokens[2].matches("\\d+,\\d+")) {
						x = extractPosition(tokens[2], "x");
						y = extractPosition(tokens[2], "y");
						System.out.println(
								selectController.selectCityByCoordination(x, y)
						);
						return;
					}
					System.out.println(Message.INVALID_COMMAND);
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
		if (tokens[0].equals("EXIT GAME")) {
			//TODO:
			return;
		}
		System.out.println(Message.INVALID_COMMAND);
	}*/

	private void taskHandler(String command) {
		GameMenuExtractor extractor = GameMenuExtractor.extractor(command);
		switch (extractor.getType()) {
			case INFO:
				//TODO:
				break;
			case SELECT_COMBAT_UNIT:
				//TODO:
				break;
			case SELECT_NONCOMBAT_UNIT:
				//TODO:
				break;
			case SELECT_CITY_BY_NAME:
				//TODO:
				break;
			case SELECT_CITY_BY_POSITION:
				//TODO:
				break;

	//UNITS
			case UNIT_MOVE:
				System.out.println(
						unitController.moveUnit(extractor.ARGS2.get("x"), extractor.ARGS2.get("y"))
				);
				return;
			case UNIT_SLEEP:
				System.out.println(unitController.sleep());
				return;
			case UNIT_ALERT:
				System.out.println(unitController.alert());
				break;
			case UNIT_FORTIFY:
				System.out.println(unitController.fortify(false));
				return;
			case UNIT_FORTIFY_AND_HEAL:
				System.out.println(unitController.fortify(true));
				return;
			case UNIT_GARRISON:
				System.out.println(unitController.garrison());
				return;
			case UNIT_SETUP_FOR_RANGED:
				System.out.println(unitController.setupForRangedAttack());
				return;
			case UNIT_ATTACK:
				System.out.println(
						unitController.attack(extractor.ARGS2.get("x"), extractor.ARGS2.get("y"))
				);
				return;
			case UNIT_FOUND_CITY:
				System.out.println(unitController.foundCity());
				return;
			case UNIT_CANCEL_MISSION:
				System.out.println(unitController.cancelMissions());
				return;
			case UNIT_WAKE:
				System.out.println(unitController.wake());
				return;
			case UNIT_DELETE:
				System.out.println(unitController.delete());
				return;
			case UNIT_BUILD_ROAD:
				System.out.println(unitController.buildRoad(true));
				return;
			case UNIT_BUILD_RAILROAD:
				System.out.println(unitController.buildRoad(false));
				return;
			case UNIT_BUILD_IMPROVEMENT:
				//TODO:
				break;
			case UNIT_REMOVE:
				//TODO:
				break;
			case UNIT_REMOVE_JUNGLE:
				//TODO;
				break;
			case UNIT_REPAIR:
				//TODO
				break;
	//MAP
			case MAP_MOVE:
				//TODO:
				break;
			case MAP_SHOW_BY_CITY_NAME:
				//TODO:
				break;
			case MAP_SHOW_BY_POSITION:
				//TODO:
				break;
			default:
				System.out.println(Message.INVALID_COMMAND);

		}
	}

	private boolean isPositionValid(String position) {
		return position.matches("\\d+,\\d+");
	}

	private int extractPosition(String position, String flag) {
		String[] tokens = position.split(",");
		if (flag.equals("x"))
			return Integer.parseInt(tokens[0]);
		return Integer.parseInt(tokens[1]);
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
