package crackingAndMadeEasy.dynamicProgramming.cracking.dp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: xinyuwan, Date: 12/5/13, Time: 10:44 PM
 */
public class BuildTallestBoxStack
{
    /*
     * Problem: you have a stack of n boxes, with width wi, heights hi, and length li.
     * The boxes cannot be rotated and can only be stacked on top of the one another if
     * each box in the stack is strictly larger than the box above it. Implement a method to
     * build the tallest stack possible, where the height of the stack is the sum of all
     */
    public static void main(String[] args)
    {
        Box[] boxes={new Box(1, 2, 3), new Box(4, 5, 6), new Box(3, 7, 4)};
        ArrayList<Box> stack1=createStack(boxes, null);
        ArrayList<Box> stack2=createStackDP(boxes, null);
        System.out.println(stack1);
        System.out.println(stack2);

    }
    /*
     * if the stack is b1, b2, ... bn. sThe biggest stack we can build equals the
     * max of(biggest stack with bottom b1, biggest stack with bottom b2, ... bn)
     * That is, if we experimented with each box as a bottom and built the biggest
     * stack possible with each, we would find the biggest stack possible.
     *
     * To find the biggest stack with a particular bottom, we do it recursively.
     * we only experiment with valid boxes: if b5 is bigger than b1, then there's
     * no point in trying to build a stack that looks like{b1, b5, ....}
     */
    public static ArrayList<Box> createStack(Box[] boxes, Box bottom)
    {
        ArrayList<Box> maxStack=null;
        int maxHeight=0;
        for(int i=0; i<boxes.length; i++)
        {
            if(boxes[i].canBeAbove(bottom))
            {
                ArrayList<Box> newStack=createStack(boxes, boxes[i]);
                int newHeight=stackHeight(newStack);
                if(newHeight > maxHeight)
                {
                    maxStack=newStack;
                    maxHeight=newHeight;
                }
            }
        }
        /*
         * this happens when one of the followings happens:
         *
         * 1. bottom is the smallest
         * 2. no other box can be place above bottom
         */
        if(maxStack == null)
            maxStack=new ArrayList<Box>();
        if(bottom!=null)
            maxStack.add(0, bottom);
        return maxStack;
    }

    private static HashMap<Box, ArrayList<Box>> cache=new HashMap<Box, ArrayList<Box>>();
    /*
     * In DP, we clone the value of the result of recursive call when using
     * hashtable as cache, and the value stored is Object type which we
     * will modify throughout the recursion stacks.
     *
     * We clone the value at both
     * the get and put step: for get(), we make sure the value we fetch from
     * the hashtable won't be modified after we assign it to a variable(in this
     * case, variable is newStack); for put(), because we are about to return
     * the calculated value(in this case, the value refers to maxStack) up to
     * the recursive call, and that will be assigned to a variable(newStack) which
     * will then be modified. So at this time, we are not getting the value from
     * the hashtable(which we put the value in before go to the upper recursion level),
     * and therefore we should also clone the result when we put into hashtable
     */
    public static ArrayList<Box> createStackDP(Box[] boxes, Box bottom)
    {
        if(bottom !=null && cache.containsKey(bottom))
            return (ArrayList<Box>)cache.get(bottom).clone();
        ArrayList<Box> maxStack=null;
        int maxHeight=0;
        for(int i=0; i<boxes.length; i++)
        {
            if(boxes[i].canBeAbove(bottom))
            {
                ArrayList<Box> newStack=createStack(boxes, boxes[i]);
                int newHeight=stackHeight(newStack);
                if(newHeight > maxHeight)
                {
                    maxStack=newStack;
                    maxHeight=newHeight;
                }
            }
        }
        if(maxStack == null)
            maxStack=new ArrayList<Box>();
        if(bottom!=null)
        {
            maxStack.add(0, bottom);
            cache.put(bottom, (ArrayList<Box>)maxStack.clone());
        }
        return maxStack;

    }
    private static int stackHeight(ArrayList<Box> stack)
    {
        int result=0;
        for(Box b : stack)
            result+=b.height;
        return result;
    }
}

class Box
{
    int length;
    int width;
    int height;

    public Box(int length, int width, int height)
    {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public boolean canBeAbove(Box b)
    {
        if(b==null)
            return true;
        if(b==this)
            return false;
        return width<b.width && length<b.length
                && height<b.height;
    }

    public String toString()
    {
        return "["+length+","+width+","+height+"]";
    }
}
