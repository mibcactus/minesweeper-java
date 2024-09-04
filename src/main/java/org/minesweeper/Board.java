package org.minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public ArrayList<ArrayList<Tile>> board = new ArrayList<>();
    private BoardProperties _properties;
    private final String TAG = "Board";

    private final int[][] _fullLookup = {{-1,-1}, {0,-1}, {1,-1},
                                     {-1, 0},         {1,0},
                                     {-1, 1}, {0, 1}, {1,1}};

    public Board(){
        new BoardProperties(10,10,30);
    }

    public Board(int width, int height, int mines){
        _properties = new BoardProperties(width, height, mines);
    }

    public Board(BoardProperties properties){
        _properties = properties;
    }

    public void revealEntireBoard(){
        for (int i = 0; i < board.size(); i++) { //height
            for (int j = 0; j < board.get(i).size(); j++) { //width
                checkSurroundingTiles(j, i);
            }
        }

    }

    public void updateTiles(){
        revealEntireBoard();
    }

    public void checkSurroundingTiles(int x, int y){

        Tile tile = board.get(y).get(x);
        if(tile.getDanger() != -1){
            return;
        }
        int h = _properties._height - 1, w = _properties._width - 1;
        int danger = 0;
        if(x > 0 && y > 0 && x < w && y < h){
            for (int i = 0; i < 8; i++) {
                danger += getMineInt(x + _fullLookup[i][0], y + _fullLookup[i][1]);
            }
            //board.get(y).get(x).setDanger(danger);
        } else if (x == w && y == h){ //bottom right corner
            danger = getMineInt(x-1, y) + getMineInt(x, y-1) + getMineInt(x-1, y-1);
        } else if (x == 0 && y == h){ // bottom left corner
            danger = getMineInt(x, y-1) + getMineInt(x+1, y-1) + getMineInt(x+1, y);
        } else if (x == 0 && y == 0){ // top left corner
            danger = getMineInt(x, y+1) + getMineInt(x+1, y+1) + getMineInt(x+1, y);
        } else if (x == w && y == 0){ // top right corner
            danger = getMineInt(x, y+1) + getMineInt(x-1, y+1) + getMineInt(x-1, y);
        } else if (x == 0) { //left edge
            danger += getMineInt(x, y-1) + getMineInt(x, y+1);
            danger += getMineInt(x+1, y-1) + getMineInt(x+1, y) + getMineInt(x+1, y+1);
        } else if (x == w) { // right edge
            danger += getMineInt(x, y-1) + getMineInt(x, y+1);
            danger += getMineInt(x-1, y-1) + getMineInt(x-1, y) + getMineInt(x-1, y+1);
        } else if (y == 0) { // top edge
            danger += getMineInt(x-1, y) + getMineInt(x+1, y);
            danger += getMineInt(x-1, y+1) + getMineInt(x, y+1) + getMineInt(x+1, y+1);
        } else if (y == h) { // bottom edge
            danger += getMineInt(x-1, y) + getMineInt(x+1, y);
            danger += getMineInt(x-1, y-1) + getMineInt(x, y-1) + getMineInt(x+1, y-1);
        }

        board.get(y).get(x).setDanger(danger);
    }

    public int getMineInt(int x, int y){
        return board.get(y).get(x).hasMine() ? 1 : 0;
    }


    public void printBoard(){
        StringBuilder str = new StringBuilder("  |");
        for (int i = 0; i < board.get(0).size(); i++) {
            str.append(String.format("  %2d", i+1));
        }
        System.out.println(str);

        for (int i = 0; i < board.size(); i++) {
            str = new StringBuilder(String.format("%2s:", i+1));
            for (int j = 0; j < board.get(i).size(); j++) {
                String c = board.get(i).get(j).draw();
                str.append(String.format("  %2s", c));
            }

            System.out.println(str);

        }

    }

    public void generateBoard(){
        Random random = new Random();
        int minesLeft = _properties._mines;
        int tilesLeft = _properties._height * _properties._width;
        for(int i = 0; i < _properties._height; i++){
            ArrayList<Tile> row = new ArrayList<>();
            for(int j = 0; j < _properties._width; j++){
                String debug = String.format("Tile: %d, %d, %d tiles left", i, j, tilesLeft-1);
                boolean mine = false;
                if(minesLeft > 0){
                    int probability = (int) ((double) minesLeft/tilesLeft * 100);

                    int val = random.nextInt(101);
                    if(val <= probability){
                        mine = true;
                        minesLeft--;
                    }

                    debug = String.format("Tile: %d,%d mine: %b, probability is %d/100, now %d mines left and %d tiles left", i,j, mine, probability, minesLeft, tilesLeft-1);

                }

                Tile tile = new Tile(mine);
                row.add(tile);

                tilesLeft--;
                Util.debugMessage(TAG, debug);
            }
            board.add(row);
        }
    }


}
