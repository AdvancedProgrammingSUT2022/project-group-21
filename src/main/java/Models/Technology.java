package Models;

import java.util.ArrayList;
import java.util.Arrays;

public enum Technology{
	AGRICULTURE(		20, Era.ANCIENT, new Technology[]{}),
	ANIMAL_HUSBANDRY(	35, Era.ANCIENT, new Technology[]{}),
	ARCHERY(			35, Era.ANCIENT, new Technology[]{}),
	BRONZE_WORKING(		55, Era.ANCIENT, new Technology[]{}),
	CALENDAR(			70, Era.ANCIENT, new Technology[]{}),
	MASONRY(			55, Era.ANCIENT, new Technology[]{}),
	MINING(				35, Era.ANCIENT, new Technology[]{}),
	POTTERY(			35, Era.ANCIENT, new Technology[]{}),
	THE_WHEEL(			55, Era.ANCIENT, new Technology[]{}),
	TRAPPING(			55, Era.ANCIENT, new Technology[]{}),
	WRITING(			55, Era.ANCIENT, new Technology[]{}),

	CONSTRUCTION(    100, Era.CLASSICAL, new Technology[]{}),
	HORSEBACK_RIDING(100, Era.CLASSICAL, new Technology[]{}),
	IRON_WORKING(    150, Era.CLASSICAL, new Technology[]{}),
	MATHEMATICS(     100, Era.CLASSICAL, new Technology[]{}),
	PHILOSOPHY(      100, Era.CLASSICAL, new Technology[]{}),

	CHIVALRY(        440, Era.MEDIEVAL, new Technology[]{}),
	CIVIL_SERVICE(   400, Era.MEDIEVAL, new Technology[]{}),
	CURRENCY(        250, Era.MEDIEVAL, new Technology[]{}),
	EDUCATION(       440, Era.MEDIEVAL, new Technology[]{}),
	ENGINEERING(     250, Era.MEDIEVAL, new Technology[]{}),
	MACHINERY(       440, Era.MEDIEVAL, new Technology[]{}),
	METAL_CASTING(   240, Era.MEDIEVAL, new Technology[]{}),
	PHYSICS(         440, Era.MEDIEVAL, new Technology[]{}),
	STEEL(           440, Era.MEDIEVAL, new Technology[]{}),
	THEOLOGY(        250, Era.MEDIEVAL, new Technology[]{}),

	ACOUSTICS(         650, Era.RENAISSANCE, new Technology[]{}),
	ARCHAEOLOGY(      1300, Era.RENAISSANCE, new Technology[]{}),
	BANKING(           650, Era.RENAISSANCE, new Technology[]{}),
	CHEMISTRY(         900, Era.RENAISSANCE, new Technology[]{}),
	ECONOMICS(         900, Era.RENAISSANCE, new Technology[]{}),
	FERTILIZER(       1300, Era.RENAISSANCE, new Technology[]{}),
	GUNPOWDER(         680, Era.RENAISSANCE, new Technology[]{}),
	METALLURGY(        900, Era.RENAISSANCE, new Technology[]{}),
	MILITARY_SCIENCE( 1300, Era.RENAISSANCE, new Technology[]{}),
	PRINTING_PRESS(    650, Era.RENAISSANCE, new Technology[]{}),
	RIFLING(          1425, Era.RENAISSANCE, new Technology[]{}),
	SCIENTIFIC_THEORY(1300, Era.RENAISSANCE, new Technology[]{}),

	BIOLOGY(          1680, Era.INDUSTRIAL, new Technology[]{}),
	COMBUSTION(       2200, Era.INDUSTRIAL, new Technology[]{}),
	DYNAMITE(         1900, Era.INDUSTRIAL, new Technology[]{}),
	ELECTRICITY(      1900, Era.INDUSTRIAL, new Technology[]{}),
	RADIO(            2200, Era.INDUSTRIAL, new Technology[]{}),
	RAILROAD(         1900, Era.INDUSTRIAL, new Technology[]{}),
	REPLACEABLE_PARTS(1900, Era.INDUSTRIAL, new Technology[]{}),
	STEAM_POWER(      1680, Era.INDUSTRIAL, new Technology[]{}),
	TELEGRAPH(        2200, Era.INDUSTRIAL, new Technology[]{}),
	;

	public final int cost;
	public final Era era;
	public final Technology prequisiteTechs[];

	Technology(int cost, Era era, Technology[] prequisiteTechs){
		this.cost=cost;
		this.era=era;
		this.prequisiteTechs=prequisiteTechs;
	}
}
