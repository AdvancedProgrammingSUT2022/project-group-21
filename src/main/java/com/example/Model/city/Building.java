package com.example.Model.city;

import com.example.Model.Technology;

public enum Building{
	// TODO
	;

	public final String name;
	public final int cost;
	public final int maintenance;
	public final int rangedCombatStrength;
	public final Technology technologyRequired;

	private Building(String name, int cost, int maintenance, int rangedCombatStrength, Technology technologyRequired){
		this.name=name;
		this.cost=cost;
		this.maintenance=maintenance;
		this.rangedCombatStrength=rangedCombatStrength;
		this.technologyRequired=technologyRequired;
	}
}
