package com.example.ViewController;

import com.example.View.TechnologyTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TechnologyTreeMenu {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private void backButtonPressed(ActionEvent event) throws IOException {

	}

	@FXML
	private void initialize(){
		TechnologyTree.putTechnologiesOnAnchorPane(anchorPane);
	}

}
