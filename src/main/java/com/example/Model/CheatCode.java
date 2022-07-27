package com.example.Model;

import java.util.ArrayList;

import com.example.Model.city.City;
import com.example.Model.tile.Tile;
import com.example.Model.unit.Unit;

public enum CheatCode {
	REVEAL_ALL_TILES {
		@Override
		public void apply(Civilization civilization) {
			civilization.revealAllTiles();
			
		}
	},
	ADD_100_GOLD {
		@Override
		public void apply(Civilization civilization) {
			civilization.addGold(100);
			
		}
	},
	ADD_100_SCIENCE {
		@Override
		public void apply(Civilization civilization) {
			civilization.addScience(100);
		}
	},
	GROW_ALL_CITIES {
		@Override
		public void apply(Civilization civilization) {
			for (City city : civilization.getCities()){
				city.growCity();
			}
		}
	},
	END_CURRENT_RESEARCH {
		@Override
		public void apply(Civilization civilization) {
			civilization.endCurrentResearchTech();
		}
	},
	BUY_A_TILE_FOR_CAPITAL {
		@Override
		public void apply(Civilization civilization) {
			City city = civilization.getCapitalCity();
			ArrayList<Tile> tiles = city.getPossibleTilesToBuy();
			if (tiles.isEmpty()) return ;
			city.addTile(tiles.get(0));
		}
	},
	BUY_A_TILE_FOR_ALL_CIIES {
		@Override
		public void apply(Civilization civilization) {
			for (City city : civilization.getCities()){
				ArrayList<Tile> tiles = city.getPossibleTilesToBuy();
				if (tiles.isEmpty()) continue ;
				city.addTile(tiles.get(0));
			}
		}
	},
	HEAL_ALL_UNITS {
		@Override
		public void apply(Civilization civilization) {
			for (Unit unit : civilization.getUnits()){
				unit.resetHP();
			}
		}
	},
	RESEARCH_ALL_AVAILABLE_TECHS {
		@Override
		public void apply(Civilization civilization) {
			ArrayList<Technology> technologies =  civilization.getResearchableTechnologies();
			for (Technology technology : technologies){
				civilization.addTechnology(technology);
			}
		}
	},
	FINNISH_ALL_CITY_PROJECTS {
		@Override
		public void apply(Civilization civilization) {
			for (City city: civilization.getCities()){
				city.endActiveProject();
			}
		}
	};

	public static ArrayList<CheatCode> getAllCheated() {
		ArrayList<CheatCode> out = new ArrayList<>();
		for (CheatCode cheatCode : CheatCode.values()) {
			out.add(cheatCode);
		}
		return out;
	}

	public abstract void apply(Civilization civilization);

}
