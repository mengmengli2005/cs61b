package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.BLANK;

public class Board implements WorldState {
    private int[][] board;
    private int[][] goal;
    private int N;

    public Board(int[][] tiles) {
        this.board = tiles;
        this.N = tiles[0].length;
        this.goal = findGoal();
    }
    private int[][] findGoal() {
        int s = 1;
        int[][] goal = new int[N][N];
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j ++) {
                goal[i][j] = s ++;
            }
        }
        goal[N - 1][N - 1] = 0;
        return goal;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) throw new IndexOutOfBoundsException();
//        if (board[i][j] == 0) return 0;
        return board[i][j]; // 返回哪个tile？？？
    }

    public int size() {
        return this.N;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int xIndex = -1, yIndex = -1;
        boolean flag = false;
        for (int i = 0; i < N; i ++) {
            if (flag) break;
            for (int j = 0; j < N; j ++) {
                if (board[i][j] == 0) {
                    xIndex = i;
                    yIndex = j;
                    flag = true;
                    break;
                }
            }
        }
        int[][] neighborUp = copyBoard(board);
        if (xIndex - 1 >= 0) {
            neighborUp[xIndex][yIndex] = tileAt(xIndex - 1, yIndex);
            neighborUp[xIndex - 1][yIndex] = 0;
            neighbors.enqueue(new Board(neighborUp));
        }
        int[][] neighborDown = copyBoard(board);
        if (xIndex + 1 <= N - 1) {
            neighborDown[xIndex][yIndex] = tileAt(xIndex + 1, yIndex);
            neighborDown[xIndex + 1][yIndex] = 0;
            neighbors.enqueue(new Board(neighborDown));
        }
        int[][] neighborLeft = copyBoard(board);
        if (yIndex - 1 >= 0) {
            neighborLeft[xIndex][yIndex] = tileAt(xIndex, yIndex - 1);
            neighborLeft[xIndex][yIndex - 1] = 0;
            neighbors.enqueue(new Board(neighborLeft));
        }
        int[][] neighborRight = copyBoard(board);
        if (yIndex + 1 <= N - 1) {
            neighborRight[xIndex][yIndex] = tileAt(xIndex, yIndex + 1);
            neighborRight[xIndex][yIndex + 1] = 0;
            neighbors.enqueue(new Board(neighborRight));
        }
        return neighbors;
    }

    private int[][] copyBoard(int[][] board) {
        int M = board.length;
        int N = board[0].length;
        int[][] newBoard = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
//    public Iterable<WorldState> neighbors() {  //Sited from CS61B@UCB @Author: Josh Hug.
//        Queue<WorldState> neighbors = new Queue<>();
//        int hug = size();
//        int bug = -1;
//        int zug = -1;
//        for (int rug = 0; rug < hug; rug++) {
//            for (int tug = 0; tug < hug; tug++) {
//                if (tileAt(rug, tug) == BLANK) {
//                    bug = rug;
//                    zug = tug;
//                }
//            }
//        }
//
//        int[][] ili1li1 = new int[hug][hug];
//        for (int pug = 0; pug < hug; pug++) {
//            for (int yug = 0; yug < hug; yug++) {
//                ili1li1[pug][yug] = tileAt(pug, yug);
//            }
//        }
//        for (int l11il = 0; l11il < hug; l11il++) {
//            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
//                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
//                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
//                    ili1li1[l11il][lil1il1] = BLANK;
//                    Board neighbor = new Board(ili1li1);
//                    neighbors.enqueue(neighbor);
//                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
//                    ili1li1[bug][zug] = BLANK;
//                }
//            }
//        }
//        return neighbors;
//    }

    public int hamming() {
        int wrong = 0;
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j ++) {
                int curValue = i * N + j + 1;
                int correctValue = (curValue == N * N) ? 0 : curValue;
                int actualValue = board[i][j];
                if (actualValue != correctValue) {
                    wrong++;
                }
            }
        }
        return wrong;
    }

    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < N; i ++) {
            for (int j =0; j < N; j ++) {
                //if (i == N - 1 && j == N - 1) continue;
                int curValue = i * N + j + 1;
                int correctValue = (curValue == N * N) ? 0 : curValue;
                int actualValue = board[i][j];
                if (actualValue != correctValue) {
                    dist += Math.abs(actualValue / N - i) + Math.abs(actualValue % N - 1 - j);
                }
            }
        }
        return dist;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
//        return (hamming() + manhattan()) / 2;
    }

    public boolean equals(Object y) {
        if (y.getClass() != getClass()) throw new ExceptionInInitializerError();
        int[][] that = ((Board)y).board;
        for (int i = 0; i < N; i ++) {
            for (int j =0; j < N; j ++) {
                if (board[i][j] != that[i][j]) return false;
            }
        }
        return true;
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
