package lab11.graphs;

import java.util.LinkedList;
import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int INFINITY = 999;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        edgeTo[s] = s;
    }

    @Override
    public void solve() {
        // TODO: Your code here!

        Stack<Integer> st = new Stack<>();

        marked[s] = true;
        announce();
        st.push(s);

        boolean flag = false;

        while (!st.isEmpty()) {
            if (flag) break;
            int v = st.pop();

            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    announce();
                    marked[w] = true;
                    st.push(w);
                } else {

                    for (int u : maze.adj(w)) {
                        if ( marked[u] && (edgeTo[w] != u)) flag = true;
                    }


                }




            }
        }


    }

    // Helper methods go here
}

