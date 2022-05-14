package Contoller;

public class InfoMenuController {
    private static InfoMenuController instance;
    private InfoMenuController() {
    }
    public static InfoMenuController getInstance() {
        if (instance == null) instance = new InfoMenuController();
        return instance;
    }
    public String showInfoMenu(String infoType) {
        switch (infoType) {
            case "RESEARCH":
                return researchInfo();
            case "UNITS":
                return unitsInfo();
            case "CITIES":
                return citiesInfo();
            case "DIPLOMACY":
                return diplomacyInfo();
            case "VICTORY":
                return victoryInfo();
            case "DEMOGRAPHICS":
                return demographicsInfo();
            case "NOTIFICATIONS":
                return notificationsInfo();
            case "MILITARY":
                return militaryInfo();
            case "ECONOMIC":
                return economicInfo();
            case "DIPLOMATIC":
                return diplomaticInfo();
            case "DEALS":
                return dealsInfo();
            default:
                return null;
        }
    }

    public String research(String technology) {
        //TODO
        return null;
    }

    private String researchInfo(){
        //TODO
        return null;
    }

    private String unitsInfo() {
        //TODO
        return null;
    }

    private String citiesInfo() {
        //TODO
        return null;
    }

    private String diplomacyInfo() {
        //TODO
        return null;
    }

    private String victoryInfo() {
        //TODO
        return null;
    }

    private String demographicsInfo() {
        //TODO
        return null;
    }

    private String notificationsInfo() {
        //TODO
        return null;
    }

    private String militaryInfo() {
        //TODO
        return null;
    }

    private String economicInfo() {
        //TODO
        return null;
    }

    private String diplomaticInfo() {
        //TODO
        return null;
    }

    private String dealsInfo() {
        //TODO
        return null;
    }


}
