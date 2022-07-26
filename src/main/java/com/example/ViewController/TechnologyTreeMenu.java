package com.example.ViewController;

import com.example.App;
import com.example.View.TechnologyTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TechnologyTreeMenu {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button back;

	@FXML
	private void backButtonPressed(ActionEvent event) throws IOException {
		Stage stage = (Stage) back.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void initialize(){
		TechnologyTree.putTechnologiesOnAnchorPane(anchorPane);
	}

	public static void show() {
		Stage stage = new Stage();
		try {
			Scene scene = new Scene(App.loadFXML("TechnologyTreePage"));
			stage.setScene(scene);
		} catch (IOException e) {
			Dialog.error_message("Error", e.getMessage());
		}
		stage.show();
	}

}
