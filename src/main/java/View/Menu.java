package View;

import java.util.Scanner;

public abstract class Menu {
    private static final Scanner scanner = new Scanner(System.in);


    protected static Scanner getScanner() {
        return scanner;
    }
    protected String getInput(){
		String input=scanner.nextLine();
		String S[]=input.split("\\s");
		String T="";
		for (String string : S) if (string.length()>0){
			if (T.length()>0) T+=" ";
			T+=string;
		}
		return T;
	}
    public abstract Menu run();
    protected abstract void showCurrentMenu();
    protected abstract void enterMenu();
}
