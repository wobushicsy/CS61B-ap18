package lab11.graphs;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return -1;
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        marked[s] = true;
        announce();

        if (s == t) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }

        Map<Integer, Integer> nextDisMap = new TreeMap<>();
        for (int neighbor : maze.adj(s)) {
            if (marked[neighbor]) {
                continue;
            }
            edgeTo[neighbor] = s;
            announce();
            distTo[neighbor] = distTo[s] + 1;
            int manhattanDistance = Math.abs(maze.toX(t) - maze.toX(neighbor)) +
                                        Math.abs(maze.toY(t) - maze.toY(neighbor));
            nextDisMap.put(neighbor, manhattanDistance + distTo[neighbor]);
        }


        int size = nextDisMap.size();
        for (int i = 0; i < size; i += 1) {
            int minNeighbor = Integer.MAX_VALUE;
            int minDist = Integer.MAX_VALUE;
            for (Integer key: nextDisMap.keySet()) {
                int value = nextDisMap.get(key);
                if (value < minDist) {
                    minNeighbor = key;
                    minDist = value;
                }
            }
            astar(minNeighbor);
        }


    }

    @Override
    public void solve() {
        astar(s);
    }

}

