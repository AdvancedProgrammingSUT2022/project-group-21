package View;

import Enums.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum ExtractorRegexes {
    CREATE_USER("^(user\\s+create\\s+(?=.*((--username)|(-u))\\s+(?<username>\\S+))" +
            "(?=.*((--nickname)|(-n))\\s+(?<nickname>\\S+))" +
            "(?=.*((--password)|(-p))\\s+(?<password>\\S+)))"),
    LOGIN_USER("^(user\\s+login\\s+" +
            "(?=.*((--username)|(-u))\\s+(?<username>\\S+))" +
            "(?=.*((--password)|(-p))\\s+(?<password>\\S+)))"),
    PLAY_GAME("(?=.*((--player(?<num>\\d+))|(-p(?<num>\\d+))\\s+(?<username>\\S+))"),
    CHANGE_NICKNAME("^(profile\\s+change\\s+" +
            "(?=.*((--nickname)|(-n))\\s+(?<nickname>\\S+)))$"),
    CHANGE_PASSWORD("profile\\s+change\\s+(--password)|(-p)\\s+" +
            "(?=.*((--current)|(-c))\\s+(?<current_password>\\S+))" +
            "(?=.*((--new)|(-n))\\s+(?<new_password>\\S+))")
    ;


    String regex;
    Pattern pattern;
    ExtractorRegexes(String regex) {
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }
    public Matcher getMatcher(String command) {
        return pattern.matcher(command);
    }
}


public class CommandExtractor {
    public static Message registerAnalyse (String command) {
        Matcher matcher;
        if ((matcher = ExtractorRegexes.CREATE_USER.getMatcher(command)).find())
            RegisterMenu.getInstance().createUser(matcher.group("username"),
                    matcher.group("nickname"),
                    matcher.group("password"));
        else if ((matcher = ExtractorRegexes.LOGIN_USER.getMatcher(command)).find()) {
            RegisterMenu.getInstance().login(matcher.group("username"), matcher.group("password"));
            return Message.LOGIN_MATCHED;
        }

        return null;

    }
}
