package hw4.puzzle;

import java.util.ArrayList;
import edu.princeton.cs.algs4.MinPQ;

/**
 * Created by Zhenye Na on Jun, 2018
 */


public class Solver {

    // private int capacity;
    private Node[] items;
    private ArrayList solution;


    /**
     *  Constructor which solves the puzzle, computing
     *  everything necessary for moves() and solution() to
     *  not have to solve the problem again. Solves the
     *  puzzle using the A* algorithm.
     *
     *  Assumes a solution exists.
     */
    public Solver(WorldState initial) {




    }


    private class Node<T> {
        private T myItem;
        private double myPriority;

        private Node(T item, double priority) {
            myItem = item;
            myPriority = priority;
        }

        public T item(){
            return myItem;
        }

        public double priority() {
            return myPriority;
        }

        @Override
        public String toString() {
            return myItem.toString() + ", " + myPriority;
        }
    }


    /**
     *  Returns the minimum number of moves to solve the puzzle starting at the initial WorldState.
     */
    public int moves() {
        return 0;
    }


    /**
     * Returns a sequence of WorldStates from the initial WorldState to the solution.
     */
    public Iterable<WorldState> solution() {
        return null;
    }


}
