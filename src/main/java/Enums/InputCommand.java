package Enums;

import java.util.regex.Pattern;

public enum InputCommand{
	// TODO
	USER_CREATE("user create", new String[][]{
		{"username", "u", "[a-zA-Z0-9_]+"},
		{"password", "p", "[a-zA-Z0-9_]{8, 32}"},
		{"nickname", "n", "[a-zA-Z0-9_ ]+"},
	}),
	USER_LOGIN("user login", new String[][]{
			{"username", "u", "\\S+"},
			{"password", "p", "\\S+"},
	}),
	CHANGE_NICKNAME("profile change", new String[][]{
			{"nickname", "n", "[a-zA-Z0-9_ ]+"}
	}),
	CHANGE_PASSWORD("profile change --password", new String[][]{
			{"current_password", "c", "\\S+"},
			{"new_password", "n", "[a-zA-Z0-9_]{8, 32}"}
	}),
	PLAY_WITH_2_PLAYER("play game", new String[][]{
			{"player1", "p1", "[a-zA-Z0-9_]+"},
			{"player2", "p2", "[a-zA-Z0-9_]+"}
	}),
	PLAY_WITH_3_PLAYER("play game", new String[][]{
			{"player1", "p1", "[a-zA-Z0-9_]+"},
			{"player2", "p2", "[a-zA-Z0-9_]+"},
			{"player3", "p3", "[a-zA-Z0-9_]+"}
	}),
	PLAY_WITH_4_PLAYER("play game", new String[][]{
			{"player1", "p1", "[a-zA-Z0-9_]+"},
			{"player2", "p2", "[a-zA-Z0-9_]+"},
			{"player3", "p3", "[a-zA-Z0-9_]+"},
			{"player4", "p4", "[a-zA-Z0-9_]+"}
	}),
	PLAY_WITH_5_PLAYER("play game", new String[][]{
			{"player1", "p1", "[a-zA-Z0-9_]+"},
			{"player2", "p2", "[a-zA-Z0-9_]+"},
			{"player3", "p3", "[a-zA-Z0-9_]+"},
			{"player4", "p4", "[a-zA-Z0-9_]+"},
			{"player5", "p5", "[a-zA-Z0-9_]+"}
	}),
	PLAY_WITH_6_PLAYER("play game", new String[][]{
			{"player1", "p1", "[a-zA-Z0-9_]+"},
			{"player2", "p2", "[a-zA-Z0-9_]+"},
			{"player3", "p3", "[a-zA-Z0-9_]+"},
			{"player4", "p4", "[a-zA-Z0-9_]+"},
			{"player5", "p5", "[a-zA-Z0-9_]+"},
			{"player6", "p6", "[a-zA-Z0-9_]+"}
	}),
	PLAY_WITH_7_PLAYER("play game", new String[][]{
			{"player1", "p1", "[a-zA-Z0-9_]+"},
			{"player2", "p2", "[a-zA-Z0-9_]+"},
			{"player3", "p3", "[a-zA-Z0-9_]+"},
			{"player4", "p4", "[a-zA-Z0-9_]+"},
			{"player5", "p5", "[a-zA-Z0-9_]+"},
			{"player6", "p6", "[a-zA-Z0-9_]+"},
			{"player7", "p7", "[a-zA-Z0-9_]+"}
	}),
	PLAY_WITH_8_PLAYER("play game", new String[][]{
			{"player1", "p1", "[a-zA-Z0-9_]+"},
			{"player2", "p2", "[a-zA-Z0-9_]+"},
			{"player3", "p3", "[a-zA-Z0-9_]+"},
			{"player4", "p4", "[a-zA-Z0-9_]+"},
			{"player5", "p5", "[a-zA-Z0-9_]+"},
			{"player6", "p6", "[a-zA-Z0-9_]+"},
			{"player7", "p7", "[a-zA-Z0-9_]+"},
			{"player8", "p8", "[a-zA-Z0-9_]+"}
	}),
	PLAY_WITH_9_PLAYER("play game", new String[][]{
			{"player1", "p1", "[a-zA-Z0-9_]+"},
			{"player2", "p2", "[a-zA-Z0-9_]+"},
			{"player3", "p3", "[a-zA-Z0-9_]+"},
			{"player4", "p4", "[a-zA-Z0-9_]+"},
			{"player5", "p5", "[a-zA-Z0-9_]+"},
			{"player6", "p6", "[a-zA-Z0-9_]+"},
			{"player7", "p7", "[a-zA-Z0-9_]+"},
			{"player8", "p8", "[a-zA-Z0-9_]+"},
			{"player9", "p9", "[a-zA-Z0-9_]+"}
	});


	public final String command;
	public final String[][] args;
	public final String regex;
	public final Pattern pattern;
	
	private InputCommand(String command, String[][] args){
		this.command=command;
		this.args=args;
		
		StringBuilder res=new StringBuilder();
		res.append("\\A"+command);
		for (String[] arg : args) {
			StringBuilder tmp=new StringBuilder();
			tmp.append("(?=.*");
			{
				String S="(--"+arg[0]+")";
				if (arg[1]!=null) S="("+S+"|(-"+arg[1]+"))";
				tmp.append(S);

				if (arg[2]==null) continue ;
				tmp.append("(?<"+arg[0]+">"+arg[2]+")");
			}
			tmp.append(")");
			res.append(tmp);
		}
		this.regex=res.toString();
		this.pattern=Pattern.compile(this.regex);
	}

}

/*
\Auser create
(?=.*?((-u)|(--username)) (?<username><username>))
(?=.*((--nickname)|(-n)) (?<nickname><nickname>))
(?=.*((--password)|(-p)) (?<password><password>))
*/

