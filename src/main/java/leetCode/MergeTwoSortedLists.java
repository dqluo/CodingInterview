package leetCode;

import leetCode.ADT.ListNode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: xinyuwan, Date: 12/15/13, Time: 5:24 PM
 */
public class MergeTwoSortedLists
{
    /*
     * Problem: Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        ArrayList<Integer>  al= (ArrayList<Integer>)Arrays.asList(new Integer[2]);
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        ListNode head=null, last=null;
        if(l1.val <= l2.val)
        {
            head=l1;
            l1=l1.next;
        }
        else
        {
            head=l2;
            l2=l2.next;
        }
        last=head;
        while(l1!=null && l2!=null)
        {
            if(l1.val<=l2.val)
            {
                last.next=l1;
                l1=l1.next;
            }
            else
            {
                last.next=l2;
                l2=l2.next;
            }
            last=last.next;
        }
        last.next=l1==null? l2 : l1;
        return head;
    }
}
