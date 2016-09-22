import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private int n;
    private int length;
    private int topIndex;
    private int bottomIndex;
    private WeightedQuickUnionUF uf;
    private boolean[] opened;
    
    public Percolation(int n)
    {
        if (n <= 0) 
        {
            throw new java.lang.IllegalArgumentException("n must be > 0");
        }
        
        this.n = n;
        length = n * n;
        topIndex = length;
        bottomIndex = length + 1;
        opened = new boolean[length];
        
        uf = new WeightedQuickUnionUF(length + 2);

        for (int i = 0; i < length; i++)
        {
            opened[i] = false;
        }
    }
    
    private int getIndex(int i, int j)
    {
        if (i < 1 || i > n || j < 1 || j > n)
        {
            throw new java.lang.IndexOutOfBoundsException(
                "Parameter is out of bounds"
            );
        }
        
        // get index by cell number
        return (i - 1) * n + j - 1;
    }
    
    private int[] adjacentIndexes(int i, int j)
    {
        int[] indexes = new int[4];
        int index = getIndex(i, j);

        if (j == 1) // left side
        {
            indexes[0] = index - n;
            indexes[1] = index + 1;
            indexes[2] = index + n;
            indexes[3] = -1;
        }
        else if (j == n) // right side
        {
            indexes[0] = index - n;
            indexes[1] = index - 1;
            indexes[2] = index + n;
            indexes[3] = -1;
        }
        else // somewhere in the middle
        {
            indexes[0] = index - n;
            indexes[1] = index - 1;
            indexes[2] = index + 1;
            indexes[3] = index + n;
        }

        return indexes;
    }
    
    // open site (row i, column j) if it is not open already
    public void open(int i, int j)
    {
        int index = getIndex(i, j);
        boolean hasAdjacent = false;
        
        if (opened[index]) return;
        
        opened[index] = true;

        int[] adjacentIndexes = adjacentIndexes(i, j);
        
        
        // if site is in top row, connect it to top index
        if (i == 1)
        {
            uf.union(index, topIndex);
        }

        for (int k = 0; k < adjacentIndexes.length; k++)
        {
            if (adjacentIndexes[k] < 0 || adjacentIndexes[k] > length - 1) continue;
            
            if (opened[adjacentIndexes[k]])
            {
                uf.union(index, adjacentIndexes[k]);
                hasAdjacent = true;
            }
        }
        
        // if site has adjacent sites, find if any of bottom 
        // row sites is now connected to top and union it 
        // with bottom index
        if (hasAdjacent || n == 1)
        {
            for (int k = length - n; k < length; k++)
            {
                if (opened[k] && uf.connected(topIndex, k))
                {
                    uf.union(k, bottomIndex);
                }
            }
        }
    }
    
    public boolean isOpen(int i, int j) // is site (row i, column j) open?
    {
        return opened[getIndex(i, j)];
    }
    
    public boolean isFull(int i, int j) // is site (row i, column j) full?
    {
        int index = getIndex(i, j);
        return uf.connected(index, topIndex);
    }
    
    public boolean percolates() // does the system percolate?
    {
        return uf.connected(topIndex, bottomIndex);
    }
    
    public static void main(String[] args) 
    {
        Percolation p = new Percolation(4);
        p.open(1, 1);
        System.out.println(p.percolates());
        
        p.open(2, 1);
        System.out.println(p.percolates());
        
        p.open(3, 1);
        System.out.println(p.percolates());
        
        p.open(3, 2);
        System.out.println(p.percolates());
        
        p.open(4, 2);
        System.out.println(p.percolates());
    }
}