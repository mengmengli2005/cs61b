package lab11.graphs;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Stack;

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
    private boolean[] onStack;
    private Stack cycle;

    public MazeCycles(Maze m) {
        super(m);
        maze = m; //我加的，是否需要呢？
        onStack = new boolean[maze.N() * maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        int s = 0;
        distTo[s] = 0;
        bfs(s);
    }

    // Helper methods go here
    private void bfs(int v) {
        onStack[v] = true;
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (this.hasCycle()) {return;}
            else if (!marked[w]) {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                bfs(w);
            }
            else if (marked[w] && edgeTo[v] != w) {
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(v);
                cycle.push(w);
                edgeTo = new int[maze.N() * maze.N()];
                int start = (Integer) cycle.pop();
                for (int i = 0; i < cycle.size() - 1; i ++) {
                    edgeTo[start] = (Integer) cycle.pop();
                    announce();
                    start = edgeTo[start];
                }
                return;
            }
        }
    }

    private boolean hasCycle() {return cycle != null;}
}

