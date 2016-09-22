public class CountingInversions
{
    private int[] A;
    private int inversions = 0;

    public CountingInversions(int[] A)
    {
        this.A = mergeSort(A);
    }

    private int[] merge(int[] left, int[] right, int[] aux)
    {
        int ll = left.length;
        int rl = right.length;
        int i = 0;
        int j = 0;

        while (i < ll || j < rl)
        {
            if (i < ll && j < rl)
            {
                if (left[i] <= right[j])
                {
                    aux[i + j] = left[i];
                    i++;
                }
                else
                {
                    inversions += ll - i;
                    aux[i + j] = right[j];
                    j++;
                }
            }
            else if (i < ll)
            {
                aux[i + j] = left[i];
                i++;
            }
            else if (j < rl)
            {
                aux[i + j] = right[j];
                j++;
            }
        }

        return aux;
    }

    private int[] mergeSort(int[] array)
    {
        int c = array.length;

        if (c < 2) return array;
                
        Double midD = Math.floor(c / 2);
        int mid = midD.intValue();
        
        int[] aux = new int[c];
        int[] left = new int[mid];
        int[] right = new int[c - mid];
        int i = 0;
        
        for (i = 0; i < mid; i++)
        {
            left[i] = array[i];
        }
        
        for (i = mid; i < c; i++)
        {
            right[i - mid] = array[i];
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right, aux);
    }
    
    public int getCount()
    {
        return inversions;
    }
    
    public int[] getSorted()
    {
        return A;
    }
        
    public static void main(String[] args)
    {
        int[] a = {4, 1, 3, 2, 9, 1};

        CountingInversions c = new CountingInversions(a);
        
        System.out.println(c.getCount() == 8);
    }
}
