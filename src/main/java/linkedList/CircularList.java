package linkedList;

import util.Node;

import java.util.Hashtable;

/**
 * User: xinyuwan, Date: 11/21/13, Time: 10:09 PM
 */
public class CircularList
{
    public static void main(String[] args)
    {
        Node<String> h=new Node<String>("h");
        Node<String> g=new Node<String>("g", h);
        Node<String> f=new Node<String>("f", g);
        Node<String> e=new Node<String>("e", f);
        Node<String> d=new Node<String>("d", e);
        Node<String> c=new Node<String>("c", d);
        Node<String> b=new Node<String>("b", c);
        Node<String> a=new Node<String>("a", b);
        Node<String> z=new Node<String>("z", a);
        Node<String> y=new Node<String>("y", z);
        Node<String> x=new Node<String>("x", y);

        //Test isCircular()
        System.out.println(CircularListCheck.isCircular(x)+" "+CircularListCheck.isCircular2(x));
        //Test find()
        System.out.println(FindLoopStart.find(x));

        //Set LinearDataStructure to be circular
        h.next=d;

        //Test isCircular()
        System.out.println(CircularListCheck.isCircular(x)+" "+CircularListCheck.isCircular2(x));
        //Test find()
        System.out.println(FindLoopStart.find(x).data);
        //Test split()
        h.next=x;
        Node.printList(SplitCircularList.split(x));
        Node.printList(x);
        /*
         * output
         * d e f g h
         * x y z a b c
         */
    }


}

class CircularListCheck
{
    /*
     * Problem1: check whether a LinearDataStructure is circular or not
     */
    //Method1: using hashtable
    public static boolean isCircular(Node head)
    {
        Hashtable table=new Hashtable();
        while (head!=null)
        {
            if(table.containsKey(head))
                return true;
            table.put(head, true);
            head=head.next;
        }
        return false;
    }
    //Method2: fast/slow pointers
    public static boolean isCircular2(Node head)
    {
        Node slow=head;
        Node fast=head;
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

class FindLoopStart
{
    /*
     * Problem2:  find the start of circular node
     */
    public static Node find(Node head)
    {
        Node slow=head, fast=head;
        while(fast!=null && fast.next!=null)
        {
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow)
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
        return fast;
    }
}

class SplitCircularList
{
    /*
     * Problem3: split a circular LinearDataStructure to two;
     * if the LinearDataStructure size is odd, the first LinearDataStructure has one more element
     */
    public static Node split(Node head)
    {
        if(head==null)
            return null;
        Node slow=head;
        Node fast=head;
        while(fast.next!=head && fast.next.next!=head)
        {
            slow=slow.next;
            fast=fast.next.next;
        }
        //result is start of the second LinearDataStructure
        Node result=slow.next;
        slow.next=null;
        //the LinearDataStructure number is odd
        if(fast.next==head)
            fast.next=null;
        else
            fast.next.next=null;
        return result;
    }
}

