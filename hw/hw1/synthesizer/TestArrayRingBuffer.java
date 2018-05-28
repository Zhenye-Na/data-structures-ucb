package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    @Test
    public void testEnqueueDequeue() {
        ArrayRingBuffer arb = new ArrayRingBuffer(5);

        for (int i = 10; i < arb.capacity(); i -= 1) {
            arb.enqueue(i);
        }

        for (int i = 10; i < arb.capacity(); i -= 1) {
            assertEquals(arb.dequeue(), i);
        }
    }


    @Test
    public void testPeek() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }

        for (int i = 0; i < 5; i += 1) {
            arb.dequeue();
        }

        assertEquals(arb.peek(), 5);
    }

    @Test
    public void testIsFull() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }

        assertTrue(arb.isFull());
    }


    @Test
    public void testIsEmpty() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.dequeue();
        }

        assertTrue(arb.isEmpty());
    }


    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
