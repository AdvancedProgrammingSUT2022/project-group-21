package com.example.Model.city;

import java.util.ArrayList;

import com.example.Model.Technology;
import com.example.Model.resource.Resource;
import com.example.Model.tile.Terrain;

public enum Building{
	BARRACKS("Barracks", 80, 1, Technology.BRONZE_WORKING)
			{
				@Override
				public String toString() {
					return name;
				}
			}
	,
	GRANARY("Granary", 100, 1, Technology.POTTERY)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	LIBRARY("Library", 80, 1, Technology.WRITING)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	MONUMENT("Monument", 60, 1, null)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	WALLS("Walls", 100, 1, Technology.MASONRY)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	WATER_MILL("Water Mill", 120, 2, Technology.THE_WHEEL){
		@Override
		public boolean canBuildOnCity(City city) {
			if (city.getCenter().countRivers()==0) return false	;
			return super.canBuildOnCity(city);
		}
		@Override
		public String toString() {
			return name;
		}
	},

	ARMORY("Armory", 130, 3, Technology.IRON_WORKING){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(BARRACKS);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	BURIAL_TOMB("Burial Tomb", 120, 0, Technology.PHILOSOPHY){
		@Override
		public void addBuildingToCity(City city) {
			city.getOwner().addHappiness(+2);
			super.addBuildingToCity(city);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	CIRCUS("Circus", 150, 3, Technology.HORSEBACK_RIDING){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && (city.hasImprovedResource(Resource.HORSE) || city.hasImprovedResource(Resource.IVORY));
		}
		@Override
		public void addBuildingToCity(City city) {
			city.getOwner().addHappiness(+3);
			super.addBuildingToCity(city);
		}

		@Override
		public String toString() {
			return name;
		}

	},
	COLOSSEUM("Colosseum", 150, 3, Technology.CONSTRUCTION){
		@Override
		public void addBuildingToCity(City city) {
			city.getOwner().addHappiness(+4);
			super.addBuildingToCity(city);
		}

		@Override
		public String toString() {
			return name;
		}
	},
	COURTHOUSE("Courthouse", 200, 5, Technology.MATHEMATICS){
		@Override
		public void addBuildingToCity(City city) {
			city.getOwner().addHappiness(+4);
			super.addBuildingToCity(city);
		}

		@Override
		public String toString() {
			return name;
		}
	},
	STABLE("Stable", 100, 1, Technology.HORSEBACK_RIDING){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasImprovedResource(Resource.HORSE);
		}

		@Override
		public String toString() {
			return name;
		}

	},
	TEMPLE("Temple", 120, 2, Technology.PHILOSOPHY){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(MONUMENT);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	CASTLE("Castle", 200, 3, Technology.CHIVALRY){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(WALLS);
		}

		@Override
		public String toString() {
			return name;
		}
	},
	FORGE("Forge", 150, 2, Technology.METAL_CASTING){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasImprovedResource(Resource.IRON);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	GARDEN("Garden", 120, 2, Technology.THEOLOGY){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.getCenter().countRivers()>0;
		}
		@Override
		public String toString() {
			return name;
		}
	},
	MARKET("Market", 120, 0, Technology.CURRENCY)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	MINT("Mint", 120, 0, Technology.CURRENCY)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	MONASTERY("Monastery", 120, 2, Technology.THEOLOGY)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	UNIVERSITY("University", 200, 3, Technology.EDUCATION){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(LIBRARY);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	WORKSHOP("Workshop", 100, 2, Technology.METAL_CASTING)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	BANK("Bank", 220, 0, Technology.BANKING){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(MARKET);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	MILITARY_ACADEMY("Military Academy", 350, 3, Technology.MILITARY_SCIENCE){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(BARRACKS);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	MUSEUM("Museum", 350, 3, Technology.ARCHAEOLOGY){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(OPERA_HOUSE);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	OPERA_HOUSE("Opera House", 220, 3, Technology.ACOUSTICS){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(TEMPLE) && city.hasBuilding(BURIAL_TOMB);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	PUBLIC_SCHOOL("Public School", 350, 3, Technology.SCIENTIFIC_THEORY){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(UNIVERSITY);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	SATRAPS_COURT("Satrap’s Court", 220, 0, Technology.BANKING){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(MARKET);
		}
		@Override
		public void addBuildingToCity(City city) {
			city.getOwner().addHappiness(+2);
			super.addBuildingToCity(city);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	THEATER("Theater", 300, 5, Technology.PRINTING_PRESS){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(COLOSSEUM);
		}
		@Override
		public void addBuildingToCity(City city) {
			city.getOwner().addHappiness(+4);
			super.addBuildingToCity(city);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	WINDMILL("Windmill", 180, 2, Technology.ECONOMICS){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.getCenter().getTerrain()!=Terrain.HILL;
		}
		@Override
		public String toString() {
			return name;
		}
	},
	ARSENAL("Arsenal", 350, 3, Technology.RAILROAD){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(MILITARY_ACADEMY);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	BROADCAST_TOWER("Broadcast Tower", 600, 3, Technology.RADIO){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(MUSEUM);
		}
		@Override
		public String toString() {
			return name;
		}

	},
	FACTORY("Factory", 300, 3, Technology.STEAM_POWER){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasImprovedResource(Resource.COAL);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	HOSPITAL("Hospital", 400, 2, Technology.BIOLOGY)
			{
				@Override
				public String toString() {
					return name;
				}
			},
	MILITARY_BASE("Military Base", 450, 4, Technology.TELEGRAPH){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && city.hasBuilding(CASTLE);
		}
		@Override
		public String toString() {
			return name;
		}
	},
	STOCK_EXCHANGE("Stock Exchange", 650, 0, Technology.ELECTRICITY){
		@Override
		public boolean canBuildOnCity(City city) {
			return super.canBuildOnCity(city) && (city.hasBuilding(BANK) || city.hasBuilding(SATRAPS_COURT));
		}
		@Override
		public String toString() {
			return name;
		}
	};

	public final String name;
	public final int cost;
	public final int maintenance;
	public final Technology technologyRequired;

	private Building(String name, int cost, int maintenance, Technology technologyRequired){
		this.name=name;
		this.cost=cost;
		this.maintenance=maintenance;
		this.technologyRequired=technologyRequired;
	}

	public boolean canBuildOnCity(City city){
		if (technologyRequired==null) return true;
		if (city.getOwner().hasTechnology(technologyRequired)) return true;
		return false;
	}

	public void addBuildingToCity(City city){
		city.addBuilding(this);
	}

	public static ArrayList<Building> getAllPossibleBuildingsToBuild(City city){
		ArrayList<Building> out = new ArrayList<>();
		for (Building building : Building.values()) {
			if (building.canBuildOnCity(city)){
				out.add(building);
			}
		}
		return out;
	}

	public static ArrayList<Building> getAllPossibleBuildingsToBuild(){
		ArrayList<Building> out = new ArrayList<>();
		for (Building building : Building.values()) {
			out.add(building);
		}
		return out;
	}
}
