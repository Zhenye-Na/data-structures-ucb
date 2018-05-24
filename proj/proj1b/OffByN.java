/**  Project 1B: Applying and Testing Data Structures version 1.0
 *
 *   @author Zhenye Na 05/23/2018
 *
 *   Task 5: OffByN
 * */

public class OffByN implements CharacterComparator {

    /** Declare variable. */
    private int N;

    public OffByN(int N) {
        this.N = N;
    }


    @Override
    public boolean equalChars(char x, char y) {
        return (Math.abs((int) x - (int) y) == N);
    }
}
