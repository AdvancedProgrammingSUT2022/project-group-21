package View;

import java.util.Scanner;

public abstract class Menu {
    private final Scanner scanner = new Scanner(System.in);


    protected String getInput() {
        return scanner.nextLine();
    }
    public abstract Menu run();
    protected abstract void showCurrentMenu();
    protected abstract void enterMenu();
}
