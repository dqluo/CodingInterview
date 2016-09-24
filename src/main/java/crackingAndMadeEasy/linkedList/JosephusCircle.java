package crackingAndMadeEasy.linkedList;

import util.Node;

import static util.Node.printList;

/**
 * User: xinyuwan, Date: 11/23/13, Time: 3:18 PM
 */
public class JosephusCircle
{
    /*
     * Problem: Given a circular LinearDataStructure, the element starts from 1 to n, and a number m
     * means that delete node for every m nodes: e.g. 1, 2, 3, 4, 5, 6, m=3,
     * node to delete is 4. This method keeps deleting node util only one left,
     * which is the winner
     */
    public static void main(String[] args)
    {
        findWinner(7, 3);
        /*
         * output:
         * 1 2 3 4 5 6 7
         * The winner is position 2
         */
    }
    public static Node findWinner(int n, int m)
    {
        Node JosList=createJPcircle(n);
        //check if JosList is null or one, if either of them
        //occurs, then we're done
        if(JosList==null || JosList.next==null)
            return JosList;
        Node cursor=JosList;
        while(cursor.next!=cursor)
        {
            //move m-1 steps to find the node before the one to be deleted
            for(int i=0; i<m-1; i++)
                cursor=cursor.next;
            cursor.next=cursor.next.next;
            cursor=cursor.next;
        }
        System.out.println("The winner is position "+cursor.data);
        return cursor;
    }

    public static Node createJPcircle(int n)
    {
        if(n<=0)
            return null;
        Node head=new Node(1);
        Node current=head;
        for(int i=2; i<=n; i++)
        {
            current.next=new Node(i);
            current=current.next;
        }
        printList(head);
        current.next=head;
        return head;
    }
}
