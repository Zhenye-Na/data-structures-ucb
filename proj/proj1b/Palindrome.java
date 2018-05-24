/**
 *
 *
 *
 *
 *
 * */

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> worddeque = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            worddeque.addLast(word.charAt(i));
        }

        return worddeque;

    }


    public boolean isPalindrome(String word) {

        
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {

            if (word.charAt(0) == word.charAt(word.length() - 1)) {
                return true;
            } else {
                isPalindrome(isPalindromeRecursive(word));
            }


        }

    }


    private String isPalindromeRecursive(String word) {


    }

}
