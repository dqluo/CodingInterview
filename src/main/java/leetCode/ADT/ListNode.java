package leetCode.ADT;

/**
 * User: xinyuwan, Date: 12/14/13, Time: 2:53 PM
 */

public class ListNode
{
    public int val;
    public ListNode next;
    public ListNode(int x)
    {
        val = x;
        next = null;
    }
    public ListNode(int x, ListNode next)
    {
        this.val=x;
        this.next=next;
    }

    public static ListNode createList(int[] a)
    {
        ListNode current=null;
        for(int i=a.length-1; i>=0; i--)
        {
            current=new ListNode(a[i], current);
        }
        return current;
    }
    public static void printList(ListNode head)
    {
        while(head!=null)
        {
            System.out.print(head.val+" ");
            head=head.next;
        }
        System.out.println();
    }
}
