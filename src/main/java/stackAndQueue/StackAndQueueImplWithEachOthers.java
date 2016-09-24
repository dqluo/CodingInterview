package stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 6:01 PM
 */
public class StackAndQueueImplWithEachOthers
{
    public static void main(String[] args)
    {
        //Test QueueByStack
        QueueByStack q=new QueueByStack();
        for(int i=0; i<10; i++)
            q.enqueue(i);
        System.out.println("Queue size is "+q.size());
        System.out.print("Queue elements are: ");
        while(!q.isEmpty())
            System.out.print(q.dequeue()+" ");
        System.out.println();

        //Test StackByQueue
        StackByQueue s=new StackByQueue();
        for(int i=0; i<10; i++)
            s.push(i);
        System.out.println("Stack size is "+s.size());
        System.out.print("Stack elements are: ");
        while(!s.isEmpty())
            System.out.print(s.pop()+" ");
        System.out.println();
    }
}

class QueueByStack
{
    /*
     * Problem1: create a queue using two stacks
     *
     * This requires only one shift operation when the the popStack, which
     * is used to dequeue elements, is empty. In other cases, the two stacks
     * can work fine separately.
     */
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public QueueByStack()
    {
        pushStack=new Stack<Integer>();
        popStack=new Stack<Integer>();
    }

    public void enqueue(int data)
    {
        pushStack.push(data);
    }
    public int dequeue()
    {
        if(popStack.isEmpty())
            shiftStack();
        return popStack.pop();
    }
    public int getFront()
    {
        if(popStack.isEmpty())
            shiftStack();
        return popStack.peek();
    }
    public boolean isEmpty()
    {
        return popStack.isEmpty() && pushStack.isEmpty();
    }
    public int size()
    {
        return popStack.size()+pushStack.size();
    }
    private void shiftStack()
    {
        while(!pushStack.isEmpty())
        {
            popStack.push(pushStack.pop());
        }
    }
}

class StackByQueue
{
    /*
     * Problem2: Create a stack with two queues
     *
     * This requires some trick when pop() is called. We need to dequeue the
     * workingQ until one element is left, and that's what we want to pop actually.
     * Therefore, the complexity of pop is O(n)
     */
    Queue<Integer> workingQ;
    Queue<Integer> backUpQ;

    public StackByQueue()
    {
        workingQ=new LinkedList<Integer>();
        backUpQ=new LinkedList<Integer>();
    }

    public void push(int data)
    {
        workingQ.add(data);
    }
    public int pop()
    {
        while(workingQ.size()>1)
        {
            backUpQ.add(workingQ.remove());
        }
        Queue<Integer> temp=workingQ;
        workingQ=backUpQ;
        backUpQ=temp;
        return backUpQ.remove();
    }
    public int peek()
    {
        while(workingQ.size()>1)
        {
            backUpQ.add(workingQ.remove());
        }
        Queue<Integer> temp=workingQ;
        workingQ=backUpQ;
        backUpQ=temp;
        return backUpQ.element();
    }
    public boolean isEmpty()
    {
        return workingQ.isEmpty();
    }
    public int size()
    {
        return workingQ.size();
    }
}
