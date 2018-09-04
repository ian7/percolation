import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF weightedQuickUnionUF = null;
    private int size = 0;
    private int numberOfOpenSites = 0;
    private boolean openMarker[];
    private int topElementIndex;
    private int bottomElementIndex;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        final int gridSize = this.size * this.size;
        this.openMarker = new boolean[gridSize];

        this.weightedQuickUnionUF = new WeightedQuickUnionUF(gridSize + 2);
        this.topElementIndex = gridSize;
        this.bottomElementIndex = gridSize + 1;
    }

    public void open(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size) {
            throw new IllegalArgumentException();
        }

        int elementIndex = getElementIndex(row, col);

        if (openMarker[elementIndex] == false) {
            final int top = elementIndex - this.size;
            final int bottom = elementIndex + this.size;
            final int left = elementIndex - 1;
            final int right = elementIndex + 1;

            if (col > 1 && isOpen(row, col - 1)) {
                this.weightedQuickUnionUF.union(elementIndex, left);
            }
            if (col < this.size && isOpen(row, col + 1)) {
                this.weightedQuickUnionUF.union(elementIndex, right);
            }

            if (row == 1) {
                this.weightedQuickUnionUF.union(elementIndex, topElementIndex);
            }
            if (row > 1 && isOpen(row - 1, col)) {
                this.weightedQuickUnionUF.union(elementIndex, top);
            }
            if (row == this.size) {
                this.weightedQuickUnionUF.union(elementIndex, bottomElementIndex);
            }
            if (row < this.size)
                this.weightedQuickUnionUF.union(elementIndex, bottom);

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
        return !isOpen(row, col);
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }       // number of open sites

    public boolean percolates() {
        return this.weightedQuickUnionUF.find(topElementIndex) == this.weightedQuickUnionUF.find(bottomElementIndex);
    }              // does the system percolate?

}
