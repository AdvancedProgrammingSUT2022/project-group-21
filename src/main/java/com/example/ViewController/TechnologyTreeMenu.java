package com.example.ViewController;

import com.example.View.TechnologyTree;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class TechnologyTreeMenu {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private void backButtonPressed(ActionEvent event) {

	}

	@FXML
	private void initialize(){
		TechnologyTree.putTechnologiesOnAnchorPane(anchorPane);
	}

}
