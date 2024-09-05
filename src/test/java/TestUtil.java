import org.minesweeper.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUtil {
    @Test
    public void testArgsToInteger(){
        String[] nums = {"12", "34"};

        Assertions.assertArrayEquals(new Integer[]{12, 34}, Util.argsToInteger(nums, new int[]{50, 50}));
        Assertions.assertNull(Util.argsToInteger(nums, new int[]{10, 10}));

        Assertions.assertNull(Util.argsToInteger(new String[]{"1", "two"}, new int[]{30, 30}));
        Assertions.assertNull(Util.argsToInteger(new String[]{"1"}, new int[]{30, 30}));
        Assertions.assertNull(Util.argsToInteger(new String[]{"1", "two"}, new int[]{30}));
        Assertions.assertNull(Util.argsToInteger(null, new int[]{30, 30}), "Thrown whenever whatever inputted was wrong and not strings");
        Assertions.assertNull(Util.argsToInteger(null, new int[]{30}), "This should return null when not given two numbers within the grid boundaries as strings.");
    }

    @Test
    public void testIsQuit(){
        Assertions.assertTrue(Util.isQuit("q"));
        Assertions.assertTrue(Util.isQuit("quit"));
        Assertions.assertTrue(Util.isQuit("Q"));
        Assertions.assertTrue(Util.isQuit("QuIt"));
        Assertions.assertFalse(Util.isQuit("aaaaa"));
        Assertions.assertFalse(Util.isQuit(""));
    }
}
