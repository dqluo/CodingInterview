package crackingAndMadeEasy.linkedList;

import util.Node;

import java.util.HashMap;

import static util.Node.createList;
import static util.Node.printList;

/**
 * User: xinyuwan, Date: 11/23/13, Time: 5:50 PM
 */
public class CopyList
{
    /*
     * Problem: copy a linkedlist
     */
    public static void main(String[] args)
    {
        Integer[] a={1, 2, 3, 4, 5};
        Node nodeA=createList(a);
        printList(copy(nodeA));
        printList(copy2(nodeA));

        printList(copy3(nodeA));

    }
    //iterative approach
    public static Node copy(Node head)
    {
        if(head==null)
            return null;
        Node current=head;
        Node newHead=new Node(current.data), newCurrent=newHead;
        //begin to iterate both original and new list
        while(current.next!=null)
        {
            /*
             * instead of set up current node, we set up the next node
             * of the current node, meanwhile apply the value of the next
             * node in original(current.next) to value of newCurrent.next
             */
            newCurrent.next=new Node(current.next.data);
            newCurrent=newCurrent.next;
            current=current.next;
        }
        return newHead;
    }
    //recursive approach
    public static Node copy2(Node head)
    {
        if(head==null)
            return null;
        return new Node(head.data, copy2(head.next));
    }
    //use hashmap to copy value first, then set up the next node
    public static Node copy3(Node head)
    {
        if(head==null)
            return null;
        HashMap<Node, Node> map=new HashMap<Node, Node>();
        Node current=head;
        while(current!=null)
        {
            map.put(current, new Node(current.data));
            current=current.next;
        }
        current=head;
        Node result=map.get(current);
        while(current!=null)
        {
            map.get(current).next=map.get(current.next);
            current=current.next;
        }
        return result;
    }
}
