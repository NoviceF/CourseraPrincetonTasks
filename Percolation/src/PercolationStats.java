import java.security.PermissionCollection;

public class PercolationStats 
{
    public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
    {
    	if (N <= 0 || T <= 0)
    		throw new java.lang.IllegalArgumentException();
    	
    	fractions_ = new double[T];
    	int sitesCount = N * N;
    	
        for (int i = 0; i < T; ++i)
        {
        	Percolation perc = new Percolation(N);
        	
        	for (int j = 0; j < sitesCount; ++j) 
        	{
                Percolation.CoordPair pair = ConvertOne2TwoDemIndex(StdRandom.uniform(N), N);

                while (perc.isOpen(pair.i, pair.j))
                {
                    pair = ConvertOne2TwoDemIndex(StdRandom.uniform(N), N);
                }

                perc.open(pair.i, pair.j);
                perc.PrintArray();
                
                if (perc.percolates())
                {
                    fractions_[i] = i / sitesCount;
                    break;
                }
        	}
        }
    }
    
    private Percolation.CoordPair ConvertOne2TwoDemIndex(int i, int n)
    {
    	Percolation.CoordPair pair = new Percolation.CoordPair();
    	// because index start from 1
    	pair.i = i / n + 1;
    	pair.j = i % n + 1;
    	
    	return pair;
    }

    public double mean()                      // sample mean of percolation threshold
    {
    	return 0.0;
    }
    	
    public double stddev()                    // sample standard deviation of percolation threshold
    {
    	return 0.0;
    	
    }
    public double confidenceLo()              // low  endpoint of 95% confidence interval
    {
    	return 0.0;
    	
    }
    public double confidenceHi()              // high endpoint of 95% confidence interval
    {
    	return 0.0;
    	
    }
    
    private double fractions_[];

    public static void main(String[] args) 
    {
    	if (args.length < 2)
    	{
            System.out.println("invalid argument count!");
    		System.exit(0);
    	}

    	int N = Integer.parseInt(args[0]);
    	int T = Integer.parseInt(args[1]);

        System.out.println("N = " + N + " T = " + T);

        PercolationStats stats = new PercolationStats(N, T);
        
    	
	}
}
