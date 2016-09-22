public class StackWithMax
{
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;
    private int max;
    
    public StackWithMax()
    {
        stack = new Stack<Integer>();
        maxStack = new Stack<Integer>();
    }
    
    public void push(Integer item)
    {
        stack.push(item);
        
        if (item > max)
        {
            maxStack.push(item);
            max = item;
        }
    }

    public Integer pop()
    {
        int retval = stack.pop();
        
        if (retval == max)
        {
            maxStack.pop();
            
            if (!isEmpty())
            {
                max = maxStack.pop();
                maxStack.push(max);
            }
        }
        
        return retval;
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    
    public int getMax()
    {
        return max;
    }
    
    public static void main(String[] args)
    {
        StackWithMax s = new StackWithMax();
        
        s.push(1);
         
        
        s.push(2);
        System.out.println(s.getMax());
        
        s.push(3);
        System.out.println(s.getMax());
        
        s.pop();
        System.out.println(s.getMax());
        
        s.pop();
        System.out.println(s.getMax());
    }
}