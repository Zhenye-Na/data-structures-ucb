package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * Created by Zhenye Na on Jun, 2018
 */


public class PercolationStats {

    private int[] C;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {

        if (N <= 0 || T <= 0) throw new IllegalArgumentException("Illegal Arguments!");

        int count = 0;

        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int rdmRow = StdRandom.uniform(N);
                int rdmCol = StdRandom.uniform(N);
                try {
                    p.open(rdmRow, rdmCol);
                    count++;
                } catch (Exception e) {
                    continue;
                }

            }
            C[i] = count;
        }

    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(C);

    }


    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(C);
    }


    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev() / C.length);
    }


    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev() / C.length);
    }



}




