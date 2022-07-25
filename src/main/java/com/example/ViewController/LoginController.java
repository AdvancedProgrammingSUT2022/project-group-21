package com.example.ViewController;

import java.io.IOException;

import com.example.App;
import com.example.Contoller.UserController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LoginController {
	public Button exit_btn;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button register;
	@FXML
	private Button guest;
	@FXML
	private Button submit;

	@FXML
	private Label error;


	public void Submit(MouseEvent mouseEvent) throws Exception {
		login();
	}

	public void login() throws Exception {
		String username = this.username.getText().toString();
		String password = this.password.getText().toString();
		try {
			UserController.getInstance().loginUser(username, password);
		}catch (Exception e) {
			Dialog.error_message("Error", e.getMessage());
			return;
		}
		Dialog.information_message("", "You are LoggedIn successfully!\n" +
				"wait to go to Main menu...");
		App.setRootFromFXML("MainMenu");
		// stage.setTitle("MainMenu");
		// stage.initStyle(StageStyle.UTILITY);
	}
	public void Register(MouseEvent mouseEvent) throws IOException {
		goToRegisterPage();
	}

	private void goToRegisterPage() throws IOException {
		App.setRootFromFXML("RegisterPage");
	}

	public void Clear() {
		error.setVisible(false);
	}

	public void checkEnterPressed(KeyEvent keyEvent) throws Exception {
		if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			login();
		}
		else {
			Clear();
		}
	}

	public void exit(MouseEvent mouseEvent) {
		System.exit(0);
	}
}