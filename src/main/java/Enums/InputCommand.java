package Enums;

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
	;


	public final String command;
	public final String[][] args;
	public final String regex;
	
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
	}

}

/*
\Auser create
(?=.*?((-u)|(--username)) (?<username><username>))
(?=.*((--nickname)|(-n)) (?<nickname><nickname>))
(?=.*((--password)|(-p)) (?<password><password>))
*/

