package org.example;

import java.io.IOException;

public class StateMachine {
    iState currentState;

    public StateMachine(){}

    public void finishMachine(){
        if(currentState != null)
            currentState.exit();
    }

    public void quit(){
        finishMachine();
        System.exit(0);
    }

    public void changeState(iState newState){
        if(currentState != null){
            currentState.exit();
        }
        currentState = newState;
        currentState.enter();
    }

    public void executeMachine() throws IOException {
        currentState.execute();
    }
}
