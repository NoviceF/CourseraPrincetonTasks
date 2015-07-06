
public class PercolationStats 
{
    public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
    {
    	if (N <= 0 || T <= 0)
    		throw new java.lang.IllegalArgumentException();
    	
    	TimesToRun_ = T;
    	fractions_ = new double[TimesToRun_];
    	int sitesCount = N * N;
    	
        for (int experementsCounter = 0; experementsCounter < TimesToRun_;
        		++experementsCounter)
        {
        	Percolation perc = new Percolation(N);
        	
        	for (int countsOfTry = 0; countsOfTry < sitesCount; ++countsOfTry) 
        	{
                Percolation.CoordPair pair = 
                		ConvertOne2TwoDemIndex(StdRandom.uniform(N), N);

                while (perc.isOpen(pair.i, pair.j))
                {
                    pair = ConvertOne2TwoDemIndex(StdRandom.uniform(sitesCount), N);
                }

                perc.open(pair.i, pair.j);
//                perc.PrintArray();
                
                if (perc.percolates())
                {
                	double rate = (double)countsOfTry / (double)sitesCount;
                    fractions_[experementsCounter] = rate;
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
    	return StdStats.mean(fractions_);
    }
    	
    public double stddev()                    // sample standard deviation of percolation threshold
    {
    	return StdStats.stddev(fractions_);
    }

    public double confidenceLo()              // low  endpoint of 95% confidence interval
    {
    	double mean = mean();
    	double stdDev = stddev();
    	return mean - (1.96 * stdDev / Math.sqrt(TimesToRun_));
    }
    public double confidenceHi()              // high endpoint of 95% confidence interval
    {
    	double mean = mean();
    	double stdDev = stddev();
    	return mean + (1.96 * stdDev) / Math.sqrt(TimesToRun_);
    }
    
    private double fractions_[];
    private int TimesToRun_;

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
        

        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() +
        		", " + stats.confidenceHi());
	}
}
