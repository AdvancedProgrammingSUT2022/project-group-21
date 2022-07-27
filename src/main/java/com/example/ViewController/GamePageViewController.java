package com.example.ViewController;

import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.View.hexagons.MapPaneMaker;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class GamePageViewController {

    @FXML
    private AnchorPane infoAnchorPane;

    @FXML
    private ScrollPane scrollPane;

	@FXML
	private void initialize(){
		scrollPane.setContent(MapPaneMaker.createScrollPane(Game.getInstance(), Game.getInstance().getCurrentPlayer().getCivilization()));
	
	}


}
