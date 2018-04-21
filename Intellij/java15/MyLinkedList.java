import java.util.ArrayList;
import java.util.List;

public class MyLinkedList implements ListInterface {

	/* TODO: Write a LinkedList implementation for all the methods specified in ListInterface */
    protected class Node{
        Node prev;
        Node next;
        Object obj;
        public Node(Node prev,Object obj,Node next){
            this.prev=prev;
            this.obj=obj;
            this.next=next;
        }
    }
    protected Node first;
    protected Node last;
    protected int size;
    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(int index, Object value) throws ListIndexOutOfBoundsException {
           if(index<0||index>size){
               throw new IllegalArgumentException();
           }
           if(index==size){
               linkLast(value);
           }else {
               linkBefore(value,GetNode(index));
           }
           this.size++;
    }
    private Node GetNode(int index){
        Node temp=first;
        if(temp!=null){
        for(int i=0;i<index;i++){
            temp=temp.next;
        }
        }
        return temp;
    }
    private void linkLast(Object obj){
        Node temp=new Node(last,obj,null);
        if(first==null){
            first=last=temp;
        }else {
            last.next=temp;
            last=temp;
        }
    }
    private  void linkBefore(Object obj,Node node){
        Node temp=new Node(null,obj,node);
        Node prev_node=node.prev;
        if(prev_node!=null){
            prev_node.next=temp;
            temp.prev=prev_node;
            node.prev=temp;
        }else {
            first=temp;
        }
    }
    @Override
    public void remove(int index) throws ListIndexOutOfBoundsException {
         Node remove_node=GetNode(index);
         Node prev_node=remove_node.prev;
         Node next_node=remove_node.next;
         if(prev_node==null||next_node==null){
             if(prev_node==null && next_node==null){
                 first=last=null;
                 return;
             }
         if(prev_node==null){//remove the first node
             next_node.prev=null;
             first=next_node;
             this.size--;
             return;
         }
         if(next_node==null){//remove the last node
             prev_node.next=null;
             last=prev_node;
             this.size--;
             return;
         }
         }
         prev_node.next=next_node;
         next_node.prev=prev_node;
         size--;
    }

    @Override
    public void removeAll() {
        Node temp=first;
           while(temp!=null){
               Node next=temp.next;
               temp.prev=null;
               temp.obj=null;
               temp.next=null;
               temp=next;
           }
           this.size=0;
           first=last=null;
    }

    @Override
    public Object get(int index) throws ListIndexOutOfBoundsException {
        Node temp=GetNode(index);
        return temp==null?null:temp.obj;
    }

    @Override
    public int find(Object o) {
        int index=0;
        Node temp=first;
        if(first!=null){
        while(temp!=null){
            if(temp.obj.equals(o)){
                return index;
            }
            index++;
            temp=temp.next;
        }}
        return  -1;
    }

    @Override
    public String toString() {
        List<String> result=new ArrayList<>();
        Node temp=first;
        if(first!=null){
        while (temp!=null){
            result.add(String.valueOf(temp.obj));
            temp=temp.next;
        }
        }else {
            return null;
        }
        return String.join(",",result);
    }
}
