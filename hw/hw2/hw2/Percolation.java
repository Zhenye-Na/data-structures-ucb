package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private int numOfOpen;

    private WeightedQuickUnionUF WQU;


    /** create N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException(N + " is not smaller or equal to 0! ");
        }

        grid = new int[N][N]; //default value is 0.
        numOfOpen = 0;
        WQU = new WeightedQuickUnionUF(matrix2vector(N - 1, N - 1));

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
        return p * n + q + 1;
    }


    /** open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        // validate whether index is legal
        validate(row);
        validate(col);

        // Check whether it is open
        if (isOpen(row, col)) {
            grid[row][col] = 1;
            numOfOpen += 1;
        }
        Connect(row, col);

    }


    /**
     * private helper method
     *
     * connect the two components
     */
    private void Connect(int p, int q) {
        int cord = matrix2vector(p, q);
        int[] neighbors = new int[] {cord - 1, cord + 1, cord - grid.length, cord + grid.length};



    }


    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        // validate whether index is legal
        validate(row);
        validate(col);

        return grid[row][col] == 1;
    }


    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        // validate whether index is legal
        validate(row);
        validate(col);

        return grid[row][col] == 1 && ;
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

        // Dummy version for checking percolation
        for (int i = 0; i < grid.length; i += 1) {
            if (WQU.connected(0, i)) {
                return true;
            }
        }

        return false;

    }


    /** use for unit testing (not required) */
    // public static void main(String[] args) {}



}
