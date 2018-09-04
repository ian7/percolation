import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF weightedQuickUnionUF = null;
    private int size = 0;
    private int numberOfOpenSites = 0;
    private boolean openMarker[];

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        final int gridSize = this.size * this.size;
        final int extendedGridSize = gridSize + 2 * size;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(extendedGridSize + 2);
        this.openMarker = new boolean[extendedGridSize];
        final int topElementIndex = extendedGridSize;
        final int bottomElementIndex = extendedGridSize + 1;
        for (int i = 0; i < n; i++) {
            // connect upper element to the first row
            weightedQuickUnionUF.union(topElementIndex, i);
            // connect lower element
            weightedQuickUnionUF.union(bottomElementIndex, extendedGridSize - i - 1);
        }
    }

    public void open(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size) {
            throw new IllegalArgumentException();
        }

        int elementIndex = getElementIndex(row, col);

        if (openMarker[elementIndex] == false) {
            if (col > 1) {
                this.weightedQuickUnionUF.union(elementIndex, elementIndex - 1);
            }
            if (col < this.size) {
                this.weightedQuickUnionUF.union(elementIndex, elementIndex + 1);
            }

            this.weightedQuickUnionUF.union(elementIndex, elementIndex - this.size);
            this.weightedQuickUnionUF.union(elementIndex, elementIndex + this.size);

            this.openMarker[elementIndex] = true;
            this.numberOfOpenSites++;
        }

    }    // open site (row, col) if it is not open already

    private int getElementIndex(int row, int col) {
        return row * this.size + col - 1;
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
        final int gridSize = this.size * this.size;
        return this.weightedQuickUnionUF.find(gridSize) == this.weightedQuickUnionUF.find(gridSize + 1);
    }              // does the system percolate?

}
