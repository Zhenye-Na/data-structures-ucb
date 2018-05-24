/**
 *
 *
 * */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    // static CharacterComparator OffByN = new OffByN(5);


    @Test
    public void testequalChars1() {

        CharacterComparator OffByN = new OffByN(5);

        assertFalse(OffByN.equalChars('a', 'b'));

    }

    @Test
    public void testequalChars2() {

        CharacterComparator OffByN = new OffByN(5);

        assertTrue(OffByN.equalChars('a', 'f'));

    }



}
