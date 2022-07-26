package com.example.View.hexagons;

import com.example.Model.Civilization;
import com.example.Model.tile.Tile;
import com.example.View.button.CitySelectButton;
import com.example.View.button.CivilianUnitSelectButton;
import com.example.View.button.MilitaryUnitSelectButton;
import com.example.View.button.TileSelectButton;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class HexagonGraphicTile extends Polygon {
	public final static double r = 80; // the inner radius from hexagon center to outer corner
	public final static double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the
															// axis
	public final static double TILE_HEIGHT = 2 * r;
	public final static double TILE_WIDTH = 2 * n;
    public static final ImagePattern cityImage=new ImagePattern(new Image(HexagonGraphicTile.class.getResource("/city.png").toExternalForm()));
	public static final ImagePattern statusImage=new ImagePattern(new Image(HexagonGraphicTile.class.getResource("/status.png").toExternalForm()));

	public Label coordinates;
	public TileSelectButton mainButton; // دایره قرمز :) اطلاعات
	public CitySelectButton rightButton;// نوار آبی دراز وسط که تایل سلکت میشه فقط برای اونر میاد
	public CivilianUnitSelectButton lowerLeftButton; // سمت چپ پایینه اگر باشه فقط برای اونور ، و سیویلیان یونیت انتخاب
														// میشه
	public MilitaryUnitSelectButton higherLeftButton; // عین بالا برای میلیتاری یونیت ها
	public Tile tile;
    public FogOfWar fogOfWar;
	public HexagonGraphicTile(double x, double y, int j, int i, Tile tile){
		this.tile = tile;
		fogOfWar=new FogOfWar(x,y);
		setCoordinates(x, y, j, i);
		setMainButton(x, y, j, i);
		setRightButton(x, y, j, i);
		setHigherLeftButton(x, y, j, i);
		setLowerLeftButton(x, y, j, i);
		// creates the polygon using the corner coordinates
		getPoints().addAll(x, y, x, y + r, x + n, y + r * 1.5, x + TILE_WIDTH, y + r, x + TILE_WIDTH, y, x + n,
				y - r * 0.5);
		setFill(new ImagePattern(new Image(getClass().getResource("/Terrain/" +
				tile.getTerrain().name().toLowerCase() + ".png").toExternalForm())));
		setOpacity(0.7);
		setStrokeWidth(1);
		setStroke(Color.BLACK);
	}

	public void setRightButton(double x, double y, int j, int i) {
		rightButton = new CitySelectButton(tile);
		rightButton.setLayoutX(x + 50);
		rightButton.setLayoutY(y + 20);
		rightButton.setWidth(40);
		rightButton.setHeight(40);
		rightButton.setFill(cityImage);
		rightButton.setCursor(Cursor.HAND);
	}

	private void setMainButton(double x, double y, int j, int i) {
		mainButton = new TileSelectButton(tile);
		mainButton.setLayoutX(x + 90);
		mainButton.setLayoutY(y + 20);
		mainButton.setWidth(40);
		mainButton.setHeight(40);
		mainButton.setFill(statusImage);
		// todo: show information like gold and ... , every body can clicked it
		mainButton.setCursor(Cursor.HAND);

	}

	private void setHigherLeftButton(double x, double y, int j, int i){
		higherLeftButton = new MilitaryUnitSelectButton(tile);
		higherLeftButton.setLayoutX(x + 10);
		higherLeftButton.setLayoutY(y + 5);
		higherLeftButton.setWidth(30);
		higherLeftButton.setHeight(30);
		// todo: open unit popup for military unit
	}

	private void setLowerLeftButton(double x, double y, int j, int i) {
		lowerLeftButton = new CivilianUnitSelectButton(tile);
		lowerLeftButton.setLayoutX(x + 10);
		lowerLeftButton.setLayoutY(y + 45);
		lowerLeftButton.setWidth(30);
		lowerLeftButton.setHeight(30);
		// todo: open unit popup for Civilian unit	
	}

	private void setCoordinates(double x, double y, int j, int i){
		coordinates = new Label("(x: " + i + ", y: " + j+")");
		coordinates.setLayoutX(x + 50);
		coordinates.setLayoutY(y - 15);
		coordinates.setBackground(new Background(new BackgroundFill(Color.CYAN, null, null)));
		// Dear Ali, you might be asking: "Why Cyan?"
		// Because I like it, that's why :)
		coordinates.setTextFill(Color.DEEPPINK);
	}

	public void update(Civilization civilization) {
		lowerLeftButton.update(civilization);
		higherLeftButton.update(civilization);
		mainButton.update(civilization);
		rightButton.update(civilization);
		if(civilization.isTileRevealed(tile.X,tile.Y))
			fogOfWar.setVisible(false);
		else
			fogOfWar.setVisible(true);
	}
}
