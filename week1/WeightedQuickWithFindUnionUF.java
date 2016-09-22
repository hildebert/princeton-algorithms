public class WeightedQuickWithFindUnionUF
{
    private int[] id;
    private int[] sz;
    private int[] max;
    
    public WeightedQuickWithFindUnionUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        max = new int[N];
        
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
            max[i] = i;
        }
    }
    
    private int root(int i)
    {
        while (i != id[i]) 
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        
        return i;
    }
    
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }
    
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        
        if (i == j) return;
        
        if (sz[i] < sz[j])
        {
            id[i] = j;
            sz[j] += sz[i];
        }
        else
        {
            id[j] = i;
            sz[i] += sz[j];
        }
        
        
        if (max[i] < max[j])
        {
            max[i] = max[j];
        }
        else
        {
            max[j] = max[i];
        }
    }
    
    public int find(int p)
    {
        return  max[root(p)];
    }
    
    public static void main(String[] args) 
    {
        WeightedQuickWithFindUnionUF uf = new WeightedQuickWithFindUnionUF(10);
                
        System.out.println( uf.find(1) == 1 ); // [1]
            
        uf.union(2, 3);
        System.out.println( uf.find(2) == 3 );
        System.out.println( uf.find(3) == 3 );
        
        uf.union(3, 4);
        System.out.println( uf.find(2) == 4 );
        System.out.println( uf.find(3) == 4 );
        System.out.println( uf.find(4) == 4 );
        
        uf.union(3, 5);
        System.out.println( uf.find(2) == 5 );
        System.out.println( uf.find(3) == 5 );
        System.out.println( uf.find(4) == 5 );
        System.out.println( uf.find(5) == 5 );
        
        uf.union(3, 9);
        System.out.println( uf.find(2) == 9 );
        System.out.println( uf.find(3) == 9 );
        System.out.println( uf.find(4) == 9 );
        System.out.println( uf.find(5) == 9 );
        System.out.println( uf.find(9) == 9 );
    }
}