package lab11.graphs;

import java.util.LinkedList;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean hasCycle = false;
    private LinkedList<Integer> cycle;
    private int[] myEdgeTo;


    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        myEdgeTo = new int[maze.V()];
        System.arraycopy(edgeTo, 0, myEdgeTo, 0, maze.V());
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        for (int i = 1; i <= maze.N(); i += 1) {
            if (hasCycle) {
                break;
            }
            for (int j = 1; j <= maze.N(); j += 1) {
                if (hasCycle) {
                    break;
                }
                int src = maze.xyTo1D(i, j);
                if (marked[src]) {
                    continue;
                }
                dfs(src);
            }
        }
    }

    // Helper methods go here
    private void dfs(int v) {
        marked[v] = true;
        announce();

        if (hasCycle) {
            return;
        }

        for (int neighbor : maze.adj(v)) {
            if (marked[neighbor]) {
                if (myEdgeTo[v] != neighbor) {
                    myEdgeTo[neighbor] = v;
                    hasCycle = true;
                    int p = myEdgeTo[neighbor];
                    int q = neighbor;
                     do {
                        edgeTo[q] = p;
                        q = p;
                        p = myEdgeTo[p];
                    } while (q != neighbor);
                    announce();
                    return;
                } else {
                    continue;
                }
            }
            if (hasCycle) {
                return;
            }
            myEdgeTo[neighbor] = v;
            dfs(neighbor);
        }

    }

}

