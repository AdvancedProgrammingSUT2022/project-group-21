package com.example.View.hexagons;

import com.example.Model.Civilization;
import com.example.Model.Game;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

public class MapPaneMaker {
	private static ArrayList<HexagonGraphicTile> hexagonGraphicTiles=new ArrayList<>();
	public static Pane createScrollPane(Game game, Civilization civilization) throws IOException {
		// ScrollPane scrollPane = new ScrollPane();
		// scrollPane.setPrefSize(960, 680);
		// scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		// scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		// scrollPane.setPannable(true);
		Pane content = new Pane();
		content.setPrefSize(HexagonGraphicTile.TILE_WIDTH*game.WIDTH+400, HexagonGraphicTile
				.TILE_HEIGHT*3*game.HEIGHT/4+400);
		// scrollPane.setContent(content);
		addHexagons(content, game, civilization);
		// return scrollPane;
		return content;
	}

	private static void addHexagons(Pane pane, Game game, Civilization civilization) throws IOException {
		for (int j = 0; j < game.HEIGHT; j++)
			for (int i = 0; i < game.WIDTH; i++) {
				HexagonGraphicTile hex;
				if (j % 2 == 0){
					hex = new HexagonGraphicTile(
							200 + i * 2 * HexagonGraphicTile.n,
							200 + HexagonGraphicTile.r * 1.5 * j, j, i, game.getTile(i, j),
							civilization);
				}
				else{
					hex = new HexagonGraphicTile(200 +
							HexagonGraphicTile.n + i * 2 *
									HexagonGraphicTile.n,
							200 +
									HexagonGraphicTile.r * 1.5 * j,
							j, i, game.getTile(i, j),civilization);
				}
				pane.getChildren().add(hex);
				pane.getChildren().add(hex.coordinates);
				addButton(pane, hex.mainButton);
				addButton(pane, hex.rightButton);
				addButton(pane, hex.higherLeftButton);
				addButton(pane, hex.lowerLeftButton);
              if(game.getTile(i, j).getOwner()!=civilization) {
				  hex.rightButton.setVisible(false);
				  hex.higherLeftButton.setVisible(false);
				  hex.lowerLeftButton.setVisible(false);
			  }
			  hexagonGraphicTiles.add(hex);
			  /////////////////////////NeighbourTest
			  if(game.getTile(5, 7).isNeighbourWith(game.getTile(i, j)))
				  hex.setCoordinatesText();
			}
	}
	private static void addButton(Pane pane, Rectangle button){
		if (button==null) return ;
		pane.getChildren().add(button);
	}
   public static void updateButtons(Civilization civilization)
	{
		for(HexagonGraphicTile hexagonGraphicTile:hexagonGraphicTiles)
		{
			if(hexagonGraphicTile.tile.getOwner()!=civilization)
			{
				hexagonGraphicTile.rightButton.setVisible(false);
				hexagonGraphicTile.rightButton.setOnMouseClicked(null);
				hexagonGraphicTile.rightButton.setCursor(Cursor.DEFAULT);

				hexagonGraphicTile.higherLeftButton.setVisible(false);
				hexagonGraphicTile.higherLeftButton.setOnMouseClicked(null);
				hexagonGraphicTile.higherLeftButton.setCursor(Cursor.DEFAULT);

				hexagonGraphicTile.lowerLeftButton.setVisible(false);
				hexagonGraphicTile.lowerLeftButton.setOnMouseClicked(null);
				hexagonGraphicTile.lowerLeftButton.setCursor(Cursor.DEFAULT);
			}
			else
			{
//				todo: bayad begi chikar konam :D

				hexagonGraphicTile.rightButton.setVisible(true);
				hexagonGraphicTile.rightButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (mouseEvent.isShiftDown()) {
							CitySelectedController.setCity(hexagonGraphicTile.tile.getCityOnTile());
							Popup popup = new CitySelectedView();
							try {
								popup.show();
							} catch (Exception e) {
								Dialog.error_message("Error", e.getMessage());
							}
						}
						else {
							TileSelectedController.setCivilization(hexagonGraphicTile.tile.getOwner());
							Popup popup = new TileSelectedView();
							try {
								popup.show();
							} catch (Exception e) {
								Dialog.error_message("Error", e.getMessage());
							}
						}
					}
				});
				hexagonGraphicTile.higherLeftButton.setVisible(true);
				hexagonGraphicTile.higherLeftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						UnitSelectedController.setUnit(hexagonGraphicTile.tile.getMilitaryUnit());
						Popup popup = new UnitSelectedView();
						try {
							popup.show();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				hexagonGraphicTile.lowerLeftButton.setVisible(true);
				hexagonGraphicTile.lowerLeftButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						UnitSelectedController.setUnit(hexagonGraphicTile.tile.getCivilianUnit());
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
}
