package Enums;

public enum InputCommand{
	// TODO
	USER_CREATE("user create", new String[][]{
		{"username", "[a-zA-Z0-9_]+"},
		{"password", "[a-zA-Z0-9_]{8, 32}"},
		{"nickname", "[a-zA-Z0-9_ ]+"},
	}),

	;


	public final String command;
	public final String[][] args;
	
	private InputCommand(String command, String[][] args){
		this.command=command;
		this.args=args;
	}
}
