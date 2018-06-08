/**
 * Created by Zhenye Na on Jun, 2018
 */

import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Test;


public class RadixSortTest {

    @Test
    public void LSDSortTest() {
        String[] testArr1 = {"alatn",  "hello", "succe", "donld", "hcdlo","heleh", "12321", "!*^&!"};
        String[] result1 = RadixSort.sort(testArr1);

        System.out.println(Arrays.toString(result1));

        String[] expected1 = {"alatn",  "hello", "succe", "donld", "hcdlo","heleh", "12321", "!*^&!"};
        assertArrayEquals(testArr1, expected1);

    }


}
