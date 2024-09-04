package org.minesweeper;

public class Tile {
    private final String TAG = "TILE";
    private final boolean _mine;
    private boolean _flag = false;
    private int _danger = -1;

    public Tile(boolean mine){
        _mine = mine;
    }

    public void setDanger(int danger) throws IllegalArgumentException {
        if(danger > 9 || danger < 0){
            throw new IllegalArgumentException("Danger value entered must be between 0 and 9 inclusive!!!!! >:(");
        }
        _danger = danger;
    }

    public int getDanger(){
        return _danger;
    }


    public boolean hasMine(){
        return _mine;
    }

    public String draw(){
        if(_danger == 0)
            return Util.skin.clear();
            //return "-";

        if(_danger > 0)
            return Util.skin.number(_danger);
            //return Integer.toString(_danger);

        if(_flag)
            return Util.skin.flag();
            //return "F";

        return Util.skin.hidden();
        //return "#";
    }

    public void setFlag(boolean flag){
        _flag = flag;
    }

    public boolean getFlag(){
        return _flag;
    }

    // TODO: Refactor this, this is just a placeholder
    public void onReveal(){}
}
