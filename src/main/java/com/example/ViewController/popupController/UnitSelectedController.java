package com.example.ViewController.popupController;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UnitSelectedController {
    public Button Delete_btn;
    public Button move_btn;
    public Button meleeAttack_btn;
    public Button rangeAtaack_btn;
    public Button preAttackSetup_btn;
    public Button sleep_wake_btn;
    public Button alert_btn;
    public Button pillage_btn;
    public Button foundcity_btn;
    public Button workerAction_btn;
    public Button back_btn;

    public void Delete(MouseEvent mouseEvent) {
    }

    public void move(MouseEvent mouseEvent) {
    }

    public void meleeAttack(MouseEvent mouseEvent) {
    }

    public void rangeAttack(MouseEvent mouseEvent) {
    }

    public void preAttackSetUp(MouseEvent mouseEvent) {
    }

    public void sleep_wake(MouseEvent mouseEvent) {
    }

    public void alert(MouseEvent mouseEvent) {
    }

    public void pillage(MouseEvent mouseEvent) {
    }

    public void foundCity(MouseEvent mouseEvent) {
    }

    public void woerkerAction(MouseEvent mouseEvent) {
    }

    public void back(MouseEvent mouseEvent) {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();
    }
}
