import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

    @Test
    public void testequalChars1() {
        assertTrue(offByOne.equalChars('a', 'b'));

    }


    @Test
    public void testequalChars2() {
        assertFalse(offByOne.equalChars('a', 'c'));

    }


    @Test
    public void testequalChars3() {
        assertTrue(offByOne.equalChars('&', '%'));

    }

    @Test
    public void testequalChars4() {
        assertFalse(offByOne.equalChars('a', 'B'));

    }




}
