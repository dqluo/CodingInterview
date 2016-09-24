package leetCode.linkedList;

import leetCode.ADT.ListNode;

import static leetCode.ADT.ListNode.createList;
import static leetCode.ADT.ListNode.printList;

/**
 * User: xinyuwan, Date: 12/28/13, Time: 3:10 PM
 */
public class SortList
{
    /*
     * Total Problems: 2
     */
    public static void main(String[] args)
    {
        //test insertion sort
        int[] a={2, 1, 3, 9, 4, 19, 8};
        InsertionSortList isl=new InsertionSortList();
        System.out.print("Result after insertion sorting: ");
        printList(isl.insertionSortList(createList(a)));

        //test merge sort
        int[] b={4,19,14,5,-3,1,8,5,11,15};
        MergeSortList msl=new MergeSortList();
        System.out.print("Result after merge sorting: ");
        printList(msl.sortList(createList(b)));
    }

}

class InsertionSortList
{
    /*
     * Problem: Sort a linked list using insertion sort.
     */
    public ListNode insertionSortList(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode result=new ListNode(0);
        result.next=head;
        ListNode sortedEnd=head, current=head.next;
        head=result;
        while(current!=null)
        {
            while(head.next!=current && head.next.val <= current.val)
            {
                head=head.next;
            }
            //the logic of this part is similar to RemoveDuplicatesFromSortedList2
            if(head.next!=current)
            {
                sortedEnd.next=current.next;
                ListNode temp=head.next;
                head.next=current;
                current.next=temp;
            }
            //if we don't need to insert current, we simply advance sortedEnd to next
            else
                sortedEnd=sortedEnd.next;
            head=result;
            current=sortedEnd.next;
        }
        return result.next;
    }
}

class MergeSortList
{
    /*
     * Problem: Sort a linked list in O(n log n) time using constant space complexity.
     */
    public ListNode sortList(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode slow=head, fast=head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode secondHead=slow.next;
        slow.next=null;
        head=sortList(head);
        secondHead=sortList(secondHead);
        return merge(head, secondHead);
    }
    private ListNode merge(ListNode l1, ListNode l2)
    {
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        ListNode head=null;
        if(l1.val < l2.val)
        {
            head=l1;
            l1=l1.next;
        }
        else
        {
            head=l2;
            l2=l2.next;
        }
        ListNode current=head;
        while(l1!=null && l2!=null)
        {
            if(l1.val < l2.val)
            {
                current.next=l1;
                l1=l1.next;
            }
            else
            {
                current.next=l2;
                l2=l2.next;
            }
            current=current.next;
        }
        current.next=l1==null? l2 : l1;
        return head;
    }

//    public ListNode sortList2(ListNode head)
//    {
//        if(head==null || head.next==null)
//            return head;
//        int step=1, size=listSize(head);
//        ResultWrapperHE result=null;
//        ListNode headOfNextItr=null;
//        while(step < size)
//        {
//            ListNode headL=head, headR=null, previous=null;
//            int startR=step;
//            while(startR+step <= size)
//            {
//                ListNode slow=headL, fast=headL;
//                for(int i=0; i<step-1; i++)
//                {
//                    slow=slow.next;
//                    fast=fast.next.next;
//                }
//                headR=slow.next;
//                ListNode temp=fast.next.next;
//                fast.next.next=null;
//                slow.next=null;
//                result=merge2(headL, headR);
//                if(previous!=null)
//                    previous.next=result.head;
//                else
//                    head=result.head;
//                previous=result.end;
//                headL=temp;
//                headOfNextItr=temp;
//                startR=startR+2*step;
//            }
//            if(headOfNextItr!=null)
//                result=merge2(head, headOfNextItr);
//            step*=2;
//        }
//        return result.head;
//    }
//    private int listSize(ListNode head)
//    {
//        int size=0;
//        while(head!=null)
//        {
//            size++;
//            head=head.next;
//        }
//        return size;
//    }
//    private ResultWrapperHE merge2(ListNode l1, ListNode l2)
//    {
//        if(l1==null)
//            return new ResultWrapperHE(l2, null);
//        if(l2==null)
//            return new ResultWrapperHE(l1, null);
//        ListNode head=null;
//        if(l1.val < l2.val)
//        {
//            head=l1;
//            l1=l1.next;
//        }
//        else
//        {
//            head=l2;
//            l2=l2.next;
//        }
//        ListNode current=head;
//        while(l1!=null && l2!=null)
//        {
//            if(l1.val < l2.val)
//            {
//                current.next=l1;
//                l1=l1.next;
//            }
//            else
//            {
//                current.next=l2;
//                l2=l2.next;
//            }
//            current=current.next;
//        }
//        current.next=l1==null? l2 : l1;
//        while(current.next!=null)
//            current=current.next;
//        return new ResultWrapperHE(head, current);
//    }
//
//    class ResultWrapperHE
//    {
//        ListNode head;
//        ListNode end;
//        public ResultWrapperHE(ListNode h, ListNode e)
//        {
//            head=h;
//            end=e;
//        }
//    }
}


