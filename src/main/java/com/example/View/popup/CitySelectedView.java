package com.example.View.popup;

import java.io.IOException;

import com.example.App;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CitySelectedView implements Popup {
	public void show() throws IOException {
		Stage stage = new Stage();
		Scene scene = new Scene(App.loadFXML("CitySelectedPopup"));
		stage.setTitle("selected City");
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
	}
}
