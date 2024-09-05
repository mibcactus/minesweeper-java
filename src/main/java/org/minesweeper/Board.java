package org.minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public ArrayList<ArrayList<Tile>> board = new ArrayList<>();
    private final BoardProperties _properties;
    private final String TAG = "Board";

    private final int[][] _fullLookup = {{-1, -1}, {0, -1}, {1, -1},
            {-1, 0}, {1, 0},
            {-1, 1}, {0, 1}, {1, 1}};

    public Board(BoardProperties properties) {
        _properties = properties;
    }

    public int[] getSize() {
        return new int[]{_properties.width, _properties.height};
    }

    public Tile getTile(int x, int y){
        if(checkInputBounds(x, y))
            return null;
        return board.get(y).get(x);
    }

    public void onFlag(int x, int y) {
        if (checkInputBounds(x, y)){
            System.out.printf("Please enter co-ordinates up to (%d, %d)\n", _properties.width, _properties.height);
            return;
        }

        Tile tile = board.get(y).get(x);
        if (tile.getDanger() != -1) {
            System.out.println("You can't flag a tile that's already been revealed!");
            return;
        }

        getTile(x, y).updateFlag();
    }

    public void onStep(int x, int y) {
        if (checkInputBounds(x, y))
            return;

        if (board.get(y).get(x).hasFlag()) {
            System.out.println("That tile has a flag on it! You can't step on it until you remove it.");
            return;
        }

        if(getTile(x, y).hasMine()){
            GameData.setLose();
            return;
        }

        checkSurroundingTiles(x, y);
    }


    public void checkSurroundingTiles(int x, int y) {

        if(checkInputBounds(x,y)){
            //System.out.println("Sorry, this isn't within bounds");
            return;
        }

        //Tile tile = board.get(y).get(x);
        /* move into input checking
        if (tile.getDanger() != -1) {
            System.out.println("That tile has already been revealed!!");
            return;
        }*/
        ArrayList<int[]> surrounding_tiles = new ArrayList<>();
        int h = _properties.height - 1, w = _properties.width - 1;
        int danger = 0;
        if (x > 0 && y > 0 && x < w && y < h) {
            for (int i = 0; i < 8; i++) {
                int tx = x + _fullLookup[i][0], ty = y + _fullLookup[i][1];
                danger += getMineInt(tx, ty);
                if(danger == 0){
                    Tile t = getTile(tx, ty);
                    if(t == null){
                        String str = String.format("Tile at (%d, %d)", tx, ty);
                        Util.debugMessage(TAG, str);
                        return;
                    }
                    if(!t.hasMine() && t.getDanger() == -1){
                        int[] coords = new int[]{tx, ty};
                        surrounding_tiles.add(coords);
                    }
                }
            }
            //board.get(y).get(x).setDanger(danger);
        } else if (x == w && y == h) { //bottom right corner
            danger = getMineInt(x - 1, y) + getMineInt(x, y - 1) + getMineInt(x - 1, y - 1);
            if(danger == 0){
                if(!getTile(x - 1, y).hasMine() || getTile(x-1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x - 1, y - 1).hasMine() || getTile(x-1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x, y - 1).hasMine() || getTile(x-1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
            }
        } else if (x == 0 && y == h) { // bottom left corner
            danger = getMineInt(x, y - 1) + getMineInt(x + 1, y - 1) + getMineInt(x + 1, y);
            if(danger == 0){
                if(!getTile(x, y - 1).hasMine() || getTile(x, y - 1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x + 1, y - 1).hasMine() || getTile(x+1, y-1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x + 1, y).hasMine() || getTile(x+1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
            }
        } else if (x == 0 && y == 0) { // top left corner
            danger = getMineInt(x, y + 1) + getMineInt(x + 1, y + 1) + getMineInt(x + 1, y);
            if(danger == 0){
                if(!getTile(x, y + 1).hasMine() || getTile(x, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x + 1, y + 1).hasMine() || getTile(x+1, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x+1, y).hasMine() || getTile(x+1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
            }
        } else if (x == w && y == 0) { // top right corner
            danger = getMineInt(x, y + 1) + getMineInt(x - 1, y + 1) + getMineInt(x - 1, y);
            // TODO: Put this bit into it's own function
            if(danger == 0){
                if(!getTile(x, y + 1).hasMine() || getTile(x, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x - 1, y + 1).hasMine() || getTile(x, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x-1, y).hasMine() || getTile(x-1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
            }
        } else if (x == 0) { //left edge
            danger += getMineInt(x, y - 1) + getMineInt(x, y + 1);
            danger += getMineInt(x + 1, y - 1) + getMineInt(x + 1, y) + getMineInt(x + 1, y + 1);

            if(danger == 0){
                if(!getTile(x, y - 1).hasMine() || getTile(x, y-1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x, y + 1).hasMine() || getTile(x, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x+1, y-1).hasMine() || getTile(x+1, y-1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x+1, y).hasMine() || getTile(x+1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x+1, y+1).hasMine() || getTile(x+1, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
            }


        } else if (x == w) { // right edge
            danger += getMineInt(x, y - 1) + getMineInt(x, y + 1);
            danger += getMineInt(x - 1, y - 1) + getMineInt(x - 1, y) + getMineInt(x - 1, y + 1);

            if(danger == 0){
                if(!getTile(x, y - 1).hasMine() || getTile(x, y-1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x, y + 1).hasMine() || getTile(x, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x-1, y-1).hasMine() || getTile(x+1, y-1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x-1, y).hasMine() || getTile(x+1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x-1, y+1).hasMine() || getTile(x+1, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
            }
        } else if (y == 0) { // top edge
            danger += getMineInt(x - 1, y) + getMineInt(x + 1, y);
            danger += getMineInt(x - 1, y + 1) + getMineInt(x, y + 1) + getMineInt(x + 1, y + 1);

            if(danger == 0){
                if(!getTile(x - 1, y).hasMine() || getTile(x-1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x + 1, y).hasMine() || getTile(x+1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x-1, y+1).hasMine() || getTile(x-1, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x, y+1).hasMine() || getTile(x, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x+1, y+1).hasMine() || getTile(x+1, y+1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
            }
        } else if (y == h) { // bottom edge
            danger += getMineInt(x - 1, y) + getMineInt(x + 1, y);
            danger += getMineInt(x - 1, y - 1) + getMineInt(x, y - 1) + getMineInt(x + 1, y - 1);

            if(danger == 0){
                if(!getTile(x - 1, y).hasMine() || getTile(x-1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
                if(!getTile(x + 1, y).hasMine() || getTile(x+1, y).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x-1, y-1).hasMine() || getTile(x-1, y-1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x, y-1).hasMine() || getTile(x, y-1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }

                if(!getTile(x+1, y-1).hasMine() || getTile(x+1, y-1).getDanger() == -1){
                    surrounding_tiles.add(new int[]{x-1, y});
                }
            }
        }

        board.get(y).get(x).setDanger(danger);
        _properties.updateTilesLeft();
        if(danger == 0 && !surrounding_tiles.isEmpty())
            //System.out.println("This tile has 0 danger");
            for(int[] coord : surrounding_tiles){
                //String str = String.format("Checking tile (%d, %d)", coord[0], coord[1]);
                //Util.debugMessage(TAG, str);
                checkSurroundingTiles(coord[0], coord[1]);
            }
    }

    public int getMineInt(int x, int y) {
        return getTile(x, y).hasMine() ? 1 : 0;
    }

    public void printBoard() {
        StringBuilder str = new StringBuilder("  |");
        for (int i = 0; i < board.get(0).size(); i++) {
            str.append(String.format("  %2d", i + 1));
        }
        System.out.println(str);

        for (int i = 0; i < board.size(); i++) {
            str = new StringBuilder(String.format("%2s:", i + 1));
            for (int j = 0; j < board.get(i).size(); j++) {
                String c = board.get(i).get(j).draw();
                str.append(String.format("  %2s", c));
            }

            System.out.println(str);
        }
    }

    public void generateBoard() {
        Random random = new Random();
        int minesLeft = _properties._mines;
        int tilesLeft = _properties.height * _properties.width;
        for (int i = 0; i < _properties.height; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < _properties.width; j++) {
                String debug = String.format("Tile: %d, %d, %d tiles left", i, j, tilesLeft - 1);
                boolean mine = false;
                if (minesLeft > 0) {
                    int probability = (int) ((double) minesLeft / tilesLeft * 100);

                    int val = random.nextInt(101);
                    if (val <= probability) {
                        mine = true;
                        minesLeft--;
                    }

                    debug = String.format("Tile: %d,%d mine: %b, probability is %d/100, now %d mines left and %d tiles left", i, j, mine, probability, minesLeft, tilesLeft - 1);

                }

                Tile tile = new Tile(mine);
                row.add(tile);

                tilesLeft--;
                Util.debugMessage(TAG, debug);
            }
            board.add(row);
        }
    }

    boolean checkInputBounds(int x, int y) {
        return x < 0 || y < 0 || x >= _properties.width || y >= _properties.height;
    }


}
