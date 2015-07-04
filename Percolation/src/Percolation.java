public class Percolation 
{
	class CoordPair
	{
		int i;
		int j;
	}

	public Percolation(int n) 
	{
		if (n < 1)
			throw new java.lang.IllegalArgumentException();
//		
		N_ = n + 2;
		fields_ = new int[N_][N_];
        uf_ = new WeightedQuickUnionUF(N_ * N_);
//        int lastLine = N_ * (N_ - 1) - 1;
        int lastLine = ConvertTwo2OneDemIndex(N_ - 1, 0);

        // init high and low lines
		for (int j = 0; j < N_; ++j) 
		{
            fields_[0][j] = 1;
            uf_.union(0, j);
            fields_[N_ - 1][j] = 1;
            uf_.union(lastLine, lastLine + j);
		}
		
		int checkBound1 = 0;
		int checkBound2 = N_ - 1;
		assert(uf_.connected(checkBound1, checkBound2));

		int checkBound3 = lastLine;
		int checkBound4 = ConvertTwo2OneDemIndex(N_ - 1, N_ - 1);
		assert(uf_.connected(checkBound3, checkBound4));
    }

	public void Open(int i, int j) 
	{
		Validate(i);
		Validate(j);
		// Convert two-dimensional array index to one-dimensional
		CoordPair[] neighbors = GetNeighbors(i, j); 
		int oneDemArrayIndex = ConvertTwo2OneDemIndex(i, j);
		
		for (int k = 0; k < neighbors.length; ++k)
		{
			if (neighbors[k] != null)
			{
				int neighborCoord = ConvertTwo2OneDemIndex(neighbors[k].i, 
						neighbors[k].j);
				
                if (!uf_.connected(oneDemArrayIndex, neighborCoord))
                {
                    uf_.union(oneDemArrayIndex, neighborCoord);
                }
			}
		}
		
        fields_[i][j] = 1;
	}

	boolean IsOpen(int i, int j) 
	{ 
		Validate(i);
		Validate(j);

		return fields_[i][j] == 1;
    }
	
	private boolean IsOpenOutBounds(int i, int j)
	{
		ValidateOutBounds(i);
		ValidateOutBounds(j);

		return fields_[i][j] == 1;
	}
	
	private void ValidateOutBounds(int index)
	{
		if (index > N_ + 1 || index < 0) 
			throw new IndexOutOfBoundsException("row index is out of bounds");
	}

	boolean IsFull(int i, int j) 
	{ 
		Validate(i);
		Validate(j);
		
		boolean isFull = false;
		
        int siteCoords = ConvertTwo2OneDemIndex(i, j);
		
		for (int k = 1; k < (N_ - 1); ++k)
		{
			int baseCoords = ConvertTwo2OneDemIndex(0, k);
			
			if (!isFull)
				isFull = uf_.connected(baseCoords, siteCoords);
			else 
				return true;
		}
		
		return isFull; 
    }
	public boolean Percolates()  { return true; }
	
	private void Validate(int index)
	{
		if (index > N_ || index < 1)
        {
			throw new IndexOutOfBoundsException("row index " + index + 
					" is out of bounds");
        }
	}
	
	private CoordPair[] GetNeighbors(int i, int j)
	{
		Validate(i);
		Validate(j);
		CoordPair[] neighbors = new CoordPair[4];
		// need to check neighbors sites on open
		if (IsOpenOutBounds(i - 1, j))
		{
			neighbors[0] = new CoordPair();
			neighbors[0].i = i - 1;
			neighbors[0].j = j;
		}
		
		if (IsOpenOutBounds(i + 1, j))
		{
			neighbors[1] = new CoordPair();
			neighbors[1].i = i + 1;
			neighbors[1].j = j;
		}
		
		if (IsOpenOutBounds(i, j - 1))
		{
			neighbors[2] = new CoordPair();
			neighbors[2].i = i;
			neighbors[2].j = j - 1;
		}
		
		if (IsOpenOutBounds(i, j + 1))
		{
			neighbors[3] = new CoordPair();
			neighbors[3].i = i;
			neighbors[3].j = j + 1;
		}

		return neighbors;
	}
	
	
	private int ConvertTwo2OneDemIndex(int i, int j)
	{
		return i * N_ + j;
	}

	private int N_;
	private int[][] fields_;
    private WeightedQuickUnionUF uf_;
	
	void PrintArray()
	{
		for (int[] is : fields_) 
		{
			for (int i : is) 
			{
                System.out.print(i + " ");
			}
                System.out.println();
		}

        System.out.println();
	}

	public static void main(String[] args) 
	{
		
		Percolation perc = new Percolation(2);
		perc.PrintArray();
		perc.Open(1, 1);
		perc.Open(2, 2);
		perc.Open(1, 2);
		perc.PrintArray();
		
		
        System.out.println("is site full = " + perc.IsFull(2, 1));
        System.out.println("is site full = " + perc.IsFull(2, 2));
        

		
		
		

//		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(15);
//		uf.union(0, 3);
//		if (uf.connected(0, 3))
//        {
//            System.out.println("1 4 is connected!");
//        }
//		
//		if (!uf.connected(2, 3))
//		{
//            System.out.println("2 3 is not connected!"); 
//		}
	}
}