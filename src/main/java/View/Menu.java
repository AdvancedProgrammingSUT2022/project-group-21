package View;

import java.util.Scanner;

public abstract class Menu {
    private static final Scanner scanner = new Scanner(System.in);


    protected static Scanner getScanner() {
        return scanner;
    }
    protected String getInput() {
        return scanner.nextLine();
    }
    public abstract Menu run();
    protected abstract void showCurrentMenu();
    protected abstract void enterMenu();
}
