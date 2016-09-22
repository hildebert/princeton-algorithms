import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot
    private int prevRand;
    

    /**
     * Initializes an empty queue.
     */
    public RandomizedQueue() 
    {
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() 
    {
        return n == 0;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size()
    {
        return n;
    }

    // resize the underlying array
    private void resize(int capacity)
    {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        
        for (int i = 0; i < n; i++)
        {
            temp[i] = q[(first + i) % q.length];
        }
        
        q = temp;
        first = 0;
        last  = n;
    }

    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void enqueue(Item item)
    {
        if (item == null) throw new NullPointerException("Cannot add null to queue");

        // double size of array if necessary and recopy to front of array
        // double size of array if necessary
        if (n + 1 == q.length) resize(2 * q.length); 
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        
        int rk = randomKey();
        Item item = q[rk];
        
        q[rk] = q[--last];
        q[last] = null;
        n--;
                
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length/4) resize(q.length/2); 
        return item;
    }

    public Item sample() 
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        
        int rk = randomKey();
        Item retval = q[rk];
        
        return retval;
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()
    {
        return new ArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item>
    {
        private int i = 0;
        private Deque<Item> deque;
        
        public ArrayIterator()
        {
            deque = new Deque<Item>();
                
            for (int k = first; k < last; k++)
            {
                double rand = StdRandom.uniform(0, 9);

                if (rand >= 5)
                {
                    deque.addFirst(q[k]);
                }
                else
                {
                    deque.addLast(q[k]);
                }
            }
        }
        
        public boolean hasNext() 
        { 
            return i < n;                               
        }
        
        public void remove()      
        { 
            throw new UnsupportedOperationException();  
        }

        public Item next() 
        {
            double rand = StdRandom.uniform(0, 9);

            i++;
            
            if (rand >= 5)
            {
                return deque.removeFirst();
            }
            else
            {
                return deque.removeLast();
            }
        }
    }
    
    private int randomKey()
    {
        if (first == 0 && last == 0) return 0;
        return StdRandom.uniform(first, last);
    }

   /**
     * Unit tests the {@code ResizingArrayQueue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) 
    {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("AA");
        q.enqueue("BB");
        q.enqueue("BB");
        q.enqueue("BB");
        q.enqueue("BB");
        q.enqueue("BB");
        q.enqueue("CC");
        q.enqueue("CC");
        
        Iterator<String> it = q.iterator();
        
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());        
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        
        System.out.println("------------------");
                
        int k = 8;
        
        while (k > 0)
        {
            System.out.println(q.dequeue());
            k--;
        }
        
        return ;
    }
}