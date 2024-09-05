package org.minesweeper;

public class iStateWin extends iStateLose{
    public iStateWin(Game game) {
        super(game);
    }

    @Override
    void enter() {
        System.out.println("Congratulations, you win!!");
    }

    @Override
    String name(){
        return "WIN";
    }
}
