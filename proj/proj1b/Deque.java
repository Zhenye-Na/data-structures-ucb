/**  Project 1B: Applying and Testing Data Structures version 1.0
 *
 *   Task 1: Deque Interface
 *
 *   @author Zhenye Na 05/23/2018
 *
 * */

public interface Deque<Item> {

    // Interface methods must be `public`, so add `public` to the front is redundant.

    /** Add one item to the front of the list. */
    void addFirst(Item i);

    /** Remove the last item of the queue. */
    void addLast(Item i);

    /** Return a boolean to tell if a queue is empty. */
    boolean isEmpty();

    /** Return the size of a queue. */
    int size();

    /** Print every elements in a queue, separated by a space. */
    void printDeque();

    /** Delete the first item and return it. */
    Item removeFirst();

    /** Delete the last item and return it. */
    Item removeLast();

}
