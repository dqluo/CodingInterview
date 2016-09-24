package util;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 4:54 PM
 */
public class LinkedQueue<T> implements QueueInterface<T>
{
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    public void enqueue(T data)
    {
        Node<T> newNode=new Node<T>(data);
        if(isEmpty())
            firstNode=newNode;
        else
            lastNode.next=newNode;
        lastNode=newNode;
        size++;
    }
    public T getFront()
    {
        if(isEmpty())
            return null;
        return firstNode.data;
    }
    public T dequeue()
    {
        if(isEmpty())
            return null;
        T result=firstNode.data;
        firstNode=firstNode.next;
        size--;
        if(firstNode==null)
            lastNode=null;
        return result;
    }
    public boolean isEmpty()
    {
        return size==0 && firstNode==null && lastNode==null;
    }
    public void clear()
    {
        firstNode=null;
        lastNode=null;
        size=0;
    }
    public int size()
    {
        return size;
    }
}
