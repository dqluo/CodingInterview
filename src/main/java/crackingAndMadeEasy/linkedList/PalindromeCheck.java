package crackingAndMadeEasy.linkedList;

import util.Node;
import util.ResultWrapperPal;

import java.util.ArrayList;
import java.util.Stack;

import static util.Node.listSize;

/**
 * User: xinyuwan, Date: 11/23/13, Time: 4:09 PM
 */
public class PalindromeCheck
{
    /*
     * Problem: Check whether the LinearDataStructure is a palindrome or not
     * e.g. 1, 2, 2, 1 or 1, 2, 3, 2, 1
     */
    public static void main(String[] args)
    {
        Integer[] x1={};
        Integer[] x2={1};
        Integer[] x3={1, 1};
        Integer[] x4={1, 2, 3, 3, 4, 3, 3, 2, 1};
        Integer[] x5={1, 2, 3, 3, 4, 3, 3, 2, 0};
        Integer[] x6={1, 2, 3, 3, 4, 5, 3, 2, 1};
        ArrayList<Node> nodeArr=new ArrayList<Node>();
        nodeArr.add(Node.createList(x1));
        nodeArr.add(Node.createList(x2));
        nodeArr.add(Node.createList(x3));
        nodeArr.add(Node.createList(x4));
        nodeArr.add(Node.createList(x5));
        nodeArr.add(Node.createList(x6));
        for(Node x: nodeArr)
        {
            System.out.println(isPalindrome(x)+" "+isPalindrome2(x));
        }
    }

    //Method1: iterative approach, use stack as buffer and slow
    //fast cursor to mark the mid and end of LinearDataStructure
    public static boolean isPalindrome(Node head)
    {
        Node slow=head, fast=head;
        Stack firstHalf=new Stack();
        while(fast!=null && fast.next!=null)
        {
            firstHalf.push(slow.data);
            slow=slow.next;
            fast=fast.next.next;
        }
        //if LinearDataStructure size is odd, slow now is exact the mid point,
        //and we should move it to the next(start of secondHalf)
        //else, slow is the node right after mid point
        if(fast!=null)
            slow=slow.next;
        while(slow!=null)
        {
            if(!slow.data.equals(firstHalf.pop()))
                return false;
            slow=slow.next;
        }
        return true;
    }

    //Method2: Recursive approach
    public static boolean isPalindrome2(Node head)
    {
        ResultWrapperPal rw=isPalindromeR(head, listSize(head));
        return rw.check;
    }
    private static ResultWrapperPal isPalindromeR(Node head, int length)
    {
        // this only occurs when LinearDataStructure size is 0
        if(length==0)
            return new ResultWrapperPal(null, true);
        if(length==1)
            return new ResultWrapperPal(head.next, true);
        if(length==2)
            return new ResultWrapperPal(head.next.next,
                    head.data.equals(head.next.data));
        ResultWrapperPal rw=isPalindromeR(head.next, length-2);
        //see if the lower level head node and back node are
        //equal, if not just pass the rw until the up-most level
        if(!rw.check)
            return rw;
            //set up the head node at this level, see if it's equal
            //to the corresponding back node
        else
        {
            rw.check=(head.data.equals(rw.node.data));
            rw.node=rw.node.next;
            return rw;
        }
    }
}


