package org.minesweeper;

import java.io.IOException;

public class iStateGame extends iState{
    Board _board;
    public iStateGame(Game game) {
        super(game);
        _board = new Board(owner.properties);
    }

    @Override
    void enter() {
        _board.generateBoard();
        _board.printBoard();
        System.out.println(Util.gameMenu);
    }

    @Override
    void execute() throws IOException {
        String input = Util.parseStringInput(">> ");
        String[] args = input.split(" ");
        if(Util.isQuit(args[0])){
            owner.stateMachine.quit();
        }

        _board.updateTiles();
        _board.printBoard();
    }

    @Override
    void exit() {
        System.out.println("Thank you for playing! :)");
    }

    @Override
    String name() {
        return "";
    }
}
