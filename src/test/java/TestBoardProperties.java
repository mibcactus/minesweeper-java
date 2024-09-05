import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.minesweeper.BoardProperties;
import org.minesweeper.GameData;

public class TestBoardProperties {

    @Test
    public void testConstructor(){
        int w = 7, h = 9, m = 30;
        BoardProperties p = new BoardProperties(w, h, m);
        Assertions.assertEquals(w, p.width);
        Assert.assertEquals(h, p.height);
        Assert.assertEquals(m, p._mines);
    }

    @Test
    public void testUpdateTilesLeft(){
        int w = 1, h = 1, m = 0;
        BoardProperties p = new BoardProperties(w, h, m);
        Assertions.assertEquals(w, p.width);
        Assert.assertEquals(h, p.height);
        Assert.assertEquals(m, p._mines);

        Assert.assertFalse(GameData.isWin);
        Assert.assertFalse(GameData.gameOver);

        p.updateTilesLeft();
        Assert.assertTrue(GameData.gameOver);
        Assert.assertTrue(GameData.isWin);
    }
}
