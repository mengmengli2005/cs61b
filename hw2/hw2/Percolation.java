package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private site[][] Grid;
    private WeightedQuickUnionUF GridUF;
    private int openSize;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        Grid = new site[N][N];
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j ++) {
                Grid[i][j] = new site();
            }
        }
        GridUF = new WeightedQuickUnionUF(N * N + 2); // N*N: number of real sites; 2: one virtual top site and one virtual bottom site.
        openSize = 0;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isException(row, col)) throw new IllegalArgumentException();
        if (Grid[row][col].openness) return;
        Grid[row][col].openness = true;
        this.unionOthers(row, col);
        openSize += 1;
    }

    private void unionOthers(int row, int col) {
        if (isException(row, col)) throw new IllegalArgumentException();
        int N = Grid[0].length;
        if (row == 0) GridUF.union(rcTo1D(row, col), N * N - 2); // opened sites in the first row be union with the virtual top site.
        if (row == N - 1) GridUF.union(rcTo1D(row, col), N * N - 1); // opened sites in the last row be union with the virtual bottom site.
        if (!isException(row, col - 1) && Grid[row][col - 1].openness) GridUF.union(rcTo1D(row, col), rcTo1D(row, col - 1));
        if (!isException(row, col + 1) && Grid[row][col + 1].openness) GridUF.union(rcTo1D(row, col), rcTo1D(row, col + 1));
        if (!isException(row - 1, col) && Grid[row - 1][col].openness) GridUF.union(rcTo1D(row, col), rcTo1D(row - 1, col));
        if (!isException(row + 1, col) && Grid[row + 1][col].openness) GridUF.union(rcTo1D(row, col), rcTo1D(row + 1, col));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isException(row, col)) throw new IllegalArgumentException();
        return Grid[row][col].openness;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) { // 还有bug: 考虑Hug的PPT最后一页
        int N = Grid[0].length;
        if (isException(row, col)) throw new IllegalArgumentException();
        return GridUF.connected(rcTo1D(row, col), N * N - 2);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSize;
    }

    // does the system percolate?
    public boolean percolates() {
        int N = Grid[0].length;
        return GridUF.connected(N * N - 2, N * N - 1);
    }

    private int rcTo1D(int row, int col) {
        if (isException(row, col)) throw new IllegalArgumentException();
        int N = this.Grid[0].length;
        return row * N + col;
    }

    private boolean isException(int row, int col) {
        int N = this.Grid[0].length;
        return (row <= 0 || col <= 0 || row >= N || col >= N);
    }

    private class site {
        boolean openness;
        boolean fullness;

        public site() {
            openness = false;
        }
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }
}
