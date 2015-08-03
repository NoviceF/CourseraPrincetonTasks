public class Percolation 
{
	public Percolation(int n) 
	{
		if (n < 1)
			throw new java.lang.IllegalArgumentException();
		//TODO: 1 site example 
		// Add high and low lines
		N_ = n;
		fields_ = new int[N_ * N_ + 2];
        uf_ = new WeightedQuickUnionUF(N_ * N_ + 2);

        // init high and low lines
		for (int j = 0; j < N_ * N_; ++j) 
		{
			fields_[j] = 0;
		}

        fields_[N_*N_]=1;
        fields_[N_*N_+1]=1;		
    }

	public void open(int i, int j) 
	{
        Validate(i, j);

        if (isOpen(i,j))
            return;       

        int site = getSiteIndex(i,j);

        fields_[site]=1;
        //if not top row
        if (i!=1 && isOpen(i-1,j))
        {
            union(getSiteIndex(i-1,j),site);
        }
        else if (i==1)
        {
        //connect to virtual top cell
            union(site,N_ * N_);
        }

        //if not bottom row
        if (i != N_ && isOpen(i+1,j))
        {       
            union(getSiteIndex(i+1,j),site);
        }
        else if (i == N_)
        {
        //connect to virtual bottom cell
            union(site,N_ * N_ + 1);
        }
        //if not left border
        if (j != 1 && isOpen(i, j - 1))
            union(getSiteIndex(i, j - 1),site);
        //if not right border
        if (j != N_ && isOpen(i, j + 1))
            union(getSiteIndex(i ,j + 1), site);
    }

	public boolean isOpen(int i, int j) 
	{ 
       Validate(i,j);
       return fields_[getSiteIndex(i,j)] == 1;
    }
	
	private void Validate(int i, int j)
	{
        if (i <= 0 || j <= 0 || i > N_ || j > N_)
			throw new IndexOutOfBoundsException();
	}
	
	public boolean isFull(int i, int j) 
	{ 
        Validate(i,j);
        return uf_.connected(N_*N_,getSiteIndex(i, j));
    }

	public boolean percolates()  
	{ 
		return uf_.connected(N_ * N_, N_ * N_ + 1);
    }
	
    private void union(int a, int b)
    {
        if (!uf_.connected(a,b))
            uf_.union(a,b);
    }
	
    private int getSiteIndex(int row, int column)
    {
        return (N_*(row-1))+column-1;
    }

	private int N_;
	private int[] fields_;
    private WeightedQuickUnionUF uf_;
	
	public static void main(String[] args) 
	{
		
		Percolation perc = new Percolation(2);
//		perc.PrintArray();
		perc.open(1, 1);
		perc.open(2, 2);
		perc.open(1, 2);
//		perc.PrintArray();
		
        System.out.println("is site full = " + perc.isFull(2, 1));
        System.out.println("is site full = " + perc.isFull(2, 2));
        System.out.println("is percolates = " + perc.percolates());
	}
}