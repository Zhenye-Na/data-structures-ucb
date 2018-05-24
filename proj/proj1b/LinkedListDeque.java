/**  Project 1A: Linked List Deque.
 *
 *   ref = "https://sp18.datastructur.es/materials/proj/proj1a/proj1a"
 *
 *   @author Zhenye Na 05/18/2018
 *
 * */

public class LinkedListDeque<Item> implements Deque<Item> {

    /**  private helper class for node in Linked-List-Deque.
     *
     *   Circular Sentinel
     *
     * */
    private class ListNode {
        private Item item;
        private ListNode prev;
        private ListNode next;

        private ListNode(ListNode p, Item i, ListNode n) {
            prev = p;
            item = i;
            next = n;
        }

    }


    /** Declare variables. */
    private ListNode sentinel;
    private int size;


    /**  Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    /**  Adds an item of type <Item> to the front of the deque.
     *
     *   Args:
     *       item (Item): new first item.
     *
     *   Returns:
     *       Nothing to return.
     * */
    @Override
    public void addFirst(Item item) {
        sentinel.next = new ListNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }


    /**  Adds an item of type <Item> to the back of the deque.
     *
     *   Args:
     *       item (Item): new first item.
     *
     *   Returns:
     *       Nothing to return.
     * */
    @Override
    public void addLast(Item item) {
        sentinel.prev.next = new ListNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }


    /**  Returns true if deque is empty, false otherwise.*/
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }


    /**  Returns the number of items in the deque in constant time. */
    @Override
    public int size() {
        return size;
    }


    /**  Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        ListNode node = sentinel;
        while (node.next != sentinel) {
            System.out.print(node.next.item + " ");
            node = node.next;
        }
    }


    /**  Removes and returns the item at the front of the deque.
     *   If no such item exists, returns null.
     *
     *   Args:
     *       None
     *
     *   Returns:
     *       First item in Linked-List-Deque or null
     * */
    @Override
    public Item removeFirst() {

        if (size == 0) {
            // no such item exists, return null
            return null;
        } else {
            // remove first item and return that item
            Item firstItem = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;

            return firstItem;
        }

    }


    /**  Removes and returns the item at the back of the deque.
     *   If no such item exists, returns null.
     *
     *   Args:
     *       None
     *
     *   Returns:
     *       Last item in Linked-List-Deque or null
     * */
    @Override
    public Item removeLast() {

        if (size == 0) {
            // no such item exists, return null
            return null;
        } else {
            // remove last item and return that item
            Item lastItem = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;

            return lastItem;
        }

    }


    /**  Gets the item at the given index using <iteration>
     *   where 0 is the front, 1 is the next item, and so forth.
     *   If no such item exists, returns null.
     *
     *   <Must not alter the deque!>
     *
     *   Args:
     *       i (int): index.
     *
     *   Returns:
     *       index^{th} item in the deque.
     * */
    public Item getIterative(int index) {
        int length = size;
        ListNode target = sentinel.next;

        // no such item exists, returns null.
        if (index > length - 1) {
            return null;
        } else {
            for (int i = 0; i < index; i++) {
                target = target.next;
            }

            return target.item;
        }

    }


    /**  Gets the item at the given index using <recursion>
     *   where 0 is the front, 1 is the next item, and so forth.
     *   If no such item exists, returns null.
     *
     *   <Must not alter the deque!>
     *
     *   Args:
     *       i (int): index.
     *
     *   Returns:
     *       index^{th} item in the deque.
     * */
    public Item getRecursive(int index) {
        int length = size;

        // no such item exists, returns null.
        if (index > length - 1) {
            return null;
        } else {
            return traverse(sentinel.next, index);
        }

    }


    /**  getRecursive helper function.
     *
     *   Args:
     *       n (ListNode): current pointer.
     *       i (int): index.
     *
     *   Returns:
     *       target item (Item).
     * */
    public Item traverse(ListNode n, int i) {

        if (i == 0) {
            return n.item;
        } else {
            return traverse(n.next, i - 1);
        }
    }


}
