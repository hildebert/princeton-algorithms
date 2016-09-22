public class WeightedQuickUnionWithCountUF
{
    private int[] id;
    private int[] sz;
    private int connectedComponents;
    
    public WeightedQuickUnionWithCountUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        connectedComponents = N;
        
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
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
        
        connectedComponents--;
        
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
    }
    
    public boolean allConnected()
    {
        //System.out.println(connectedComponents);
        return connectedComponents == 1;
    }
    
     public static void main(String[] args) {
         // simulate 16 timestamps
         String[] timestamps = { 
             "2016.08.01", "2016.08.02", "2016.08.03", "2016.08.04", 
             "2016.08.05", "2016.08.06", "2016.08.07", "2016.08.08", 
             "2016.08.09", "2016.08.10", "2016.08.11", "2016.08.12",
             "2016.08.13", "2016.08.14", "2016.08.15", "2016.08.16",
         };
         
         // 16 outgoing friend requests
         int[] from = { 
             0, 0, 0, 0, 
             1, 1, 1, 1, 
             2, 2, 2, 2, 
             3, 3, 3, 3, 
         };
                  
         // 16 accepted friend requests
         int[] to = { 
             1, 2, 3, 4, 
             2, 3, 4, 5,
             6, 7, 8, 9,
             6, 7, 8, 9,
         };
         
         WeightedQuickUnionWithCountUF uf = new WeightedQuickUnionWithCountUF(10);

         // simulate one outgoing and accepted request for a date
         for (int i = 0; i < timestamps.length; i++)
         {
             uf.union(from[i], to[i]);

             if (uf.allConnected())
             {
                 System.out.println("All users became connected on " + timestamps[i]);
                 break;
             }
         }
     }
}