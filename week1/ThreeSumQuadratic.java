import java.util.Arrays;
import java.util.ArrayList;

public class ThreeSumQuadratic
{
    private int[] a;
    
    public ThreeSumQuadratic(int[] a)
    {
        Arrays.sort(a);
        this.a = a;
    }
    
    public ArrayList<Integer[]> getResult()
    {
        ArrayList<Integer[]> result = new ArrayList<Integer[]>();
        
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a.length; j++)
            {
                if (j == i) continue;

                int sum = a[i] + a[j];
                int key = binarySearch(a, -sum);
                
                if (key > -1 && key != i && key != j)
                {
                    result.add(new Integer[]{a[i], a[j], -sum});
                }
            }
        }
        
        return result;
    }
        
    public static int binarySearch(int[] a, int key)
    {
        int lo = 0, hi = a.length-1;
        
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        
        return -1;
    }

    public static void main(String[] args)
    {
        int[] input = { 50, -40, -10, -20, -30, 60 };
        
        ThreeSumQuadratic tsq = new ThreeSumQuadratic(input);
        ArrayList<Integer[]> result = tsq.getResult();
        
        for (int i = 0; i < result.size(); i++) 
        {
            System.out.println(Arrays.toString(result.get(i)));
        }
    }
}