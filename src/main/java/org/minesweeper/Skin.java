package org.minesweeper;

public class Skin{
    public String clear(){
        return "-";
    }
    public String flag(){
        return "F";
    }
    public String hidden(){
        return "#";
    }

    public String number(int danger){
        return Integer.toString(danger);
    }

    public String mine(){return "M";}
}
