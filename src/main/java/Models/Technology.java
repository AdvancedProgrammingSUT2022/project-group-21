package Models;

import java.util.ArrayList;
import java.util.Arrays;

public enum Technology{
	AGRICULTURE(		20, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	ANIMAL_HUSBANDRY(	35, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	ARCHERY(			35, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	BRONZE_WORKING(		55, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	CALENDAR(			70, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	MASONRY(			55, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	MINING(				35, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	POTTERY(			35, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	THE_WHEEL(			55, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	TRAPPING(			55, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),
	WRITING(			55, Era.ANCIENT, new ArrayList<Technology>(Arrays.asList())),

	CONSTRUCTION(    100, Era.CLASSICAL, new ArrayList<Technology>(Arrays.asList())),
	HORSEBACK_RIDING(100, Era.CLASSICAL, new ArrayList<Technology>(Arrays.asList())),
	IRON_WORKING(    150, Era.CLASSICAL, new ArrayList<Technology>(Arrays.asList())),
	MATHEMATICS(     100, Era.CLASSICAL, new ArrayList<Technology>(Arrays.asList())),
	PHILOSOPHY(      100, Era.CLASSICAL, new ArrayList<Technology>(Arrays.asList())),

	CHIVALRY(        440, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	CIVIL_SERVICE(   400, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	CURRENCY(        250, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	EDUCATION(       440, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	ENGINEERING(     250, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	MACHINERY(       440, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	METAL_CASTING(   240, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	PHYSICS(         440, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	STEEL(           440, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),
	THEOLOGY(        250, Era.MEDIEVAL, new ArrayList<Technology>(Arrays.asList())),

	ACOUSTICS(         650, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	ARCHAEOLOGY(      1300, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	BANKING(           650, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	CHEMISTRY(         900, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	ECONOMICS(         900, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	FERTILIZER(       1300, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	GUNPOWDER(         680, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	METALLURGY(        900, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	MILITARY_SCIENCE( 1300, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	PRINTING_PRESS(    650, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	RIFLING(          1425, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),
	SCIENTIFIC_THEORY(1300, Era.RENAISSANCE, new ArrayList<Technology>(Arrays.asList())),

	BIOLOGY(          1680, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
	COMBUSTION(       2200, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
	DYNAMITE(         1900, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
	ELECTRICITY(      1900, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
	RADIO(            2200, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
	RAILROAD(         1900, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
	REPLACEABLE_PARTS(1900, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
	STEAM_POWER(      1680, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
	TELEGRAPH(        2200, Era.INDUSTRIAL, new ArrayList<Technology>(Arrays.asList())),
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
