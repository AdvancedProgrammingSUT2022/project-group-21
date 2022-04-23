package Models;

import java.util.ArrayList;
import java.util.Arrays;

public enum Technology{
	Agriculture(20, null, new ArrayList<Technology>(Arrays.asList())),
	;

	public final int cost;
	public final Era era;
	public final ArrayList<Technology> prequisiteTechs;

	Technology(int cost, Era era, ArrayList<Technology> prequisiteTechs){
		this.cost=cost;
		this.era=era;
		this.prequisiteTechs=prequisiteTechs;
	}
}
