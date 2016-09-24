package leetCode.ADT;

/**
 * User: xinyuwan, Date: 12/26/13, Time: 11:11 PM
 */
public class RandomListNode
{
    public int label;
    public RandomListNode next, random;
    public RandomListNode(int x) { this.label = x; }
    public static void printList(RandomListNode head)
    {
        while(head!=null)
        {
            System.out.print(head.label+" ");
            head=head.next;
        }
        System.out.println();
    }
}
