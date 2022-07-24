package com.example.ViewController.popupController;

import com.example.Contoller.GameController;
import com.example.Model.Game;
import com.example.Model.UserAction.UnitUserAction;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.city.Building;
import com.example.Model.tile.Improvement;
import com.example.Model.unit.Unit;
import com.example.Model.unit.WorkerProject;
import com.example.ViewController.Dialog;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

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

    private static Unit unit;

    public static void setUnit(Unit u) {
        unit = u;
    }
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
        if (unit == null) {
            Dialog.error_message("Error", "please select a unit first!");
            return;
        }
        int x1 = unit.getTile().X;
        int y1 = unit.getTile().Y;
        int x2;
        int y2;

    }

    public void alert(MouseEvent mouseEvent) {
    }

    public void pillage(MouseEvent mouseEvent) {
    }

    public void foundCity(MouseEvent mouseEvent) {
        if (unit == null) {
            Dialog.error_message("Error", "please select a unit first!");
            return;
        }
        int x1 = unit.getTile().X;
        int y1 = unit.getTile().Y;
        UserActionQuery userActionQuery = UnitUserAction.foundCity(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "done successfully");
        }
    }

    public void woerkerAction(MouseEvent mouseEvent) {
        if (unit == null) {
            Dialog.error_message("Error", "please select a unit first!");
            return;
        }
        int x1 = unit.getTile().X;
        int y1 = unit.getTile().Y;
        WorkerProject.WorkerProjectType workerProjectType = null;
        try {
            ArrayList<String> list = new ArrayList<>();
            ArrayList<WorkerProject.WorkerProjectType> bf =
                    WorkerProject.WorkerProjectType.getAllType();
            for (WorkerProject.WorkerProjectType w : bf) {
                list.add(w.name());
//                todo: check to work correctly
            }
            String type = Dialog.selectFromComboBox("select a worker project type", list);
            for (WorkerProject.WorkerProjectType w : bf) {
                if (w.name().equals(type)) {
                    workerProjectType = w;
                    break;
                }
            }
        } catch (Exception e) {
            Dialog.error_message("Error", e.getMessage());
            return;
        }
        Improvement improvement = null;
        try {
            ArrayList<String> list = new ArrayList<>();
            ArrayList<Improvement> bf = Improvement.getAllType();
            for (Improvement i : bf) {
                list.add(i.name());
//                todo: check to work correctly
            }
            String type = Dialog.selectFromComboBox("select a worker project type", list);
            for (Improvement i : bf) {
                if (i.name().equals(type)) {
                    improvement = i;
                    break;
                }
            }
        } catch (Exception e) {
            Dialog.error_message("Error", e.getMessage());
            return;
        }
        UserActionQuery userActionQuery = UnitUserAction.workerAction(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, workerProjectType, improvement
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "done successfully");
        }
    }

    public void back(MouseEvent mouseEvent) {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();
    }
}
