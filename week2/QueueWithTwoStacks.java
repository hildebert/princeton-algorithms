public class QueueWithTwoStacks
{
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    
    public QueueWithTwoStacks()
    {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }
    
    public void enqueue(Integer item)
    {
        stack1.push(item);
    }

    public Integer dequeue()
    {
        if (stack2.isEmpty())
        {
            while (!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    public boolean isEmpty()
    {
        return stack1.isEmpty();
    }
    
    public static void main(String[] args)
    {
        QueueWithTwoStacks q = new QueueWithTwoStacks();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        
        System.out.println(q.dequeue());
        
        q.enqueue(4);
        
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}