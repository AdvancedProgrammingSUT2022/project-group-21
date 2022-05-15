package View;


import Enums.Message;
import Models.Game;

import java.util.HashMap;

public class GameMenuExtractor {

    final HashMap<String ,String> ARGS1 = new HashMap<>();
    final HashMap<String ,Integer> ARGS2 = new HashMap<>();
    CommandTypes type;

    static GameMenuExtractor extractor(String command) {
        String[] tokens = command.split(" ");
        switch (tokens[0]) {
            case "INFO":
                return handleInfo(tokens);
            case "SELECT":
                return handleSelect(tokens);
            case "UNIT":
                return handleUnit(tokens);
            case "MAP":
                return handleMAP(tokens);
            case "CITY":
                return handleCity(tokens);
            default:
                return null;
        }
    }

    private static GameMenuExtractor handleInfo(String[] tokens) {
        GameMenuExtractor instance = new GameMenuExtractor();
        instance.type = CommandTypes.INFO;
        instance.ARGS1.put("info type", tokens[1]);
        return instance;
    }

    private static GameMenuExtractor handleSelect(String[] tokens) {
        GameMenuExtractor instance = new GameMenuExtractor();
        switch (tokens[1]) {
            case "UNIT":
                if (tokens[2].equals("COMBAT")) {
                    instance.type = CommandTypes.SELECT_COMBAT_UNIT;
                    return instance;
                }
                if (tokens[2].equals("NONCOMBAT")) {
                    instance.type = CommandTypes.SELECT_NONCOMBAT_UNIT;
                    return instance;
                }
                return null;
            case "CITY":
                if (tokens[2].matches("\\d+,\\d+")) {
                    int x = extractPosition(tokens[2], "x");
                    int y = extractPosition(tokens[2], "y");
                    instance.type = CommandTypes.SELECT_CITY_BY_POSITION;
                    instance.ARGS2.put("x", x);
                    instance.ARGS2.put("y", y);
                    return instance;
                }
                break;
        }
        return null;
    }

    private static GameMenuExtractor handleUnit(String[] tokens) {
        GameMenuExtractor instance = new GameMenuExtractor();
        int x,y;
        switch (tokens[1]) {
            case "MOVETO":
                if (tokens[2].matches("\\d+,\\d+")) {
                    x = extractPosition(tokens[2], "x");
                    y = extractPosition(tokens[2], "y");
                    instance.type = CommandTypes.UNIT_MOVE;
                    instance.ARGS2.put("x", x);
                    instance.ARGS2.put("y", y);
                    return instance;
                }
                return null;
            case "SLEEP":
                instance.type = CommandTypes.UNIT_SLEEP;
                return instance;
            case "ALERT":
                instance.type = CommandTypes.UNIT_ALERT;
                return instance;
            case "FORTIFY":
                if (tokens[2].equals("HEAL"))
                    instance.type = CommandTypes.UNIT_FORTIFY_AND_HEAL;
                else instance.type = CommandTypes.UNIT_FORTIFY;
                return instance;
            case "GARRISON":
                instance.type = CommandTypes.UNIT_GARRISON;
                return instance;
            case "SETUP":
                instance.type = CommandTypes.UNIT_SETUP_FOR_RANGED;
                return instance;
            case "ATTACK":
                x = extractPosition(tokens[2], "x");
                y = extractPosition(tokens[2], "y");
                instance.type = CommandTypes.UNIT_ATTACK;
                instance.ARGS2.put("x",x);      instance.ARGS2.put("y", y);
                return instance;
            case "FOUND":
                instance.type = CommandTypes.UNIT_FOUND_CITY;
                return instance;
            case "CANCEL":
                instance.type = CommandTypes.UNIT_CANCEL_MISSION;
                return instance;
            case "WAKE":
                instance.type = CommandTypes.UNIT_WAKE;
                return instance;
            case "DELETE":
                instance.type = CommandTypes.UNIT_DELETE;
                return instance;
            case "BUILD":
                if (tokens[2].equals("ROAD")) {
                    instance.type = CommandTypes.UNIT_BUILD_ROAD;
                    return instance;
                }
                if (tokens[2].equals("RAILROAD")) {
                    instance.type = CommandTypes.UNIT_BUILD_RAILROAD;
                    return instance;
                }
                instance.type = CommandTypes.UNIT_BUILD_IMPROVEMENT;
                instance.ARGS1.put("improvement", tokens[2]);
                return instance;
            case "REMOVE":
                instance.type = CommandTypes.UNIT_REMOVE;
                instance.ARGS1.put("removeType", tokens[2]);
                return instance;
            case "REPAIR":
                instance.type = CommandTypes.UNIT_REPAIR;
                return instance;
        }
        return null;
    }

    private static GameMenuExtractor handleMAP(String[] tokens) {
        GameMenuExtractor instance = new GameMenuExtractor();
        int x,y,c;
        switch (tokens[1]) {
            case "SHOW":
                if (tokens[2].matches("\\d+,\\d+")) {
                    instance.type = CommandTypes.MAP_SHOW_BY_POSITION;
                    x = extractPosition(tokens[2], "x");
                    y = extractPosition(tokens[2], "y");
                    instance.ARGS2.put("x", x);
                    instance.ARGS2.put("y", y);
                    return instance;
                }
                break;
            case "MOVE":
                c = Integer.parseInt(tokens[3]);
                instance.type = CommandTypes.MAP_MOVE;
                instance.ARGS1.put("direction", tokens[2]);
                instance.ARGS2.put("amount", c);
                return instance;
        }
        return null;
    }

    private static GameMenuExtractor handleCity(String[] tokens) {
        GameMenuExtractor instance = new GameMenuExtractor();
        switch (tokens[1]) {
            case "PURCHASE":
                if (tokens[2].equals("UNIT") && tokens.length == 4) {
                    instance.type = CommandTypes.CITY_PURCHASE_UNIT;
                    instance.ARGS1.put("unitType", tokens[3]);
                    return instance;
                }
                if (tokens[2].equals("TILE") && tokens.length == 4) {
                    instance.type = CommandTypes.CITY_PURCHASE_TILE;
                    instance.ARGS2.put("x", extractPosition(tokens[3], "x"));
                    instance.ARGS2.put("y", extractPosition(tokens[3], "y"));
                    return instance;
                }
                return null;
            case "GET":
                if (tokens[2].equals("OUTPUT") && tokens.length == 3)
                    //TODO:
                    return null;
                return null;
            case "LOCK":
                if (tokens[2].equals("CITIZEN") && tokens.length == 3)
                    //TODO
                    return null;
                return null;
            case "REMOVE":
                if (tokens[2].equals("FROM") && tokens[3].equals("WORK") && tokens.length == 4)
                    //TODO
                    return null;
                return null;
            case "BANNER":
                if (tokens.length == 2)
                    //TODO
                    return null;
                return null;
            case "DESTROY":
                if (tokens.length == 2)
                    //TODO
                    return null;
                return null;
            default:
                return null;
        }
    }


    private static int extractPosition(String position, String flag) {
        String[] tokens = position.split(",");
        if (flag.equals("x"))
            return Integer.parseInt(tokens[0]);
        return Integer.parseInt(tokens[1]);
    }

    public CommandTypes getType() {
        return type;
    }
}
