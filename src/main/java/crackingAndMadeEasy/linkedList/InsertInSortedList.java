package crackingAndMadeEasy.linkedList;

import util.Node;

/**
 * User: xinyuwan, Date: 11/23/13, Time: 2:59 PM
 */
public class InsertInSortedList
{
   /*
    * Problem: insert into sorted list a single node, return head after insertion
    */
    public static void main(String[] args)
    {
        Integer[] a={2, 4, 6, 8, 10};
        Node nodeA=Node.createList(a);
        insert(nodeA, 5);
        insert(nodeA, 11);
        nodeA=insert(nodeA, 1);
        Node.printList(nodeA);

        /*
         * output: 1, 2, 3, 4, 5, 6, 8, 10, 11
         */
    }
    public static Node insert(Node<Integer> head, int newData)
    {
        Node newNode=new Node(newData);
        Node<Integer> current=head;
        if(head==null || newData<=head.data)
        {
            newNode.next=head;
            return newNode;
        }
        while(current.next!=null && newData>current.next.data)
        {
            current=current.next;
        }
        newNode.next=current.next;
        current.next=newNode;
        return head;
    }
}
