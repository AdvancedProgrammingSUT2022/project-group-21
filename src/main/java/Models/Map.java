package Models;

import Contoller.GameController;
import Enums.Colors;


public class Map {
    private int HEIGHT = GameController.getInstance().getGame().HEIGHT;
    private int WIDTH = GameController.getInstance().getGame().WIDTH;
    private String[][] map = new String[this.HEIGHT][this.WIDTH];
    public void printMap(){
        for (int i = 0;i<HEIGHT;i++){
            for (int j = 0;j<WIDTH;j++){
                if (map[i][j].equals("DESERT")){
                    System.out.print(Colors.YELLOW_BACKGROUND + " " + Colors.RESET);
                }
                else if (map[i][j].equals("GRASSLAND")){
                    System.out.print(Colors.GREEN_BACKGROUND_BRIGHT + " " + Colors.RESET);
                }
                else if (map[i][j].equals("HILL")){
                    System.out.print(Colors.YELLOW_BACKGROUND_BRIGHT + " " + Colors.RESET);
                }
                else if(map[i][j].equals("MOUNTAIN")){
                    System.out.print(Colors.RED_BACKGROUND + " " + Colors.RESET);
                }
                else if (map[i][j].equals("OCEAN")){
                    System.out.print(Colors.BLUE_BACKGROUND + " " + Colors.RESET);
                }
                else if (map[i][j].equals("PLAIN")){
                    System.out.print(Colors.GREEN_BACKGROUND + " " + Colors.RESET);
                }
                else if (map[i][j].equals("SNOW")){
                    System.out.print(Colors.WHITE_BACKGROUND + " " + Colors.RESET);
                }
                else if (map[i][j].equals("TUNDRA")){
                    System.out.print(Colors.PURPLE_BACKGROUND + " " + Colors.RESET);
                }
                else if (map[i][j].charAt(0) == 'R'){
                    if (map[i][j].equals("R\\")){
                        System.out.print(Colors.CYAN_BACKGROUND + "\\" + Colors.RESET);
                    }
                    else if (map[i][j].equals("R/")){
                        System.out.print(Colors.CYAN_BACKGROUND + "/" + Colors.RESET);
                    }
                    else if (map[i][j].equals("R_")){
                        System.out.print(Colors.CYAN_BACKGROUND + "_" + Colors.RESET);
                    }
                }
                else if (map[i][j].equals("\\")){
                    System.out.print(Colors.BLACK_BACKGROUND + "\\" + Colors.RESET);
                }
                else if (map[i][j].equals("/")){
                    System.out.print(Colors.BLACK_BACKGROUND + "/" + Colors.RESET);
                }
                else if (map[i][j].equals("_")){
                    System.out.print(Colors.BLACK_BACKGROUND + "_" + Colors.RESET);
                }
                else {
                    System.out.print(Colors.BLACK_BACKGROUND + map[i][j] + Colors.RESET);
                }
            }
            System.out.println();
        }
    }
    public void setspace(){
        for (int i = 0;i<HEIGHT;i++){
            for (int j = 0;j<WIDTH;j++){
                map[i][j] = " ";
            }
        }
    }
    public void mapsetter(){
        setspace();
        int first_x = 0;
        int first_y = 0;
        for (int i = 0;i<HEIGHT;i++){
            hexsetter(map,first_x,first_y);
            first_y = first_y + 16;
        }
        for (int i = 0;i<WIDTH;i++){
            first_x = first_x + 3;

        }
        for (int i = 0;i<HEIGHT;i++){
            for (int j = 0;j<WIDTH;j++){
                hexsetter(map,first_x,first_y);
                first_y = first_y + 16;
            }
            if (i % 2 == 0){
                first_y = 8;
            }
            else {
                first_y = 0;
            }
            first_x = first_x + 3;
        }
    }
    public static void hexsetter(String map[][],int x,int y){
        Tile tile = GameController.getInstance().getGame().getTile(x,y);
        int copy_x = x;
        int copy_y = y;
        int row = 5;
        for (int i = 0;i<3;i++){
            for (int j = 0;j<(9-row)/2;j++){
                if (map[copy_x][copy_y].equals(" ")){
                    map[copy_x][copy_y] = " ";
                }
                copy_y = copy_y + 1;
            }
            if (tile.isRiver(5)){
                map[copy_x][copy_y] = "R/";
            }
            else {
                map[copy_x][copy_y] = "/";
            }
            copy_y = copy_y + 1;
            for (int j = 0;j<row;j++){
                if (tile.getTerrain().equals(Terrain.DESERT)){
                    map[copy_x][copy_y] = "DESERT";
                }
                else if (tile.getTerrain().equals(Terrain.GRASSLAND)){
                    map[copy_x][copy_y] = "GRASSLAND";
                }
                else if (tile.getTerrain().equals(Terrain.HILL)){
                    map[copy_x][copy_y] = "HILL";
                }
                else if (tile.getTerrain().equals(Terrain.MOUNTAIN)){
                    map[copy_x][copy_y] = "MOUNTAIN";
                }
                else if (tile.getTerrain().equals(Terrain.OCEAN)){
                    map[copy_x][copy_y] = "OCEAN";
                }
                else if (tile.getTerrain().equals(Terrain.PLAINS)){
                    map[copy_x][copy_y] = "PLAIN";
                }
                else if (tile.getTerrain().equals(Terrain.SNOW)){
                    map[copy_x][copy_y] = "SNOW";
                }
                else if (tile.getTerrain().equals(Terrain.TUNDRA)){
                    map[copy_x][copy_y] = "TUNDRA";
                }
                copy_y = copy_y + 1;
            }
            if (tile.isRiver(1)){
                map[copy_x][copy_y] = "R\\";
            }
            else {
                map[copy_x][copy_y] = "\\";
            }
            copy_y = copy_y + 1;
            for (int j = 0;j<(9-row)/2;j++){
                if (map[copy_x][copy_y].equals(" ")){
                    map[copy_x][copy_y] = " ";
                }
                copy_y = copy_y + 1;
            }
            row = row + 2;
            copy_x = copy_x + 1;
            copy_y = y;
        }
        row = 9;
        for (int i = 0;i<3;i++){
            for (int j = 0;j<(9-row)/2;j++){
                if (map[copy_x][copy_y].equals(" ")){
                    map[copy_x][copy_y] = " ";
                }
                copy_y = copy_y + 1;
            }
            if (tile.isRiver(4)){
                map[copy_x][copy_y] = "R\\";
            }
            else {
                map[copy_x][copy_y] = "\\";
            }
            copy_y = copy_y + 1;
            for (int j = 0;j<row;j++){
                if (tile.getTerrain().equals(Terrain.DESERT)){
                    map[copy_x][copy_y] = "DESERT";
                }
                else if (tile.getTerrain().equals(Terrain.GRASSLAND)){
                    map[copy_x][copy_y] = "GRASSLAND";
                }
                else if (tile.getTerrain().equals(Terrain.HILL)){
                    map[copy_x][copy_y] = "HILL";
                }
                else if (tile.getTerrain().equals(Terrain.MOUNTAIN)){
                    map[copy_x][copy_y] = "MOUNTAIN";
                }
                else if (tile.getTerrain().equals(Terrain.OCEAN)){
                    map[copy_x][copy_y] = "OCEAN";
                }
                else if (tile.getTerrain().equals(Terrain.PLAINS)){
                    map[copy_x][copy_y] = "PLAIN";
                }
                else if (tile.getTerrain().equals(Terrain.SNOW)){
                    map[copy_x][copy_y] = "SNOW";
                }
                else if (tile.getTerrain().equals(Terrain.TUNDRA)){
                    map[copy_x][copy_y] = "TUNDRA";
                }
                copy_y = copy_y + 1;
            }
            if (tile.isRiver(2)){
                map[copy_x][copy_y] = "R/";
            }
            else {
                map[copy_x][copy_y] = "//";
            }
            copy_y = copy_y + 1;
            for (int j = 0;j<(9 - row)/2;j++){
                if (map[copy_x][copy_y].equals(" ")){
                    map[copy_x][copy_y] = " ";
                }
                copy_y = copy_y + 1;
            }
            copy_x = copy_x + 1;
            row = row - 2;
            copy_y = y;
        }
        copy_x = copy_x - 1;
        copy_y = y + 3;
        for (int i = 0;i<5;i++){
            if (tile.isRiver(3)){
                map[copy_x][copy_y] = "R_";
            }
            else {
                map[copy_x][copy_y] = "_";
            }
            copy_y = copy_y + 1;
        }

    }
}
