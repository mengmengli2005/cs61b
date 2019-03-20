import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 *
 * 1.PriorityQueue construction
 *      * comparator()
 * 2.Node construction using flights data
 *      * construct nodes as PQ's nodes
 *      * push nodes into PQ
 * 3.Scan the "time line": PQ.pop() nodes to find "globalMax"
 *
 */
public class FlightSolver {
    private PriorityQueue<Node> timeNodePQ;
    private ArrayList<Flight> flights;

    FlightSolver(ArrayList<Flight> flights) {

        // 2.Construct timeNodePQ using flights data.
        PriorityQueue<Node> timeNodePQ = new PriorityQueue<>();
        for (int i = 0; i < flights.size(); i += 1) {
            Node startNode = new Node(flights.get(i).startTime, i);
            timeNodePQ.add(startNode);
            Node endNode = new Node(-1 * flights.get(i).endTime, i);
            timeNodePQ.add(endNode);
        }

        this.timeNodePQ = timeNodePQ;
        this.flights = flights;
    }

    int solve() {
        // 3.Scan the "time line": PQ.pop() nodes to find "globalMax".
        int globalMax = 0;
        int cur = 0;
        for (int i = 0; i < timeNodePQ.size(); i += 1) {
            Node node = timeNodePQ.poll();
            if (node.value < 0) cur = cur - flights.get(node.index).passengers;
            else cur = cur + flights.get(node.index).passengers;

            if (globalMax < cur) globalMax = cur;
        }
        return globalMax;
    }

    protected class Node implements Comparable<Node> {
        int value; //time value
        int index; //Flight index

        Node(int val, int ind) {
            if (ind < 0) throw new IndexOutOfBoundsException("The flight index must be positive.");
            this.value = val;
            this.index = ind;
        }

        @Override
        public int compareTo(Node o) {
            if (this.value == -1 * o.value && this.value != 0) return -1 * (this.value - o.value);
            return Math.abs(this.value) - Math.abs(o.value);
        }
    }


    // 1.PriorityQueue construction.
    PriorityQueue<Node> pq = new PriorityQueue<>(
            new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if (o1.value == -1 * o2.value && o1.value != 0) return -1 * (o1.value - o2.value);
                    return Math.abs(o1.value) - Math.abs(o2.value);
                }
            }
    );


//    protected class PriorityQueue<Node> {
//
//        //add a node into the PQ
//        void add(Node node) {
//
//        }
//
//        //return and remove the top node in the PQ
//        Node poll() {
//
//            return null;
//        }
//
//        //compare node using abs(value)
//        int comparator() {
//
//            return 0;
//        }
//    }

}