package org.example;

import java.io.IOException;

public class Game {
    StateMachine stateMachine;
    BoardProperties properties;

    public Game(){
        properties = new BoardProperties(10,10, 30);
        stateMachine = new StateMachine();
        stateMachine.changeState(new iStateMenu(this));
    }

    public void run() throws IOException {
        while(true)
            stateMachine.executeMachine();
    }

}
