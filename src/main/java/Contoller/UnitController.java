package Contoller;

import Enums.Message;
import Models.Improvement;
import Models.Unit.Unit;

public class UnitController {
	private static UnitController instance;
	private static Unit selectedUnit;

	public static UnitController getInstance(){
		if (instance==null) instance=new UnitController();
		return instance;
	}

	public Message moveUnit(Unit unit, int x, int y) {
		//TODO:
		return null;
	}

	public Message sleep() {
		//TODO:
		return null;
	}

	public Message alert() {
		//TODO:
		return null;
	}

	public Message fortify(boolean isHealing) {
		//TODO:
		return null;
	}

	public Message garrison() {
		//TODO:
		return null;
	}

	public Message setupForRangedAttack() {
		//TODO:
		return null;
	}

	public Message attack(int x, int y) {
		//TODO:
		return null;
	}

	public Message findCity() {
		//TODO:
		return null;
	}

	public Message cancelMissions() {
		//TODO:
		return null;
	}

	public Message wake() {
		//TODO:
		return null;
	}

	public Message delete() {
		//TODO:
		return null;
	}

	public Message buildRoad(boolean isNormal) {
		//TODO
		return null;
	}

	public Message build(Improvement improvement) {
		//TODO:
		return null;
	}

	public Message removeRoad() {
		//TODO:
		return null;
	}

	public Message removeJungle() {
		//TODO:
		return null;
	}

	public Message repair() {
		//TODO:
		return null;
	}



	public static Unit getSelectedUnit() {
		return selectedUnit;
	}

	public static void setSelectedUnit(Unit selectedUnit) {
		UnitController.selectedUnit = selectedUnit;
	}
}
