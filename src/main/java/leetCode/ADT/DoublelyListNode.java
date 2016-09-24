package leetCode.ADT;

/**
 * Created by Danqin on 9/12/14.
 */
public class DoublelyListNode {
    public int data;
    public DoublelyListNode prev;
    public DoublelyListNode next;

    public DoublelyListNode(int data, DoublelyListNode prev, DoublelyListNode next){
        this.data=data;
        this.prev=prev;
        this.next=next;
    }

    public DoublelyListNode(int data){
        this.data=data;
        this.prev=null;
        this.next=null;
    }
}
