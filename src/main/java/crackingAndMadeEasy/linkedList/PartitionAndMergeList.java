package crackingAndMadeEasy.linkedList;

import util.Node;

/**
 * User: xinyuwan, Date: 11/23/13, Time: 2:02 PM
 */
public class PartitionAndMergeList
{
    public static void main(String[] args)
    {
        //Test partition()
        Integer[] a={4, 3, 10, 9, 7, 2, 5, 1, 6};
        Node nodeA=Node.createList(a);
        Node result1=PartitionListByValueX.partition(nodeA, 6);
        Node.printList(result1);
        /*
         * output: 4 3 2 5 1 10 9 7 6
         */

        //Test merge()
        Integer[] x={1, 3, 4, 6, 9};
        Integer[] y={2, 5, 8, 10, 11, 12};
        Node nodeX=Node.createList(x);
        Node nodeY=Node.createList(y);
        Node.printList(MergeSortedLists.merge(nodeX, nodeY));
        //Node.printList(MergeSortedLists.merge2(nodeX, nodeY));
        /*
         * output: 1 2 3 4 5 6 8 9 10 11 12
         */
    }

}

class PartitionListByValueX
{
    /*
     * Problem1: partition and reconstruct a list around the value x
     */
    public static Node partition(Node<Integer> head, int x)
    {
        if(head==null)
            return null;
        Node head1=null, end1=null, head2=null, end2=null;
        while(head!=null)
        {
            Node temp=head.next;
            //we need set head.next to be null to ensure we only insert
            //one node in either list(no node in l1 will point to l2 after partition)
            head.next=null;
            if(head.data<x)
            {
                if(head1==null)
                    head1=head;
                else
                    end1.next=head;
                end1=head;
            }
            else
            {
                if(head2==null)
                    head2=head;
                else
                    end2.next=head;
                end2=head;
            }
            head=temp;
        }
        if(head1==null)
            return head2;
        end1.next=head2;
        return head1;
    }
}
class MergeSortedLists
{
    /*
     * Problem2: merge two sorted lists: use recursion
     */
    public static Node<Integer> merge(Node<Integer> n1, Node<Integer> n2)
    {
        if(n1==null)
            return n2;
        if(n2==null)
            return n1;
        Node resultHead=null, resultEnd=null;
        while(n1!=null && n2!=null)
        {
            if(n1.data<=n2.data)
            {
                if(resultHead==null)
                    resultHead=n1;
                else
                    resultEnd.next=n1;
                resultEnd=n1;
                n1=n1.next;
            }
            else
            {
                if(resultHead==null)
                    resultHead=n2;
                else
                    resultEnd.next=n2;
                resultEnd=n2;
                n2=n2.next;
            }
        }
        //append the rest of non-null list
        resultEnd.next=n1==null? n2 : n1;
        return resultHead;
    }

    public static Node<Integer> merge2(Node<Integer> n1, Node<Integer> n2)
    {
        if(n1==null)
            return n2;
        if (n2==null)
            return n1;
        Node result=null;
        if(n1.data<=n2.data)
        {
            result=n1;
            result.next=merge2(n1.next, n2);
        }
        else
        {
            result=n2;
            result.next=merge2(n1, n2.next);
        }
        return result;
    }
}
