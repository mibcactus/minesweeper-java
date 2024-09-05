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

    public void updateTilesLeft(){
        _tilesLeft--;
        if(_tilesLeft == 0){
            GameData.gameOver = true;
            GameData.isWin = true;
        }

    }
}
