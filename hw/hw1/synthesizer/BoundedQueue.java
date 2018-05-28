package synthesizer;

/**
 * Created by Zhenye Na on May, 2018
 */
public interface BoundedQueue<T> {

    /** return size of the buffer. */
    int capacity();

    /** return number of items currently in the buffer. */
    int fillCount();

    /** add item x to the end. */
    void enqueue(T x);

    /** delete and return item from the front. */
    T dequeue();

    /** return (but do not delete) item from the front. */
    T peek();

    /** is the buffer empty (fillCount equals zero)? */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** is the buffer full (fillCount is same as capacity)? */
    default boolean isFull() {
        return fillCount() == capacity();
    }

}
