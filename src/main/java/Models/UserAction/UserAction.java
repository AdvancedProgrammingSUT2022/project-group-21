package Models.UserAction;

public class UserAction {
	public String username;
	public CityUserAction cityUserAction;
	public DiplomacyUserAction diplomacyUserAction;
	public TechUserAction techUserAction;
	public UnitUserAction unitUserAction;

	public UserAction(String username, CityUserAction cityUserAction, DiplomacyUserAction diplomacyUserAction, TechUserAction techUserAction, UnitUserAction unitUserAction){
		this.username=username;
		this.cityUserAction=cityUserAction;
		this.diplomacyUserAction=diplomacyUserAction;
		this.techUserAction=techUserAction;
		this.unitUserAction=unitUserAction;
	}
}
