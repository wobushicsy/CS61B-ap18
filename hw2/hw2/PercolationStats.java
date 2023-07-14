package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] xi;
    private int N;
    private int T;
    double mean;
    double sigmasquare;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("input N and T should be a positive number");
        }
        this.N = N;
        this.T = T;
        Percolation pc;
        xi = new double[T];
        for (int i = 0; i < T; i += 1) {
            xi[i] = simulation(pf, N);
        }

        // calculate mean
        mean = 0;
        for (int i = 0; i < T; i += 1) {
            mean += xi[i];
        }
        mean /= T;

        // calculate stddev
        sigmasquare = 0;
        for (int i = 0; i < T; i += 1) {
            sigmasquare += Math.pow(xi[i] - mean, 2);
        }
        sigmasquare /= T-1;
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
        return mean;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return sigmasquare;
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean - 1.96 * Math.sqrt(sigmasquare) / Math.sqrt(T);
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean + 1.96 * Math.sqrt(sigmasquare) / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(5, 10, pf);
    }
}
