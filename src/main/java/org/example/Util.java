package org.example;

public class Util {
    public static boolean DEBUG = true;
    public static final int MAX_SIDE = 25;
    public static final int MIN_SIDE = 5;
    public static final int MIN_MINES = 5;


    public static void debugMessage(String TAG, String consoleMessage){
        if(DEBUG){
            System.out.println(TAG + ": " + consoleMessage);
        }
    }

    public static int findMaxMines(int width, int height){
        return (int) ((double) width * height * 0.80);
    }
}
