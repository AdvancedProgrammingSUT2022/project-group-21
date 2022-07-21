package com.example.View.button;

import com.example.Model.city.City;
import javafx.scene.control.Button;


public class CitySelectButton extends Button {
	private City city;

	public CitySelectButton(City city) {
		this.city = city;
	}
}
