package org.minesweeper;

public class BoardProperties {
    public int width, height, _mines;
    private int _tilesLeft;

    public BoardProperties(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        _mines = mines;
        _tilesLeft = width * height - mines;
    }

    public void setMines(int mines){
        if(mines > getArea()){
            _mines = (int) (getArea() * 0.6);
        } else {
            _mines = mines;
        }
    }

    public int getArea(){
        return width * height;
    }

    public void setSize(Integer[] xy){
        width = xy[0];
        height = xy[1];
    }

    public void setSize(int x, int y){
        width = x;
        height = y;
    }

    public void updateTilesLeft(){
        _tilesLeft--;
        if(_tilesLeft == 0){
            GameData.setWin();
        }

    }
}
