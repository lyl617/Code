public class BinarySearchTree implements BSTInterface {

	class BSTNode{//The constructor will initialize the item to a value and the left and right nodes to null
		private String item;
		private BSTNode left;
		private BSTNode right;
		public BSTNode(String item){
			this.item=item;
			this.left=null;
			this.right=null;
		}
	}
	private BSTNode root;//node root

	public BinarySearchTree(){
		this.root=null;
	}//constructor method

	@Override
	public boolean isEmpty() {//Returns true if BST is empty, false otherwise
		return this.root==null;
	}

	@Override
	public void makeEmpty() {//Clears BST so that it is empty
		this.root=null;
	}

	@Override
	public MyQueue inOrder() {//Returns a queue with the elements in-order
	    MyQueue myQueue=new MyQueue();
	    inOrderRecursive(this.root,myQueue);
		return myQueue;
	}

	@Override
	public MyQueue preOrder() {//Returns a queue with the elements in pre-order
        MyQueue myQueue=new MyQueue();
        preOrderRecursive(this.root,myQueue);
        return myQueue;
	}

	@Override
	public MyQueue postOrder() {//Returns a queue with the elements in post-order
        MyQueue myQueue=new MyQueue();
        postOrderRecursive(this.root,myQueue);
        return myQueue;
	}

	@Override
	public boolean contains(String s) {
		return recursiveSearch(this.root,s);
	}//Returns true if the BST contains the string, otherwise returns false

	@Override
	public void put(String s) {
	    this.root=recursiveInsert(this.root,s);
	}//If the string is already in the BST, then put will do nothing. If the string is not in the BST, then put will add the string to the BST.

	@Override
	public void delete(String s) {
         this.root=recursiveRemove(this.root,s);
	}//Removes the specified string from the BST, if the string cannot be found, then delete does nothing

	// TODO: Fill this in and call it from contains()
	protected boolean recursiveSearch(BSTNode node, String s) {
	    if (node==null) return false;
	    boolean contains;
        if (s.compareTo(node.item)>0)
        {
            contains=recursiveSearch(node.right,s);
        }else if (s.compareTo(node.item)<0)
        {
            contains=recursiveSearch(node.left,s);
        }else
        {
            contains=true;
        }
		return contains;
	}

	// TODO: Fill this in and call it from put()
	protected BSTNode recursiveInsert(BSTNode node, String s){
	    if (node==null)
        {
            node=new BSTNode(s);
            return node;
        }else
        {
            if (s.compareTo(node.item)>0)
            {
                node.right=recursiveInsert(node.right,s);
            }else if (s.compareTo(node.item)<0)
            {
                node.left=recursiveInsert(node.left,s);
            }
        }
	    return node;
	}

	// TODO: Fill this in and call it from delete()
	protected BSTNode recursiveRemove(BSTNode node, String s) {
          if (node!=null)
          {
              if (s.compareTo(node.item)>0)
              {
                  node.right=recursiveRemove(node.right,s);
              }else if (s.compareTo(node.item)<0)
              {
                  node.left=recursiveRemove(node.left,s);
              }else
              {
                  node=deleteNode(node);
              }
          }
          return node;
	}
	
	// TODO: Fill this in and call it from recursiveRemove()
	protected BSTNode deleteNode(BSTNode node) {
	      if (node.left == null && node.right == null)
          {
              node = null;
          }else if (node.right == null)
          {
              node = node.left;
          }else if (node.left == null)
          {
              node = node.right;
          }else
          {
              String Smallest_node = getSmallest(node.right);//get the in-order successor. This is the smallest value that is larger than the current node item.
              node.item = Smallest_node;
              node.right = recursiveRemove(node.right,Smallest_node);//remove the smallest node in the right tree of node
          }
          return node;
	}

	// TODO: Fill this in and call it from deleteNode()
	protected String getSmallest(BSTNode node) {
	      String smallest = node.item;
	      while (node.left != null)//search the node until the left node is null
          {
              smallest = node.left.item;
              node = node.left;
          }
          return  smallest;
	}


	// TODO: Fill this in and call it from inOrder()
	protected void inOrderRecursive(BSTNode node, MyQueue queue) {
	     if (node==null) return;
         inOrderRecursive(node.left,queue);
         queue.enqueue(node.item);
         inOrderRecursive(node.right,queue);

	}


	// TODO: Fill this in and call it from preOrder()
	protected void preOrderRecursive(BSTNode node, MyQueue queue) {
         if (node==null) return;
         queue.enqueue(node.item);
         preOrderRecursive(node.left,queue);
         preOrderRecursive(node.right,queue);
	}

	// TODO: Fill this in and call it from postOrder()
	protected void postOrderRecursive(BSTNode node, MyQueue queue) {
	     if (node==null) return;
	     postOrderRecursive(node.left,queue);
	     postOrderRecursive(node.right,queue);
	     queue.enqueue(node.item);
	}

	// Prints out the tree structure, using indenting to show the different levels of the tree
	public void printTreeStructure() { 
		printTree(0, root);
	}

	// Recursive helper for printTreeStructure()
	protected void printTree(int depth, BSTNode node) {
		indent(depth);
		if (node != null) {
	    	System.out.println(node.item);
	    	printTree(depth + 1, node.left);
	    	printTree(depth + 1, node.right);
	 	} 
	 	else {
	  		System.out.println("null");
	  	}
	}

	// Indents with with spaces 
	protected void indent(int depth) {
		for(int i = 0; i < depth; i++)
			System.out.print("  "); // Indents two spaces for each unit of depth
	}


	// Extra Credit 

    @Override
    public void balanceBST() {
         MyQueue BST_values=inOrder();//get a queue of the BST values in sorted order.
         MyQueue temp_queue=new MyQueue();
         int size=0;
         while (!BST_values.isEmpty())//create a temporary queue and  dequeue()  elements into the temporary queue,
         {
             temp_queue.enqueue(BST_values.dequeue());
             size++;

         }
         String[] array=new String[size];
         for (int i = 0;i < size;i++)//create a  String[]  of this size and copy values from the temporary queue into the array.
         {
             array[i]=(String)temp_queue.dequeue();
         }
         makeEmpty();//Empty the BST.
         this.root=balanceRecursive(array,0,size-1);
    }

    // TODO: If doing the extra credit, fill this in and call it from balanceBST()
	 protected BSTNode balanceRecursive(String[] array, int first, int last) {// binarySearch and recursive
	      if (first>last) return null;
	      int mid = (first+last)/2;
	      BSTNode temp_root=new BSTNode(array[mid]);
	      temp_root.left = balanceRecursive(array,first,mid-1);
	      temp_root.right = balanceRecursive(array,mid+1,last);
          return temp_root;
	}
}