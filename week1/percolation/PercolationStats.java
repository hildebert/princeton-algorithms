import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
    private int n;
    private int trials;
    private int totalOpenedSites;
    private double[] results;
    private Map<String, Boolean> openCache;
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0) 
        {
            throw new java.lang.IllegalArgumentException("n and trials must be > 0");
        }
        
        this.n = n;
        this.trials = trials;
        totalOpenedSites = 0;
        openCache = new HashMap<String, Boolean>();
        
        results = new double[trials];
        
        for (int k = 0; k < trials; k++)
        {
            int opened = 0;
            Percolation p = new Percolation(n);

            do
            {
                int i = 0;
                int j = 0;
                
                do
                {
                    i = 1 + StdRandom.uniform(n);
                    j = 1 + StdRandom.uniform(n);
                }
                while (openCache.containsKey(i + "-" + j));
                
                openCache.put(i + "-" + j, true);

                p.open(i, j);
                opened++;
            }
            while (!p.percolates());

            openCache.clear();
            results[k] = (double) opened / n / n;
            totalOpenedSites += opened;
        }
    }
    
    public double mean() 
    {
        return (double) totalOpenedSites / trials / n / n;
    }
    
    public double stddev() 
    {
        return StdStats.stddev(results);
    }
    
    public double confidenceLo()
    {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }
    
    public double confidenceHi()
    {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }
    
    public static void main(String[] args) 
    {
        PercolationStats p = new PercolationStats(20, 30);
        
        System.out.println(p.mean());
        System.out.println(p.stddev());
        System.out.println(p.confidenceLo());
        System.out.println(p.confidenceHi());
    }
}