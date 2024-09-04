package org.minesweeper;

import java.io.IOException;

public class iStateGame extends iState {
    Board _board;
    private final String _menu = """
            Options:
                f: flag x y
                s: step x y
                q: quit""";

    public iStateGame(Game game) {
        super(game);
        _board = new Board(owner.properties);
    }

    @Override
    void enter() {
        _board.generateBoard();
        _board.printBoard();
        System.out.println(_menu);
    }


    @Override
    void execute() throws IOException {
        String input = Util.parseStringInput(">> ");
        String[] args = input.split(" ");
        if (Util.isQuit(args[0])) {
            owner.stateMachine.quit();
        }

        if (args.length >= 3 && (args[0].equalsIgnoreCase("f") || args[0].equalsIgnoreCase("flag"))) {
            String[] args2 = {args[1], args[2]};
            Integer[] nums = Util.argsToInteger(args2, _board.getSize());
            if (nums != null) {
                _board.onFlag(nums[0] - 1, nums[1] - 1);
            }
        } else if (args.length >= 3 && (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("step"))) {
            String[] args2 = {args[1], args[2]};
            Integer[] nums = Util.argsToInteger(args2, _board.getSize());
            if (nums != null) {
                _board.onStep(nums[0] - 1, nums[1] - 1);
            }
        } else {
            System.out.println("Please enter one of the commands :) Thank you");
        }


        _board.printBoard();
    }

    @Override
    void exit() {
        System.out.println("Thank you for playing! :)");
    }

    @Override
    String name() {
        return "GAME";
    }
}
