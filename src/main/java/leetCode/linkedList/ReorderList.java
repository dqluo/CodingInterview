package leetCode.linkedList;

import leetCode.ADT.ListNode;

import static leetCode.ADT.ListNode.createList;
import static leetCode.ADT.ListNode.printList;

/**
 * User: xinyuwan, Date: 12/28/13, Time: 6:08 PM
 */
public class ReorderList
{
    public static void main(String[] args)
    {
        int[] a={1, 2, 3, 4, 5};
        ListNode listA=createList(a);
        ReorderList rl=new ReorderList();
        rl.reorderList(listA);
        System.out.print("Result of reordering list: ");
        printList(listA);
    }
    /*
     * Problem: Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     * You must do this in-place without altering the nodes' values.
     * For example, * Given {1,2,3,4}, reorder it to {1,4,2,3}.
     *
     */
    public void reorderList(ListNode head)
    {
        if(head==null || head.next==null)
            return;
        //now at least two nodes in list
        ListNode slow=head, fast=head.next;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode headOfSecond=slow.next;
        slow.next=null;

        //reverse the second half
        headOfSecond=reverseList(headOfSecond);

        //insert each node of second half into the next of the node in first half
        slow=head;
        fast=headOfSecond;
        while(slow!=null)
        {
            ListNode temp=slow.next;
            slow.next=fast;
            slow=temp;
            if(slow!=null)
            {
                temp=fast.next;
                fast.next=slow;
                fast=temp;
            }
        }
    }
    private ListNode reverseList(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode result=new ListNode(0);
        result.next=head;
        ListNode previous=result;
        while(head!=null)
        {
            ListNode temp=head.next;
            head.next=previous;
            previous=head;
            head=temp;
        }
        result.next.next=null;
        result.next=null;
        return previous;
    }
}
