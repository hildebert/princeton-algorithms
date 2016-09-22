public class SuccessorWithDelete
{
    private boolean[] flags;
    private WeightedQuickWithFindUnionUF uf;
    private int length;
    
    public SuccessorWithDelete(int N)
    {
        flags = new boolean[N];
        length = N;
        uf = new WeightedQuickWithFindUnionUF(N);
        
        for (int i = 0; i < N; i++)
        {
            flags[i] = true;
        }
    }
    
    public void remove(int p) 
    {
        flags[p] = false;
        
        if (p > 0 && !flags[p - 1])
        {
            uf.union(p, p - 1);
        }
        
        if (p < length - 1 && !flags[p + 1])
        {
            uf.union(p, p + 1);       
        }
    }
    
    public int successor(int p)
    {
        if (flags[p])
        {
            return p;
        } 
        else 
        {
            int retval = uf.find(p) + 1;
            
            if (retval >= length)
            {
                return -1;
            } 
            else
            {
                return retval;
            }
        }
    }
    
    public static void main(String[] args)
    {
        SuccessorWithDelete swd = new SuccessorWithDelete(10);
        
        swd.remove(2);
        System.out.println(swd.successor(2) == 3);

        swd.remove(3);
        System.out.println(swd.successor(2) == 4);
        System.out.println(swd.successor(3) == 4);
                           
        swd.remove(4);
        System.out.println(swd.successor(2) == 5);
        System.out.println(swd.successor(3) == 5);
        System.out.println(swd.successor(4) == 5);
                           
        swd.remove(5);
        System.out.println(swd.successor(2) == 6);
        System.out.println(swd.successor(3) == 6);
        System.out.println(swd.successor(4) == 6);
        System.out.println(swd.successor(5) == 6);
                
        System.out.println(swd.successor(9) == 9);
                           
    }
}