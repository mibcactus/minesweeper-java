package org.minesweeper;

import java.io.IOException;

public class iStateLose extends iState{
    public iStateLose(Game game) {
        super(game);
    }

    @Override
    void enter() {
        System.out.println("Oh no! You got blown up. Sad!");
    }

    @Override
    void execute() throws IOException {
        String input = Util.parseStringInput(">> ");
        if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("settings")) {
            owner.stateMachine.changeState(new iStateSettings(owner));
        } else if (input.equalsIgnoreCase("m") || input.equalsIgnoreCase("menu")) {
            owner.stateMachine.changeState(new iStateMenu(owner));
        } else if (Util.isQuit(input)) {
            owner.stateMachine.quit();
        }
    }

    @Override
    void exit() {

    }

    @Override
    String name() {
        return "END";
    }
}
