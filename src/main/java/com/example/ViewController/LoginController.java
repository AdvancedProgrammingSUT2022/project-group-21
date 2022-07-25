package com.example.ViewController;

import com.example.App;
import com.example.Contoller.UserController;
import com.example.Model.Civilization;
import com.example.Model.city.City;
import com.example.Model.tile.Terrain;
import com.example.Model.tile.Tile;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;
import com.example.View.LoginPage;
import com.example.View.MainMenu;
import com.example.View.popup.CitySelectedView;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private AudioClip audioClip;
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
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
        MainMenu.show();
    }
    public void Register(MouseEvent mouseEvent) throws IOException {
        goToRegisterPage();
    }

    private void goToRegisterPage() throws IOException {
        LoginPage.stage.setScene(
                new Scene(LoginPage.loadFXML("RegisterPage"))
        );
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
}