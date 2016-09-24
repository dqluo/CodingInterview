package PostedFacebookQuestions;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-16
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
public class ImplementQueueUsingStack {
    static Stack<Integer> stack1=new Stack<Integer>();
    static Stack<Integer> stack2=new Stack<Integer>();

    public static void enqueue(int number)
    {
        stack1.push(number);
    }

    public static int dequeue()
    {
        if(isEmpty())
            return -1;
        if(stack2.isEmpty())
        {
            while(!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static boolean isEmpty(){
        return stack1.isEmpty()&&stack2.isEmpty();
    }

    public static void main(String[] args)
    {
        enqueue(1);
        enqueue(2);
        enqueue(3);
        System.out.print(dequeue() + " ");
        enqueue(3);
        enqueue(4);
        System.out.print(dequeue() + " ");
        System.out.print(dequeue()+" ");
        System.out.print(dequeue() + " ");
        System.out.print(dequeue()+" ");
        enqueue(3);
        enqueue(4);
        enqueue(5);
        enqueue(6);
        System.out.print(dequeue()+" ");
    }
}
