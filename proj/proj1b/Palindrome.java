/**
 *
 *
 *
 *
 *
 * */

public class Palindrome {

    /**
     *
     * */
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
        while (d.size() > 0) {
            string += d.removeFirst();
        }
        return string;
    }


    /**
     *
     *
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> worddeque = wordToDeque(word);

        if (worddeque.size() == 0 || worddeque.size() == 1) {
            return true;
        } else {

            if (cc.equalChars(worddeque.removeFirst(), worddeque.removeLast())) {
                return isPalindrome(DequeToString(worddeque), cc);
            } else {
                return false;
            }
        }
    }











}
