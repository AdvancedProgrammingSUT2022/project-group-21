package com.example.ViewController.popupController;

import com.example.Contoller.GameController;
import com.example.Model.Game;
import com.example.Model.UserAction.CityUserAction;
import com.example.Model.UserAction.UserAction;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.city.Building;
import com.example.Model.city.City;
import com.example.Model.unit.UnitType;
import com.example.Model.user.UserDatabase;
import com.example.View.Notifier;
import com.example.View.popup.CitySelectedView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CitySelectedController{
    @FXML
    public Button LOCK_UNLOCK_CITIZEN;
    @FXML
    public Button SHOOT;
    @FXML
    public Button UNIT_PRODUCTION;
    @FXML
    public Button back;
    @FXML
    public Button building_production;
    @FXML
    public Button buy_building;
    @FXML
    public Button buy_unit;
    @FXML
    private AnchorPane pane;


    private static City city;

    public void setCity(City c) {
        city = c;
    }

    public void back(MouseEvent mouseEvent) {
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
    }

    //    TODO: قبلش به ایف بزار اگه اون یونیت/سیتی برا پلیر فعلی بود باز نکنه
    public void LOCK_UNLOCK_CITIZEN_func(MouseEvent mouseEvent) {
        int x1 = city.getCenter().X;
        int y1 = city.getCenter().Y;
        TextInputDialog t = new TextInputDialog();
        t.setContentText("x2 y2:(separated with space)");
        t.show();
        t.setTitle(null);
        t.setHeaderText(null);
        t.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent dialogEvent) {
                try {
                    String s[] = t.getEditor().getText().split(" ");
                    int x2 = Integer.parseInt(s[0]);
                    int y2 = Integer.parseInt(s[1]);
                    do_Locked_Unlock_Helper(x1, y1, x2, y2);
                }catch (Exception e) {
                    Notifier.error_message("Error", e.getMessage());
                }
            }
        });
    }

    private void do_Locked_Unlock_Helper(int x1, int y1, int x2, int y2) {
        UserActionQuery userAction = CityUserAction.lockUnlockCitizenToTile
                (Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, x2, y2);
        GameController.getInstance().handleQueryFromView(userAction);
    }

    public void SHOOT_func(MouseEvent mouseEvent) {
        int x1 = city.getCenter().X;
        int y1 = city.getCenter().Y;
        TextInputDialog t = new TextInputDialog();
        t.setContentText("x2 y2:(separated with space)");
        t.show();
        t.setTitle(null);
        t.setHeaderText(null);
        t.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent dialogEvent) {
                try {
                    String s[] = t.getEditor().getText().split(" ");
                    int x2 = Integer.parseInt(s[0]);
                    int y2 = Integer.parseInt(s[1]);
                    do_shoot(x1, y1, x2, y2);
                }catch (Exception e) {
                    Notifier.error_message("Error", e.getMessage());
                }
            }
        });
    }

    private void do_shoot(int x1, int y1, int x2, int y2) {
        UserActionQuery userAction = CityUserAction.shootTile
                (Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, x2, y2);
        GameController.getInstance().handleQueryFromView(userAction);
    }

    public void UNIT_PRODUCTION_func(MouseEvent mouseEvent) {
        int x1 = city.getCenter().X;
        int y1 = city.getCenter().Y;
//        TODO get Unit Producation
//        do_unit_production();
    }

    private void do_unit_production(int x1, int y1, UnitType unitType) {
        UserActionQuery userAction = CityUserAction.produceUnit
                (Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, unitType);
        GameController.getInstance().handleQueryFromView(userAction);
    }

    public void building_production_func(MouseEvent mouseEvent) {
//        TODO: complete methods
        //        do_building_production()
    }

    private void do_building_production(int x1, int y1, Building building) {
        UserActionQuery userAction = CityUserAction.produceBuilding
                (Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, building);
        GameController.getInstance().handleQueryFromView(userAction);
    }
    public void buy_tile_func(MouseEvent mouseEvent) {
        int x1 = city.getCenter().X;
        int y1 = city.getCenter().Y;
        TextInputDialog t = new TextInputDialog();
        t.setContentText("x2 y2:(separated with space)");
        t.show();
        t.setTitle(null);
        t.setHeaderText(null);
        t.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent dialogEvent) {
                try {
                    String s[] = t.getEditor().getText().split(" ");
                    int x2 = Integer.parseInt(s[0]);
                    int y2 = Integer.parseInt(s[1]);
                    do_buy_tile(x1, y1, x2, y2);
                }catch (Exception e) {
                    Notifier.error_message("Error", e.getMessage());
                }
            }
        });
    }

    private void do_buy_tile(int x1, int y1, int x2, int y2) {
        UserActionQuery userAction = CityUserAction.buyTile
                (Game.getInstance().getCurrentPlayer().getUsername(), x1, y1, x2, y2);
        GameController.getInstance().handleQueryFromView(userAction);
    }

    public void buy_building_func(MouseEvent mouseEvent) {

    }

    public void buy_unit_func(MouseEvent mouseEvent) {
    }
}
