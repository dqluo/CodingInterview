package linkedList;

import util.Node;

import java.util.Hashtable;
import java.util.Stack;

import static util.Node.listSize;

/**
 * User: xinyuwan, Date: 11/21/13, Time: 11:40 PM
 */
public class FindMergePoint
{
    /*
     * Problem: Two lists merges at certain node, return the merge node
     */

    public static void main(String[] args)
    {
        Integer[] a={1, 2, 3};
        Integer[] b={4, 5, 6, 7, 8};
        Integer[] c={9, 10};
        Node nodeA=Node.createList(a);
        Node nodeB=Node.createList(b);
        Node nodeC=Node.createList(c);
        nodeA.next=nodeC;
        nodeB.next=nodeC;
        System.out.println(find(nodeA, nodeB).data);
        System.out.println(find2(nodeA, nodeB).data);
        System.out.println(find3(nodeA, nodeB).data);
        System.out.println(find3(nodeB, nodeA).data);
    }
    //Method1: using Hashtable
    public static Node find(Node n1, Node n2)
    {
        Hashtable table=new Hashtable();
        while(n1!=null)
        {
            table.put(n1, true);
            n1=n1.next;
        }
        while(n2!=null)
        {
            if(table.containsKey(n2))
                return n2;
            n2=n2.next;
        }
        return null;
    }

    //Method2: using 2 stacks
    public static Node find2(Node n1, Node n2)
    {
        Stack<Node> s1=new Stack<Node>();
        Stack<Node> s2=new Stack<Node>();
        while(n1!=null)
        {
            s1.push(n1);
            n1=n1.next;
        }
        while(n2!=null)
        {
            s2.push(n2);
            n2=n2.next;
        }
        while(!s1.empty() && !s2.empty()
                && s1.peek()==s2.peek())
        {
            //n1 marks the last equal node popped by s1 and s2
            n1=s1.pop();
            s2.pop();
        }
        return n1;
    }

    //Method3: space efficient, using iterative approach
    public static Node find3(Node n1, Node n2)
    {
        Node longer=n1, shorter=n2;
        //get the length difference
        int diff=Node.listSize(n1)-Node.listSize(n2);
        if(diff<0)
        {
            longer=n2;
            shorter=n1;
            //remember to negate diff
            diff=-diff;
        }
        //now iterate the longer first to make them at the same start
        for(; diff>0; diff--)
            longer=longer.next;
        while(longer!=null)
        {
            if(longer==shorter)
                return longer;
            longer=longer.next;
            shorter=shorter.next;
        }
        return null;
    }
}

