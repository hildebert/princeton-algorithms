public class BitonicSearch
{
    private int[] a;
    private int key;
    
    public BitonicSearch(int[] a)
    {
        this.a = a;
        this.key = bitonicKey(a);
    }
        
    public static int bitonicKey(int[] a)
    {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;

            if (a[mid - 1] < a[mid] && a[mid] > a[mid + 1])
            {
                return mid + 1;
            }
            else if (a[mid] < a[mid + 1])
            {
                lo = mid;
            }
            else if (a[mid] > a[mid + 1])
            {
                hi = mid + 1;
            }
        }

        return -1;
    }
    
    public static int binarySearch(int[] a, int key, int lo, int hi, boolean reverse)
    {
        if (hi == 0) hi = a.length - 1;

        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;

            if (key < a[mid])
            {
                if (!reverse) hi = mid - 1;
                else lo = mid + 1;
            }
            else if (key > a[mid])
            {
                if (!reverse) lo = mid + 1;
                else hi = mid - 1;
            }
            else
            {
                return mid;
            }
        }

        return -1;
    }
    
    public int find(int needle)
    {
        int res1 = binarySearch(a, needle, 0, key - 1, false);
        if (res1 > -1) return res1;

        int res2 = binarySearch(a, needle, key, a.length - 1, true);
        if (res2 > -1) return res2;

        return -1;
    }

    public static void main(String[] args)
    {
        int[] input = {1, 3, 4, 6, 9, 14, 15, 13, 12, 7, 2, -4, -9};
        
        BitonicSearch bs = new BitonicSearch(input);

        System.out.println(bs.find(1) == 0);
        System.out.println(bs.find(3) == 1);
        System.out.println(bs.find(-9) == 12);
    }
}