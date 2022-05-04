package View;

import Contoller.GameController;

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
		return null;
	}

	@Override
	protected void showCurrentMenu() {

	}

	@Override
	protected void enterMenu() {

	}
}
