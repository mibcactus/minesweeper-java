package org.example;

import java.io.IOException;

public class iStateMenu extends iState {

    public iStateMenu(Game game) {
        super(game);
    }

    @Override
    public void enter() {
        System.out.println(Util.startMenu);
    }

    @Override
    public void execute() throws IOException {
        String input = Util.parseStringInput(">> ");
        if(input.equalsIgnoreCase("s") || input.equalsIgnoreCase("settings")){
            owner.stateMachine.changeState(new iStateSettings(owner));
        } else if (input.equalsIgnoreCase("p") || input.equalsIgnoreCase("play")){
            owner.stateMachine.changeState(new iStateGame(owner));
        } else if (Util.isQuit(input)){
            owner.stateMachine.quit();
        }

    }

    @Override
    public void exit() {

    }

    @Override
    public String name() {
        return "MENU";
    }
}
