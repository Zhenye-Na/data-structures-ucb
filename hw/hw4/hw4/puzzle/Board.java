package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    /**
     *  Board(tiles): Constructs a board from an N-by-N array of tiles where
     *                tiles[i][j] = tile at row i, column j
     *
     *  tileAt(i, j): Returns value of tile at row i, column j (or 0 if blank)
     *
     *  size():       Returns the board size N
     *
     *  neighbors():  Returns the neighbors of the current board
     *
     *  hamming():    Hamming estimate described below
     *
     *  manhattan():  Manhattan estimate described below
     *
     *  estimatedDistanceToGoal(): Estimated distance to goal. This method should
     *                simply return the results of manhattan() when submitted to
     *                Gradescope.
     *
     *  equals(y):    Returns true if this board's tile values are the same
     *                position as y's
     *
     *  toString():   Returns the string representation of the board. This
     *                method is provided in the skeleton
     */



    public Board(int[][] tiles) {

    }


    public int tileAt(int i, int j) {

    }


    public int size() {

    }

    /**
     *  Returns neighbors of this board.
     *  SPOILERZ: This is the answer.
     *  @source Josh Hug
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;


    public int hamming() {

    }


    public int manhattan() {

    }


    public int estimatedDistanceToGoal() {

    }


    public boolean equals(Object y) {

    }



    /**
     *  Return the string representation of the board.
     *  Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
