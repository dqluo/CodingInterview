package stackAndQueue;

import util.LinkedStack;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 9:13 PM
 */
public class StackWithMin
{
    /*
     * Problem: create a stack with O(1) min() method, which gets the
     * min value of the stack.
     */
    public static void main(String[] args)
    {
        StackWithMinNode s=new StackWithMinNode();
        System.out.println("Testing StackWithMinNode:");
        s.push(6);
        System.out.println("Top Node Value: " + s.peek().data + " Min: " + s.min());
        s.push(5);
        System.out.println("Top Node Value: " + s.peek().data + " Min: " + s.min());
        s.push(3);
        System.out.println("Top Node Value: " + s.peek().data + " Min: " + s.min());
        s.push(7);
        System.out.println("Top Node Value: " + s.peek().data + " Min: " + s.min());
        s.push(4);
        System.out.println("Top Node Value: " + s.peek().data + " Min: " + s.min());
        System.out.println("Begin popping: ");
        s.pop();
        System.out.println("Top Node Value: "+s.peek().data+" Min: "+s.min());
        s.pop();
        System.out.println("Top Node Value: "+s.peek().data+" Min: "+s.min());
        s.pop();
        System.out.println("Top Node Value: "+s.peek().data+" Min: "+s.min());


        StackWithMinStack s2=new StackWithMinStack();
        System.out.println("\nTesting StackWithMinStack:");
        s2.push(6);
        System.out.println("Top Node Value: " + s2.peek() + " Min: " + s2.min());
        s2.push(5);
        System.out.println("Top Node Value: " + s2.peek() + " Min: " + s2.min());
        s2.push(3);
        System.out.println("Top Node Value: " + s2.peek() + " Min: " + s2.min());
        s2.push(7);
        System.out.println("Top Node Value: " + s2.peek() + " Min: " + s2.min());
        s2.push(4);
        System.out.println("Top Node Value: " + s2.peek() + " Min: " + s2.min());

        System.out.println("Begin popping: ");
        s2.pop();
        System.out.println("Top Node Value: "+s2.peek()+" Min: "+s2.min());
        s2.pop();
        System.out.println("Top Node Value: "+s2.peek()+" Min: "+s2.min());
        s2.pop();
        System.out.println("Top Node Value: "+s2.peek()+" Min: "+s2.min());

    }
}

class StackWithMinNode extends LinkedStack<StackWithMinNode.NodeWithMin>
{
    /*
     * Wrapping data with the nextMin value in NodeWithMin;
     * Drawback: every node carries a nextMin, space consumable
     */
    public void push(int data)
    {
        int newMin=Math.min(data, min());
        super.push(new NodeWithMin(data, newMin));
    }
    public int min()
    {
        if(isEmpty())
            return Integer.MAX_VALUE;
        return super.peek().nextMin;
    }

    class NodeWithMin
    {
       int data;
       int nextMin;
       NodeWithMin next;
       NodeWithMin(int data, int nextMin)
       {
           this.data=data;
           this.nextMin=nextMin;
       }
   }
}
class StackWithMinStack extends LinkedStack<Integer>
{
    /*
     * Using min Stack to keep track of nextMin only when necessary
     */
    LinkedStack<Integer> minStack=new LinkedStack<Integer>();
    public void push(int data)
    {
        if(data<min())
            minStack.push(data);
        super.push(data);
    }
    //need to check with minStack to see if the popped element in minStack
    public Integer pop()
    {
        Integer result=super.pop();
        if(result!=null)
        {
            if(minStack.peek().equals(result))
                minStack.pop();
        }
        return result;
    }
    public int min()
    {
        if(minStack.isEmpty())
            return Integer.MAX_VALUE;
        return minStack.peek();
    }
}