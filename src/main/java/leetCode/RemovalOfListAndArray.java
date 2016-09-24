package leetCode;

import leetCode.ADT.ListNode;

import java.util.ArrayList;
import java.util.HashMap;

import static leetCode.ADT.ListNode.createList;
import static leetCode.ADT.ListNode.printList;

/**
 * User: xinyuwan, Date: 12/14/13, Time: 2:52 PM
 */
public class RemovalOfListAndArray
{
    public static void main(String[] args)
    {
        int[] a={1, 2, 3, 4, 5};
        ListNode listA= createList(a);
        RemoveNthNode rn=new RemoveNthNode();
        printList(rn.removeNthFromEnd(listA, 1));
        printList(rn.removeNthFromEnd(listA, 2));
        printList(rn.removeNthFromEnd(listA, 3));

        //test deleteDuplicates
        int[] b={1, 1, 2, 2, 3};
        ListNode listB=createList(b);
        DeleteDuplicates dd=new DeleteDuplicates();
        printList(dd.deleteDuplicates(listB));
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

class DeleteDuplicates
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

class DeleteDuplicates2
{
    /*
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
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

class RemoveDupFromSortedArray
{
    /*
     * Problem:Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * For example,
     * Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     */
    public int removeDuplicates(int[] A)
    {
        if(A.length==0)
            return 0;
        /*
         * Use index to mark the position of the last unique element,
         * and while iterating throught the array, check the current with
         * previous if they are dup. If not, we find the next unique element
         * and therefore, we need to advance index and copy that element to index.
         * One extreme situation is that every element in A is unique. In this
         * case, we just copy every element to its index since index and i are updating
         * at the same time.
         */
        int index=0;
        for(int i=1; i<A.length; i++)
        {
            if(A[i]!=A[i-1])
            {
                index++;
                A[index]=A[i];
            }
        }
        return index+1;
    }
}

class RemoveDupFromSortedArray2
{
    /*
     * Problem: Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
     * For example, Given sorted array A = [1,1,1,2,2,3],
     * Your function should return length = 5, and A is now [1,1,2,2,3].
     */
    public int removeDuplicates(int[] A)
    {
        if(A.length==0)
            return 0;
        /*
         * The basic logic is the same. We still use index to mark
         * the index of last unique one, the difference is that we
         * advance index and copy A[i] when A[i]==A[i-1]. This is because
         * when we there are more than 2 dups, like 1, 1, 1, 2, 2, and when
         * index is at position 2 and we copy element 2 to index, i++ but index
         * stays at 2. This time since 2 is dup, and we need the 2 at position 4,
         * we copy that to position 3.
         */
        int index=0;
        for(int i=1; i<A.length;)
        {
            if(A[i]==A[i-1])
            {
                index++;
                A[index]=A[i];
                while(i<A.length && A[i]==A[i-1])
                    i++;
            }
            else
            {
                index++;
                A[index]=A[i];
                i++;
            }
        }
        return index+1;
    }
}

class RemoveElement
{
    /*
     * Problem: Given an array and a value, remove all instances of that value in place and return the new length.
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     */
    public int removeElement(int[] A, int elem)
    {
        int lastIndex=A.length-1;
        for(int i=0; i<=lastIndex;)
        {
            if(A[i]==elem)
            {
                A[i]=A[lastIndex];
                lastIndex--;
            }
            else
                i++;
        }
        return lastIndex+1;
    }
}

