package Models.Resource;

public enum ResourceType{
	BONUS(0),
	STRATEGIC(0),
	LUXURY(1);
	
	public final int happiness;

	private ResourceType(int happiness){
		this.happiness=happiness;
	}
	
}
