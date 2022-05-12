package View;

import Enums.InputCommand;

import java.util.HashMap;
import java.util.regex.Matcher;

public class CommandExtractor {
	public InputCommand command;
	public HashMap<String, String> args;

	private CommandExtractor(InputCommand inputCommand, Matcher matcher){
		this.command=inputCommand;
		this.args=new HashMap<>();
		for (String[] arg : inputCommand.args) {
			args.put(arg[0], matcher.group(arg[0]));
		}
	}

	public static CommandExtractor extractCommand(String input){
		for (InputCommand inputCommand : InputCommand.values()){
			if (!input.startsWith(inputCommand.command)) continue ;
			Matcher matcher=inputCommand.pattern.matcher(input);
			if (matcher.matches()) return new CommandExtractor(inputCommand, matcher);
		}
		return null;
	}
}

