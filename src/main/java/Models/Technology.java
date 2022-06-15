package Models;

public enum Technology{
	AGRICULTURE("Agriculture", 20, Era.ANCIENT,
			new Technology[] {}),
	ANIMAL_HUSBANDRY("Animal Husbandry", 35, Era.ANCIENT,
			new Technology[] { Technology.AGRICULTURE }),
	ARCHERY("Archery", 35, Era.ANCIENT,
			new Technology[] { Technology.AGRICULTURE }),
	MINING("Mining", 35, Era.ANCIENT,
			new Technology[] { Technology.AGRICULTURE }),
	POTTERY("Pottery", 35, Era.ANCIENT,
			new Technology[] { Technology.AGRICULTURE }),
	BRONZE_WORKING("Bronze Working", 55, Era.ANCIENT,
			new Technology[] { Technology.MINING }),
	CALENDAR("Calender", 70, Era.ANCIENT,
			new Technology[] { Technology.POTTERY }),
	MASONRY("Masonry", 55, Era.ANCIENT,
			new Technology[] { Technology.MINING }),
	THE_WHEEL("The Wheel", 55, Era.ANCIENT,
			new Technology[] { Technology.ANIMAL_HUSBANDRY }),
	TRAPPING("Trapping", 55, Era.ANCIENT,
			new Technology[] { Technology.ANIMAL_HUSBANDRY }),
	WRITING("Writing", 55, Era.ANCIENT,
			new Technology[] { Technology.POTTERY }),

	CONSTRUCTION("Construction", 100, Era.CLASSICAL,
			new Technology[] { Technology.MASONRY }),
	HORSEBACK_RIDING("Horseback Riding", 100, Era.CLASSICAL,
			new Technology[] { Technology.THE_WHEEL }),
	IRON_WORKING("Iron Working", 150, Era.CLASSICAL,
			new Technology[] { Technology.BRONZE_WORKING }),
	MATHEMATICS("Mathematics", 100, Era.CLASSICAL,
			new Technology[] { Technology.THE_WHEEL, Technology.ARCHERY }),
	PHILOSOPHY("Philosophy", 100, Era.CLASSICAL,
			new Technology[] { Technology.WRITING }),

	// TODO: change names from here :woozy:

	THEOLOGY("THEOLOGY", 250, Era.MEDIEVAL,
			new Technology[] { Technology.CALENDAR, Technology.PHILOSOPHY }),
	CIVIL_SERVICE("CIVIL_SERVICE", 400, Era.MEDIEVAL,
			new Technology[] { Technology.PHILOSOPHY, Technology.TRAPPING }),
	CURRENCY("CURRENCY", 250, Era.MEDIEVAL,
			new Technology[] { Technology.MATHEMATICS }),
	CHIVALRY("CHIVALRY", 440, Era.MEDIEVAL,
			new Technology[] { Technology.CIVIL_SERVICE, Technology.HORSEBACK_RIDING, Technology.CURRENCY }),
	EDUCATION("EDUCATION", 440, Era.MEDIEVAL,
			new Technology[] { Technology.THEOLOGY }),
	ENGINEERING("ENGINEERING", 250, Era.MEDIEVAL,
			new Technology[] { Technology.MATHEMATICS, Technology.CONSTRUCTION }),
	MACHINERY("MACHINERY", 440, Era.MEDIEVAL,
			new Technology[] { Technology.ENGINEERING }),
	METAL_CASTING("METAL_CASTING", 240, Era.MEDIEVAL,
			new Technology[] { Technology.IRON_WORKING }),
	PHYSICS("PHYSICS", 440, Era.MEDIEVAL,
			new Technology[] { Technology.ENGINEERING, Technology.METAL_CASTING }),
	STEEL("STEEL", 440, Era.MEDIEVAL,
			new Technology[] { Technology.METAL_CASTING }),

