package com.example.View.hexagons;

import java.io.IOException;
import java.util.ArrayList;

import com.example.Model.Civilization;
import com.example.Model.Game;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class MapPaneMaker {
	private static ArrayList<HexagonGraphicTile> hexagonGraphicTiles = new ArrayList<>();

	public static Pane createScrollPane(Game game) throws IOException {
		Pane content = new Pane();
		content.setPrefSize(HexagonGraphicTile.TILE_WIDTH * game.WIDTH + 400,
				HexagonGraphicTile.TILE_HEIGHT * 3 * game.HEIGHT / 4 + 400);
		addHexagons(content, game);
		updateButtons(game.getCurrentPlayer().getCivilization());
		
		System.out.println(game.getCurrentPlayer().getCivilization().getCapitalCity().getCenter().X);
		System.out.println(game.getCurrentPlayer().getCivilization().getCapitalCity().getCenter().Y);

		return content;
	}

	private static void addHexagons(Pane pane, Game game) throws IOException {
		for (int j = 0; j < game.HEIGHT; j++)
			for (int i = 0; i < game.WIDTH; i++) {
				HexagonGraphicTile hex;
				if (j % 2 == 0) {
					hex = new HexagonGraphicTile(
							200 + i * 2 * HexagonGraphicTile.n,
							200 + HexagonGraphicTile.r * 1.5 * j, j, i, game.getTile(i, j));
				} else {
					hex = new HexagonGraphicTile(200 +
							HexagonGraphicTile.n + i * 2 *
									HexagonGraphicTile.n,
							200 +
									HexagonGraphicTile.r * 1.5 * j,
							j, i, game.getTile(i, j));
				}
				pane.getChildren().add(hex);
				pane.getChildren().add(hex.coordinates);
				addButton(pane, hex.mainButton);
				addButton(pane, hex.rightButton);
				addButton(pane, hex.higherLeftButton);
				addButton(pane, hex.lowerLeftButton);
				// if (game.getTile(i, j).getOwner() != civilization) {
				// 	hex.rightButton.setVisible(false);
				// 	hex.higherLeftButton.setVisible(false);
				// 	hex.lowerLeftButton.setVisible(false);
				// }
				hexagonGraphicTiles.add(hex);
			}
	}

	private static void addButton(Pane pane, Rectangle button) {
		if (button == null)
			return;
		pane.getChildren().add(button);
	}

	public static void updateButtons(Civilization civilization) {
		for (HexagonGraphicTile hexagonGraphicTile : hexagonGraphicTiles) {
			hexagonGraphicTile.update(civilization);
		}
	}
}
