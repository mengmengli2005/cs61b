package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.HashSet;


public class Solver {
    private WorldState init;
    private MinPQ<searchNode> pq;
    private int minMoves;
    private Stack<WorldState> solute;
    private HashSet<WorldState> redu;

    public Solver(WorldState initial) {
        this.init = initial;
        this.minMoves = 0;
        this.pq = new MinPQ<>();
        pq.insert(new searchNode(init));
        this.solute = new Stack<>();
        this.redu = new HashSet<>();

        solve();
    }

    public int moves() {
        return minMoves;
    }

    public Iterable<WorldState> solution() {
        return solute;
    }

    /** Helper methods. */
    void solve() {
        if (pq.isEmpty()) return;
        searchNode X = pq.delMin();
        this.solute.push(X.ws);
        this.redu.add(X.ws);
        if (X.ws.isGoal()) {
            minMoves = X.move;
        } else {
            for (WorldState w : X.ws.neighbors()) {
                if (!redu.contains(w)) pq.insert(new searchNode(w, X.move + 1, X));
            }
            solve();
        }
    }


    /** Subclasses. */
    private class searchNode implements Comparable<searchNode> {
        WorldState ws;
        int move;
        searchNode preSN;

        // constructor: create a search node with initial WorldState.
        public searchNode(WorldState ini) {
            ws = ini;
            move = 0;
            preSN = null;
        }
        public searchNode(WorldState w, int m, searchNode p) {
            ws = w;
            move = m;
            preSN = p;
        }

        @Override
        public int compareTo(searchNode sn) {
            int thisPriority = this.ws.estimatedDistanceToGoal() + this.move;
            int thatPriority = sn.ws.estimatedDistanceToGoal() + sn.move;
            return thisPriority - thatPriority;
        }
    }
}
