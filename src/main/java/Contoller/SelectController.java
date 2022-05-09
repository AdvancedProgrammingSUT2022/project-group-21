package Contoller;

import Enums.Message;

public class SelectController {
    private static SelectController instance;
    private SelectController() {

    }
    public static SelectController getInstance() {
        if (instance == null)
            instance = new SelectController();
        return instance;
    }
    public Message selectUnit(int x, int y, boolean isCombatUnit) {
        //TODO:
        return null;
    }
    public Message selectCityByName(String name) {
        //TODO:
        return null;
    }
    public Message selectCityByCoordination(int x, int y) {
        //TODO:
        return null;
    }
}
