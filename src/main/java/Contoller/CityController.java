package Contoller;

import Enums.Message;
import Models.City;
import Models.Civilization;
import Models.Terrain;
import Models.Tile;
import Models.Unit.UnitType;

public class CityController {
    private City city;
    public Message findCity(Tile tile, Civilization civilization) {
        //We assume that cities can only be established in neutral tiles
        if (tile.getOwner()!=null)      return Message.FAIL;
        if (tile.getTerrain().equals(Terrain.OCEAN) || tile.getTerrain().equals(Terrain.MOUNTAIN))
            return Message.UNSUITABLE_TERRAIN;
        city = new City(0, 0, tile, civilization);
        civilization.addCity(city);
        return Message.SUCCESS;
    }


    public Message assignCitizen(Tile tile) {
        if (!city.hasTile(tile)) {
            return Message.FAIL;
        }
        else if (tile.getWorkingCitizen() != null) {
            return Message.ALREADY_ASSIGNED;
        }
        city.lockCitizen(tile);
        return Message.SUCCESS;
    }





}
