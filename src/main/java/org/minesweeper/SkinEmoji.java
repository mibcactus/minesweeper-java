package org.minesweeper;

public class SkinEmoji extends Skin{

    @Override
    public String clear(){
        return "🟩";
    }

    @Override
    public String flag(){
        return "⛳";
    }

    @Override
    public String hidden(){
        return "⬛";
    }

    @Override
    public String number(int danger){
        return switch (danger) {
            case 1 -> "1️⃣";
            case 2 -> "2️⃣";
            case 3 -> "3️⃣";
            case 4 -> "4️⃣";
            case 5 -> "5️⃣";
            case 6 -> "6️⃣";
            case 7 -> "7️⃣";
            case 8 -> "8️⃣";
            default -> "❗";
        };
    }
}
