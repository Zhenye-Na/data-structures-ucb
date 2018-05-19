/**  Project 1A: Linked List Deque.
 *
 *   ref = "https://sp18.datastructur.es/materials/proj/proj1a/proj1a"
 *
 *   Zhenye Na 05/18/2018
 *
 * */

public class LinkedListDeque<PlaceholderType> {

    /**  private helper class for node in Linked-List-Deque.
     *
     *   Circular Sentinel
     *
     * */
    private class ListNode {
        public PlaceholderType item;
        public ListNode prev;
        public ListNode next;


        /**
         *
         *
         * */
        public ListNode (ListNode p, PlaceholderType i, ListNode n){
            prev = p;
            item = i;
            next = n;

        }

    }


    /** Declare variables. */
    private ListNode sentinel;
    private int size;


    /**  Creates an empty linked list deque.
     *
     *   Args:
     *
     *
     *   Returns:
     *
     * */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null , null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }



    /**  Adds an item of type <PlaceholderType> to the front of the deque.
     *
     *   Args:
     *       item (PlaceholderType): new first item
     *
     *   Returns:
     *       Nothing to return
     * */
    public void addFirst(PlaceholderType item){
        sentinel.next = new ListNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }



    /**  Adds an item of type <PlaceholderType> to the back of the deque.
     *
     *   Args:
     *
     *
     *   Returns:
     *
     * */
    public void addLast(PlaceholderType item){
        sentinel.prev.next = new ListNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }




    /**  Returns true if deque is empty, false otherwise.
     *
     *   Args:
     *
     *   Returns:
     *
     * */
    public boolean isEmpty() {
        return (this.size == 0);
    }



    /**  Returns the number of items in the deque in constant time.
     *
     *   Args:
     *
     *
     *   Returns:
     *
     * */
    public int size() {
        return size;

    }


    /**  Prints the items in the deque from first to last, separated by a space.
     *
     *   Args:
     *
     *
     *   Returns:
     *
     * */
    public void printDeque() {
        ListNode node = sentinel;
        while (node.next != sentinel){
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
    public PlaceholderType removeFirst(){

        if (size == 0) {
            // no such item exists, return null
            return null;
        }
        else {
            // remove first item and return that item
            PlaceholderType firstItem = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;

            return firstItem;

        }

    }



    /**  Removes and returns the item at the back of the deque.
     *   If no such item exists, returns null.
     *
     *
     *   Args:
     *
     *
     *   Returns:
     *
     * */
    public PlaceholderType removeLast(){

        if (size == 0) {
            // no such item exists, return null
            return null;
        }
        else {
            // remove last item and return that item
            PlaceholderType lastItem = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;

            return lastItem;

        }

    }



    /**  Gets the item at the given index using <iteration>, where 0 is the front, 1 is the next item, and so forth.
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
    public PlaceholderType getIterative(int index){
        int length = size;
        ListNode target = sentinel.next;

        if (index > length - 1) {
            return null;
        }
        else {
            for (int i=0; i < index; i++) {
                target = target.next;
            }

            return target.item;
        }

    }


    /**  Gets the item at the given index using <recursion>, where 0 is the front, 1 is the next item, and so forth.
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
    public PlaceholderType getRecursive(int index){
        int length = size;

        if (index > length - 1) {
            return null;
        }
        else {
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
     *       target item (PlaceholderType).
     * */
    public PlaceholderType traverse(ListNode n, int i) {

        if (i == 0) {
            return n.item;
        }
        else {
            return traverse(n.next, i - 1);
        }
    }




    /**  Dummy main function for test.
     *
     * */
    public static void main(String[] args){
        LinkedListDeque<Integer> Dllist = new LinkedListDeque<>();
        Dllist.addFirst(666);
        Dllist.addLast(6666);
        Dllist.addLast(66666);
        Dllist.printDeque();                        // expected (666 6666 66666)
        System.out.println( );
        System.out.println(Dllist.getIterative(0)); // expected 666
        System.out.println(Dllist.getIterative(1)); // expected 6666
        System.out.println(Dllist.getIterative(5)); // expected null
        System.out.println( );
        System.out.println(Dllist.getRecursive(0)); // expected 666
        System.out.println(Dllist.getRecursive(1)); // expected 6666
        System.out.println( );

        Dllist.removeFirst();
        Dllist.printDeque();                        // expected (6666 66666)
        System.out.println( );
        System.out.println(Dllist.getIterative(0)); // expected 6666
        System.out.println(Dllist.getIterative(1)); // expected 66666
        System.out.println( );
        System.out.println(Dllist.getRecursive(0)); // expected 6666
        System.out.println(Dllist.getRecursive(1)); // expected 66666

        Dllist.removeLast();
        Dllist.printDeque();                        // expected 6666
        System.out.println( );
        System.out.println(Dllist.getIterative(0)); // expected 6666
        System.out.println(Dllist.getIterative(1)); // expected null
        System.out.println( );
        System.out.println(Dllist.getRecursive(0)); // expected 6666
        System.out.println(Dllist.getRecursive(1)); // expected null
    }



}
