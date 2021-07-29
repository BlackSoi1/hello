import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
    private final double[] samples;
    private final int trails;
    private static final double CONFIDENCE_95 =1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trails = trials;
        samples = new double[trials];
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < trials; i++) {
            Percolation pl = new Percolation(n);
            while (!pl.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                pl.open(row, col);
                // System.out.println("Open (" + row + ", " + col + ")");
            }
            double number = pl.numberOfOpenSites();
            samples[i] = number / (n * n);
        }
        // for(int i =0;i<samples.length;i++){
        //     System.out.println(samples[i]);
        // }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(samples);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(samples);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trails);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trails);
    }

    // test client (see below)
    public static void main(String[] args) {
        // if(args.length!=2){
        //     System.out.println(args.length);
        //     System.out.println("Incorrect number of arguments input!");
        //     return;
        // }
        // try{
            int n = Integer.parseInt(args[0]);
            int trails = Integer.parseInt(args[1]);
            // int n = StdIn.readInt();
            // int trails = StdIn.readInt();
            PercolationStats pls = new PercolationStats(n, trails);
            double mean = pls.mean();
            double stddev = pls.stddev();
            double confidenceLo = pls.confidenceLo();
            double confidenceHi = pls.confidenceHi();
            System.out.println("mean              = " + mean);
            System.out.println("stddev              = " + stddev);
            System.out.println("95% confidence interval              = [" + confidenceLo + ", " + confidenceHi + "]");
        // }
        // catch (NumberFormatException e)
        // {
        //     System.out.println("Incorrect format of arguments input!");
        // }
        // catch (IllegalArgumentException e)
        // {
        //     System.out.println("Illegal arguments input!");
        // }
    }
}
