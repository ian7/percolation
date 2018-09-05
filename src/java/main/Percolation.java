import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF weightedQuickUnionUf;
  private final int size;
  private final int topElementIndex;
  private final int bottomElementIndex;
  private int numberOfOpenSites = 0;
  private boolean[] openMarker;

  /**
   * constructor.
   *
   * @param n size
   */
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    this.size = n;
    final int gridSize = this.size * this.size;
    this.openMarker = new boolean[gridSize];

    this.weightedQuickUnionUf = new WeightedQuickUnionUF(gridSize + 2);
    this.topElementIndex = gridSize;
    this.bottomElementIndex = gridSize + 1;
  }

  public void open(int row, int col) {
    if (row < 1 || row > this.size || col < 1 || col > this.size) {
      throw new IllegalArgumentException();
    }

    int elementIndex = getElementIndex(row, col);

    if (!openMarker[elementIndex]) {
      final int top = elementIndex - this.size;
      final int bottom = elementIndex + this.size;
      final int left = elementIndex - 1;
      final int right = elementIndex + 1;

      if (col > 1 && isOpen(row, col - 1)) {
        this.weightedQuickUnionUf.union(elementIndex, left);
      }
      if (col < this.size && isOpen(row, col + 1)) {
        this.weightedQuickUnionUf.union(elementIndex, right);
      }

      if (row == 1) {
        this.weightedQuickUnionUf.union(elementIndex, topElementIndex);
      }
      if (row > 1 && isOpen(row - 1, col)) {
        this.weightedQuickUnionUf.union(elementIndex, top);
      }
      if (row == this.size) {
        this.weightedQuickUnionUf.union(elementIndex, bottomElementIndex);
      }
      if (row < this.size) {
        this.weightedQuickUnionUf.union(elementIndex, bottom);
      }

      this.openMarker[elementIndex] = true;
      this.numberOfOpenSites++;
    }

  }    // open site (row, col) if it is not open already

  private int getElementIndex(int row, int col) {
    return (row - 1) * this.size + col - 1;
  }

  public boolean isOpen(int row, int col) {
    if (row < 1 || row > this.size || col < 1 || col > this.size) {
      throw new IllegalArgumentException();
    }
    return openMarker[getElementIndex(row, col)];
  }  // is site (row, col) open?

  public boolean isFull(int row, int col) {
    if (row < 1 || row > this.size || col < 1 || col > this.size) {
      throw new IllegalArgumentException();
    }
    return
      isOpen(row, col) &&
        find(getElementIndex(row, col)) == find(topElementIndex);
  }  // is site (row, col) full?

  public int numberOfOpenSites() {
    return this.numberOfOpenSites;
  }       // number of open sites

  public boolean percolates() {
    return find(topElementIndex) == find(bottomElementIndex);
  }              // does the system percolate?

  private int find(int n) {
    return this.weightedQuickUnionUf.find(n);
  }
}
