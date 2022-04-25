package Contoller;

public class ProfileController {
    private static ProfileController instance;

    public static void setInstance(ProfileController instance) {
        ProfileController.instance = instance;
    }

    public static ProfileController getInstance() {
        if (instance == null)       setInstance(new ProfileController());
        return instance;
    }
}
