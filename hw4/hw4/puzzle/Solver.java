package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.LinkedList;
import java.util.Queue;

import java.util.Comparator;
import java.util.HashSet;


public class Solver {
    private WorldState init;
    private MinPQ<searchNode> pq;
    private int minMoves;
    private Queue<WorldState> solute;
    private HashSet<WorldState> redu;

    public Solver(WorldState initial) {
        this.init = initial;
//        System.out.println(init);
        this.minMoves = 0;
        this.pq = new MinPQ<>(
                new Comparator<searchNode>() {
                    public int compare(searchNode sn1, searchNode sn2) {
                        int Priority1 = sn1.ws.estimatedDistanceToGoal() + sn1.move;
                        int Priority2 = sn2.ws.estimatedDistanceToGoal() + sn2.move;
                        return Priority1 - Priority2;
                    }
                }
        );
        pq.insert(new searchNode(init));
        this.solute = new LinkedList<>();
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
//    void solve() {
//        if (pq.isEmpty()) return;
//        searchNode X = pq.delMin();
//        this.solute.push(X.ws);
//        this.redu.add(X.ws);
//        if (X.ws.isGoal()) {
//            minMoves = X.move;
//        } else {
//            for (WorldState w : X.ws.neighbors()) {
//                if (!redu.contains(w)) pq.insert(new searchNode(w, X.move + 1, X));
//            }
//        }
//    }

    private void solve() {
        while (!pq.isEmpty()) {
            searchNode cur = pq.delMin();
//            System.out.println(cur.ws);
            this.solute.add(cur.ws);
            this.redu.add(cur.ws);
            if (cur.ws.isGoal()) {
                minMoves = cur.move;
                return ;
            }
            for (WorldState w : cur.ws.neighbors()) {
                if (redu.contains(w)) continue;
                pq.insert(new searchNode(w, cur.move + 1, cur));
            }
        }
    }


    /** Subclasses. */
    class searchNode {
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

//        @Override
//        public int compare(searchNode sn1, searchNode sn2) {
//            int Priority1 = sn1.ws.estimatedDistanceToGoal() + sn1.move;
//            int Priority2 = sn2.ws.estimatedDistanceToGoal() + sn2.move;
//            return Priority1 - Priority2;
//        }
    }
}
