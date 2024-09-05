package org.minesweeper;

import java.io.IOException;

public class iStateSettings extends iState {

    public iStateSettings(Game game) {
        super(game);
    }

    @Override
    public void enter() {
        System.out.println(Util.settingsMenu);
    }

    @Override
    public void execute() throws IOException {
        String input = Util.parseStringInput(">> ");
        String[] args = input.split(" ");
        switch (args[0]) {
            case "f", "finish":
                owner.stateMachine.changeState(new iStateMenu(owner));
                break;
            default:
                System.out.println(Util.settingsMenu);
                break;
        }

    }

    @Override
    public void exit() {
        String str = String.format(Util.settingsFormat, owner.properties.width,
                owner.properties.height, owner.properties._mines);
        System.out.println(str);
    }

    @Override
    public String name() {
        return "SETTINGS";
    }
}
