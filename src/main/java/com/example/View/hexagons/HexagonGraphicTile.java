package com.example.View.hexagons;

import com.example.Model.Civilization;
import com.example.Model.city.City;
import com.example.Model.tile.Tile;
import com.example.View.button.CitySelectButton;
import com.example.View.button.TileSelectButton;
import com.example.View.button.UnitSelectButton;

import com.example.View.popup.CitySelectedView;
import com.example.View.popup.Popup;
import com.example.View.popup.TileSelectedView;
import com.example.View.popup.UnitSelectedView;
import com.example.ViewController.Dialog;
import com.example.ViewController.popupController.CitySelectedController;
import com.example.ViewController.popupController.TileSelectedController;
import com.example.ViewController.popupController.UnitSelectedController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Locale;

public class HexagonGraphicTile extends Polygon {
	public final static double r = 80; // the inner radius from hexagon center to outer corner
	public final static double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the
																// axis
	public final static double TILE_HEIGHT = 2 * r;
	public final static double TILE_WIDTH = 2 * n;
	
	public Label coordinates;
	public Rectangle mainButton; // دایره قرمز :) اطلاعات
	public Rectangle rightButton;// نوار آبی دراز وسط که تایل سلکت میشه فقط برای اونر میاد
	public Rectangle lowerLeftButton; // سمت چپ پایینه اگر باشه فقط برای اونور ، و سیویلیان یونیت انتخاب میشه
	public Rectangle higherLeftButton; // عین بالا برای میلیتاری یونیت ها
    public Tile tile;
	public HexagonGraphicTile(double x, double y, int j, int i, Tile tile, Civilization civilization) throws IOException {
		this.tile=tile;
		setCoordinates(x, y, j, i);
			setMainButton(x, y, j, i, tile);
			setRightButton(x, y, j, i, tile.getCityOnTile());
			setHigherLeftButton(x, y, j, i, tile);
			setLowerLeftButton(x, y, j, i, tile);
		// creates the polygon using the corner coordinates
		getPoints().addAll(x, y, x, y + r, x + n, y + r * 1.5, x + TILE_WIDTH, y + r, x + TILE_WIDTH, y, x + n,
				y - r * 0.5);
		setFill(new ImagePattern(new Image(getClass().getResource("/Terrain/"+
				tile.getTerrain().name().toLowerCase()+".png").toExternalForm())));
		setOpacity(0.7);
		setStrokeWidth(1);
		setStroke(Color.BLACK);

	}

	public void setRightButton(double x, double y, int j, int i, City city) {
		 rightButton = new CitySelectButton(city);
		rightButton = new Rectangle();
		rightButton.setLayoutX(x + 60);
		rightButton.setLayoutY(y +10);
		rightButton.setWidth(20);
		rightButton.setHeight(60);
		rightButton.setFill(Color.PALETURQUOISE);

		rightButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.isShiftDown()) {
					CitySelectedController.setCity(city);
					Popup popup = new CitySelectedView();
					try {
						popup.show();
					} catch (Exception e) {
						Dialog.error_message("Error", e.getMessage());
					}
				}
				else {
					TileSelectedController.setCivilization(city.getOwner());
					Popup popup = new TileSelectedView();
					try {
						popup.show();
					} catch (Exception e) {
						Dialog.error_message("Error", e.getMessage());
					}
				}
			}
		});

	}

	private void setMainButton(double x, double y, int j, int i, Tile tile) 	{
		mainButton = new TileSelectButton(tile);
		mainButton.setLayoutX(x + 90);
		mainButton.setLayoutY(y + 20);
		mainButton.setWidth(40);
		mainButton.setHeight(40);
		mainButton.setFill(new ImagePattern(
				new Image(getClass().getResource("/status.png").toExternalForm())));
//		todo: show information like gold and ... , every body can clicked it
	}

	private void setHigherLeftButton(double x, double y, int j, int i, Tile tile) throws IOException {
		higherLeftButton = new UnitSelectButton(tile.getMilitaryUnit());
		higherLeftButton.setLayoutX(x + 10);
		higherLeftButton.setLayoutY(y + 5);
		higherLeftButton.setWidth(30);
		higherLeftButton.setHeight(30);
		try {
			higherLeftButton.setFill(new ImagePattern(new Image(getClass().getResource("/Unit/"+
					tile.getMilitaryUnit().unitType.name().toLowerCase()+".png").toExternalForm())));
		}
		catch (Exception e)
		{
			higherLeftButton.setFill(Color.TRANSPARENT);
		}
		higherLeftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				UnitSelectedController.setUnit(tile.getMilitaryUnit());
				Popup popup = new UnitSelectedView();
				try {
					popup.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		//		todo: open unit popup for military unit
		setCursor(Cursor.HAND);
	}

	private void setLowerLeftButton(double x, double y, int j, int i, Tile tile) throws IOException {
		lowerLeftButton = new UnitSelectButton(tile.getCivilianUnit());
		lowerLeftButton.setLayoutX(x + 10);
		lowerLeftButton.setLayoutY(y + 45);
		lowerLeftButton.setWidth(30);
		lowerLeftButton.setHeight(30);
		try {
			lowerLeftButton.setFill(new ImagePattern(new Image(getClass().getResource("/Unit/" +
					tile.getCivilianUnit().unitType.name().toLowerCase() + ".png").toExternalForm())));
		} catch (Exception e) {
			lowerLeftButton.setFill(Color.TRANSPARENT);
		}
		//		todo: open unit popup for Civilian unit
		lowerLeftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				UnitSelectedController.setUnit(tile.getCivilianUnit());
				Popup popup = new UnitSelectedView();
				try {
					popup.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		setCursor(Cursor.HAND);
	}

	private void setCoordinates(double x, double y, int j, int i) {
		coordinates = new Label("y:" + j + ", x:" + i);
		coordinates.setLayoutX(x + 50);
		coordinates.setLayoutY(y - 15);
		coordinates.setBackground(new Background(new BackgroundFill(Color.CYAN, null, null)));
		// Dear Ali, you might be asking: "Why Cyan?"
		// Because I like it, that's why :)
		coordinates.setTextFill(Color.DEEPPINK);
	}
    ////////////////////////////For Neighbour test
	public void setCoordinatesText() {
		coordinates.setText("Neighbour");
	}
}


