package leetCode.linkedList;

import leetCode.ADT.ListNode;

import java.util.HashMap;

import static leetCode.ADT.ListNode.createList;
import static leetCode.ADT.ListNode.printList;

/**
 * User: xinyuwan, Date: 12/26/13, Time: 2:03 PM
 */
public class RemoveAndRotate
{
    /*
     * Total Problems: 4
     */
    public static void main(String[] args)
    {
        //test deleteDuplicates
        int[] a={1, 1, 2, 2, 3};
        RemoveDuplicatesFromSortedList rdsl=new RemoveDuplicatesFromSortedList();
        System.out.print("Result after removing dups: ");
        printList(rdsl.deleteDuplicates(createList(a)));

        //test deleteDuplicates
        int[] b={1, 1, 1, 2, 3, 3};
        RemoveDuplicatesFromSortedList2 rdsl2=new RemoveDuplicatesFromSortedList2();
        System.out.print("Result after removing nodes with dups: ");
        printList(rdsl2.deleteDuplicates(createList(b)));

        int[] c={1, 2, 3, 4, 5};
        ListNode listC= createList(c);
        RemoveNthNode rn=new RemoveNthNode();
        System.out.print("Removing node1: ");
        printList(rn.removeNthFromEnd(listC, 1));
        System.out.print("Removing node2: ");
        printList(rn.removeNthFromEnd(listC, 2));
        System.out.print("Removing node3: ");
        printList(rn.removeNthFromEnd(listC, 3));

        int[] d={1, 2, 3, 4, 5};
        RotateList rl=new RotateList();
        System.out.print("Result after rotating: ");
        printList(rl.rotateRight(createList(d), 2));
    }
}

class RemoveDuplicatesFromSortedList
{
    //Because this is sorted list, we need only O(n) complexity
    public ListNode deleteDuplicates(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode current=head;
        while(current.next!=null)
        {
            if(current.val==current.next.val)
                current.next=current.next.next;
            else
                current=current.next;
        }
        return head;
    }
    public ListNode deleteDuplicates2(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        HashMap<Integer, Boolean> map=new HashMap<Integer, Boolean>();
        ListNode current=head;
        map.put(current.val, true);
        //now list.size()>=2 && current won't be null in iteration
        while(current.next!=null)
        {
            if(map.containsKey(current.next.val))
                current.next=current.next.next;
            else
            {
                map.put(current.next.val, true);
                current=current.next;
            }
        }
        return head;
    }
    public ListNode deleteDuplicates3(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode current=head;
        //need to check whether current becomes null in the iteration
        while(current!=null && current.next!=null)
        {
            ListNode runner=current;
            while(runner.next!=null)
            {
                if(current.val==runner.next.val)
                    runner.next=runner.next.next;
                else
                    runner=runner.next;
            }
            current=current.next;
        }
        return head;
    }
}

class RemoveDuplicatesFromSortedList2
{
    /*
     * Given a sorted linked list, delete all nodes that have duplicate numbers,
     * leaving only distinct numbers from the original list.
     * For example, Given 1->2->3->3->4->4->5, return 1->2->5.
     * Given 1->1->1->2->3, return 2->3.
     */
    public ListNode deleteDuplicates(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        /*
         * by adding prev, we make sure that the nodes from prev to head
         * are unique and checked. so prev marks the last checked element
         */
        ListNode prev=new ListNode(0);
        prev.next=head;
        head=prev;

        while(prev.next!=null)
        {
            //as long as the list size>=2, we need to check from prev.next
            ListNode current=prev.next;
            while(current.next!=null && current.val==current.next.val)
                current=current.next;
            /*
             * this means current encounters duplicates in the iteration and
             * we delete all elements that current has iterated though
             */
            if(prev.next!=current)
                prev.next=current.next;
                //otherwise, we advance prev to the next element
            else
                prev=prev.next;
        }
        //head.next could be null if all elements are dup, or could be the first unique node
        return head.next;
    }
}

class RemoveNthNode
{
    /*
     * Problem: Given a linked list, remove the nth node from the end of list and return its head.
     */
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        if(head==null)
            return null;
        ListNode slow=head, fast=head;
        for(; n>0 && fast!=null; n--)
            fast=fast.next;
        if(fast==null)
        {
            //abnormal case: n>list.size()
            if(n > 0)
                return head;
            //n==list.size(), we need to remove head
            else
                return head.next;
        }
        while(fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next;
        }
        slow.next=slow.next.next;
        return head;
    }
}

class RotateList
{
    /*
     * Problem: Given a list, rotate the list to the right by k places, where k is non-negative.
     * For example: Given 1->2->3->4->5->NULL and k = 2,
     * return 4->5->1->2->3->NULL.
     */
    public ListNode rotateRight(ListNode head, int n)
    {
        if(head==null || head.next==null || n<=0)
            return head;
        ListNode current=head;
        int size=1;
        while(current.next!=null)
        {
            current=current.next;
            size++;
        }
        //now current is at the last node
        current.next=head;
        //now we only need to go for size-n%size steps to the node before the kth node from last
        //since n might be greater than size we need to get n%size
        for(int i=0; i<size-n%size; i++)
            current=current.next;
        head=current.next;
        current.next=null;
        return head;
    }
}
