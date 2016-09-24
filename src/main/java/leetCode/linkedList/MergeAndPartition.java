package leetCode.linkedList;

import leetCode.ADT.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static leetCode.ADT.ListNode.createList;
import static leetCode.ADT.ListNode.printList;

/**
 * User: xinyuwan, Date: 12/15/13, Time: 5:24 PM
 */
public class MergeAndPartition
{
    /*
     * Total problems: 3
     */
    public static void main(String[] args)
    {
        //test mergeTwoList
        int[] a1={3, 8, 12, 21, 32};
        int[] a2={4, 5, 7, 19, 81};
        System.out.print("Result of merging two lists: ");
        printList(new MergeTwoSortedLists().mergeTwoLists(createList(a1), createList(a2)));

        //test mergeKList
        int[] b1={2, 8, 19, 53};
        int[] b2={1, 9, 15, 35};
        int[] b3={9, 12, 22, 93};
        int[] b4={10, 18, 49, 53};
        int[] b5={4, 11, 11, 66};
        ArrayList<ListNode> list=new ArrayList<ListNode>();
        list.add(createList(b1)); list.add(createList(b2)); list.add(createList(b3));
        list.add(createList(b4)); list.add(createList(b5));
        System.out.print("Result of merging k lists: ");
        printList(new MergeKSortedLists().mergeKLists(list));

        //test partitionList
        int[] arr={1, 4, 3, 2, 5, 2};
        int[] arr2={2,1};
        ListNode listArr=createList(arr);
        ListNode listArr2=createList(arr2);
        System.out.print("Result of partitioning: ");
        printList(new PartitionList().partition(listArr, 3));
        printList(new PartitionList().partition2(listArr2, 1));
    }
}

class MergeTwoSortedLists
{
    /*
     * Problem: Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
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

class MergeKSortedLists
{
    public ListNode mergeKLists(ArrayList<ListNode> lists)
    {
        if(lists==null || lists.isEmpty())
            return null;
        //since listNode does not have compareTo(), we need to pass a comparator in
        PriorityQueue<ListNode> q=new PriorityQueue<ListNode>(lists.size(), new NodeComparator());
        for(ListNode n : lists)
        {
            //we need to check the node first, otherwise q will throw NP Exception if n is null
            if(n!=null)
                q.add(n);
        }
        //stop going forward when q is empty
        if(q.isEmpty())
            return null;
        //initialize the head of the new list, don't forget to add head.next to q
        ListNode head=q.poll();
        if(head.next!=null)
            q.add(head.next);
        ListNode current=head;
        while(!q.isEmpty())
        {
            current.next=q.poll();
            current=current.next;
            //current.next is the next node in original list
            if(current.next!=null)
                q.add(current.next);
        }
        return head;
    }
    class NodeComparator implements Comparator<ListNode>
    {
        public int compare(ListNode l1, ListNode l2)
        {
            if(l1.val==l2.val)
                return 0;
            else if(l1.val < l2.val)
                return -1;
            else
                return 1;
        }
    }
    /*
     * Analysis: Merge lists 2 by 2.
     * E.g. Give 4 lists, l1, l2, l3, l4,
     * merge l1 and l4 to l1
     * merge l2 and l3 to l2
     * merge l1 and l2 to l1
     * E.g. Give 5 lists, l1, l2, l3, l4, l5,
     * merge l1 and l5 to l1
     * merge l2 and l4 to l2
     * merge l1 and l3 to l1
     * merge l1 and l2 to l1
     *
     * Time Complexity:
     * In mergesort solution, each original list has been touched O(logk) times,
     * where k is the number of lists. That said, each node has been touched O(logk) times.
     * So the total running time is O(nlogk).
     *
     * In heapsort solution, only head nodes of lists are kept in the min-heap.
     * Building up such a heap takes time O(klogk), each following insertion requires O(logk) time
     * and there are at most n insertions in total. So the total running time is also O(nlogk).
     *
     * Typically, n >> k, which means both algorithms performs better than O(nlogn).
     *
     * In the case of n <= k, there must exist empty lists.
     * Knowing that: Merging one empty list to another one takes time O(1);
     * Heap insertion for null values also takes O(1) time (no real insertion but a null check).
     * Suppose there are m nonempty lists. The total running time becomes
     * (k - m)*O(1) + O(nlogm) = O(nlogm).
     * Therefore, the time complexity of both algorithms is O(nlogm),
     * where n is the total number of nodes in lists and m is the number of nonempty lists.
     *
     * Space Complexity:
     * But the space complexity is quite different:
     * In the former solution, we need to keep previous merged lists; while in the latter solution, we only need to keep a k-heap.
     * Suppose you are given k infinite streams instead of k lists, the heap solution will be a better choice.
     */
    public ListNode mergeKLists2(ArrayList<ListNode> lists)
    {
         if(lists==null || lists.isEmpty())
             return null;
         MergeTwoSortedLists mtl=new MergeTwoSortedLists();
         int last=lists.size()-1;
         while(last > 0)
         {
             int first=0;
             while(first < last)
             {
                 lists.set(first, mtl.mergeTwoLists(lists.get(first), lists.get(last)));
                 first++;
                 last--;
             }
         }
         return lists.get(0);
    }
}

class PartitionList
{
    /*
     * Problem: Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
     * You should preserve the original relative order of the nodes in each of the two partitions.
     * For example, Given 1->4->3->2->5->2 and x = 3,
     * return 1->2->2->4->3->5.
     */
    public ListNode partition(ListNode head, int x)
    {
        ListNode beforeStart=null, beforeEnd=null, afterStart=null, afterEnd=null;
        while(head!=null)
        {
            ListNode temp=head.next;
            //we need set head.next to be null to ensure no node
            // in l1 will point to l2 after partition and vice-versa
            head.next=null;
            if(head.val<x)
            {
                if(beforeStart==null)
                    beforeStart=head;
                else
                    beforeEnd.next=head;
                beforeEnd=head;
            }
            else
            {
                if(afterStart==null)
                    afterStart=head;
                else
                    afterEnd.next=head;
                afterEnd=head;
            }
            head=temp;
        }
        //check corner case: every node.val > x
        if(beforeStart==null)
            return afterStart;
        //remember to connect the first and second part together
        beforeEnd.next=afterStart;
        return beforeStart;
    }

    public ListNode partition2(ListNode head, int x) {
        ListNode beforeStart=null;
        ListNode beforeEnd=null;
        ListNode afterStart=null;
        ListNode afterEnd=null;
        while(head!=null){
            if(head.val<=x){
                if(beforeStart==null){
                    beforeStart=head;
                    beforeEnd=head;
                    head=head.next;
                }
                else{
                    beforeEnd.next=head;
                    beforeEnd=beforeEnd.next;
                    head=head.next;
                }
            }
            else{
                if(afterStart==null){
                    afterStart=head;
                    afterEnd=head;
                    head=head.next;
                }
                else{
                    afterEnd.next=head;
                    afterEnd=afterEnd.next;
                    head=head.next;
                }
            }
        }
        if(beforeStart==null)
            return afterStart;
        beforeEnd.next=afterStart;
        return beforeStart;
    }
}
