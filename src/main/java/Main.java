import View.*;


public class Main {
	public static void main(String[] args) {
		Menu menu = RegisterMenu.getInstance();
		while (menu != null) {
			menu = menu.run();
		}
	}
}
