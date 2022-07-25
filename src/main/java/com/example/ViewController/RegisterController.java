package com.example.ViewController;

import com.example.Contoller.UserController;
import com.example.Model.user.User;
import com.example.Model.user.UserDatabase;
import com.example.View.LoginPage;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Random;

public class RegisterController {
    @FXML
    private ImageView avatar;
    @FXML
    private TextField username;
    @FXML
    private TextField nickname;
    @FXML
    private PasswordField password;
    @FXML
    private Button submit;
    @FXML
    private Label error;

    public void Submit(MouseEvent mouseEvent) throws IOException {
        register();
    }


    public void register() throws IOException {
        String username = this.username.getText().toString();
        String nickname = this.nickname.getText().toString();
        String password = this.password.getText().toString();
        if (username.equals("") || password.equals("")) {
            error.setText("all field must be filled");
            error.setVisible(true);
            return;
        }
        try {
            UserController.getInstance().registerUser(username, password, nickname);
        }catch (Exception e) {
            Dialog.error_message("Error", e.getMessage());
            return;
        }
        Dialog.information_message("", "You are register successfully");
    }


    public void Clear() {
        error.setVisible(false);
    }

    public void clear() {
        username.setText("");
        nickname.setText("");
        password.setText("");
    }

    public void EntetPressedCheck(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            register();
        }
        else {
            Clear();
        }
    }

    public void Back(MouseEvent mouseEvent) throws IOException {
        LoginPage.stage.setScene(
                new Scene(LoginPage.loadFXML("LoginPage"))
        );
    }
}
