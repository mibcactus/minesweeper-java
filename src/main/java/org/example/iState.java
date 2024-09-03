package org.example;

import java.io.IOException;

public abstract class iState {
    Game owner;
    public iState(Game game){
        owner = game;
    }
    abstract void enter();
    abstract void execute() throws IOException;
    abstract void exit();
    abstract String name();
}
