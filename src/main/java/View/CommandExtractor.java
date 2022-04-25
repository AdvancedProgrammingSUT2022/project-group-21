package View;

import Enums.InputCommand;
import Enums.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandExtractor {
	InputCommand command;
	HashMap<String, String> args;
	ArrayList<String> flags;


	public static CommandExtractor extractCommand(String input){
		return null;
	}
}
