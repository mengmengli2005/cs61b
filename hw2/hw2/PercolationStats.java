package hw2;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    double[] Possibility;
    double MeanOfPossibility;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        double[] Possibility = new double[T];
        for (int i = 0; i < T; i ++) {
            Percolation P = pf.make(N);
            Possibility[i] = Trail(P, N);
        }

        MeanOfPossibility = mean();

    }

    // A trail: calculation p/p* for a single N*N Percolation.
    private double Trail(Percolation perc, int N) {
        while (!perc.percolates()) {
            int a = StdRandom.uniform(0, N);
            int b = StdRandom.uniform(0, N);
            perc.open(a, b);
        }
        return (double) perc.openSize / (N * N);
    }

    public double sum() {
        double accumulate = 0;
        for (int i = 0; i < Possibility.length; i ++) accumulate += this.Possibility[i];
        return accumulate;
    }

    // sample mean of percolation threshold
    public double mean() {
        return sum() / Possibility.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return 0.0;
    }

}
