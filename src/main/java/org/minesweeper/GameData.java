package org.minesweeper;

public class GameData {
    public static Skin skin = new Skin();
    public static boolean gameOver = false;
    public static boolean isWin = false;

    public static void setWin(){
        isWin = true;
        gameOver = true;
    }

    public static void reset(){
        gameOver = false;
        isWin = false;
    }

    public static void setLose(){
        isWin = false;
        gameOver = true;
    }
}
