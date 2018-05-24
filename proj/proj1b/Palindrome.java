/**  Project 1B: Applying and Testing Data Structures version 1.0
 *
 *   @author Zhenye Na 05/23/2018
 *
 * */

public class Palindrome {

    /**  Task 2: wordToDeque
     *
     *   Transform string into Deque.
     *
     *   Args:
     *       word (String).
     *
     *   Returns:
     *       worddeque (Character Deque).
     *
     * */
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> worddeque = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            worddeque.addLast(word.charAt(i));
        }

        return worddeque;

    }


    /**  Task 3B: isPalindrome
     *
     *   Check whether given string is a Palindrome.
     *
     *   Args:
     *       word (String): Given string.
     *
     *   Returns:
     *       true if word is Palindrome;
     *       false if not.
     *
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


    /**  private helper function for `isPalindrome`
     *
     *   Transform Character Deque back to string.
     *
     *
     *   Args:
     *       d (Character Deque)
     *
     *   Returns:
     *       string (String)
     *
     * */
    private String DequeToString(Deque d) {
        String string = "";
        while (d.size() > 0) {
            string += d.removeFirst();
        }
        return string;
    }


    /**  Task 4: Generalized Palindrome and OffByOne
     *
     *   Generalized Palindrome.
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
