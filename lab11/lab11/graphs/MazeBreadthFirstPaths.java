package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int src;
    private int dest;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> searchQueue;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        src = maze.xyTo1D(sourceX, sourceY);
        dest = maze.xyTo1D(targetX, targetY);
        distTo[src] = 0;
        edgeTo[src] = src;
        searchQueue = new LinkedList<>();
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        marked[v] = true;
        announce();

        if (v == dest) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }

        for (int neighbor : maze.adj(v)) {
            if (marked[neighbor]) {
                continue;
            }
            edgeTo[neighbor] = v;
            announce();
            searchQueue.add(neighbor);
            distTo[neighbor] = distTo[v] + 1;
        }

        if (searchQueue.isEmpty()) {
            return;
        }

        /*
            really important, if we don't apply the while loop and just call bfs(next)
            straightly, we will end up with a very deep recursion stack and cause really
            large amount of time and memory cost
         */
        int next = searchQueue.remove();
        while (marked[next]) {
            next = searchQueue.remove();
        }
        bfs(next);

    }


    @Override
    public void solve() {
        bfs(src);
    }
}

