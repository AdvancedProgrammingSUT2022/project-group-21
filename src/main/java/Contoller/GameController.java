package Contoller;

public class GameController {
    private static GameController instance;
    private static void setInstance(GameController instance) {
        GameController.instance = instance;
    }
    public static GameController getInstance() {
        if (instance == null)       GameController.setInstance(new GameController());
        return instance;
    }
}
