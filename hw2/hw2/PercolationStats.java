package hw2;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    double[] Possibility;
//    double MeanOfPossibility;
//    double StandardDeviation;
//    double[] ConfidenceInterval;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        double[] Possibility = new double[T];
        for (int i = 0; i < T; i ++) {
            Percolation P = pf.make(N);
            Possibility[i] = Trail(P, N);
        }
        this.Possibility = Possibility;
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
        for (double p: Possibility) accumulate += p;
        return accumulate;
    }

    // sample mean of percolation threshold
    public double mean() { return sum() / Possibility.length; }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double accumulate = 0;
        double Mean = mean();
        for (double p: Possibility) accumulate += (p - Mean) * (p - Mean);
        return accumulate / (Possibility.length - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(Possibility.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev()) / Math.sqrt(Possibility.length);
    }


    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats PS = new PercolationStats(500, 100, pf);

        double MeanOfPossibility = PS.mean();
        System.out.println("The sample mean of percolation threshold is: " + MeanOfPossibility);
        double StandardDeviation = PS.stddev();
        System.out.println("The sample standard deviation of percolation threshold is: " + StandardDeviation);
        double[] ConfidenceInterval = {PS.confidenceLow(), PS.confidenceHigh()};
        System.out.println("The 95% confidence interval of percolation threshold is: " + ConfidenceInterval);

    }
}
