package linkedList;

import util.IntWrapper;
import util.Node;

import java.util.Hashtable;

/**
 * User: xinyuwan, Date: 11/21/13, Time: 11:13 PM
 */
public class FindKthNodeToLast
{
    /*
     * Problem: find the the element at the position k counting from
     * the last element, if k is longer than the LinearDataStructure length, return null
     */
    public static void main(String[] args)
    {
        Integer[] a={1, 2, 3, 4, 5};
        Node nodeA=Node.createList(a);

        System.out.println(KToLast(nodeA, 2).data);
        System.out.println(KToLast2(nodeA, 3, new IntWrapper()).data);
        System.out.println(KToLast3(nodeA, 3).data);
    }
    //Method1: iterative approach, first move fast k steps
    public static Node KToLast(Node head, int k)
    {
        if(k<=0) return null;
        Node slow=head;
        Node fast=head;
        for(int i=0; i<k && fast!=null; i++)
            fast=fast.next;
        while(fast!=null)
        {
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
    //Method2: recursive approach, recurse to last, then accumulate
    //the value in interWrapper, if value==k, then return that head
    public static Node KToLast2(Node head, int k, IntWrapper i)
    {
        if(head==null)
            return null;
        Node node=KToLast2(head.next, k, i);
        i.value++;
        if(i.value==k)
            return head;
        return node;
    }

    //Method3: Use hashtable to store count
    public static Node KToLast3(Node head, int k)
    {
        if(head==null || k<=0)
            return null;
        Hashtable<Integer, Node> table=new Hashtable<Integer, Node>();
        int count=0;
        while(head!=null)
        {
            count++;
            table.put(count, head);
            head=head.next;
        }
        return table.get(count-k+1);
    }
}


