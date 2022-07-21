package com.example.hexagons;

import com.example.Model.Civilization;
import com.example.Model.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
public class Map extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ScrollPane scrollPane = createScrollPane(null,null);
        Scene scene = new Scene(scrollPane);
        stage.setTitle("Map");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public ScrollPane createScrollPane(Game game, Civilization civilization)
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1280, 720);
        Pane content = new Pane();
        content.setPrefSize(1500,1000);
        scrollPane.setContent(content);
        addHexagons(content,game);
        return  scrollPane;
    }
    private void addHexagons(Pane pane,Game game)
    {
          for(int j=0;j<game.HEIGHT;j++)
              for(int i=0;i<game.WIDTH;i++)
              {
                  if(j%2==0)
                  {
                      HexagonGraphicTile tile = new HexagonGraphicTile(200+i*2*
                              HexagonGraphicTile.getN(), 200+
                              HexagonGraphicTile.getR()*1.5*j,j,i,game.getTile(j,i));
                      pane.getChildren().addAll(tile,tile.getCoordinates(),tile.getMainButton(),tile.getRightButton()
                      ,tile.getHigherLeftButton(),tile.getLowerLeftButton());
                  }
                  if(j%2==1)
                  {
                      HexagonGraphicTile tile = new HexagonGraphicTile(200+
                              HexagonGraphicTile.getN()+i*2*
                              HexagonGraphicTile.getN(), 200+
                              HexagonGraphicTile.getR()*1.5*j,j,i,game.getTile(j,i));
                      pane.getChildren().addAll(tile,tile.getCoordinates(),tile.getMainButton(),tile.getRightButton()
                              ,tile.getHigherLeftButton(),tile.getLowerLeftButton());
                  }
              }
    }

    public static void main(String[] args) {
        launch();
    }
}