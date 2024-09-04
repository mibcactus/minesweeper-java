package org.minesweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {
    public static boolean DEBUG = !false;
    public static final int MAX_SIDE = 25;
    public static final int MIN_SIDE = 5;
    public static final int MIN_MINES = 5;

    public static Skin skin = new Skin();

    public static final String startMenu = """ 
Hi! Welcome to Minesweeper. Please select the following options:
    s: settings
    p: play
    q: quit""";

    public static final String settingsFormat = "Current settings: %2dx%2d, difficulty is %d";
    public static final String settingsMenu = """
Options:
    s: size x y
    d: difficulty x
    r: random board
    f: finish
    q: quit program""";
    public static final String gameMenu = """
Options:
    f: flag x y
    s: step x y
    r: restart
    q: quit
    h: help""";
    public static final String helpFormat = "x and y may be any number between 1 and %d";


    public static void debugMessage(String TAG, String consoleMessage){
        if(DEBUG){
            System.out.println(TAG + ": " + consoleMessage);
        }
    }

    public static int findMaxMines(int width, int height){
        return (int) ((double) width * height * 0.80);
    }

    public static boolean isQuit(String str){
        return str.equalsIgnoreCase("q") || str.equalsIgnoreCase("quit");
    }


    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Double parseDoubleInput(String consoleString) throws IOException {
        System.out.println(consoleString);

        Double val = null;
        while(val == null) {
            String str = reader.readLine();

            if(str.equals("quit")){
                System.exit(0);
            }

            try {
                val = Double.parseDouble(str);
                if(val <= 0){
                    val = null;
                    System.out.println("Please enter a number greater than 0");
                }
            } catch (Exception e) {
                System.out.println("Error! You haven't entered a number!!!! :(");
            }
        }

        return val;
    }

    public static Integer parseIntegerInput(String consoleString) throws IOException {
        System.out.println(consoleString);

        Integer val = null;
        while(val == null) {
            String str = reader.readLine();

            if(str.equals("quit")){
                System.exit(0);
            }

            try {
                val = Integer.parseInt(str);
                if(val <= 0){
                    val = null;
                    System.out.println("Please enter a number greater than 0");
                }
            } catch (Exception e) {
                System.out.println("Error! You haven't entered a number!!!! :(");
            }
        }

        return val;
    }

    public static String parseStringInput(String consoleString) throws IOException {
        //System.out.println(consoleString);
        System.out.print(consoleString);
        return reader.readLine();
    }
}
