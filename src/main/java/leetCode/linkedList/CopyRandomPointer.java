package leetCode.linkedList;

import leetCode.ADT.RandomListNode;

import java.util.HashMap;

import static leetCode.ADT.RandomListNode.printList;

/**
 * User: xinyuwan, Date: 12/26/13, Time: 11:10 PM
 */
public class CopyRandomPointer
{
    public static void main(String[] args)
    {
        RandomListNode r1=new RandomListNode(1);
        RandomListNode r2=new RandomListNode(2);
        RandomListNode r3=new RandomListNode(3);
        r1.next=r2; r2.next=r3;
        r1.random=null; r2.random=r1; r3.random=r2;
        CopyRandomPointer crp=new CopyRandomPointer();
        System.out.print("Copy of list: ");
        printList(crp.copyRandomList2(r1));


    }
    /*
     * Problem: A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
     * Return a deep copy of the list.
     *
     * Analysis: Use Hashmap to store key: original node, value: new node. The first iteration copies value, and
     * stores the nodes in hashmap. In the second iteration, we got the deep copy of random and next by
     * mapping the original node's random and next based on hashmap
     */
    public RandomListNode copyRandomList(RandomListNode head)
    {
        if(head==null)
            return null;
        HashMap<RandomListNode, RandomListNode> map=new HashMap<RandomListNode, RandomListNode>();
        RandomListNode current=head;
        while(current!=null)
        {
            map.put(current, new RandomListNode(current.label));
            current=current.next;
        }
        current=head;
        while(current!=null)
        {
            RandomListNode newNode=map.get(current);
            newNode.next=map.get(current.next);
            newNode.random=map.get(current.random);
            current=current.next;
        }
        return map.get(head);
    }
    /*
     * Analysis: This method use O(1) space complexity with the same O(n) time complexity
     * 1. copy value of each node and insert it to the next node of each node A->A1->B-B1
     * 2. copy random pointer of each node for the new node in the list
     * 3. separate the list and restore the next pointer of the original list
     */
    public RandomListNode copyRandomList2(RandomListNode head)
    {
        if(head==null)
            return null;
        RandomListNode result=new RandomListNode(head.label);
        RandomListNode p=head, q=result;
        //start by copying value of each node and insert it
        while(p!=null)
        {
            RandomListNode temp=p.next;
            p.next=q;
            q.next=temp;
            if(temp!=null)
                q=new RandomListNode(temp.label);
            p=temp;
        }
        //now at least two nodes in the list, we begin to copy random
        p=head;
        while(p!=null)
        {
            //NP Exception will be thrown if we don't check p.random is null
            p.next.random=p.random==null? null : p.random.next;
            p=p.next.next;
        }
        //now we need to separate the result list and the original list
        p=head;
        while(p!=null)
        {
            q=p.next;
            p.next=q.next;
            q.next=q.next==null? null : q.next.next;
            //Note that now q.next is changed, we can only update p to be p.next
            p=p.next;
        }
        return result;
    }
}
