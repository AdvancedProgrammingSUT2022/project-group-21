package Models;

public enum Technology{
	AGRICULTURE(		20, Era.ANCIENT, new Technology[]{}),
	ANIMAL_HUSBANDRY(	35, Era.ANCIENT, new Technology[]{Technology.AGRICULTURE}),
	ARCHERY(			35, Era.ANCIENT, new Technology[]{Technology.AGRICULTURE}),
	MINING(				35, Era.ANCIENT, new Technology[]{Technology.AGRICULTURE}),
	POTTERY(			35, Era.ANCIENT, new Technology[]{Technology.AGRICULTURE}),
	BRONZE_WORKING(		55, Era.ANCIENT, new Technology[]{Technology.MINING}),
	CALENDAR(			70, Era.ANCIENT, new Technology[]{Technology.POTTERY}),
	MASONRY(			55, Era.ANCIENT, new Technology[]{Technology.MINING}),
	THE_WHEEL(			55, Era.ANCIENT, new Technology[]{Technology.ANIMAL_HUSBANDRY}),
	TRAPPING(			55, Era.ANCIENT, new Technology[]{Technology.ANIMAL_HUSBANDRY}),
	WRITING(			55, Era.ANCIENT, new Technology[]{Technology.POTTERY}),

	CONSTRUCTION(    100, Era.CLASSICAL, new Technology[]{Technology.MASONRY}),
	HORSEBACK_RIDING(100, Era.CLASSICAL, new Technology[]{Technology.THE_WHEEL}),
	IRON_WORKING(    150, Era.CLASSICAL, new Technology[]{Technology.BRONZE_WORKING}),
	MATHEMATICS(     100, Era.CLASSICAL, new Technology[]{Technology.THE_WHEEL,Technology.ARCHERY}),
	PHILOSOPHY(      100, Era.CLASSICAL, new Technology[]{Technology.WRITING}),

	THEOLOGY(        250, Era.MEDIEVAL, new Technology[]{Technology.CALENDAR,Technology.PHILOSOPHY}),
	CIVIL_SERVICE(   400, Era.MEDIEVAL, new Technology[]{Technology.PHILOSOPHY,Technology.TRAPPING}),
	CURRENCY(        250, Era.MEDIEVAL, new Technology[]{Technology.MATHEMATICS}),
	CHIVALRY(        440, Era.MEDIEVAL, new Technology[]{Technology.CIVIL_SERVICE,Technology.HORSEBACK_RIDING,Technology.CURRENCY}),
	EDUCATION(       440, Era.MEDIEVAL, new Technology[]{Technology.THEOLOGY}),
	ENGINEERING(     250, Era.MEDIEVAL, new Technology[]{Technology.MATHEMATICS,Technology.CONSTRUCTION}),
	MACHINERY(       440, Era.MEDIEVAL, new Technology[]{Technology.ENGINEERING}),
	METAL_CASTING(   240, Era.MEDIEVAL, new Technology[]{Technology.IRON_WORKING}),
	PHYSICS(         440, Era.MEDIEVAL, new Technology[]{Technology.ENGINEERING,Technology.METAL_CASTING}),
	STEEL(           440, Era.MEDIEVAL, new Technology[]{Technology.METAL_CASTING}),


	ACOUSTICS(         650, Era.RENAISSANCE, new Technology[]{Technology.EDUCATION}),
	ARCHAEOLOGY(      1300, Era.RENAISSANCE, new Technology[]{Technology.ACOUSTICS}),
	BANKING(           650, Era.RENAISSANCE, new Technology[]{Technology.EDUCATION,Technology.CHIVALRY}),
	GUNPOWDER(         680, Era.RENAISSANCE, new Technology[]{Technology.PHYSICS,Technology.STEEL}),
	CHEMISTRY(         900, Era.RENAISSANCE, new Technology[]{Technology.GUNPOWDER}),
	PRINTING_PRESS(    650, Era.RENAISSANCE, new Technology[]{Technology.MACHINERY,Technology.PHYSICS}),
	ECONOMICS(         900, Era.RENAISSANCE, new Technology[]{Technology.PRINTING_PRESS,Technology.BANKING}),
	FERTILIZER(       1300, Era.RENAISSANCE, new Technology[]{Technology.CHEMISTRY}),
	METALLURGY(        900, Era.RENAISSANCE, new Technology[]{Technology.GUNPOWDER}),
	MILITARY_SCIENCE( 1300, Era.RENAISSANCE, new Technology[]{Technology.ECONOMICS,Technology.CHEMISTRY}),
	RIFLING(          1425, Era.RENAISSANCE, new Technology[]{Technology.METALLURGY}),
	SCIENTIFIC_THEORY(1300, Era.RENAISSANCE, new Technology[]{Technology.ACOUSTICS}),

	BIOLOGY(          1680, Era.INDUSTRIAL, new Technology[]{Technology.ARCHAEOLOGY,Technology.SCIENTIFIC_THEORY}),
	STEAM_POWER(      1680, Era.INDUSTRIAL, new Technology[]{Technology.SCIENTIFIC_THEORY,Technology.MILITARY_SCIENCE}),
	RAILROAD(         1900, Era.INDUSTRIAL, new Technology[]{Technology.STEAM_POWER}),
	REPLACEABLE_PARTS(1900, Era.INDUSTRIAL, new Technology[]{Technology.STEAM_POWER}),
	DYNAMITE(         1900, Era.INDUSTRIAL, new Technology[]{Technology.FERTILIZER,Technology.RIFLING}),
	COMBUSTION(       2200, Era.INDUSTRIAL, new Technology[]{Technology.REPLACEABLE_PARTS,Technology.RAILROAD,Technology.DYNAMITE}),
	ELECTRICITY(      1900, Era.INDUSTRIAL, new Technology[]{Technology.BIOLOGY,Technology.STEAM_POWER}),
	RADIO(            2200, Era.INDUSTRIAL, new Technology[]{Technology.ELECTRICITY}),
	TELEGRAPH(        2200, Era.INDUSTRIAL, new Technology[]{Technology.ELECTRICITY}),
	;

	public final int cost;
	public final Era era;
	public final Technology prequisiteTechs[];

	Technology(int cost, Era era, Technology[] prequisiteTechs){
		this.cost=cost;
		this.era=era;
		this.prequisiteTechs=prequisiteTechs;
	}

	public Technology[] getPrequisiteTechs() {
		return prequisiteTechs;
	}
}
