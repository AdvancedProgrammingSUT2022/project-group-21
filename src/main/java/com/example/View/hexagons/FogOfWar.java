package com.example.View.hexagons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class FogOfWar extends Polygon {
    public final static double r = 80; // the inner radius from hexagon center to outer corner
    public final static double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the
    // axis
    public final static double TILE_HEIGHT = 2 * r;
    public final static double TILE_WIDTH = 2 * n;
    public FogOfWar(double x,double y){
        // creates the polygon using the corner coordinates
        getPoints().addAll(x, y, x, y + r, x + n, y + r * 1.5, x + TILE_WIDTH, y + r, x + TILE_WIDTH, y, x + n,
                y - r * 0.5);
        setFill(Color.BLACK);
    }
}
