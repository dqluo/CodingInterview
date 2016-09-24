package linkedList;

import util.Node;

import java.util.HashMap;

/**
 * User: xinyuwan, Date: 11/23/13, Time: 3:41 PM
 */
public class RemoveNode
{
    public static void main(String[] args)
    {
        Integer[] a={1, 2, 2, 2, 3, 4, 4, 3, 4, 5};
        Integer[] b={1, 2, 2, 2, 3, 4, 4, 3, 4, 5};
        //test removeDup()
        Node nodeA=RemoveDuplicate.remove(Node.createList(a));
        Node nodeB=RemoveDuplicate.remove2(Node.createList(b));
        //output: 1 2 3 4 5
        Node.printList(nodeA);
        Node.printList(nodeB);

        //test removeMid()
        Node nodeC=nodeA.next.next;
        System.out.println(RemoveFromMiddle.remove(nodeC));
        //output: 1 2 4 5
        Node.printList(nodeA);
    }
}

class RemoveDuplicate
{
    /*
     * Problem1: remove all duplicate nodes in a linkedlist
     */
    public static Node remove(Node<Integer> head)
    {
        if(head==null || head.next==null)
            return head;
        HashMap<Integer, Boolean> h=new HashMap<Integer, Boolean>();
        Node<Integer> current=head;
        h.put(current.data, true);
        //we check from the second element
        while(current.next!=null)
        {
            if(h.containsKey(current.next.data))
                current.next=current.next.next;
            else
            {
                //update current only when next is not duplicate
                current=current.next;
                h.put(current.data, true);
            }
        }
        return head;
    }
    public static Node remove2(Node head)
    {
        if(head==null)
            return null;
        Node current=head;
        //check from the second
        while(current.next!=null)
        {
            Node runner=current;
            while(runner.next!=null)
            {
                if(current.data==runner.next.data)
                    runner.next=runner.next.next;
                else
                    //update runner only when next is not duplicate
                    runner=runner.next;
            }
            current=current.next;
        }
        return head;
    }
}

class RemoveFromMiddle
{
    /*
     * Problem 2: Remove node in the middle of LinearDataStructure, given param
     * head as the middle node. If head is null or head.next==null,
     * return false, because, it cannot be removed
     */
    public static boolean remove(Node head)
    {
        if(head==null || head.next==null)
            return false;
//        while(head.next!=null)
//        {
//            head.data=head.next.data;
//            //check whether head.next is the last node
//            if(head.next.next==null)
//                head.next=null;
//            else
//                head=head.next;
//        }
        Node nextNode=head.next;
        head.data=nextNode.data;
        head.next=nextNode.next;
        return true;
    }
}

