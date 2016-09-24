package leetCode.linkedList;

import leetCode.ADT.ListNode;

/**
 * User: xinyuwan, Date: 1/6/14, Time: 6:56 PM
 */
public class ListCycle
{
    /*
     * Total problems: 2
     *
     * Problem: Given a linked list, determine if it has a cycle in it.
     * Follow up: Can you solve it without using extra space?
     */
    public boolean hasCycle(ListNode head)
    {
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                return true;
        }
        return false;
    }
}

class ListCycle2
{
    /*
     * Problem: Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * Can you solve it without using extra space?
     *
     * Analysis: consider the list has k nodes before going into the loop, and the size of the list is l.
     * When slow and and fast first meet, assume fast has travelled m nodes from the start(included), we know that
     * fast has travelled nodes twice as the slow, so we haven m+k+n*(l-k)=2*(k+m) => k= n*(l-k)-m;
     * n*(l-k) is n times of the loop circle. Therefore if we put slow to head at this time and move both pointers
     * forward at the same pace for k steps, they will meet right at the begin of the loop
     */
    public ListNode detectCycle(ListNode head)
    {
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                break;
        }
        if(fast==null || fast.next==null)
            return null;
        slow=head;
        while(slow!=fast)
        {
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
}
