package linkedList;

import util.Node;
import util.ResultWrapperNode;

import static util.Node.createList;
import static util.Node.printList;

/**
 * User: xinyuwan, Date: 11/23/13, Time: 9:24 PM
 */
public class ReverseList
{
    public static void main(String[] args)
    {
        //Test reverseAll()
        Integer[] a={1, 2, 3, 4, 5};
        Node nodeA=createList(a);
        printList(Reverse.reverseAll(nodeA));
        /*
         * output: 5 4 3 2 1
         */

        //Test reverseInPair() and reverseInPair2()
        Integer[] b={1, 3, 4};
        Integer[] c={2, 5, 8, 10, 11, 12};
        Node nodeB=Node.createList(b);
        Node nodeC=Node.createList(c);
        nodeB=ReverseListInPairs.reversePair(nodeB);
        Node.printList(nodeB);
        nodeB=ReverseListInPairs.reversePair2(nodeB);
        Node.printList(nodeB);
        nodeC=ReverseListInPairs.reversePair(nodeC);
        Node.printList(nodeC);
        nodeC=ReverseListInPairs.reversePair2(nodeC);
        Node.printList(nodeC);
        /*
         * output:
         * 3 1 4
         * 1 3 4
         * 5 2 10 8 12 11
         * 2 5 8 10 11 12
         */

        //Test reverseBlock
        Integer[] d={1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        Node nodeD=Node.createList(d);
        Node.printList(ReverseListInBlocks.reverseBlock(nodeD, 4));
        /*
         * output: 4 3 2 1 8 7 6 5 12 11 10 9 13
         */
    }
}

class Reverse
{
    /*
     * Problem1: reverse a linkedlist
     */
    public static Node reverseAll(Node head)
    {
        return reverseAux(head).head;
    }
    public static ResultWrapperNode reverseAux(Node head)
    {
        if(head==null)
            return null;
        if(head.next==null)
            return new ResultWrapperNode(head, head);
        ResultWrapperNode rw=reverseAux(head.next);
        rw.node.next=head;
        rw.node=head;
        //when it comes to the first node, we need to cut off its next pointer
        head.next=null;
        return rw;
    }

    public static Node reverseAll2(Node head)
    {
        Node previous=null, next=null;
        while(head!=null)
        {
            next=head.next;
            head.next=previous;
            previous=head;
            head=next;
        }
        return previous;
    }
}

class ReverseListInPairs
{
    /*
     * Problem2: reverse LinearDataStructure is pairs, if the LinearDataStructure is odd,
     * leave the last node as it is
     */

    //Method1: recursive approach
    public static Node reversePair(Node head)
    {
        if(head==null || head.next==null)
            return head;
        //use temp to store the pointer to head.next
        Node temp=head.next;
        //temporarily store head.next to head.next.next
        head.next=temp.next;
        //redirect temp.next to head
        temp.next=head;
        //after recurse reset head.next
        head.next=reversePair(head.next);
        return temp;
    }

    //Method2: iterative approach
    //Note: important thing is to set head.next to head.next.next
    //temporarily to store the pointer. And when moving to next iteration,
    //use previous to set the next to current head.
    public static Node reversePair2(Node head)
    {
        if(head==null || head.next==null)
            return head;
        Node temp=null, previous=null;
        Node result=head.next;
        //head can be null during iteration, so we must check head!=null first
        while(head!=null && head.next!=null)
        {
            //update next here to make sure current!=null
            temp=head.next;
            head.next=temp.next;
            temp.next=head;
            if(previous!=null)
                previous.next=temp;
            //update precious and current
            previous=head;
            head=head.next;
        }
        return result;
    }
}

class ReverseListInBlocks
{
    /*
     * Problem3: reverse every K nodes as a block, like
     * 1 2 3 4 5 6->3 2 1 5 4
     */

    public static Node reverseBlock(Node head, int k)
    {
        if(!hasKNodes(head, k))
            return head;
        //we need to keep the pointer head after reverse the k
        //nodes in this level, so as to set it's next to the node
        //returned by the next level
        Node current=head;
        Node previous=null;
        for(int i=0; i<k; i++)
        {
            Node temp=current.next;
            current.next=previous;
            previous=current;
            current=temp;
        }
        //now current is the k+1 th node from head
        //previous is the kth node and is the new head of LinearDataStructure
        head.next=reverseBlock(current, k);
        return previous;

    }
    private static boolean hasKNodes(Node head, int k)
    {
        for(int i=0; i<k-1 && head!=null; i++)
            head=head.next;
        if(head==null)
            return false;
        return true;
    }
}
