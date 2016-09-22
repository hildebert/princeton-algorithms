import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset
{
    public static void main(String[] args)
    {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
                
        int k = Integer.parseInt(args[0]);
        
        while (!StdIn.isEmpty()) 
        {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        
        Iterator<String> it = q.iterator();
        
        while (k > 0)
        {
            StdOut.println(it.next());
            k--;
        }
        
        return;
    }
}