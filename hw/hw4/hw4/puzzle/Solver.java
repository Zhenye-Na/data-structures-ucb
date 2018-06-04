package hw4.puzzle;

import java.util.ArrayList;
import edu.princeton.cs.algs4.MinPQ;

/**
 * Created by Zhenye Na on Jun, 2018
 */


public class Solver {


    private ArrayList<WorldState> solution;


    /**
     *  Constructor which solves the puzzle, computing
     *  everything necessary for moves() and solution() to
     *  not have to solve the problem again. Solves the
     *  puzzle using the A* algorithm.
     *
     *  Assumes a solution exists.
     */
    public Solver(WorldState initial) {

        // solution ArrayList
        solution = new ArrayList<>();

        // Priority Queue
        MinPQ<SNode> pq = new MinPQ<>();

        // Insert initial WorldState
        pq.insert(new SNode(initial, 0, null));

        // Remove the search node with minimum priority `X`. If it is the goal node, then we’re done.
        // Otherwise, for each neighbor of X’s world state, create a new search node that obeys
        // the description above and insert it into the priority queue.
        while (!pq.min().getWorldState().isGoal()) {

            SNode X = pq.delMin();

            for (WorldState neighbor : X.getWorldState().neighbors()) {

                // critical optimization
                if ( X.getPrev() == null || !(neighbor.equals(X.getPrev().getWorldState())) ) {
                    pq.insert(new SNode(neighbor, X.getMoves() + 1, X));
                }

            }

        }


        SNode s = pq.min();
        while (s != null) {
            solution.add(0, s.getWorldState());
            s = s.getPrev();
        }


    }


    private class SNode implements Comparable<SNode> {
        private WorldState ws;
        private int numOfMoves;        // number of moves made to reach this world state from the initial state.
        private SNode prev;            // a reference to the previous search node.


        private SNode(WorldState ws, int m, SNode p) {
            this.ws = ws;
            this.numOfMoves = m;
            this.prev = p;
        }

        public WorldState getWorldState() {
            return ws;
        }

        public int getMoves() {
            return numOfMoves;
        }

        public SNode getPrev() {
            return prev;
        }

        @Override
        public int compareTo(SNode sn) {
            return ( ( this.numOfMoves + ws.estimatedDistanceToGoal() ) - ( sn.numOfMoves + sn.ws.estimatedDistanceToGoal() ) );
        }

    }


    /**
     *  Returns the minimum number of moves to solve the puzzle starting at the initial WorldState.
     */
    public int moves() {
        return solution.size() - 1;
    }


    /**
     * Returns a sequence of WorldStates from the initial WorldState to the solution.
     */
    public Iterable<WorldState> solution() {
        return solution;
    }


}
