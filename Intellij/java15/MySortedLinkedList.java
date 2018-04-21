public class MySortedLinkedList extends MyLinkedList {

	/* TODO 
	   define the method
	   public void add(Comparable c)
	   that, given a Comparable (an interface type for all Object subclasses that define a compareTo() method), adds it to the 
	   list in sorted order.
	*/
	public void add(Comparable c){
	        Node add_node=new Node(null,c,null);
            if(first==null){
                first=last=add_node;

            }else {
                Node temp=first;
                while(temp!=null&&c.compareTo(temp.obj)>0){
                    temp=temp.next;
                }
                if(temp==null){
                    last.next=add_node;
                    add_node.prev=last;
                    last=add_node;
                    return;
                }
                if(temp.prev==null){
                        temp.prev=add_node;
                        add_node.next=temp;
                        first=add_node;
                        return;
                }
                temp.prev.next=add_node;
                add_node.prev=temp.prev;
                temp.prev=add_node;
                add_node.next=temp;
            }
    }
	/* TODO
	   override the method
	   void add(int index, Object o)
	   so that it throws an UnsupportedOperationException with the message "Do not call add(int, Object) on MySortedLinkedList".
	   Directly adding objects at an index would mess up the sorted order.
	*/
	public void add(int index,Object o){
           throw  new UnsupportedOperationException("Do not call add(int, Object) on MySortedLinkedList");
    }
}