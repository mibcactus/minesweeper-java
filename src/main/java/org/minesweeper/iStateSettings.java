package org.minesweeper;

import java.io.IOException;

public class iStateSettings extends iState {

    public final String settingsMenu = """
Options:
    s: size x y
    d: difficulty x
    f: finish
    q: quit program""";

    public final String settingsFormat = "Current settings: %2dx%2d, difficulty is %d";

    public iStateSettings(Game game) {
        super(game);
    }

    @Override
    public void enter() {
        System.out.println(settingsMenu);
    }

    @Override
    public void execute() throws IOException {
        System.out.printf(settingsFormat, owner.properties.width, owner.properties.height, owner.properties._mines);
        String input = Util.parseStringInput(">> ");
        String[] args = input.split(" ");


        if (args[0].equalsIgnoreCase("f") || args[0].equalsIgnoreCase("finish")) {
            owner.stateMachine.changeState(new iStateMenu(owner));

        } else if (args.length >= 3 && (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("size"))) {
            String[] args2 = {args[1], args[2]};
            Integer[] nums = Util.args2DToInteger(args2, new int[]{Util.MAX_SIDE, Util.MAX_SIDE});

            if(nums != null && nums[0] >= Util.MIN_SIDE && nums[1] >= Util.MIN_SIDE) {
                owner.properties.setSize(nums);
                if(owner.properties._mines >= owner.properties.getArea()){
                    owner.properties._mines = (int) (owner.properties.getArea() * 0.6);
                }

            } else{
                String str = String.format("Boards must be larger than %d x %d", Util.MIN_SIDE, Util.MIN_SIDE);
                System.out.println(str);
            }


        } else if ((args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("difficulty"))){

            try{
                int mines = Integer.parseInt(args[1]);
                if(mines >= 0)
                    owner.properties.setMines(mines);
                else
                    System.out.println("You can't have negative difficulty!!");
            } catch (Exception e){
                System.out.println("Please enter a valid number");
            }

        }  else {
            System.out.println("Please enter one of the commands :) Thank you");
        }

    }

    @Override
    public void exit() {
        String str = String.format(settingsFormat, owner.properties.width,
                owner.properties.height, owner.properties._mines);
        System.out.println(str);
    }

    @Override
    public String name() {
        return "SETTINGS";
    }
}
