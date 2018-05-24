/**  Project 1B: Applying and Testing Data Structures version 1.0
 *
 *   Task 1: Deque Interface
 *
 *   @author Zhenye Na 05/23/2018
 *
 * */

public interface Deque<Item> {

    /**
     * Add one item to the front of the list.
     */
    public void addFirst(Item i);

    /**
     * Remove the last item of the queue.
     */
    public void addLast(Item i);

    /**
     * Return a boolean to tell if a queue is empty.
     */
    public boolean isEmpty();

    /**
     * Returns the size of a queue.
     */
    public int size();

    /**
     * Print every elements in a queue, separated by a space.
     */
    public void printDeque();

    /**
     * Delete the first item and return it.
     */
    public Item removeFirst();

    /**
     * Delete the last item and return it.
     */
    public Item removeLast();

}
