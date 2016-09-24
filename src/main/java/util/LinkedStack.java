package util;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 3:52 PM
 */
public class LinkedStack<T> implements StackInterface<T>
{
    private Node<T> topNode;
    private Node<T> bottomNode;
    private int size=0;

    public void push(T data)
    {
        Node<T> newNode=new Node<T>(data, topNode);
        if(isEmpty())
            bottomNode=newNode;
        topNode=newNode;
        size++;
    }
    public T peek()
    {
       if(isEmpty())
           return null;
       return topNode.data;
    }
    public T pop()
    {
        if(isEmpty())
            return null;
        T result=topNode.data;
        topNode=topNode.next;
        size--;
        if(topNode==null)
            bottomNode=null;
        return result;
    }
    public boolean isEmpty()
    {
        return size==0 && topNode==null;
    }
    public void clear()
    {
        size=0;
        topNode=null;
        bottomNode=null;
    }
    public int size()
    {
        return size;
    }

    public T removeBottom()
    {
        if(isEmpty())
            return null;
        T result=bottomNode.data;
        if(topNode==bottomNode)
        {
            topNode=null;
            bottomNode=null;
        }
        else
        {
            Node current=topNode;
            while(current.next!=bottomNode)
                current=current.next;
            bottomNode=current;
            bottomNode.next=null;
        }
        size--;
        return result;
    }
}
