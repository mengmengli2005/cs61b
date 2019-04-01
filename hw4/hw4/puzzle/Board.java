package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;
import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.BLANK;

public class Board implements WorldState {
    private int[][] start;
    private int[][] goal;
    private int N;

    public Board(int[][] tiles) {
        this.start = tiles;
        this.N = tiles[0].length;
        this.goal = findGoal();
    }
    private int[][] findGoal() {
        int s = 0;
        int[][] goal = new int[N][N];
        for (int i = 0; i < N - 1; i ++) {
            for (int j = 0; j < N; j ++) {
                goal[i][j] = s ++;
            }
        }
        goal[N - 1][N - 1] = 0;
        return goal;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) throw new IndexOutOfBoundsException();
        return start[i][j]; // 返回哪个tile？？？
    }

    public int size() {
        return this.N;
    }

    @Override
    public Iterable<WorldState> neighbors() {  //Sited from CS61B@UCB @Author: Josh Hug.
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }

        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {

        return 0;
    }

    public int manhattan() {

        return 0;
    }

    @Override
    public int estimatedDistanceToGoal() {

        return 0;
    }

    public boolean equals(Object y) {

        return false;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
