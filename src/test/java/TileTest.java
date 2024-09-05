import org.minesweeper.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    public void testConstructor(){
        Tile tile = new Tile(true);
        Assertions.assertTrue(tile.getMine());
        Assertions.assertFalse(tile.hasFlag());
        Assertions.assertEquals(-1, tile.getDanger());

        tile.setFlag(false);
        Assertions.assertFalse(tile.hasMine());
    }

    @Test
    public void testDraw(){
        Tile tile = new Tile(false);

        Assertions.assertEquals("#", tile.draw(), "An empty tile should return #");

        tile.updateFlag();
        Assertions.assertEquals("F", tile.draw(), "A flagged tile should return F");

        tile.updateFlag();
        Assertions.assertEquals("#", tile.draw());

        tile.setDanger(0);
        Assertions.assertEquals("-", tile.draw());

        tile.setDanger(5);
        Assertions.assertEquals("5", tile.draw());

        tile.setFlag(true);
        Assertions.assertFalse(tile.getMine(), "You can't add a flag and have a danger level above -1.");
        Assertions.assertEquals("5", tile.draw(), "Flags shouldn't render over numbers.");
        Assertions.assertEquals(5, tile.getDanger(), "Flags shouldn't affect the danger level.");
    }
}
