package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF grid;
    private final int length;
    private int opennums;
    private final boolean[][] state;

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IndexOutOfBoundsException("length N should be a positive number");
        }
        grid = new WeightedQuickUnionUF(N*N + 1);       // add two virtual nodes at top and down
        for (int i = 1; i < N+1; i += 1) {
            grid.union(0, i);
        }
        length = N;
        opennums = 0;
        state = new boolean[N][N];
    }

    private boolean isInRange(int row, int col) {
        return row >= 0 && row < length && col >= 0 && col < length;
    }

    private int getIndex(int row, int col) {
        return row * length + col + 1;
    }

    private boolean getState(int row, int col) {
        return state[row][col];
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if(isOpen(row, col)) {
            return;
        }
        opennums += 1;
        state[row][col] = true;
        int index = getIndex(row, col);
        if (isInRange(row+1, col) && getState(row+1, col)) {
            // up grid
            int upindex = getIndex(row+1, col);
            grid.union(index, upindex);
        }
        if (isInRange(row-1, col) && getState(row-1, col)) {
            // down grid
            int downindex = getIndex(row-1, col);
            grid.union(index, downindex);
        }
        if (isInRange(row, col+1) && getState(row, col+1)) {
            // right grid
            int rightindex = getIndex(row, col+1);
            grid.union(index, rightindex);
        }
        if (isInRange(row, col-1) && getState(row, col-1)) {
            // left grid
            int leftindex = getIndex(row, col-1);
            grid.union(index, leftindex);
        }
    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        if (!isInRange(row, col)) {
            throw new IndexOutOfBoundsException("row and col should be in range(0, length)");
        }
        return state[row][col];
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        if(!isInRange(row, col)) {
            throw new IndexOutOfBoundsException("row and col should be in range(0, N)");
        }
        int index = getIndex(row, col);
        return grid.find(index) == 0;
    }

    public int numberOfOpenSites() {
        // number of open sites
        return opennums;
    }

    public boolean percolates() {
        // does the system percolate?
        boolean flag = false;
        for (int i = length * length - length + 1; i < length * length + 1; i += 1) {
            if (grid.find(i) == grid.find(0)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        // use for unit testing (not required)
        Percolation test = new Percolation(5);
        test.open(0, 0);
        test.open(0, 1);
        test.open(0, 3);
        test.open(2, 3);
        test.open(1, 3);
        test.open(2, 2);
        test.open(3, 2);
        test.open(4, 2);
        test.open(2, 2);
        test.open(4, 4);
        System.out.println(test.percolates());
    }
}
