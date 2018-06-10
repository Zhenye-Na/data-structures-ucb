import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.algs4.In;


public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        // YOUR CODE HERE

        In dictionary = new In(dictPath);
        String[] dictWords;
        dictWords = dictionary.readAllLines();


        List<String> solutions = new ArrayList<>();
        String[] words;

        // Read in Board
        In in = new In(boardFilePath);
        words = in.readAllLines();
        char[][] board = new char[words.length][words[0].length()];

        // Transform to grid, like below
        // [n, e, s, s]
        // [t, a, c, k]
        // [b, m, u, h]
        // [e, s, f, t]
        for (int i = 0; i < words.length; i += 1) {
            for (int j = 0; j < words[0].length(); j += 1) {
                board[i][j] = words[i].charAt(j);
            }
        }

        return null;
    }




    public static void main(String[] args) {
        List<String> la;
        la = solve(3, "exampleBoard.txt");

    }


}
