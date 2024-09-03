package org.example;

public class Tile {
    private boolean _mine = false;
    private boolean _flag = false;
    private boolean _exploded = false;
    private int _danger = -1;


    public Tile(boolean mine){
        _mine = mine;
    }

    public Tile(){}

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


    public void setFlag(boolean flag){
        _flag = flag;
    }

    public boolean getFlag(){
        return _flag;
    }

    // TODO: Refactor this, this is just a placeholder
    public void onReveal(){
        if(_mine && !_flag){
            _exploded = true;
        }
    }
}
