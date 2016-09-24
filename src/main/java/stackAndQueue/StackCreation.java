package stackAndQueue;

import util.LinkedStack;

import java.util.ArrayList;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 10:39 PM
 */
public class StackCreation
{
    public static void main(String[] args)
    {
        //Test SetOfStackes
        SetOfStacks st=new SetOfStacks();
        for(int i=0; i<9; i++)
            st.push(i);
        System.out.println(st.popAt(1));
        System.out.println(st.popAt(1));
        System.out.println(st.popAt(1));
        System.out.println(st.popAt(1));
        System.out.println("Size: "+st.size());

        //Test ThreeStacksArray
        ThreeStacksArray tsa=new ThreeStacksArray();
        try{
            tsa.push(2, 4);
            System.out.println("Peek 2: " + tsa.peek(2));
            tsa.push(0, 3);
            tsa.push(0, 7);
            tsa.push(0, 5);
            System.out.println("Peek 0: " + tsa.peek(0));
            tsa.pop(0);
            System.out.println("Peek 0: " + tsa.peek(0));
            tsa.pop(0);
            System.out.println("Peek 0: " + tsa.peek(0));
        } catch(Exception e){
               System.out.println(e.getMessage());
        }

    }
}

class SetOfStacks
{
    /*
     * Problem1: Create a set of stacks with each stack having limited predefined
     * numbers of elements. The class should support popAt(int index) to pop at
     * any stack in the whole set.
     */
    ArrayList<LinkedStack> stacks;
    private int capacityForStack;
    private static final int DEFAULT_CAPACITY=3;
    public SetOfStacks()
    {
        stacks=new ArrayList<LinkedStack>();
        capacityForStack=DEFAULT_CAPACITY;
    }
    //push to stack, if lastStack is full, create and push to new one
    public void push(int v)
    {
        LinkedStack lastStack=getLastStack();
        //normal case, just push to lastStack
        if (!isEmpty() && lastStack.size()<capacityForStack)
            lastStack.push(v);
        else
        {
            LinkedStack newStack=new LinkedStack();
            newStack.push(v);
            stacks.add(newStack);
        }
    }
    public int pop()
    {
        //avoid NullPointerException
        if(isEmpty())
            return Integer.MAX_VALUE;
        LinkedStack lastStack=getLastStack();
        int result=(Integer)lastStack.pop();
        //we don't allow empty stack in class
        if(lastStack.isEmpty())
            stacks.remove(lastStack);
        return result;
    }
    public int popAt(int index)
    {
        //avoid NullPointerException
        if(isEmpty())
            return Integer.MAX_VALUE;
        int result=(Integer)stacks.get(index).pop();
        /*
         * from the stack chosen to pop to last stack,
         * we remove bottom of next, and push to top current
         */
        for(int i=index; i<stacks.size()-1; i++)
        {
            int removedItem=(Integer)stacks.get(i+1).removeBottom();
            stacks.get(i).push(removedItem);
        }
        //ensure no empty stack exists
        if(getLastStack().isEmpty())
            stacks.remove(stacks.size()-1);
        return result;
    }
    public boolean isEmpty()
    {
        return stacks.isEmpty();
    }
    public int size()
    {
        return stacks.size();
    }
    private LinkedStack getLastStack()
    {
        if(isEmpty())
            return null;
        int i=stacks.size()-1;
        return stacks.get(i);
    }
}

class ThreeStacksArray
{
    static int stackSize = 10;
    static int [] buffer = new int [stackSize * 3];

    // 3 stack pointers to keep track of the index of the top element
    static int [] stackPointer = {-1, -1, -1};

    static void push(int stackNum, int value) throws Exception {
		/* Check that we have space for the next element */
        if (stackPointer[stackNum] + 1 >= stackSize) {
            throw new Exception("Out of space.");
        }
		/* Increment stack pointer and then update top value*/
        stackPointer[stackNum]++;
        buffer[absTopOfStack(stackNum)] = value;
    }

    static int pop(int stackNum) throws Exception {
        if (stackPointer[stackNum] == -1) {
            throw new Exception("Trying to pop an empty stack.");
        }
        int value = buffer[absTopOfStack(stackNum)]; // Get top
        buffer[absTopOfStack(stackNum)] = 0; // Clear index
        stackPointer[stackNum]--; // Decrement pointer
        return value;
    }

    static int peek(int stackNum) {
        return buffer[absTopOfStack(stackNum)];
    }

    static boolean isEmpty(int stackNum) {
        return stackPointer[stackNum] == -1;
    }

    /* returns index of the top of the stack "stackNum", in absolute terms */
    static int absTopOfStack(int stackNum) {
        return stackNum * stackSize + stackPointer[stackNum];
    }
}






