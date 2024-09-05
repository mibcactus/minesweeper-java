import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.minesweeper.Board;
import org.minesweeper.BoardProperties;

public class TestBoard {

    @Test
    public void testFullBoard(){
        Board board = new Board(new BoardProperties(5,5,25));
        board.generateBoard();

        board.onStep(3,3);
        Assertions.assertEquals("M", board.getTile(3, 3).draw());

        board.onStep(3, 3);
        Assertions.assertEquals("-", board.getTile(3, 3).draw());

        Assertions.assertEquals("#", board.getTile(2, 2).draw());
        board.onFlag(2,2);
        Assertions.assertEquals("F", board.getTile(2, 2).draw());
    }

    @Test
    public void testEmptyBoard(){
        Board board = new Board(new BoardProperties(5,5,0));
        board.generateBoard();

        Assertions.assertEquals("#", board.getTile(3, 3).draw());

        board.onStep(3, 3);
        Assertions.assertEquals("-", board.getTile(3, 3).draw());

        Assertions.assertEquals("#", board.getTile(2, 2).draw());
        board.onFlag(2,2);
        Assertions.assertEquals("F", board.getTile(2, 2).draw());
    }
}
