package Contoller;

import Enums.Message;
import Models.Civilization;
import Models.Unit.UnitType;

public class CityController {
    private static CityController instance;
    private CityController() {
    }

    public static CityController getInstance() {
        if (instance == null)       instance = new CityController();
        return instance;
    }

    public void findCity() {
        // TODO
    }



    public Message purchaseUnit(UnitType type) {
        // TODO
        /*if(type.getCost > Civilization.getGold)
            return Message.NOT_ENOUGH_GOLD;
        return null;*/
        return null;
    }






}
