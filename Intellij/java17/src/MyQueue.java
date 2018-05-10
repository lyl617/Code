public class MyQueue implements QueueInterface {
	/* 
	* TODO 2: Implement "MyQueue"
	*/
    private MyLinkedList LinkQueue;
    private int size=0;
    /**
     *initialize the class
     */
    public MyQueue()
    {
       LinkQueue=new MyLinkedList();
    }

    /**
     *
     * @return if the queue is empty
     */
    @Override
    public boolean isEmpty()
    {
        return LinkQueue.size==0;
    }

    /**
     *
     * @param item which is inserted into queue
     */
    @Override
    public void enqueue(Object item)
    {
        LinkQueue.add( this.size, item);
        this.size++;
    }

    /**
     * pop the head of queue
     * @return item of head
     */
    @Override
    public Object dequeue()
    {
        if (LinkQueue.isEmpty())
        {
           throw new QueueException("The queue is empty;");
        }
        Object temp_item = LinkQueue.get(0);
        LinkQueue.remove(0);
        this.size--;
        return temp_item;

    }

    /**
     * pop all items in queue
     */
    @Override
    public void dequeueAll()
    {
        if (LinkQueue.isEmpty())
        {
            throw new QueueException("The queue is empty;");
        }
        LinkQueue.removeAll();
        this.size = 0;
    }

    /**
     *
     * @return the head of queue
     */
    @Override
    public Object peek()
    {
        if (LinkQueue.isEmpty())
        {
            throw new QueueException("The queue is empty;");
        }
        return LinkQueue.head.item;
    }

    /**
     *
     * @return String of items in queue
     */
    public String toString()
    {
         return LinkQueue.toString();
    }
    /*
	* TODO 2: Implement "MyQueue"
	*/

} 