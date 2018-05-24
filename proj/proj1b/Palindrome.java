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


    /**
     *
     * */
    public boolean isPalindrome(String word) {

        Deque<Character> worddeque = wordToDeque(word);

        if (worddeque.size() == 0 || worddeque.size() == 1) {
            return true;
        } else {

            if (worddeque.removeFirst() == worddeque.removeLast()) {
                return isPalindrome(DequeToString(worddeque));
            } else {
                return false;
            }
        }

    }


    /**
     *
     * */
    private String DequeToString(Deque d) {
        String string = "";
        for (int i = 0; i < d.size(); i++) {
            string += d.removeFirst();
        }
        return string;
    }


    /**
     *
     *
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return true;
    }











}
