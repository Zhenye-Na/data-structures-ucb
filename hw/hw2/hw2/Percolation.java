package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class Percolation {

    private int[][] grid;
    private boolean[][] status;

    private int numOfOpen;
    private int head;
    private int tail;

    private WeightedQuickUnionUF WQU;


    /** create N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException(N + " is not smaller or equal to 0! ");
        }

        grid = new int[N][N];                              // the chess board of our continent, by default each element in grid is 0.
        status = new boolean[N][N];                        // status (open or not) of each site, by default each element in status is false
        numOfOpen = 0;                                     // number of open site in the grid
        WQU = new WeightedQuickUnionUF(N * N + 2);      // WeightedQuickUnionUF data structures

        head = N * N;                                      // virtual top site
        tail = N * N + 1;                                  // virtual bottom site

    }


    /**
     * private helper method
     *
     * validate that p is a valid index
     */
    private void validate(int p) {
        int n = grid.length;
        if (p < 0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
        }
    }


    /**
     * private helper method
     *
     * denote matrix position in number.
     */
    private int matrix2vector(int p, int q) {
        int n = grid.length;
        return p * n + q;
    }


    /** open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        // validate whether index is legal
        validate(row);
        validate(col);

        // Check whether it is open
        if (isOpen(row, col)) {
            throw new IllegalArgumentException("This site is already opened!");
        } else {
            grid[row][col] = 1;
            numOfOpen += 1;
            Connect(row, col);
            status[row][col] = true;
        }
    }


    /**
     * private helper method
     *
     * connect the two components
     */
    private void Connect(int p, int q) {
        int curr = matrix2vector(p, q);
        List<Integer> neighbors = new ArrayList<>();

        // first row in the grid, connect to the head
        if (p == 0) WQU.union(head, curr);

        // bottom row in the grid, connect to the tail
        if (p == grid.length - 1) WQU.union(tail, curr);

        // Check current site's neighbours is opened or not
        if (p != 0 && isOpen(p - 1, q)) neighbors.add(matrix2vector(p - 1, q));
        if (p != (grid.length - 1) && isOpen(p + 1, q)) neighbors.add(matrix2vector(p + 1, q));
        if (q != 0 && isOpen(p, q - 1)) neighbors.add(matrix2vector(p, q - 1));
        if (q != (grid.length - 1) && isOpen(p, q + 1)) neighbors.add(matrix2vector(p, q + 1));

        // Union the opened sites
        for (int i : neighbors) {
            WQU.union(curr, i);
        }


    }


    /** is the site (row, col) open?
     *  whether click this site?
     */
    public boolean isOpen(int row, int col) {
        // validate whether index is legal
        validate(row);
        validate(col);

        return status[row][col];
    }


    /** is the site (row, col) full?
     *  whether connected to the top?
     */
    public boolean isFull(int row, int col) {
        // validate whether index is legal
        validate(row);
        validate(col);

        // Check this site is connected to head or not
        return WQU.connected(head, matrix2vector(row, col));
    }


    /**
     * number of open sites.
     *
     * take constant time
     */
    public int numberOfOpenSites() {
        return numOfOpen;
    }


    /** does the system percolate? */
    public boolean percolates() {

//        // Dummy version for checking percolation
//        for (int i = 0; i < grid.length; i += 1) {
//            if (WQU.connected(0, i)) {
//                return true;
//            }
//        }
//
//        return false;

        // Check tail is connected to head or not
        return WQU.connected(head, tail);

    }


    /** use for unit testing (not required) */
    // public static void main(String[] args) {}



}
