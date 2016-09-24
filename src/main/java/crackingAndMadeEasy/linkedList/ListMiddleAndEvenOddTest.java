package crackingAndMadeEasy.linkedList;

import util.Node;

public class ListMiddleAndEvenOddTest
{
    public static void main(String[] args)
    {
        Integer[] a={1, 2, 3, 4, 5};
        Integer[] b={1, 2, 3, 4};
        Integer[] c={1, 2};
        Integer[] d={1};
        Node nodeA=Node.createList(a);
        Node nodeB=Node.createList(b);
        Node nodeC=Node.createList(c);
        Node nodeD=Node.createList(d);

        //Test findMiddle()
        System.out.println(FindMiddleOfList.findMiddle(nodeA).data);
        System.out.println(FindMiddleOfList.findMiddle(nodeB).data);
        System.out.println(FindMiddleOfList.findMiddle(nodeC).data);
        System.out.println(FindMiddleOfList.findMiddle(nodeD).data);

        //Test checkEvenOrOdd
        System.out.println(CheckEvenOrOdd.check(nodeA)+" "+CheckEvenOrOdd.check(nodeB));
    }
}

class FindMiddleOfList
{
    /*
     * Problem1: find the middle element of LinearDataStructure
     * if LinearDataStructure number is even then return the first middle
     */
    public static Node findMiddle(Node head)
    {
        if(head==null)
            return null;
        Node slow=head;
        Node fast=head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
}

class CheckEvenOrOdd
{
    /*
     * Problem 2: check the number of LinearDataStructure is even(0) or odd(1)
     */
    public static int check(Node head)
    {
        Node fast=head;
        while(fast!=null && fast.next!=null)
        {
            fast=fast.next.next;
        }
        return fast==null? 0 : 1;
    }
}



