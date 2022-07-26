package com.example.View.button;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;
import com.example.View.popup.Popup;
import com.example.View.popup.UnitSelectedView;
import com.example.ViewController.popupController.UnitSelectedController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;

public class CivilianUnitSelectButton extends CustomGameButton{

	public CivilianUnitSelectButton(Tile tile) {
		super(tile);
	}
	public void update(Civilization civilization) {
		try {
			setFill(new ImagePattern(new Image(getClass().getResource("/Unit/" +
					tile.getCivilianUnit().unitType.name().toLowerCase() + ".png").toExternalForm())));
		} catch (Exception e) {
			setFill(Color.TRANSPARENT);
		}
		if(!civilization.isTileRevealed(tile.X, tile.Y))
		{
			this.setVisible(false);
			this.setOnMouseClicked(null);
			this.setCursor(Cursor.DEFAULT);
			return;
		}
		else
		{
			this.setVisible(true);
		}
		if(tile.getOwner()!=civilization) {
			this.setOnMouseClicked(null);
			this.setCursor(Cursor.DEFAULT);
			return;
		}
		else{
			this.setVisible(true);
			this.setCursor(Cursor.HAND);
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					UnitSelectedController.setCivilianUnit(tile.getCivilianUnit());
					UnitSelectedController.setMilitaryUnit(tile.getMilitaryUnit());
					Popup popup = new UnitSelectedView();
					try {
						popup.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
}
