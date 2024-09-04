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
        int h = _properties._height - 1, w = _properties._width - 1;
        if(x > 0 && y > 0 && x < w && y < h){
            int danger = 0;
            for (int i = 0; i < 8; i++) {
                int ii = y+ _fullLookup[i][1],jj = x + _fullLookup[i][0];
                Tile t = board.get(ii).get(jj);
                String debug = String.format("Checking tile %d, %d, hasmine = %b\n", ii, jj, t.hasMine());
                Util.debugMessage(TAG, debug);
                if(t.hasMine()){
                    danger+=1;
                }
            }
            board.get(y).get(x).setDanger(danger);
        } else if (x == w && y == h){ //bottom right corner
            Tile t = board.get(h).get(w);
            t.setDanger((board.get(h).get(w-1).hasMineInt()) + (board.get(h-1).get(w).hasMineInt()) + (board.get(h-1).get(w-1).hasMineInt()));
        } else if (x == 0 && y == h){ // bottom left corner
            Tile t = board.get(h).get(0);
            t.setDanger((board.get(h).get(1).hasMineInt()) + (board.get(h-1).get(1).hasMineInt()) + (board.get(h-1).get(0).hasMineInt()));
        } else if (x == 0 && y == 0){ // top left corner
            Tile t = board.get(0).get(0);
            t.setDanger((board.get(0).get(1).hasMineInt()) + (board.get(1).get(1).hasMineInt()) + (board.get(1).get(0).hasMineInt()));
        } else if (x == w && y == 0){ // top right corner
            Tile t = board.get(0).get(w);
            t.setDanger((board.get(0).get(w-1).hasMineInt()) + (board.get(1).get(w-1).hasMineInt()) + (board.get(1).get(w).hasMineInt()));
        } else if (x == 0) { //left edge
            for (int i = 1; i < board.get(0).size()-1; i++) {
                Tile tile = board.get(i).get(0);
                int danger = 0;
                // same col
                danger += board.get(i-1).get(0).hasMineInt() + board.get(i+1).get(0).hasMineInt();
                // next col
                danger += board.get(i-1).get(1).hasMineInt() + board.get(i-1).get(1).hasMineInt() + board.get(i+1).get(1).hasMineInt();
                tile.setDanger(danger);
            }
        } else if (x == w) { // right edge
            for (int i = 1; i < board.get(h).size()-1; i++) {
                Tile tile = board.get(i).get(w);
                int danger = 0;
                // same col
                danger += board.get(i-1).get(0).hasMineInt() + board.get(i+1).get(0).hasMineInt();
                // next col
                danger += board.get(i-1).get(1).hasMineInt() + board.get(i-1).get(1).hasMineInt() + board.get(i+1).get(1).hasMineInt();
                tile.setDanger(danger);
            }
        } else if (y == 0) { // top edge
            for (int i = 1; i < board.get(0).size()-1; i++) {
                Tile tile = board.get(0).get(i);
                int danger = 0;
                // same row
                danger += board.get(0).get(i-1).hasMineInt() + board.get(0).get(i+1).hasMineInt();
                // below the tile
                danger += board.get(1).get(i-1).hasMineInt() + board.get(1).get(i).hasMineInt() + board.get(1).get(i+1).hasMineInt();
                tile.setDanger(danger);
            }
        } else if (y == h) { // bottom edge
            for (int i = 1; i < board.get(h).size()-1; i++) {
                Tile tile = board.get(h).get(i);
                int danger = 0;
                // same row
                danger += board.get(h).get(i-1).hasMineInt() + board.get(h).get(i+1).hasMineInt();
                // above the tile
                danger += board.get(h-1).get(i-1).hasMineInt() + board.get(h-1).get(i).hasMineInt() + board.get(h-1).get(i+1).hasMineInt();
                tile.setDanger(danger);
            }
        }
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
                //String c = board.get(i).get(j).hasMine() ? "M" : "-";
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
