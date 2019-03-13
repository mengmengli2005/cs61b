import java.util.ArrayList;

public class UnionFind {

    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    private int[] Parent;

    public UnionFind(int n) {
        // TODO
        Parent = new int[n];
        for (int i = 0; i < n; i += 1) {
            Parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex > Parent.length - 1) {
            throw new ArrayIndexOutOfBoundsException(vertex + "is not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int root = find(v1);
        return (-1) * Parent[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return Parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        if (connected(v1, v2)) {return;}
        int root1 = find(v1);
        int root2 = find(v2);
        if (sizeOf(v1) <= sizeOf(v2)) {
            Parent[root2] += Parent[root1];
            Parent[root1] = root2;
        }else {
            Parent[root1] += Parent[root2];
            Parent[root2] = root1;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        int cmp = vertex;
        while(Parent[vertex] >= 0) {
            vertex = Parent[vertex];
        }
//        Parent[cmp] = vertex;
        return vertex;
    }

    public static void main(String[] args) {
        UnionFind DS = new UnionFind(10);
        DS.union(0,1);
        DS.union(0,2);
        DS.union(0,3);
        DS.union(5,6);
        DS.union(6,7);
        DS.union(7,8);
        DS.union(5,9);

        System.out.println("1 and 2 is connected: " + DS.connected(1,2) + ", should be true.");
        System.out.println("3 and 4 is connected: " + DS.connected(3,4) + ", should be false.");
        System.out.println("7 and 9 is connected: " + DS.connected(7,9) + ", should be true.");
        System.out.println("The size of 4's set is: " + DS.sizeOf(4) + ", should be 1.");
    }

}