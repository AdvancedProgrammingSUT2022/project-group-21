package com.example.Model;

import com.example.Model.city.Building;
import com.example.Model.city.City;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

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

	THEOLOGY("Theology", 250, Era.MEDIEVAL,
			new Technology[] { Technology.CALENDAR, Technology.PHILOSOPHY }),
	CIVIL_SERVICE("Civil Service", 400, Era.MEDIEVAL,
			new Technology[] { Technology.PHILOSOPHY, Technology.TRAPPING }),
	CURRENCY("Currency", 250, Era.MEDIEVAL,
			new Technology[] { Technology.MATHEMATICS }),
	CHIVALRY("Chivalry", 440, Era.MEDIEVAL,
			new Technology[] { Technology.CIVIL_SERVICE, Technology.HORSEBACK_RIDING, Technology.CURRENCY }),
	EDUCATION("Education", 440, Era.MEDIEVAL,
			new Technology[] { Technology.THEOLOGY }),
	ENGINEERING("Engineering", 250, Era.MEDIEVAL,
			new Technology[] { Technology.MATHEMATICS, Technology.CONSTRUCTION }),
	MACHINERY("Machinery", 440, Era.MEDIEVAL,
			new Technology[] { Technology.ENGINEERING }),
	METAL_CASTING("Metal Casting", 240, Era.MEDIEVAL,
			new Technology[] { Technology.IRON_WORKING }),
	PHYSICS("Physics", 440, Era.MEDIEVAL,
			new Technology[] { Technology.ENGINEERING, Technology.METAL_CASTING }),
	STEEL("Steel", 440, Era.MEDIEVAL,
			new Technology[] { Technology.METAL_CASTING }),

	ACOUSTICS("Acoustics", 650, Era.RENAISSANCE,
			new Technology[] { Technology.EDUCATION }),
	ARCHAEOLOGY("Archaeology", 1300, Era.RENAISSANCE,
			new Technology[] { Technology.ACOUSTICS }),
	BANKING("Banking", 650, Era.RENAISSANCE,
			new Technology[] { Technology.EDUCATION, Technology.CHIVALRY }),
	GUNPOWDER("Gunpowder", 680, Era.RENAISSANCE,
			new Technology[] { Technology.PHYSICS, Technology.STEEL }),
	CHEMISTRY("Chemistry", 900, Era.RENAISSANCE,
			new Technology[] { Technology.GUNPOWDER }),
	PRINTING_PRESS("Printing Press", 650, Era.RENAISSANCE,
			new Technology[] { Technology.MACHINERY, Technology.PHYSICS }),
	ECONOMICS("Economics", 900, Era.RENAISSANCE,
			new Technology[] { Technology.PRINTING_PRESS, Technology.BANKING }),
	FERTILIZER("Fertilizer", 1300, Era.RENAISSANCE,
			new Technology[] { Technology.CHEMISTRY }),
	METALLURGY("Metallurgy", 900, Era.RENAISSANCE,
			new Technology[] { Technology.GUNPOWDER }),
	MILITARY_SCIENCE("Military Science", 1300, Era.RENAISSANCE,
			new Technology[] { Technology.ECONOMICS, Technology.CHEMISTRY }),
	RIFLING("Rifling", 1425, Era.RENAISSANCE,
			new Technology[] { Technology.METALLURGY }),
	SCIENTIFIC_THEORY("Scientific Theory", 1300, Era.RENAISSANCE,
			new Technology[] { Technology.ACOUSTICS }),

	BIOLOGY("Biology", 1680, Era.INDUSTRIAL,
			new Technology[] { Technology.ARCHAEOLOGY, Technology.SCIENTIFIC_THEORY }),
	STEAM_POWER("Steam Power", 1680, Era.INDUSTRIAL,
			new Technology[] { Technology.SCIENTIFIC_THEORY, Technology.MILITARY_SCIENCE }),
	RAILROAD("Railroad", 1900, Era.INDUSTRIAL,
			new Technology[] { Technology.STEAM_POWER }),
	REPLACEABLE_PARTS("Replaceable Parts", 1900, Era.INDUSTRIAL,
			new Technology[] { Technology.STEAM_POWER }),
	DYNAMITE("Dynamite", 1900, Era.INDUSTRIAL,
			new Technology[] { Technology.FERTILIZER, Technology.RIFLING }),
	COMBUSTION("Combustion", 2200, Era.INDUSTRIAL,
			new Technology[] { Technology.REPLACEABLE_PARTS, Technology.RAILROAD, Technology.DYNAMITE }),
	ELECTRICITY("Electricity", 1900, Era.INDUSTRIAL,
			new Technology[] { Technology.BIOLOGY, Technology.STEAM_POWER }),
	RADIO("Radio", 2200, Era.INDUSTRIAL,
			new Technology[] { Technology.ELECTRICITY }),
	TELEGRAPH("Telegraph", 2200, Era.INDUSTRIAL,
			new Technology[] { Technology.ELECTRICITY });
	

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

	public boolean canBeResearchedBy(Civilization civilization){
		return civilization.hasTechnologies(prequisiteTechs) && !civilization.hasTechnology(this);
	}

	@Override
	public String toString(){
		return name;
	}

	public static ArrayList<Technology> getAllPossibleTechnology(){
		ArrayList<Technology> out = new ArrayList<>();
		for (Technology technology : Technology.values()) {
			out.add(technology);
		}
		return out;
	}
	public static ArrayList<Technology> getAllPossibleTechnology(Civilization civilization){
		ArrayList<Technology> out = new ArrayList<>();
		for (Technology technology : Technology.values()) {
			if (technology.canBeResearchedBy(civilization)){
				out.add(technology);
			}
		}
		return out;
	}
}
