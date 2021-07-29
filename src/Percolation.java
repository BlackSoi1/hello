
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int topSite;
    private final int botSite;
    private final boolean[] site;

    private final WeightedQuickUnionUF ufPerc;
    private int openSite;
    // creates n-by-n grid, with all sites initially blocked
    // 0 block
    // 1 open
    private final int n;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        openSite = 0;
        ufPerc = new WeightedQuickUnionUF(n * n + 2);
        site = new boolean[n * n];
        for (int i = 0; i < n * n; i++) {
            site[i] = false;
        }
        this.n = n;

        topSite = n * n;
        botSite = n * n + 1;
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return;
        }
        //如果本格子没打开的话打开
        site[(row - 1) * n + col - 1] = true;
        openSite++;
        //与人造顶端相连
        if (row == 1) {
            ufPerc.union((row - 1) * n + col - 1, topSite);
        }
        //与人造底端相连
        if (row == n) {
            ufPerc.union((row - 1) * n + col - 1, botSite);
        }
        //将新开的格子与之前打开的格子链接
        //左边为空
        if (inside(row - 1, col) && isOpen(row - 1, col)) {
            ufPerc.union((row - 1 - 1) * n + col - 1, (row - 1) * n + col - 1);
        }
        //右边为空
        if (inside(row + 1, col) && isOpen(row + 1, col)) {
            ufPerc.union((row + 1 - 1) * n + col - 1, (row - 1) * n + col - 1);
        }
        //下边为空
        if (inside(row, col + 1) && isOpen(row, col + 1)) {
            ufPerc.union((row - 1) * n + col + 1 - 1, (row - 1) * n + col - 1);
        }
        //上边为空
        if (inside(row, col - 1) && isOpen(row, col - 1)) {
            ufPerc.union((row - 1) * n + col - 1 - 1, (row - 1) * n + col - 1);
        }
    }

    private boolean inside(int row, int col) {
        return !(row <= 0 || col <= 0 || row > n || col > n);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }
        return site[(row - 1) * n + col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        if (ufPerc.find((row - 1) * n + col - 1) == ufPerc.find(topSite)) {
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSite;
    }

    // does the system percolate?
    public boolean percolates() {
        if (ufPerc.find(topSite) == ufPerc.find(botSite)) {
            return true;
        }
        return false;
    }

    // test client (optional)
    // public static void main(String[] args) {
    //

}
