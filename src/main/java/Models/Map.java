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
                if (map[i][j].equals("r_")){
                    System.out.print(Colors.RED_BACKGROUND + "_" + Colors.RESET);
                }
                else if (map[i][j].equals("r")){
                    System.out.print(Colors.RED_BACKGROUND + " " + Colors.RESET);
                }
                else {
                    System.out.print(map[i][j]);
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
            map[copy_x][copy_y] = "/";
            copy_y = copy_y + 1;
            for (int j = 0;j<row;j++){
                map[copy_x][copy_y] = "r";
                copy_y = copy_y + 1;
            }
            map[copy_x][copy_y] = "\\";
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
            map[copy_x][copy_y] = "\\";
            copy_y = copy_y + 1;
            for (int j = 0;j<row;j++){
                map[copy_x][copy_y] = "r";
                copy_y = copy_y + 1;
            }
            map[copy_x][copy_y] = "/";
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
            map[copy_x][copy_y] += "_";
            copy_y = copy_y + 1;
        }

    }
}
