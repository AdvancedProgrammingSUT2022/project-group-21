package com.example.ViewController.popupController;

import com.example.Contoller.GameController;
import com.example.Model.Game;
import com.example.Model.UserAction.UnitActionType;
import com.example.Model.UserAction.UnitUserAction;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.city.Building;
import com.example.Model.tile.Improvement;
import com.example.Model.unit.CivilianUnit;
import com.example.Model.unit.MilitaryUnit;
import com.example.Model.unit.Unit;
import com.example.Model.unit.WorkerProject;
import com.example.ViewController.Dialog;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

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

    private static MilitaryUnit militaryUnit;
    private static CivilianUnit civilianUnit;

    public static void setMilitaryUnit(MilitaryUnit u) {
        militaryUnit = u;
    }

    public static void setCivilianUnit(CivilianUnit c) {
        civilianUnit = c;
    }
    public void Delete(MouseEvent mouseEvent) {
        int x1 = civilianUnit.getTile().X;
        int y1 = civilianUnit.getTile().Y;
        boolean isMilitary = false;
        try {
            String militaryOrNot =
                    Dialog.selectFromComboBox("is Military ?" , new ArrayList<>(Arrays.asList("Yes", "No")));
            isMilitary = militaryOrNot.endsWith("YES") ? true : false;
        }catch (Exception e) {
            Dialog.error_message("Error", e.getMessage());
        }
        UserActionQuery userActionQuery = UnitUserAction.delete(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, isMilitary
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "delete done successfully");
        }
    }

    public void meleeAttack(MouseEvent mouseEvent) {
        int x1 = militaryUnit.getTile().X;
        int y1 = militaryUnit.getTile().Y;
        UserActionQuery userActionQuery = UnitUserAction.singleTileMilitary(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, UnitActionType.MELEE_ATTACK
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "melee attack done successfully");
        }
    }

    public void preAttackSetUp(MouseEvent mouseEvent) {
        int x1 = militaryUnit.getTile().X;
        int y1 = militaryUnit.getTile().Y;
        UserActionQuery userActionQuery = UnitUserAction.singleTileMilitary(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, UnitActionType.PRE_ATTACK_SETUP
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "pre attack setup done successfully");
        }
    }

    public void alert(MouseEvent mouseEvent) {
        int x1 = militaryUnit.getTile().X;
        int y1 = militaryUnit.getTile().Y;
        UserActionQuery userActionQuery = UnitUserAction.singleTileMilitary(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, UnitActionType.ALERT
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "alert done successfully");
        }
    }

    public void pillage(MouseEvent mouseEvent) {
        int x1 = militaryUnit.getTile().X;
        int y1 = militaryUnit.getTile().Y;
        UserActionQuery userActionQuery = UnitUserAction.singleTileMilitary(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, UnitActionType.PILLAGE
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "pillage done successfully");
        }
    }

    public void move(MouseEvent mouseEvent) {
        int x1 = militaryUnit.getTile().X;
        int y1 = militaryUnit.getTile().Y;
        int x2;
        int y2;
        boolean isMilitary;
        try {
            x2 = Integer.parseInt(
                    Dialog.AskQuestion("", "x2:")
            );
            y2 = Integer.parseInt(
                    Dialog.AskQuestion("", "y2:")
            );
            String militaryOrNot =
                    Dialog.selectFromComboBox("is Military ?" , new ArrayList<>(Arrays.asList("Yes", "No")));
            isMilitary = militaryOrNot.endsWith("YES") ? true : false;
        } catch (Exception e) {
            Dialog.error_message("Error", e.getMessage());
            return;
        }
        UserActionQuery userActionQuery = UnitUserAction.move(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, x2, y2, isMilitary
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "move successfully");
        }
    }

    public void rangeAttack(MouseEvent mouseEvent) {
        int x1 = militaryUnit.getTile().X;
        int y1 = militaryUnit.getTile().Y;
        int x2;
        int y2;
        try {
            x2 = Integer.parseInt(
                    Dialog.AskQuestion("", "x2:")
            );
            y2 = Integer.parseInt(
                    Dialog.AskQuestion("", "y2:")
            );
        } catch (Exception e) {
            Dialog.error_message("Error", e.getMessage());
            return;
        }
        UserActionQuery userActionQuery = UnitUserAction.rangeAttack(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, x2, y2
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "range attack successfully");
        }
    }

    public void sleep_wake(MouseEvent mouseEvent) {
        int x1 = militaryUnit.getTile().X;
        int y1 = militaryUnit.getTile().Y;
        boolean isMilitary;
        try {
            String militaryOrNot =
            Dialog.selectFromComboBox("is Military ?" , new ArrayList<>(Arrays.asList("Yes", "No")));
            isMilitary = militaryOrNot.endsWith("YES") ? true : false;
        } catch (Exception e) {
            Dialog.error_message("Error", e.getMessage());
            return;
        }
        UserActionQuery userActionQuery = UnitUserAction.sleepWake(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, isMilitary
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "done successfully");
        }
    }

    public void foundCity(MouseEvent mouseEvent) {
        int x1 = militaryUnit.getTile().X;
        int y1 = militaryUnit.getTile().Y;
        UserActionQuery userActionQuery = UnitUserAction.foundCity(
                Game.getInstance().getCurrentPlayer().getUsername(), x1, y1
        );
        if(GameController.getInstance().handleQueryFromView(userActionQuery)) {
            Dialog.information_message("", "done successfully");
        }
    }

    public void woerkerAction(MouseEvent mouseEvent) {
        int x1 = civilianUnit.getTile().X;
        int y1 = civilianUnit.getTile().Y;
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
