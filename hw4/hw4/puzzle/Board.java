package hw4.puzzle;

import java.util.LinkedList;

public class Board implements WorldState {

    private final int length;
    private final int[][] board;

    // Board(tiles): Constructs a board from an N-by-N array of tiles where
    // tiles[i][j] = tile at row i, column j
    public Board(int[][] tiles) {
        length = tiles.length;
        board = new int[length][length];
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    // tileAt(i, j): Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
        if (!inBoard(i, j)) {
            throw new IndexOutOfBoundsException("Index i, j out of bound");
        }
        return board[i][j];
    }

    // size():       Returns the board size N
    public int size() {
        return length;
    }

    private boolean inBoard(int i, int j) {
        return i >= 0 && i < length && j >= 0 && j < length;
    }

    private int[][] copyBoard(int[][] srcBoard) {
        int[][] tiles = new int[length][length];
        for (int i = 0; i < length; i += 1) {
            System.arraycopy(srcBoard[i], 0, tiles[i], 0, length);
        }
        return tiles;
    }

    private WorldState getNeighbor(int[][] srcBoard, int pos0I, int pos0J, int exI, int exJ) {
        int[][] tiles = copyBoard(srcBoard);
        int tmp = tiles[pos0I][pos0J];
        tiles[pos0I][pos0J] = tiles[exI][exJ];
        tiles[exI][exJ] = tmp;

        return new Board(tiles);
    }

    // neighbors():  Returns the neighbors of the current board
    public Iterable<WorldState> neighbors() {
        // return an array of integers to represent neighbors
        // e.g. neighbor of (1, 1) is 1, 3, 5, 7
        LinkedList<WorldState> neighbors = new LinkedList<>();

        // get the position of 0
        int posI = -1;
        int posJ = -1;
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1) {
                if (board[i][j] != 0) {
                    continue;
                }
                posI = i;
                posJ = j;
            }
        }


        // exchange with up
        if (inBoard(posI + 1, posJ)) {
            neighbors.add(getNeighbor(board, posI, posJ, posI + 1, posJ));
        }
        // exchange with down
        if (inBoard(posI - 1, posJ)) {
            neighbors.add(getNeighbor(board, posI, posJ, posI - 1, posJ));
        }
        // exchange with right
        if (inBoard(posI, posJ + 1)) {
            neighbors.add(getNeighbor(board, posI, posJ, posI, posJ + 1));
        }
        // exchange with left
        if (inBoard(posI, posJ - 1)) {
            neighbors.add(getNeighbor(board, posI, posJ, posI, posJ - 1));
        }

        return neighbors;
    }

    // number of tiles out of place
    public int hamming() {
        int cnt = 0;
        int ref = 1;
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1) {
                if (board[i][j] != ref) {
                    cnt += 1;
                }
                ref += 1;
            }
        }

        return cnt - 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int cnt = 0;
        int ref = 1;
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1) {
                int val = board[i][j];
                if (val == ref++ || val == 0) {
                    continue;
                }
                int refI = (val - 1) / length;
                int refJ = (val - 1) % length;
                cnt += Math.abs(i - refI) + Math.abs(j - refJ);
            }
        }

        return cnt;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (!this.getClass().equals(y.getClass())) {
            return false;
        }
        Board board1 = (Board) y;
        if (length != board1.size()) {
            return false;
        }
        boolean equals = true;
        for (int i = 0; i < length; i += 1) {
            if (!equals) {
                break;
            }
            for (int j = 0; j < length; j += 1) {
                if (tileAt(i, j) != board1.tileAt(i, j)) {
                    equals = false;
                    break;
                }
            }
        }

        return equals;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
