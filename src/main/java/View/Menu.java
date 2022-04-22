package View;

import java.util.Scanner;

abstract class Menu {
    private static final Scanner scanner = new Scanner(System.in);


    protected static Scanner getScanner() {
        return scanner;
    }
    protected String getInput() {
        return scanner.nextLine();
    }
    public abstract void run();
    protected abstract void showCurrentMenu();
    protected abstract void enterMenu();
}
