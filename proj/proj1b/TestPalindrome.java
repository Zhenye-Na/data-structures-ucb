import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    /** unit test for wordToDeque. */
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }


    /**  Unit Tests for isPalindrome.
     *
     *   Task 3A: isPalindrome Testing
     *
     * */
    @Test
    public void TestPalindrome1() {
        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void TestPalindrome2() {
        assertTrue(palindrome.isPalindrome("AcA"));
    }

    @Test
    public void TestPalindrome3() {
        assertFalse(palindrome.isPalindrome("Aca"));
    }

    @Test
    public void TestPalindrome4() {
        assertTrue(palindrome.isPalindrome("sttts"));
    }


    /** unit tests for isPalindrome with OffByOne. */
    @Test
    public void TestPalindromeOffByOne1() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("a", cc));
    }

    @Test
    public void TestPalindromeOffByOne2() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("AcA", cc));
    }

    @Test
    public void TestPalindromeOffByOne3() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("AcdB", cc));
    }

    @Test
    public void TestPalindromeOffByOne4() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("stttt", cc));
    }


    /** unit tests for isPalindrome with OffByN. */
    @Test
    public void TestPalindromeOffByN1() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("a", cc));
    }

    @Test
    public void TestPalindromeOffByN2() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("acf", cc));
    }

    @Test
    public void TestPalindromeOffByN3() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("AhcF", cc));
    }

    @Test
    public void TestPalindromeOffByN4() {
        CharacterComparator cc = new OffByN(5);
        assertFalse(palindrome.isPalindrome("stttt", cc));
    }


}
