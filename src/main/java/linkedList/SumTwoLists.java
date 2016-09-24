package linkedList;
import util.Node;
import util.ResultWrapperSum;

public class SumTwoLists
{
    public static void main(String[] args)
    {
        Integer[] i={1, 2 ,6, 9};
        Integer[] j={9, 3, 4};
        Node<Integer> nodeI=Node.createList(i);
        Node<Integer> nodeJ=Node.createList(j);
        //Test sum() and sum2()
        Node<Integer> result1=ForwardSum.sum(nodeI, nodeJ);
        Node<Integer> result2=ForwardSum.sum2(nodeI, nodeJ, 0);
        Node.printList(result1);
        Node.printList(result2);
        /*
         * output: 0 6 0 0 1
         *         0 6 0 0 1
         */
        //Test sum3()
        Node<Integer> result3=ReverseSum.sum3(nodeI, nodeJ);
        Node.printList(result3);
        /*
         * output: 2 2 0 3
         */

    }
}

class ForwardSum
{
    /*
     * Problem1: forward sum two lists, they start with the lowest digit
     */

    // Method1: iterative approach
    public static Node sum(Node<Integer> n1, Node<Integer> n2)
    {
        if(n1==null)
            return n2;
        if(n2==null)
            return n1;
        Node first=null;
        Node last=null;
        int carry=0, sum=0;
        while(n1!=null && n2!=null)
        {
            sum=(n1.data+n2.data+carry)%10;
            carry=(n1.data+n2.data+carry)/10;
            Node newNode=new Node(sum);
            if(first==null)
                first=newNode;
            else
                last.next=newNode;
            last=newNode;
            n1=n1.next;
            n2=n2.next;
        }
        Node<Integer> n=(n1!=null? n1 : n2);
        while(n!=null)
        {
            sum=(n.data+carry)%10;
            carry=(n.data+carry)/10;
            last.next=new Node(sum);
            last=last.next;
            n=n.next;
        }
        if(carry>0)
            last.next=new Node(1);
        return first;
    }

    //Method2: recursive approach
    public static Node sum2(Node<Integer> n1, Node<Integer> n2, int carry)
    {
        //important base case: we stop only when none of the
        //below situation exists. Otherwise, we threat our each
        //recursion step equally by judging possible null values
        if(n1==null && n2==null && carry==0)
            return null;
        Node<Integer> result=new Node<Integer>(carry);
        int value=carry;
        if(n1!=null)
            value+=n1.data;
        if(n2!=null)
            value+=n2.data;
        result.data=value%10;
        Node sumOfRest=sum2(n1==null? null : n1.next,
                n2==null? null: n2.next, value/10);
        result.next=sumOfRest;
        return result;
    }
}

class ReverseSum
{
    /*
     * Problem2:  reverse sum using recursive approach, both lists start with highest digit
     */
    public static Node sum3(Node<Integer> n1, Node<Integer> n2)
    {
        //we should pad the LinearDataStructure to make each node in n1 and n2 correspondent
        //when we recurse back, that is, they both reach the end at the same time
        int len=Node.listSize(n1)-Node.listSize(n2);
        if(len>0)
            n2=padList(n2, len);
        else
            n1=padList(n1, -len);
        ResultWrapperSum rw=sumR(n1, n2);
        Node result=rw.node;
        //now only partial sum is done, we still need to check whether carry is 1
        if(rw.carry==1)
            result=new Node<Integer>(1, rw.node);
        return result;
    }
    private static Node padList(Node n, int padding)
    {
        Node result=n;
        for(int i=0; i<padding; i++)
            result=new Node(0, result);
        return result;
    }
    //before we call this method, n1 and n2 could be in two cases: 1. they are
    //both null, 2. they are both not null. This is because we've padding them with 0
    private static ResultWrapperSum sumR(Node<Integer> n1, Node<Integer> n2)
    {
        if(n1==null && n2==null)
            return new ResultWrapperSum();
        ResultWrapperSum rw=sumR(n1.next, n2.next);
        int value=rw.carry;
        if(n1!=null)
            value+=n1.data;
        if(n2!=null)
            value+=n2.data;
        Node<Integer> result=new Node<Integer>(value%10, rw.node);
        rw.node=result;
        rw.carry=value/10;
        return rw;
    }
}
