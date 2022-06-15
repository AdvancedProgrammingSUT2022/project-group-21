package Contoller;

public class CivilizationController{
	private static CivilizationController instance;
	public static CivilizationController getInstance() {
		if (instance==null) CivilizationController.instance = new CivilizationController();
		return instance;
	}

	
}
