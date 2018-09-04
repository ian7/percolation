import edu.princeton.cs.algs4.RandomSeq;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    int size;
    private double thresholds[];

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        this.thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            final Percolation percolation = new Percolation(n);
            thresholds[i] = 0;

            while (!percolation.percolates()) {
                final int col = StdRandom.uniform(n) + 1;
                final int row = StdRandom.uniform(n) + 1;
                percolation.open(row, col);
            }
            thresholds[i] = ((double) percolation.numberOfOpenSites()) / (size * size);
        }
    }

    public static void main(String[] args)        // test client (described below)
    {

    }

    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(this.thresholds);
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(this.thresholds);
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return (mean() - 1.96 * stddev() / this.size);
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return (mean() + 1.96 * stddev() / this.size);
    }
}
