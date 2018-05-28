// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

// TODO: Make sure to make this class and all of its methods public
// TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> implements Iterable<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;


    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }


    // TODO: When you get to part 5, implement the needed code to support iteration.
    public class BufferIterator implements Iterator<T> {
        private int pointer;

        public BufferIterator() {
            pointer = 0;
        }

        @Override
        public boolean hasNext() {
            return pointer != fillCount;
        }

        @Override
        public T next() {
            T returnItem = rb[pointer];
            pointer += 1;
            return returnItem;
        }

    }


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.

        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];

        this.fillCount = 0;
        this.capacity = capacity;

    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.

        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            last += 1;
            if (last >= this.capacity) {
                last = 0;
            }
        }

        fillCount += 1;

    }


    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() throws RuntimeException {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update.

        T returnValue;

        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            returnValue = rb[first];
            rb[first] = null;
            first += 1;

            if (first >= this.capacity) {
                first = 0;
            }
        }

        fillCount -= 1;
        return returnValue;

    }


    /**
     * Return <oldest> item, but don't remove it.
     */
    @Override
    public T peek() throws RuntimeException {
        // TODO: Return the first item. None of your instance variables should change.

        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }
    }

}
