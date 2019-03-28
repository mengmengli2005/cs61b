package lab11.graphs;

import edu.princeton.cs.algs4.Queue;
/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Queue<Integer> pq;
    private boolean targetFound;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        pq = new Queue<> ();
        pq.enqueue(s);
        marked[0] = true;
        targetFound = false;
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()

        if (pq.isEmpty()) {
            return;
        }else {
            int cur = pq.dequeue();
            if (cur == t) targetFound = true;
            if (targetFound) return;

            for (int w : maze.adj(cur)) {
                if (!marked[w]) {
                    pq.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = cur;
                    distTo[w] = distTo[cur] + 1;
                    announce();
                    if (targetFound) return;
                }
            }
            bfs();
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