	ACOUSTICS("ACOUSTICS", 650, Era.RENAISSANCE,
			new Technology[] { Technology.EDUCATION }),
	ARCHAEOLOGY("ARCHAEOLOGY", 1300, Era.RENAISSANCE,
			new Technology[] { Technology.ACOUSTICS }),
	BANKING("BANKING", 650, Era.RENAISSANCE,
			new Technology[] { Technology.EDUCATION, Technology.CHIVALRY }),
	GUNPOWDER("GUNPOWDER", 680, Era.RENAISSANCE,
			new Technology[] { Technology.PHYSICS, Technology.STEEL }),
	CHEMISTRY("CHEMISTRY", 900, Era.RENAISSANCE,
			new Technology[] { Technology.GUNPOWDER }),
	PRINTING_PRESS("PRINTING_PRESS", 650, Era.RENAISSANCE,
			new Technology[] { Technology.MACHINERY, Technology.PHYSICS }),
	ECONOMICS("ECONOMICS", 900, Era.RENAISSANCE,
			new Technology[] { Technology.PRINTING_PRESS, Technology.BANKING }),
	FERTILIZER("FERTILIZER", 1300, Era.RENAISSANCE,
			new Technology[] { Technology.CHEMISTRY }),
	METALLURGY("METALLURGY", 900, Era.RENAISSANCE,
			new Technology[] { Technology.GUNPOWDER }),
	MILITARY_SCIENCE("MILITARY_SCIENCE", 1300, Era.RENAISSANCE,
			new Technology[] { Technology.ECONOMICS, Technology.CHEMISTRY }),
	RIFLING("RIFLING", 1425, Era.RENAISSANCE,
			new Technology[] { Technology.METALLURGY }),
	SCIENTIFIC_THEORY("SCIENTIFIC_THEORY", 1300, Era.RENAISSANCE,
			new Technology[] { Technology.ACOUSTICS }),

	BIOLOGY("BIOLOGY", 1680, Era.INDUSTRIAL,
			new Technology[] { Technology.ARCHAEOLOGY, Technology.SCIENTIFIC_THEORY }),
	STEAM_POWER("STEAM_POWER", 1680, Era.INDUSTRIAL,
			new Technology[] { Technology.SCIENTIFIC_THEORY, Technology.MILITARY_SCIENCE }),
	RAILROAD("RAILROAD", 1900, Era.INDUSTRIAL,
			new Technology[] { Technology.STEAM_POWER }),
	REPLACEABLE_PARTS("REPLACEABLE_PARTS", 1900, Era.INDUSTRIAL,
			new Technology[] { Technology.STEAM_POWER }),
	DYNAMITE("DYNAMITE", 1900, Era.INDUSTRIAL,
			new Technology[] { Technology.FERTILIZER, Technology.RIFLING }),
	COMBUSTION("COMBUSTION", 2200, Era.INDUSTRIAL,
			new Technology[] { Technology.REPLACEABLE_PARTS, Technology.RAILROAD, Technology.DYNAMITE }),
	ELECTRICITY("ELECTRICITY", 1900, Era.INDUSTRIAL,
			new Technology[] { Technology.BIOLOGY, Technology.STEAM_POWER }),
	RADIO("RADIO", 2200, Era.INDUSTRIAL,
			new Technology[] { Technology.ELECTRICITY }),
	TELEGRAPH("TELEGRAPH", 2200, Era.INDUSTRIAL,
			new Technology[] { Technology.ELECTRICITY }),
			;

	public final String name;
	public final int cost;
	public final Era era;
	public final Technology prequisiteTechs[];

	private Technology(String name, int cost, Era era, Technology[] prequisiteTechs){
		this.name=name;
		this.cost=cost;
		this.era=era;
		this.prequisiteTechs=prequisiteTechs;
	}

	public Technology[] getPrequisiteTechs() {
		return prequisiteTechs;
	}

	@Override
	public String toString(){
		return name;
	}
}
