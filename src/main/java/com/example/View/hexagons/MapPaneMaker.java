package com.example.View.hexagons;

import com.example.Model.Civilization;
import com.example.Model.Game;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;

public class MapPaneMaker {

	public static Pane createScrollPane(Game game, Civilization civilization) {
		// ScrollPane scrollPane = new ScrollPane();
		// scrollPane.setPrefSize(960, 680);
		// scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		// scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		// scrollPane.setPannable(true);
		Pane content = new Pane();
		content.setPrefSize(1500, 1000);
		// scrollPane.setContent(content);
		addHexagons(content, game, civilization);
		// return scrollPane;
		return content;
	}

	private static void addHexagons(Pane pane, Game game, Civilization civilization) {
		for (int j = 0; j < game.HEIGHT; j++)
			for (int i = 0; i < game.WIDTH; i++) {
				HexagonGraphicTile hex;
				if (j % 2 == 0){
					hex = new HexagonGraphicTile(200 + i * 2 * HexagonGraphicTile.n, 200 + HexagonGraphicTile.r * 1.5 * j, j, i, game.getTile(j, i));
				}
				else{
					hex = new HexagonGraphicTile(200 +
							HexagonGraphicTile.n + i * 2 *
									HexagonGraphicTile.n,
							200 +
									HexagonGraphicTile.r * 1.5 * j,
							j, i, game.getTile(j, i));
				}
				pane.getChildren().add(hex);
				pane.getChildren().add(hex.coordinates);

				addButton(pane, hex.mainButton);
				addButton(pane, hex.rightButton);
				addButton(pane, hex.higherLeftButton);
				addButton(pane, hex.lowerLeftButton);
			}
	}
	private static void addButton(Pane pane, Button button){
		if (button==null) return ;
		pane.getChildren().add(button);
	}

}