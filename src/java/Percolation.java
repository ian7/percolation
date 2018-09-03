
public class Percolation {
    private int size = 0;
    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {

    }

    public void open(int row, int col) {
    }    // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        return false;
    }  // is site (row, col) open?

    public boolean isFull(int row, int col) {
        return false;
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return 0;
    }       // number of open sites

    public boolean percolates() {
        return false;
    }              // does the system percolate?

}
