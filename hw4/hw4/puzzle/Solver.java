package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;

public class Solver {

    private int moves;
    private final LinkedList<WorldState> solution;

    public Solver(WorldState initial) {
        MinPQ<SearchNode> searchNodesPQ = new MinPQ<>(new SearchNodeComparator<>());
        SearchNode initialNode = new SearchNode(initial, 0, null);
        searchNodesPQ.insert(initialNode);

        moves = -1;
        solution = new LinkedList<>();

        SearchNode last;

        while (true) {
            SearchNode minPriNode = searchNodesPQ.delMin();

            if (minPriNode.state.isGoal()) {
                last = minPriNode;
                break;
            }

            WorldState state = minPriNode.getState();
            for (WorldState neighbor: state.neighbors()) {
                if (minPriNode.getPrevious() != null
                        && neighbor.equals(minPriNode.getPrevious().getState())) {
                    continue;
                }
                SearchNode neighborNode = new
                        SearchNode(neighbor, minPriNode.getMoves() + 1, minPriNode);
                searchNodesPQ.insert(neighborNode);
            }
        }

        SearchNode pointer = last;

        while (pointer != null) {
            moves += 1;
            solution.push(pointer.getState());
            pointer = pointer.getPrevious();
        }

    }

    // SearchNode(WorldState state, int moves, SearchNode previous)
    private static class SearchNode {
        private final WorldState state;
        private final int moves;
        private final SearchNode previous;

        public SearchNode(WorldState state, int moves, SearchNode previous) {
            this.state = state;
            this.moves = moves;
            this.previous = previous;
        }

        public WorldState getState() {
            return state;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrevious() {
            return previous;
        }

    }

    private static class SearchNodeComparator<T extends SearchNode> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            WorldState state1 = o1.getState();
            WorldState state2 = o2.getState();
            int moves1 = o1.getMoves() + state1.estimatedDistanceToGoal();
            int moves2 = o2.getMoves() + state2.estimatedDistanceToGoal();
            return moves1 - moves2;
        }
    }

    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}
