package crackingAndMadeEasy.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 6:58 PM
 */
public class StackAndQueueReversal
{
    public static void main(String[] args)
    {
        //Test ReverseQueue
        System.out.println("Queue reversal:");
        Queue q=new LinkedList();
        for(int i=0; i<10; i++)
            q.offer(i);
        System.out.print("Before reverse: ");
        for(Object data : q)
            System.out.print(data+" ");
        ReverseQueue.reverse(q);
        System.out.print("\nAfter reverse: ");
        for(Object data : q)
            System.out.print(data+" ");
        System.out.print("\nAfter reverse again: ");
        ReverseQueue.reverse2(q);
        for(Object data : q)
            System.out.print(data+" ");
        System.out.println();


        //Test ReverseStack
        System.out.println("Stack reversal:");
        Stack s=new Stack();
        for(int i=0; i<10; i++)
            s.push(i);
        System.out.print("Before reverse: ");
        for(Object data : s)
            System.out.print(data+" ");
        ReverseStack.reverse(s);
        System.out.print("\nAfter reverse: ");
        for(Object data : s)
            System.out.print(data+" ");
        System.out.print("\nAfter reverse again: ");
        ReverseStack.reverse2(s);
        for(Object data : s)
            System.out.print(data+" ");
        System.out.println();

    }
}

class ReverseQueue
{
    /*
     * Problem1: reverse a queue
     */
    public static void reverse(Queue q)
    {
        Stack s=new Stack();
        while(!q.isEmpty())
            s.push(q.poll());
        while(!s.isEmpty())
            q.offer(s.pop());
    }
    public static void reverse2(Queue q)
    {
        if(q.isEmpty())
            return;
        Object data=q.poll();
        reverse2(q);
        q.offer(data);
    }
}

class ReverseStack
{
    /*
     * Reverse2 a stack
     */
    public static void reverse(Stack s)
    {
        Queue q=new LinkedList();
        while(!s.isEmpty())
        {
            q.offer(s.pop());
        }
        while(!q.isEmpty())
        {
            s.push(q.poll());
        }
    }
    public static void reverse2(Stack s)
    {
        if(s.isEmpty())
            return;
        Object element=s.pop();
        reverse2(s);
        insertAtBottom(s, element);
    }
    private static void insertAtBottom(Stack s, Object data)
    {
        if(s.isEmpty())
        {
            s.push(data);
            return;
        }
        Object element=s.pop();
        insertAtBottom(s, data);
        s.push(element);
    }
}
