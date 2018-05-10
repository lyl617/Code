public class MyStack implements StackInterface {
	/* 
	* TODO 1: Implement "MyStack"
	*/
    private MyLinkedList LinkStack;
    /**
     *initialize the class
     */
    public MyStack()
    {
        LinkStack=new MyLinkedList();
    }
    /**
     *
     * @return if the stack is empty
     */
    @Override
    public boolean isEmpty()
    {
        return LinkStack.size == 0;
    }

    /**
     *
     * @param o which is pushed into stack
     */
    @Override
    public void push(Object o)
    {
        LinkStack.add(0, o);
    }
    /**
     * pop the top of stack
     * @return item  of top
     */
    @Override
    public Object pop()
    {
        if (LinkStack.isEmpty())
        {
            throw new StackException("Stack is empty");
        }
        Object temp_item = LinkStack.get(0);
        LinkStack.remove(0);
        return temp_item;
    }

    /**
     *
     * @return the top of stack
     */
    @Override
    public Object peek()
    {
        if (LinkStack.isEmpty())
        {
            throw new StackException("Stack is empty");
        }
        return LinkStack.head.item;
    }
    /**
     * pop all items in stack
     */
    @Override
    public void popAll()
    {
        if (LinkStack.isEmpty())
        {
            throw new StackException("Stack is empty");
        }
        LinkStack.removeAll();
    }
    /**
     *
     * @return String of items in stack
     */
    public String toString()
    {
        return LinkStack.toString();
    }
    /*
	* TODO 1: Implement "MyStack"
	*/
}