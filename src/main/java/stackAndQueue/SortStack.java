package stackAndQueue;

import java.util.Stack;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 7:46 PM
 */
public class SortStack
{
    /*
     * Problem: use one stack to sort for another
     */
    public static void main(String[] args)
    {
        Stack<Integer> s=new Stack<Integer>();
        s.push(28);
        s.push(2);
        s.push(16);
        s.push(3);
        s.push(72);
        s.push(19);
        s.push(6);
        System.out.print("Before sorting: ");
        for(int e : s)
            System.out.print(e+" ");
        System.out.println();
        sort(s);
        //the iteration starts from stack bottom
        System.out.print("After sorting: ");
        for(int e : s)
            System.out.print(e+" ");
        System.out.println();
    }

    public static void sort(Stack<Integer> s)
    {
        Stack<Integer> buffer=new Stack<Integer>();
        while(!s.isEmpty())
        {
            int next=s.pop();
            while(!buffer.isEmpty() && next<buffer.peek())
            {
                s.push(buffer.pop());
            }
            buffer.push(next);
        }
        while(!buffer.isEmpty())
            s.push(buffer.pop());
    }
}
