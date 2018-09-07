import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private static final double PERCENT_95_CONFIDENCE = 1.96d;
  private final int size;
  private final double[] thresholds;
  private final double mean;

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
    this.mean = StdStats.mean(this.thresholds);
  }

  public static void main(String[] args) {
    return;
  }

  public double mean()                          // sample mean of percolation threshold
  {
    return this.mean;
  }

  public double stddev()                        // sample standard deviation of percolation threshold
  {
    return StdStats.stddev(this.thresholds);
  }

  public double confidenceLo()                  // low  endpoint of 95% confidence interval
  {
    return (this.mean - PERCENT_95_CONFIDENCE * stddev() / this.size);
  }

  public double confidenceHi()                  // high endpoint of 95% confidence interval
  {
    return (this.mean + PERCENT_95_CONFIDENCE * stddev() / this.size);
  }
}
