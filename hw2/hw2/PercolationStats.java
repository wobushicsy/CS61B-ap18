package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("input N and T should be a positive number");
        }
        Percolation pc;
        double[] xi = new double[T];
        for (int i = 0; i < T; i += 1) {
            xi[i] = simulation(pf, N);
        }
    }

    private double simulation(PercolationFactory pf, int N) {
        Percolation pc = pf.make(N);
        int nodecnt = 0;
        int row, col;
        while (!pc.percolates()) {
            nodecnt += 1;
            row = StdRandom.uniform(0, N);
            col = StdRandom.uniform(0, N);
            while (pc.isOpen(row, col)) {
                row = StdRandom.uniform(0, N);
                col = StdRandom.uniform(0, N);
            }       // generate a random node
            pc.open(row, col);
        }

        return (nodecnt + 0.0) / (N*N);
    }

    public double mean() {
        // sample mean of percolation threshold
        return 0;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return 0;
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return 0;
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return 0;
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(5, 10, pf);
        System.out.println("simulation successful");
    }
}
