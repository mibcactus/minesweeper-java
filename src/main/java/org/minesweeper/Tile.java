package org.minesweeper;

public class Tile {
    private final String TAG = "TILE";
    private final boolean _mine;
    private boolean _flag = false;
    private int _danger = -1;

    public Tile(boolean mine) {
        _mine = mine;
    }

    public boolean getMine() {
        return _mine;
    }

    public void setDanger(int danger) throws IllegalArgumentException {
        if (danger > 8 || danger < 0) {
            throw new IllegalArgumentException(TAG + ": Danger value entered must be between 0 and 8 inclusive!!!!! >:(");
        }
        _danger = danger;
        _flag = false;
    }

    public int getDanger() {
        return _danger;
    }


    public boolean hasMine() {
        return _mine;
    }

    public boolean hasFlag() {
        return _flag;
    }

    public String draw() {
        if (_danger == 0)
            return Util.skin.clear();
        //return "-";

        if (_danger > 0)
            return Util.skin.number(_danger);
        //return Integer.toString(_danger);

        if (_flag)
            return Util.skin.flag();

        if(_mine){
            return Util.skin.mine();
        }

        return Util.skin.hidden();
        //return "#";
    }

    public void updateFlag() {
        _flag = !_flag;
    }

    public void setFlag(boolean flag) {
        if (_danger == -1)
            _flag = flag;
    }

}
