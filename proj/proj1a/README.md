# [Project 1A: Data Structures](https://sp18.datastructur.es/materials/proj/proj1a/proj1a)

> *Rather than simulating the glorious gravitational physics of our universe, we’ll instead be building data structures. Not the most glamorous thing, but this will provide you with plenty of opportunities to practice everything we’ve been doing in lecture before our midterm rolls around.*

## Linked List Deque

Implement all the methods:

* `public void addFirst(T item)`: Adds an item of type T to the front of the deque.
* `public void addLast(T item)`: Adds an item of type T to the back of the deque.
* `public boolean isEmpty()`: Returns true if deque is empty, false otherwise.
* `public int size(): Returns the number of items in the deque.
* `public void printDeque()`: Prints the items in the deque from first to last, separated by a space.
* `public T removeFirst()`: Removes and returns the item at the front of the deque. If no such item exists, returns null.
* `public T removeLast()`: Removes and returns the item at the back of the deque. If no such item exists, returns null.
* `public T get(int index)`: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!

In addition, you also need to implement:

* `public LinkedListDeque()`: Creates an empty linked list deque.
* `public T getRecursive(int index)`: Same as get, but uses recursion.

You may add any private helper classes or methods in LinkedListDeque.java if you deem it necessary.

Create a file called `LinkedListDeque.java` in your project directory.

As your first deque implementation, you’ll build the LinkedListDeque class, which will be linked list based.

### Notes

Your operations are subject to the following rules:

* `add` and `remove` operations must not involve any looping or recursion. A single such operation must take “constant time”, i.e. execution time should not depend on the size of the deque.
* `get` must use iteration, not recursion.
* `size` must take constant time.

The amount of memory that your program uses at any given time must be proportional to the number of items. For example, if you add 10,000 items to the deque, and then remove 9,999 items, the resulting size should be more like a deque with 1 item than 10,000. Do not maintain references to items that are no longer in the deque.


## Array Deque

Create a file called `ArrayDeque.java` in your project directory.

As your second deque implementation, you’ll build the `ArrayDeque class`. This deque must use arrays as the core data structure.

For this implementation, your operations are subject to the following rules:

* `add` and `remove` must take constant time, except during resizing operations.
* `get` and `size` must take constant time.
* The starting size of your array should be `8`.
* The amount of memory that your program uses at any given time must be proportional to the number of items. For example, if you add 10,000 items to the deque, and then remove 9,999 items, you shouldn’t still be using an array of length 10,000ish. For arrays of length 16 or more, your usage factor should always be at least 25%. For smaller arrays, your usage factor can be arbitrarily low.

Implement all the methods:

* `public void addFirst(T item)`: Adds an item of type T to the front of the deque.
* `public void addLast(T item)`: Adds an item of type T to the back of the deque.
* `public boolean isEmpty()`: Returns true if deque is empty, false otherwise.
* `public int size(): Returns the number of items in the deque.
* `public void printDeque()`: Prints the items in the deque from first to last, separated by a space.
* `public T removeFirst()`: Removes and returns the item at the front of the deque. If no such item exists, returns null.
* `public T removeLast()`: Removes and returns the item at the back of the deque. If no such item exists, returns null.
* `public T get(int index)`: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!

In addition, you also need to implement:  
`public ArrayDeque()`: Creates an empty array deque.


## Highlight of implementation

- Initial `nextFirst` and `nextLast` can be randomly initialized or user-determined.
- ArrayDeque `resize` function are defined not in a "normal" way. XD
