import java.util.Arrays;

public class MergingWithSmallerAuxiliaryArray
{
    private int N;
    private int length;
    private int[] A;

    public MergingWithSmallerAuxiliaryArray(int[] A)
    {
        length = A.length;
        N = length / 2;

        this.A = merge(A);
    }

    private int[] merge(int[] a)
    {
        int[] aux = new int[N];
        
        for (int i = 0; i < N; i++)
        {
            aux[i] = a[i];
        }

        int k = 0;
        int i = 0;
        int j = N;

        while (i < N || j < length)
        {
            if (i < N && j < length)
            {
                if (aux[i] <= a[j])
                {
                    a[k] = aux[i];
                    i++;
                }
                else
                {
                    a[k] = a[j];
                    j++;
                }
            }
            else if (i < N)
            {
                a[k] = aux[i];
                i++;
            }
            else
            {
                a[k] = a[j];
                j++;
            }

            k++;
        }
        
        return a;
    }

    public int[] getMerged()
    {
        return A;
    }
    
    public static void main(String[] args)
    {
        int[] a = {10, 11, 12, 13, 15, 1, 2, 3, 4, 9};

        MergingWithSmallerAuxiliaryArray m = new MergingWithSmallerAuxiliaryArray(a);

        int[] res = m.getMerged();
        
        System.out.println(Arrays.toString(res));
    }
}
