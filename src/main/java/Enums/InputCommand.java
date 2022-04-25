package Enums;

public enum InputCommand{
	// TODO
	USER_CREATE("user create", new String[][]{
		{"username", "[a-zA-Z0-9_]+"},
		{"password", "[a-zA-Z0-9_]{8, 32}"},
		{"nickname", "[a-zA-Z0-9_ ]+"},
	}),
	USER_LOGIN("user login", new String[][]{
			{"username", "\\S+"},
			{"password", "\\S+"},
	}),
	CHANGE_NICKNAME("profile change", new String[][]{
			{"nickname", "[a-zA-Z0-9_ ]+"}
	}),
	CHANGE_PASSWORD("profile change --password", new String[][]{
			{"current password", "\\S+"},
			{"new password", "[a-zA-Z0-9_]{8, 32}"}
	})
	;


	public final String command;
	public final String[][] args;
	
	private InputCommand(String command, String[][] args){
		this.command=command;
		this.args=args;
	}
}
