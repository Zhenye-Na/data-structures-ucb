/**  Project 1B: Applying and Testing Data Structures version 1.0
 *
 *   @author Zhenye Na 05/23/2018
 *
 *   Task 5: OffByOne
 * */

public class OffByOne implements CharacterComparator {


    @Override
    public boolean equalChars(char x, char y) {
        return (Math.abs((int) x - (int) y) == 1);
    }




}
