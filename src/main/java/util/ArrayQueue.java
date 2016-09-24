package util;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 5:13 PM
 */
public class ArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private int size=0;
    private static final int DEFAULT_SIZE=3;

    public ArrayQueue()
    {
        queue=(T[]) new Object[DEFAULT_SIZE+1];
        frontIndex=0;
        backIndex=DEFAULT_SIZE;
    }
    public void enqueue(T data)
    {
        if(isFull())
            doubleArray();
        backIndex=(backIndex+1)%queue.length;
        queue[backIndex]=data;
        size++;
    }
    public T getFront()
    {
        if(isEmpty())
            return null;
        return queue[frontIndex];
    }
    public T dequeue()
    {
        if(isEmpty())
            return null;
        T result=queue[frontIndex];
        queue[frontIndex]=null;
        frontIndex=(frontIndex+1)%queue.length;
        size--;
        return result;
    }
    public int size()
    {
        return size;
    }
    public void clear()
    {
        for(int i=0; i<size; i++)
            queue[i]=null;
        frontIndex=0;
        backIndex=queue.length-1;
        size=0;
    }
    public boolean isEmpty()
    {
        return frontIndex==(backIndex+1)%queue.length;
    }
    private boolean isFull()
    {
        return frontIndex==(backIndex+2)%queue.length;
    }
    private void doubleArray()
    {
        T[] array=(T[])new Object[2*size+1];
        for(int i=0; i<size; i++)
        {
            array[i]=queue[frontIndex];
            frontIndex=(frontIndex+1)%queue.length;
        }
        frontIndex=0;
        backIndex=size-1;
        queue=array;
    }

}
