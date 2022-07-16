package com.example.Contoller;

import java.util.ArrayList;

import com.example.Model.city.City;
import com.example.Model.city.CityProject;

// TODO: check if this class can be removed
public class CityLogicController {
	private City city;
	private ArrayList<CityProject> cityProjects = new ArrayList<>();
	private CityProject currentProject;

	public CityLogicController(City city){
		this.city=city;
	}

	// TODO: check if its done in City.java
	public void addCityProject(CityProject project){
		// TODO: check is project is valid(prequisit techs ...)
		for (CityProject project2 : cityProjects) {
			if (project.similarProject(project2)){
				currentProject=project2;
				return ;
			}
		}
		cityProjects.add(project);
		currentProject=project;
	}

	public void beginTurnActions(){
		// TODO
	}
	public void endTurnActions(){
		// TODO
	}

	
	// TODO
	// public Message assignCitizenToTile(int x, int y) {
	// 	City selectedCity = SelectController.getInstance().getSelectedCity();
	// 	Tile tile = GameController.getInstance().getGame().getTile(x, y);
	// 	if (selectedCity == null)
	// 		return Message.NO_CITY_ON_TILE;
	// 	if (!selectedCity.hasTile(tile))
	// 		return Message.FAIL;
	// 	if (selectedCity.countCitizens() == 0)
	// 		return Message.FAIL;
	// 	if (tile.getWorkingCitizen() != null)
	// 		return Message.ALREADY_ASSIGNED;
	// 	Citizen citizen = selectedCity.getCitizenToAssign();
	// 	tile.setCitizen(citizen);
	// 	citizen.setWorkingTile(tile);
	// 	return Message.SUCCESS;
	// }

	// TODO
	// public Message removeCitizenFromWork(int x, int y) {
	// 	// TODO
	// 	return Message.SUCCESS;
	// }

	
	// TODO
	// public Message buyTile(int x, int y) {
	// 	City city = SelectController.getInstance().getSelectedCity();
	// 	Tile tile = GameController.getInstance().getGame().getTile(x, y);
	// 	if (tile.getOwner() != null)
	// 		return Message.FAIL;
	// 	Civilization civilization = city.getOwner();
	// 	int cost = 5;
	// 	if (civilization.getGold() < cost)
	// 		return Message.NOT_ENOUGH_GOLD;
	// 	civilization.addGold(-cost);
	// 	civilization.addTile(tile);
	// 	city.addTile(tile);
	// 	tile.setOwner(civilization);
	// 	return Message.SUCCESS;
	// }

	public String getCityInfo(City city) {
		// TODO
		return null;
	}


}
