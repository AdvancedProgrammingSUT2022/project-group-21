package com.example.View.button;

import com.example.Model.city.City;

import javafx.scene.shape.Rectangle;


public class CitySelectButton extends Rectangle {
	private City city;

	public CitySelectButton(City city) {
		this.city = city;
	}
}
