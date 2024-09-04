package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public ArrayList<ArrayList<Tile>> board = new ArrayList<>();
    private BoardProperties _properties;
    private final String TAG = "Board";

    public Board(){
        new BoardProperties(10,10,30);
    }

    public Board(int width, int height, int mines){
        _properties = new BoardProperties(width, height, mines);
    }

    public Board(BoardProperties properties){
        _properties = properties;
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
                String c = board.get(i).get(j).hasMine() ? "M" : "-";
                //String c = board.get(i).get(j).draw();
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
