package util;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 4:34 PM
 */
public class ArrayStack<T> implements StackInterface<T>
{
    private T[] stack;
    private int topIndex;
    private int size=0;
    private static final int DEFAULT_SIZE=3;

    public ArrayStack()
    {
        stack=(T[])new Object[DEFAULT_SIZE];
        topIndex=-1;
    }
    public void push(T data)
    {
        topIndex++;
        if(isFull())
            doubleArray();
        stack[topIndex]=data;
        size++;
    }
    public T peek()
    {
        if(isEmpty())
            return null;
        return stack[topIndex];
    }
    public T pop()
    {
        if(isEmpty())
            return null;
        T result=stack[topIndex];
        stack[topIndex]=null;
        topIndex--;
        size--;
        return result;
    }
    public void clear()
    {
        for(int i=0; i<size; i++)
            stack[i]=null;
        size=0;
        topIndex=-1;
    }
    public boolean isEmpty()
    {
        return size==0 && topIndex==-1;
    }
    public int size()
    {
        return size;
    }

    private boolean isFull()
    {
        return size==stack.length;
    }
    private void doubleArray()
    {
        T[] array=(T[])new Object[2*size];
        System.arraycopy(stack, 0, array, 0, size);
        stack=array;
    }
}
