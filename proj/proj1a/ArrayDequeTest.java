/**  Project 1A: Circular ArrayDeque.
 *
 *   ref = "https://sp18.datastructur.es/materials/proj/proj1a/proj1a"
 *
 *   @author Zhenye Na 05/19/2018
 *
 * */

import org.junit.Test;
import static org.junit.Assert.*;

/** Dummy test cases. */
public class ArrayDequeTest {

    @Test
    public void IsEmptyTest1() {

        System.out.println("Perform IsEmptyTest1");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        // Test isEmpty()
        assertEquals(array.isEmpty(), true);

    }


    @Test
    public void IsEmptyTest2() {

        System.out.println("Perform IsEmptyTest2");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        array.addFirst(234);
        array.addLast(432);

        // Test isEmpty()
        assertEquals(array.isEmpty(), false);
    }


    @Test
    public void printDequeTest() {

        System.out.println("Perform printDequeTest1");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        array.addFirst(123);
        array.addLast(234);
        array.addFirst(345);
        array.addLast(456);

        array.printDeque();
    }

    @Test
    public void addFirstLastTest1() {

        System.out.println("Perform addFirstLastTest1");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        array.addFirst(1);
        array.addLast(2);
        array.addFirst(3);
        array.addLast(4);

        array.printDeque();

        array.addFirst(5);
        array.addLast(6);
        array.addFirst(7);
        array.addLast(8);

        array.printDeque();

        array.addFirst(9);
        array.addLast(10);
        array.addFirst(11);
        array.addLast(12);

        array.printDeque();
    }


    @Test
    public void addFirstLastTest2() {

        System.out.println("Perform addFirstLastTest2");

        ArrayDeque<Integer> array = new ArrayDeque<>();
        for (int i = 0; i <= 24; i++) {
            if (i % 2 == 0) {
                array.addFirst(i);
            } else {
                array.addLast(i);
            }

            if ((i + 1) % 8 == 0) {
                array.printDeque();
            }
        }

        array.printDeque();
    }


    @Test
    public void removeFirstLast1() {
        System.out.println("Perform removeFirstLast1");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        array.addFirst(123);
        array.addLast(234);
        array.addFirst(345);
        array.addLast(456);

        array.printDeque();

        array.removeFirst();
        array.printDeque();
    }


    @Test
    public void removeFirstLast2() {
        System.out.println("Perform removeFirstLast2");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        for (int i = 0; i <= 18; i++) {
            if (i % 2 == 0) {
                array.addFirst(i);
            } else {
                array.addLast(i);
            }

            if ((i + 1) % 8 == 0) {
                array.printDeque();
            }
        }

        array.printDeque();

        array.removeFirst();
        array.printDeque();

        array.removeLast();
        array.printDeque();
    }


    @Test
    public void removeFirstLast3() {
        System.out.println("Perform removeFirstLast3");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) {
                array.addFirst(i);
            } else {
                array.addLast(i);
            }

        }

        array.printDeque();
        int x;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                x = array.removeFirst();
            } else {
                x = array.removeLast();
            }

            if ((i + 1) % 8 == 0) {
                array.printDeque();
            }

            // System.out.println("nextFirst: " + array.nextFirst);
            // System.out.println("nextLast: " + array.nextLast);

        }

    }


    @Test
    public void removeFirstLast4() {
        System.out.println("Perform removeFirstLast4");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        for (int i = 0; i <= 16; i++) {
            if (i % 2 == 0) {
                array.addFirst(i);
            } else {
                array.addLast(i);
            }

        }

        array.printDeque();
        int x;
        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                x = array.removeFirst();
                array.printDeque();
            } else {
                x = array.removeLast();
                array.printDeque();
            }

            if ((i + 1) % 8 == 0) {
                System.out.println("ha!");
                array.printDeque();
            }

            // System.out.println("nextFirst: " + array.nextFirst);
            // System.out.println("nextLast: " + array.nextLast);

        }

    }


    @Test
    public void getTest() {
        System.out.println("Perform getTest");

        ArrayDeque<Integer> array = new ArrayDeque<>();

        array.addFirst(123);
        array.addLast(234);
        array.addFirst(345);
        array.addLast(456);

        System.out.println(array.get(3));
    }
}
