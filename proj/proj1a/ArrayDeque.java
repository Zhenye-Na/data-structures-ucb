/**  Project 1A: Circular ArrayDeque.
 *
 *   ref = "https://sp18.datastructur.es/materials/proj/proj1a/proj1a"
 *
 *   @author Zhenye Na 05/19/2018
 *
 * */

import java.util.Random;

public class ArrayDeque<PlaceholderType> {

    /** Declare variables. */
    private float usage;
    private int size;
    private int nextFirst;
    private int nextLast;
    private PlaceholderType[] Array;


    /** Create an empty Array Deque. */
    public ArrayDeque() {
        Array = (PlaceholderType[]) new Object[8];
        size = 0;
        usage = 0;
//        nextFirst = getRandomNumberInRange(0, 7);
        nextFirst = 2;

        // make sure that initial nextLast is on the RHS of nextFirst
        if (nextFirst == 7) {
            nextLast = 0;
        } else {
            nextLast = nextFirst + 1;
        }
    }


    /**  private helper function.
     *
     *   Get random integer in given range.
     *
     *   Args:
     *       min (int): lower bound of the range.
     *       max (int): upper bound of the range.
     *
     *   Returns:
     *       random number (int): random integer in range(min, max + 1) <- (Python notation for range).
     *
     * */
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    /**  private helper function
     *
     *   Check usage of array, return true will halve the array.
     * */
    private boolean checkUsageR() {

//        System.out.println("usage factor: " + usage);
        // compare ratio with 0.25
        return (usage < 0.25 && (Array.length >= 16));
    }



    /**  resize an array.
     *
     *   1. For arrays of length 16 or more, usage factor should always be at least 25%
     *      Otherwise halve the array.
     *      For smaller arrays, usage factor can be arbitrarily low.
     *
     *      Usage factor < 25% < 50%. So we can shrink the array size by 2 with no overflow.
     *
     *      References:
     *          Gitbook - Chapter 2.5: Memory Performance
     *
     *
     *   2. Array is full and enlarge the Array.
     *
     *      Enlarge Factor = 2
     *
     * */
    private void resize() {

        // condition 1 and 2
        if (checkUsageR() || (size == Array.length)) {

            int targetSize = Array.length;

            if (checkUsageR()) {
                targetSize /= 2;
            } else {
                targetSize *= 2;
            }

            // New Array
            PlaceholderType[] NewArray = (PlaceholderType[]) new Object[targetSize];

            // Copy from the first to the last element of the original array.
            // Start index setting
            int maxIndex;
            if (checkUsageR()) {
                maxIndex = Math.max(nextFirst, nextLast) + 1;
            } else {
                maxIndex = Array.length;
            }

            System.arraycopy(Array, 0, NewArray, 1, maxIndex);

            Array = NewArray;

            // pointers setting
            nextFirst = 0;
            nextLast = maxIndex + 1;


        }

    }


    /**
     *
     *
     * */
    private int addpointerFirst(int nextFirst) {
        if (nextFirst != 0) {
            // move First pointer backward
            nextFirst -= 1;
        } else {
            // move First pointer to the end of Array.
            nextFirst = Array.length - 1;
        }
        return nextFirst;
    }



    /**
     *
     *
     * */
    private int addpointerLast(int nextLast) {
        if (nextLast != Array.length - 1) {
            // move Last pointer forward
            nextLast += 1;
        } else {
            // move First pointer to the end of Array.
            nextLast = 0;
        }
        return nextLast;
    }


    /**  Adds an item of type <PlaceholderType> to the front of the deque.
     *
     *   Args:
     *       item (PlaceholderType): new first item.
     *
     *   Returns:
     *       Nothing to return.
     * */
    public void addFirst(PlaceholderType item){

        // Check usage factor.
        resize();

        Array[nextFirst] = item;
        size += 1;
        usage = (float) size / (float) Array.length;

        // move the pointer of nextFirst.
        nextFirst = addpointerFirst(nextFirst);
    }


    /**  Adds an item of type <PlaceholderType> to the back of the deque.
     *
     *   Args:
     *       item (PlaceholderType): new first item.
     *
     *   Returns:
     *       Nothing to return.
     * */
    public void addLast(PlaceholderType item){

        // Check usage factor.
        resize();

        Array[nextLast] = item;
        size += 1;
        usage = (float) size / (float) Array.length;

        // move the pointer of nextLast.
        nextLast = addpointerLast(nextLast);
    }


    /**  Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }


    /**  Returns the number of items in the deque in constant time. */
    public int size() {
        return size;
    }


    /**  Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        for (PlaceholderType p : Array) {
            System.out.print(p + " ");
        }
        System.out.print("\n");
    }


    /**
     *
     *
     * */
    private int rmpointerFirst(int nextFirst) {
        if (nextFirst != Array.length) {
            nextFirst += 1;
        } else {
            nextFirst = 0;
        }
        return nextFirst;
    }


    /**
     *
     *
     * */
    private int rmpointerLast(int nextLast) {
        if (nextLast != 0) {
            // move Last pointer forward
            nextLast -= 1;
        } else {
            // move First pointer to the end of Array.
            nextLast = Array.length - 1;
        }
        return nextLast;
    }


    /**  Removes and returns the item at the front of the deque.
     *   If no such item exists, returns null.
     *
     *   Args:
     *       None
     *
     *   Returns:
     *       First item in Circular ArrayDeque or null
     * */
    public PlaceholderType removeFirst() {

        int index = rmpointerFirst(nextFirst);
        PlaceholderType returnvalue = Array[index];
        Array[index] = null;

        // Check usage factor.
        resize();

        size -= 1;
        usage = (float) size / (float) Array.length;

        // move pointer
        nextFirst = rmpointerFirst(nextFirst);

        return returnvalue;
    }


    /**  Removes and returns the item at the back of the deque.
     *   If no such item exists, returns null.
     *
     *   Args:
     *       None
     *
     *   Returns:
     *       Last item in Circular ArrayDeque or null
     * */
    public PlaceholderType removeLast() {

        int index = rmpointerLast(nextLast);
        PlaceholderType returnvalue = Array[index];
        Array[index] = null;

        // Check usage factor.
        resize();

        size -= 1;
        usage = (float) size / (float) Array.length;

        // move pointer
        nextFirst = rmpointerLast(nextLast);

        return returnvalue;



    }


    /**  Get i^{th} item in the Circular ArrayDeque.
     *   If no such item exists, returns null.
     *
     *   Args:
     *       index (int): index of the target item.
     *
     *   Returns:
     *       index^{th} item in the deque.
     *
     * */
    public PlaceholderType get(int index) {
        if (index >= this.Array.length) {
            return null;
        } else {
            return this.Array[index];
        }

    }

}
